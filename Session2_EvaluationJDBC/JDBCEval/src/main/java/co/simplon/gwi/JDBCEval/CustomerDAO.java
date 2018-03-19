package co.simplon.gwi.JDBCEval;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class CustomerDAO extends DAO<Customer> {

	private final static String COLUMN_NAME_ID = "pk_id";
	private final static String COLUMN_NAME_FIRST_NAME = "firstname";
	private final static String COLUMN_NAME_LAST_NAME = "lastname";
	private final static String COLUMN_NAME_EMAIL = "email";
	private final static String COLUMN_NAME_KNICKNAME = "knickname";
	private final static String COLUMN_NAME_BIRTHDATE = "birthdate";
	private final static String COLUMN_NAME_CREDITS = "credits";

	public CustomerDAO(Connection dbCon) throws SQLException, InvalidParameterException {
		super(dbCon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Customer create(Customer o) {

		String insertQuery = "INSERT INTO TB_customer (";
		insertQuery += COLUMN_NAME_FIRST_NAME + ",";
		insertQuery += COLUMN_NAME_LAST_NAME + ",";
		insertQuery += COLUMN_NAME_EMAIL + ",";
		insertQuery += COLUMN_NAME_KNICKNAME + ", ";
		insertQuery += COLUMN_NAME_BIRTHDATE + ", ";
		insertQuery += COLUMN_NAME_CREDITS + ") ";
		insertQuery += " VALUES (?, ?, ?, ?, ?, ?);";

		try (PreparedStatement pS = dbCon.prepareStatement(insertQuery,
				Statement.RETURN_GENERATED_KEYS)) {
			pS.setString(1, o.getFirstName());
			pS.setString(2, o.getLastName());
			pS.setString(3, o.getEmail());
			pS.setString(4, o.getKnickname());
			pS.setDate(5,  java.sql.Date.valueOf(o.getBirthdate()));
			pS.setDouble(6, o.getCredits());

			pS.executeUpdate();

			// Only one result is expected to get customer ID
			ResultSet rS = pS.getGeneratedKeys();
			rS.next();

			o.setId(rS.getInt("pk_id"));

		} catch (SQLException e) {
			System.err.println("Customer has not been saved in database : " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return o;
	}

	@Override
	public Customer read(int id) {
		// TODO Auto-generated method stub

		String selectQuery = String.format("SELECT * FROM TB_Customer WHERE " + COLUMN_NAME_ID + " = %d", id);
		Customer customer = null;

		try (Statement s = dbCon.createStatement()) {
			ResultSet rS = s.executeQuery(selectQuery);

			rS.next();
			customer = new Customer(rS.getInt(COLUMN_NAME_ID),
					rS.getString(COLUMN_NAME_FIRST_NAME),
					rS.getString(COLUMN_NAME_LAST_NAME),
					rS.getString(COLUMN_NAME_EMAIL),
					rS.getString(COLUMN_NAME_KNICKNAME),
					rS.getDate(COLUMN_NAME_BIRTHDATE).toLocalDate(),
					rS.getDouble(COLUMN_NAME_CREDITS));

		} catch (SQLException e) {
			System.out.println("An SQL error occured during reading of customer " + id + " : " + e.getMessage());
			e.printStackTrace();
		}

		return customer;
	}

	@Override
	public Customer update(Customer o) {
		// TODO Auto-generated method stub

		String updateQuery = String.format("UPDATE TB_customer SET ");
		updateQuery += COLUMN_NAME_FIRST_NAME + "= ? ,";
		updateQuery += COLUMN_NAME_LAST_NAME + "= ? ,";
		updateQuery += COLUMN_NAME_EMAIL + "= ? ,";
		updateQuery += COLUMN_NAME_KNICKNAME + "= ?,";
		updateQuery += COLUMN_NAME_BIRTHDATE + "= ?,";
		updateQuery += COLUMN_NAME_CREDITS + "= ?";
		updateQuery += " WHERE " + COLUMN_NAME_ID + "= ? ;";

		try (PreparedStatement pS = dbCon.prepareStatement(updateQuery)) {

			pS.setString(1, o.getFirstName());
			pS.setString(2, o.getLastName());
			pS.setString(3, o.getEmail());
			pS.setString(4, o.getKnickname());
			pS.setDate(5, java.sql.Date.valueOf(o.getBirthdate()));
			pS.setDouble(6, o.getCredits());
			pS.setInt(7, o.getId());
			pS.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Update of tb_customer " + o.getId() + " failed : " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return o;
	}

	@Override
	public void delete(Customer o) {
		// TODO Auto-generated method stub

		if (o.getId() == null) {
			return;
		}

		String deleteQuery = "DELETE FROM TB_customer WHERE " + COLUMN_NAME_ID + "=" + o.getId();

		try (Statement deleteStatement = dbCon.createStatement()) {
			deleteStatement.execute(deleteQuery);
		} catch (SQLException s) {
			System.out.println("Delete of customer " + o.getId() + " falied : " + s.getMessage());
			s.printStackTrace();
		}		
	}

}
