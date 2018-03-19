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

	private static CustomerDAO getCustomerDAO() throws RuntimeException {
		if (customerFactory == null) {
			try {
				customerFactory = new CustomerDAO(getConnection());
			} catch (InvalidParameterException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Customer DAO can't be initiate");
			}
		}
		
		try {
			System.out.println("Connection Schema : " + customerFactory.dbCon.getSchema());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customerFactory;
		
	}
	
	public AppStoreDAO() {
		// TODO Auto-generated constructor stub
	}

	public Customer createCustomer(Customer c) {
		try {
			return getCustomerDAO().create(c);
		} catch( RuntimeException r) {
			r.printStackTrace();			
		}
		return null;
	}
	
	public void close() {
		try {
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
