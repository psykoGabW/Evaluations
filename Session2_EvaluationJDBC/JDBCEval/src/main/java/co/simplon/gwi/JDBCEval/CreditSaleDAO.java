package co.simplon.gwi.JDBCEval;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreditSaleDAO extends DAO<CreditSale> {

	private final static String COLUMN_NAME_ID = "pk_id";
	private final static String COLUMN_NAME_CREDITS_SOLD = "number";
	private final static String COLUMN_NAME_CUSTOMER_ID = "fk_customer_id";
	private final static String COLUMN_NAME_SALE_DATE_TIME = "sale_time";

	private final static String TABLE_NAME = "TB_credit_sale";

	public CreditSaleDAO(Connection dbCon) throws InvalidParameterException, SQLException {
		super(dbCon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CreditSale create(CreditSale o) {
		// TODO Auto-generated method stub

		String insertQuery = String.format("INSERT INTO %s ( %s, %s, %s) VALUES (?, ?, ?);",
				TABLE_NAME,
				COLUMN_NAME_CREDITS_SOLD,
				COLUMN_NAME_CUSTOMER_ID,
				COLUMN_NAME_SALE_DATE_TIME);

		try (PreparedStatement pS = dbCon.prepareStatement(insertQuery,
				Statement.RETURN_GENERATED_KEYS)) {
			pS.setDouble(1, o.getCreditsNumber());
			pS.setInt(2, o.getCustomerId());
			pS.setTimestamp(3, java.sql.Timestamp.valueOf(o.getSaleDateTime()));

			pS.executeUpdate();

			// Only one result is expected to get customer ID
			ResultSet rS = pS.getGeneratedKeys();
			rS.next();

			o.setId(rS.getInt(COLUMN_NAME_ID));

		} catch (SQLException e) {
			System.err.println("Credit sale has not been saved in database : " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return o;
	}

	@Override
	public CreditSale read(int id) {

		CreditSale result = null;

		try (Statement readQuery = dbCon.createStatement()) {
			String selectQuery = String.format("SELECT * FROM %s WHERE %s = %d;",
					TABLE_NAME,
					COLUMN_NAME_ID,
					id);
			ResultSet rS = readQuery.executeQuery(selectQuery);
			rS.next();

			result = new CreditSale(rS.getInt(COLUMN_NAME_ID),
					rS.getDouble(COLUMN_NAME_CREDITS_SOLD),
					rS.getInt(COLUMN_NAME_CUSTOMER_ID),
					rS.getTimestamp(COLUMN_NAME_SALE_DATE_TIME).toLocalDateTime());
			rS.close();

		} catch (SQLException e) {
			System.err.println("An SQL error occured during reading of Credit Sale  " + id + " : " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public CreditSale[] readAll() {

		CreditSale[] result = null; 
		
		try(Statement readQuery = dbCon.createStatement()){
			String selectQuery = String.format("SELECT * FROM %s ;",
								TABLE_NAME);
			ResultSet rS = readQuery.executeQuery(selectQuery);
			ArrayList<CreditSale> resultTmp = new ArrayList<CreditSale>();
			while (rS.next()) {
				resultTmp.add(	resultTmp.size(),
								new CreditSale(rS.getInt(COLUMN_NAME_ID),
												rS.getDouble(COLUMN_NAME_CREDITS_SOLD),
												rS.getInt(COLUMN_NAME_CUSTOMER_ID),
												rS.getTimestamp(COLUMN_NAME_SALE_DATE_TIME).toLocalDateTime()
												)
								);
			}
			rS.close();
			
			result = new CreditSale[resultTmp.size()];
			resultTmp.toArray(result);
			
		} catch (SQLException e) {
			System.err.println("An SQL error occured during retrieving all data from Credit Sale TABLE : " + e.getMessage());
			e.printStackTrace();
		}
		
		return result;		
	}

	@Override
	public CreditSale update(CreditSale o) {

		if (o.getId() == null) {
			return null;
		}

		String updateQuery = String.format("UPDATE %s SET %s = ?, %s = ? , %s = ?) WHERE %s = ?;",
				TABLE_NAME,
				COLUMN_NAME_CREDITS_SOLD,
				COLUMN_NAME_CUSTOMER_ID,
				COLUMN_NAME_SALE_DATE_TIME,
				COLUMN_NAME_ID);

		try (PreparedStatement pS = dbCon.prepareStatement(updateQuery)) {
			pS.setDouble(1, o.getCreditsNumber());
			pS.setInt(2, o.getCustomerId());
			pS.setTimestamp(3, java.sql.Timestamp.valueOf(o.getSaleDateTime()));
			pS.setInt(4, o.getId());

			pS.executeUpdate();

			pS.close();

		} catch (SQLException e) {
			System.err.println("Credit sale has not been saved in database : " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return o;
	}

	@Override
	public void delete(CreditSale o) {
		if (o.getId() == null) {
			return;
		}

		String deleteQuery = String.format("DELETE FROM %s WHERE %s = %d;",
				TABLE_NAME,
				COLUMN_NAME_ID,
				o.getId());

		try (Statement deleteStatement = dbCon.createStatement()) {
			deleteStatement.execute(deleteQuery);
		} catch (SQLException s) {
			System.out.println("Delete of CreditSale " + o.getId() + " falied : " + s.getMessage());
			s.printStackTrace();
		}
	}

}
