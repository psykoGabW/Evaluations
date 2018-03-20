package co.simplon.gwi.JDBCEval;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AppStoreDAO {

	private static Connection connect;

	private static CustomerDAO customerFactory;
	private static CreditSaleDAO creditSaleFactory;
	private static VideoGameDAO videogameFactory;
	private static PurchasedVideoGameDAO purchasedVideoGameFactory;

	private final static String PROPERTIES_KEY_CONNECTION_URL = "connectionURL";
	private final static String PROPERTIES_KEY_CONNECTION_USER = "connectionUser";
	private final static String PROPERTIES_KEY_CONNECTION_PWD = "connectionPwd";
	private final static String PROPERTIES_KEY_CONNECTION_SCHEMA = "connectionSchema";

	private static Properties loadProperties(String filename) throws IOException, FileNotFoundException {
		Properties properties = new Properties();
		FileInputStream input = new FileInputStream(filename);
		try {
			properties.load(input);
			return properties;

		} finally {
			input.close();
		}
	}

	public static Connection getConnection() {
		if (connect == null) {
			try {
				Properties conProperties = loadProperties(AppStoreDAO.class.getSimpleName() + ".properties");

				connect = DriverManager.getConnection(
						conProperties.getProperty(PROPERTIES_KEY_CONNECTION_URL),
						conProperties.getProperty(PROPERTIES_KEY_CONNECTION_USER),
						conProperties.getProperty(PROPERTIES_KEY_CONNECTION_PWD));

				connect.setSchema(conProperties.getProperty(PROPERTIES_KEY_CONNECTION_SCHEMA));

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException f) {
				f.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}

	public static CustomerDAO getCustomerDAO() {
		if (customerFactory == null) {
			try {
				customerFactory = new CustomerDAO(getConnection());
			} catch (InvalidParameterException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Customer DAO can't be initiate");
			}
		}
		return customerFactory;
	}

	public static CreditSaleDAO getCreditSaleDAO() {
		if (creditSaleFactory == null) {
			try {
				creditSaleFactory = new CreditSaleDAO(getConnection());
			} catch (InvalidParameterException | SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Credit Sale DAO can't be initiate");
				e.printStackTrace();
			}
		}
		return creditSaleFactory;
	}

	public static VideoGameDAO getVideoGameDAO() {
		if (videogameFactory == null) {
			try {
				videogameFactory = new VideoGameDAO(getConnection());
			} catch (InvalidParameterException | SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Videogame DAO can't be initiate");
				e.printStackTrace();
			}
		}
		return videogameFactory;
	}

	public static PurchasedVideoGameDAO getPurchasedVideoGameDAO() {
		if (purchasedVideoGameFactory == null) {
			try {
				purchasedVideoGameFactory = new PurchasedVideoGameDAO(getConnection());
			} catch (InvalidParameterException | SQLException e) {
				// TODO Auto-generated catch block
				System.err.println("Purchased Videogame DAO can't be initiate");
				e.printStackTrace();
			}
		}
		return purchasedVideoGameFactory;
	}

	/**
	 * Lazy method to rollbackInstructions
	 * 
	 * @return boolean to indicate if roll back is correctly done or not
	 */
	public static boolean rollbackInstructionsSeq() {
		try {
			getConnection().rollback();
			getConnection().setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			System.out.println("Critical ERROR - rollbackInstructions - Rollback failed ! ");
			return false;
		}
	}

	/**
	 * Lazy method to commit SQL instruction done until this call
	 * 
	 * @return boolean to indicate if commit is correctly done or not
	 */
	public static boolean commitInstructionsSeq() {
		try {
			getConnection().commit();
			getConnection().setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			rollbackInstructionsSeq();
			return false;
		}

	}

	/**
	 * Lazy method to set AutoCommit to false before launching any complex
	 * operations sequences.
	 * 
	 * @return boolean to indicate if setting auto commit has been correctly done.
	 */
	public static boolean beginInstructionsSeq() {
		try {
			getConnection().setAutoCommit(false);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static void close() {
		try {
			if (connect != null) {
				connect.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
