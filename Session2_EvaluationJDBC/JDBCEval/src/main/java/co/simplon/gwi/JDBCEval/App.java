package co.simplon.gwi.JDBCEval;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 *
 */

public class App {

	final static DateTimeFormatter DATE_FR_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	final static DateTimeFormatter DATE_TIME_FR_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	private static void testCustomerDAO(AppStoreDAO appStore) {
		Customer cTest = new Customer(null, "Tintin", "Unknown", "tintin.hotmail@gr", "Titi2",
				LocalDate.parse("11/04/1977", DATE_FR_PATTERN), 0);

		DAO<Customer> customerDAO = AppStoreDAO.getCustomerDAO();

		if (customerDAO == null) {
			System.err.println("Customer DAO Factory failed");
			return;
		}

		customerDAO.create(cTest);

		if (cTest.getId() == null) {
			System.out.println("Creation KO");
		} else {
			System.out.println("Creation Ok");
			Customer cRead = customerDAO.read(cTest.getId());

			if (cRead != null) {
				System.out.println(cRead.toString());

				cRead.setCredits(cRead.getCredits() + 10.);

				if (customerDAO.update(cRead) != null) {
					System.out.println("Update OK ! ");

					Customer cUpdated = customerDAO.read(cRead.getId());
					System.out.println(cUpdated);

				}

			} else {
				System.out.println("Reading failed");
			}

			customerDAO.delete(cTest);

		}

	}

	private static void testCreditSaleDAO(AppStoreDAO appStore) {
		CreditSale cTest = new CreditSale(null, 15.0, 1, LocalDateTime.parse("19/03/2018 21:00", DATE_TIME_FR_PATTERN));

		DAO<CreditSale> creditSaleDAO = AppStoreDAO.getCreditSaleDAO();

		if (creditSaleDAO == null) {
			return;
		}

		creditSaleDAO.create(cTest);

		if (cTest.getId() == null) {
			System.out.println("Credit Sale creation KO");
		} else {
			System.out.println("Credit Sale creation Ok");
			CreditSale cRead = creditSaleDAO.read(cTest.getId());

			if (cRead != null) {
				System.out.println(cRead.toString());

				cRead.setCreditsNumber(cRead.getCreditsNumber() + 10.);

				if (creditSaleDAO.update(cRead) != null) {
					System.out.println("Credit sale Update OK ! ");
					CreditSale cUpdated = creditSaleDAO.read(cRead.getId());
					System.out.println(cUpdated);
				}
			} else {
				System.out.println("Credit Sale Reading failed");
			}
			creditSaleDAO.delete(cTest);
		}
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");

		AppStoreDAO appStore = new AppStoreDAO();

		testCustomerDAO(appStore);

		testCreditSaleDAO(appStore);

		appStore.close();
		System.out.println("Connection closed");

	}
}
