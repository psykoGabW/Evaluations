package co.simplon.gwi.JDBCEval;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PurchasedVideoGameDAO extends DAO<PurchasedVideoGame> {

	/*
	CREATE TABLE APPStore.TB_purchased_videogame(
			  fk_customer_id  INTEGER NOT NULL REFERENCES APPStore.TB_customer(pk_id),
			  fk_videogame_id INTEGER NOT NULL REFERENCES APPStore.TB_videogame(pk_id),
			  purchase_time   TIMESTAMP WITH TIME ZONE NOT NULL,
			  credits_price    DECIMAL(5,2) NOT NULL CHECK (credits_price >=0), -- credits_price is store because price of TB_VIDEOGAME may change in time.
			  CONSTRAINT CONSTRAINT_PURCHASED_GAME_PK PRIMARY KEY(FK_Customer_id, FK_Videogame_id)
			);
	*/
	
	private final static String TABLE_NAME = "TB_purchased_videogame";
	
	private final static String COLUMN_NAME_ID = "pk_id";
	private final static String COLUMN_NAME_VIDEOGAME_ID = "fk_videogame_id";
	private final static String COLUMN_NAME_CUSTOMER_ID = "fk_customer_id";
	private final static String COLUMN_NAME_PURCHASE_DATE_TIME = "purchase_time";
	private final static String COLUMN_NAME_PRICE = "credits_price";

	public PurchasedVideoGameDAO(Connection dbCon) throws InvalidParameterException, SQLException {
		super(dbCon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PurchasedVideoGame create(PurchasedVideoGame o) {
		// TODO Auto-generated method stub

		String insertQuery = String.format("INSERT INTO %s ( %s, %s, %s, %s) VALUES (?, ?, ?, ?);",
				TABLE_NAME,
				COLUMN_NAME_VIDEOGAME_ID,
				COLUMN_NAME_CUSTOMER_ID,
				COLUMN_NAME_PURCHASE_DATE_TIME,
				COLUMN_NAME_PRICE);

		try (PreparedStatement pS = dbCon.prepareStatement(insertQuery)) {
			pS.setInt(1, o.getVideoGameID());
			pS.setInt(2, o.getCustomerID());
			pS.setTimestamp(3, java.sql.Timestamp.valueOf(o.getPurchaseTime()));
			pS.setDouble(4,  o.getPurchasePrice());

			pS.executeUpdate();

		} catch (SQLException e) {
			System.err.println("Purchase Videogame has not been saved in database : " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return o;
	}

	@Override
	public PurchasedVideoGame read(int id) {

		PurchasedVideoGame result = null;

		try (Statement readQuery = dbCon.createStatement()) {
			String selectQuery = String.format("SELECT * FROM %s WHERE %s = %d;",
					TABLE_NAME,
					COLUMN_NAME_ID,
					id);
			ResultSet rS = readQuery.executeQuery(selectQuery);
			rS.next();

			result = new PurchasedVideoGame(
					rS.getInt(COLUMN_NAME_CUSTOMER_ID),
					rS.getInt(COLUMN_NAME_VIDEOGAME_ID),
					rS.getTimestamp(COLUMN_NAME_PURCHASE_DATE_TIME).toLocalDateTime(),
					rS.getDouble(COLUMN_NAME_PRICE)
					);
			rS.close();

		} catch (SQLException e) {
			System.err.println("An SQL error occured during reading of Purchase Videogame " + id + " : " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public PurchasedVideoGame[] readAll() {

		PurchasedVideoGame[] result = null; 
		
		try(Statement readQuery = dbCon.createStatement()){
			String selectQuery = String.format("SELECT * FROM %s ;",
								TABLE_NAME);
			ResultSet rS = readQuery.executeQuery(selectQuery);
			ArrayList<PurchasedVideoGame> resultTmp = new ArrayList<PurchasedVideoGame>();
			while (rS.next()) {
				resultTmp.add(	resultTmp.size(),
								new PurchasedVideoGame(
										rS.getInt(COLUMN_NAME_CUSTOMER_ID),
										rS.getInt(COLUMN_NAME_VIDEOGAME_ID),
										rS.getTimestamp(COLUMN_NAME_PURCHASE_DATE_TIME).toLocalDateTime(),
										rS.getDouble(COLUMN_NAME_PRICE)
										)
								);
			}
			rS.close();
			
			result = (PurchasedVideoGame[]) resultTmp.toArray();
			
		} catch (SQLException e) {
			System.err.println("An SQL error occured during retrieving all data from Purchased VideoGame TABLE : " + e.getMessage());
			e.printStackTrace();
		}
		
		return result;		
	}

	@Override
	public PurchasedVideoGame update(PurchasedVideoGame o) {

		if (o.getCustomerID() == null || o.getVideoGameID() == null) {
			return null;
		}

		String updateQuery = String.format("UPDATE %s SET %s = ?, %s = ? ) WHERE %s = ? and %s = ?",
				TABLE_NAME,
				
				COLUMN_NAME_PURCHASE_DATE_TIME,
				COLUMN_NAME_PRICE,
				
				COLUMN_NAME_CUSTOMER_ID,
				COLUMN_NAME_VIDEOGAME_ID);

		try (PreparedStatement pS = dbCon.prepareStatement(updateQuery)) {
			pS.setTimestamp(1, java.sql.Timestamp.valueOf(o.getPurchaseTime()));
			pS.setDouble(2, o.getPurchasePrice());
			pS.setInt(3, o.getCustomerID());
			pS.setInt(4, o.getVideoGameID());

			pS.executeUpdate();

			pS.close();

		} catch (SQLException e) {
			System.err.println("Purchased videogame has not been updated in database : " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return o;
	}

	@Override
	public void delete(PurchasedVideoGame o) {
		if (o.getCustomerID() == null || o.getVideoGameID() == null) {
			return;
		}

		String deleteQuery = String.format("DELETE FROM %s WHERE %s = %d AND %s = %d ;",
				TABLE_NAME,
				COLUMN_NAME_CUSTOMER_ID,
				o.getCustomerID(),
				COLUMN_NAME_VIDEOGAME_ID,
				o.getVideoGameID());

		try (Statement deleteStatement = dbCon.createStatement()) {
			deleteStatement.execute(deleteQuery);
		} catch (SQLException s) {
			System.out.println("Delete of Purchased VideoGame [" + o.getCustomerID() + ", " + o.getVideoGameID() +"] failed : " + s.getMessage());
			s.printStackTrace();
		}
	}

}
