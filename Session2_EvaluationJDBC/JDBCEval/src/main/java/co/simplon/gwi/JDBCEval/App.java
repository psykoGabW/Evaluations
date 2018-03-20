package co.simplon.gwi.JDBCEval;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {

	final static DateTimeFormatter DATE_FR_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	final static DateTimeFormatter DATE_TIME_FR_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	final static Scanner scanner = new Scanner(System.in);

	/**
	 * This private enum is designed to specify on which element we're working on.
	 * @author Utilisateur
	 *
	 */
	private static enum MenuType {

		Customers("Customer"), VideoGames("Video game"), PurchasedVideoGames("Purchased video game"), CreditSales(
				"Credit sale");

		MenuType(String name) {
			this.MenuLabel = name;
		}

		private final String MenuLabel;

		public String toString() {
			return MenuLabel;
		}
	}

	private static void deleteMenu(Scanner scanner, MenuType typeOfElt) {

		System.out.println("Please enter Key value of " + typeOfElt + " to delete (Enter to exit): ");
		Integer userInput = Utils.getInteger(scanner, true);

		if (userInput == null) {
			return;
		}

		int iKey = userInput;
		switch (typeOfElt) {
		case Customers:
			if (AppStoreDAO.getCustomerDAO() != null) {
				Customer c = new Customer();
				c.setId(iKey);
				AppStoreDAO.getCustomerDAO().delete(c);
			}
			break;
		case VideoGames:
			if (AppStoreDAO.getVideoGameDAO() != null) {
				VideoGame v = new VideoGame();
				v.setId(iKey);
				AppStoreDAO.getVideoGameDAO().delete(v);
			}
			break;
		case PurchasedVideoGames:
			if (AppStoreDAO.getPurchasedVideoGameDAO() != null) {
				PurchasedVideoGame pV = new PurchasedVideoGame();
				pV.setId(iKey);
				AppStoreDAO.getPurchasedVideoGameDAO().delete(pV);
				// TODO : Update Customer.credits when a purchase is deleted.
			}
			break;
		case CreditSales:
			if (AppStoreDAO.getCreditSaleDAO() != null) {
				CreditSale cS = new CreditSale();
				cS.setId(iKey);
				AppStoreDAO.getCreditSaleDAO().delete(cS);
				// TODO : Update Customer.credits when purchase of credits is deleted.
			}
			break;
		default:
			break;
		}

	}

	/**
	 * List all element to display regarding type of Element
	 * 
	 * @param typeOfElt
	 *            Type of element to display
	 */
	private static void listAll(MenuType typeOfElt) {

		Object[] listToDisplay = null;
		switch (typeOfElt) {
		case Customers:
			if (AppStoreDAO.getCustomerDAO() != null) {
				listToDisplay = AppStoreDAO.getCustomerDAO().readAll();
			}
			break;
		case VideoGames:
			if (AppStoreDAO.getVideoGameDAO() != null) {
				listToDisplay = AppStoreDAO.getVideoGameDAO().readAll();
			}
			break;
		case PurchasedVideoGames:
			if (AppStoreDAO.getPurchasedVideoGameDAO() != null) {
				listToDisplay = AppStoreDAO.getPurchasedVideoGameDAO().readAll();
			}
			break;
		case CreditSales:
			if (AppStoreDAO.getCreditSaleDAO() != null) {
				listToDisplay = AppStoreDAO.getCreditSaleDAO().readAll();
			}
			break;
		default:
			break;
		}

		if (listToDisplay == null) {
			return;
		}

		for (int i = 0; i < listToDisplay.length; i++) {
			if (listToDisplay[i] != null) {
				System.out.println((listToDisplay[i]).toString());
			}
		}
	}

	private static CreditSale getCrediSaleData(Scanner scanner, CreditSale cSInit) {
		CreditSale cS = new CreditSale();

		boolean updateMode = (cSInit != null);

		if (updateMode) {
			cS.copyFrom(cSInit);
		}

		System.out.println("Please input Credit sale data ");

		System.out.print("Number of credit sold "
				+ (updateMode ? String.format("<Actual value : %.2f >", cSInit.getCreditsNumber()) : "") + " : ");
		Double credit = Utils.getDouble(scanner, updateMode);
		if (credit != null) {
			cS.setCreditsNumber(credit);
		}

		System.out.print("Id of Customer who buy those credits "
				+ (updateMode ? String.format("<Actual value : %d >", cSInit.getCustomerId()) : "") + " : ");
		Integer customerId = Utils.getInteger(scanner, updateMode);
		if (customerId != null) {
			cS.setCustomerId(customerId);
		}

		System.out.print("Date time of the transaction "
				+ (updateMode ? "<Actual value : " + cS.getSaleDateTime().format(DATE_TIME_FR_PATTERN) + " >" : "")
				+ " :");

		LocalDateTime lDT = Utils.getLocalDateTime(scanner, DATE_TIME_FR_PATTERN, updateMode);
		if (lDT != null) {
			cS.setSaleDateTime(lDT);
		}

		return cS;

	}

	/**
	 * 
	 * @param scanner
	 * @param cInit
	 * @return
	 */
	private static Customer getCustomerData(Scanner scanner, Customer cInit) {
		Customer c = new Customer();

		boolean updateMode = false;

		if (cInit != null) {
			c.copyFrom(cInit);
			updateMode = true;
		}

		System.out.println("Please input Customer data ");

		System.out.print("Firstname " + (updateMode ? "<Actual value : " + cInit.getFirstName() + " >" : "") + " :");
		String userInput = scanner.nextLine().trim();
		if (!userInput.isEmpty()) {
			c.setFirstName(userInput);
		}

		System.out.print("LastName " + (updateMode ? "<Actual value : " + cInit.getLastName() + " >" : "") + " :");
		userInput = scanner.nextLine().trim();
		if (!userInput.isEmpty()) {
			c.setLastName(userInput);
		}

		System.out.print("Birthdate "
				+ (updateMode ? "<Actual value : " + cInit.getBirthdate().format(DATE_FR_PATTERN) + " >" : "") + " :");
		LocalDate localDate = Utils.getLocalDate(scanner, DATE_FR_PATTERN, true);
		if (localDate != null) {
			c.setBirthdate(localDate);
		}

		System.out.print("Knickname " + (updateMode ? "<Actual value : " + cInit.getKnickname() + " >" : "") + " :");
		userInput = scanner.nextLine().trim();
		if (!userInput.isEmpty()) {
			c.setKnickname(userInput);
		}
		System.out.print("Email " + (updateMode ? "<Actual value : " + cInit.getEmail() + " >" : "") + " :");
		userInput = scanner.nextLine().trim();
		if (!userInput.isEmpty()) {
			c.setEmail(userInput);
		}
		System.out.print(
				"Credits " + (updateMode ? String.format("<Actual value : %.2f > " + cInit.getCredits()) : "") + " :");
		Double doubleInput = Utils.getDouble(scanner, true);
		if (doubleInput != null) {
			c.setCredits(doubleInput);
		}

		return c;
	}

	private static VideoGame getVideoGameData(Scanner scanner, VideoGame vgInit) {
		VideoGame vgResult = new VideoGame();

		boolean updateMode = (vgInit != null);

		if (updateMode) {
			vgResult.copyFrom(vgInit);
		}

		System.out.println("Please input Video Game data ");
		System.out.print("Name " + (updateMode ? "<Actual value : " + vgResult.getName() + " >" : "") + " :");
		String userInput = scanner.nextLine().trim();
		if (!userInput.isEmpty()) {
			vgResult.setName(userInput);
		}

		System.out.print(
				"Price " + (updateMode ? String.format("<Actual value : %.2f> ", vgResult.getPrice()) : "") + " :");
		Double price = Utils.getDouble(scanner, updateMode);
		if (price != null) {
			vgResult.setPrice(price);
		}

		System.out.print("PEGI classification "
				+ (updateMode ? String.format("<Actual value : %d> ", vgResult.getPegiClassification()) : "") + " :");
		Integer pegi = Utils.getInteger(scanner, true);
		if (price != null || updateMode) {
			vgResult.setPegiClassification(pegi);
		}

		System.out.print("shortDescription : ");
		userInput = scanner.nextLine().trim();
		if (!userInput.isEmpty()) {
			vgResult.setShortDescription(userInput);
		}

		System.out.print("Full Description : ");
		userInput = scanner.nextLine().trim();
		if (!userInput.isEmpty()) {
			vgResult.setFullDescription(userInput);
		}

		System.out.print("Video Game web site "
				+ (updateMode ? "<Actual value : " + vgResult.getWebSiteURL() + " >" : "") + " :");
		userInput = scanner.nextLine().trim();
		if (!userInput.isEmpty()) {
			vgResult.setWebSiteURL(userInput);
		}

		return vgResult;
	}

	/**
	 * This method is designed to get PurchasedVideoGame attributes for creation /
	 * updating.
	 * 
	 * @param scanner
	 *            Active scanner to use for input reading.
	 * @param pvgInit
	 *            If not null, this PurchasedVideoGame will be used to set default
	 *            values for the user.
	 * @return Instance of PurchasedVideoGame with user input
	 */

	private static PurchasedVideoGame getPurchasedVideoGameData(Scanner scanner, PurchasedVideoGame pvgInit) {
		PurchasedVideoGame pvg = new PurchasedVideoGame();

		boolean updateMode = (pvgInit != null);

		if (updateMode) {
			pvg.copyFrom(pvgInit);
		}

		System.out.println("Please input data regarding video game purchase ");

		System.out.print("Id of Video Game "
				+ (updateMode ? String.format("<Actual value : %d >", pvgInit.getVideoGameID()) : "") + " : ");
		Integer videogameId = Utils.getInteger(scanner, updateMode);
		if (videogameId != null) {
			pvg.setVideoGameID(videogameId);
		}

		System.out.print("Id of Customer who has bought these video game "
				+ (updateMode ? String.format("<Actual value : %d >", pvgInit.getCustomerID()) : "") + " : ");
		Integer customerId = Utils.getInteger(scanner, updateMode);
		if (customerId != null) {
			pvg.setCustomerID(customerId);
		}

		System.out.print("Price of this video game : "
				+ (updateMode ? String.format("<Actual value : %.2f >", pvgInit.getPurchasePrice()) : "") + " : ");
		Double price = Utils.getDouble(scanner, updateMode);
		if (price != null) {
			pvg.setPurchasePrice(price);
		}

		System.out.print("Date time of the transaction "
				+ (updateMode ? "<Actual value : " + pvg.getPurchaseTime().format(DATE_TIME_FR_PATTERN) + " >" : "")
				+ " :");

		LocalDateTime lDT = Utils.getLocalDateTime(scanner, DATE_TIME_FR_PATTERN, updateMode);
		if (lDT != null) {
			pvg.setPurchaseTime(lDT);
		}

		return pvg;

	}

	/**
	 * Menu CRUD -> Action add
	 * 
	 * @param scanner
	 *            active scanner to use for menu display
	 * @param menu
	 *            Type of element to display
	 */
	private static void addMenu(Scanner scanner, MenuType menu) {
		switch (menu) {
		case Customers:
			Customer c = getCustomerData(scanner, null);
			if (AppStoreDAO.getCustomerDAO() != null) {
				AppStoreDAO.getCustomerDAO().create(c);
				if (c.getId() != null) {
					System.out.println("Customer has been added with ID : " + c.getId());
				}
			}
			break;
		case CreditSales:
			CreditSale cS = getCrediSaleData(scanner, null);
			if (AppStoreDAO.getCreditSaleDAO() != null) {
				AppStoreDAO.beginInstructionsSeq();
				boolean sequenceOk = true;
				AppStoreDAO.getCreditSaleDAO().create(cS);

				if (cS.getId() != null) {
					System.out.println("Transaction recorded with ID : " + cS.getId());
					sequenceOk = (AppStoreDAO.getCustomerDAO() != null);
					if (sequenceOk) {
						Customer transactionCustomer = AppStoreDAO.getCustomerDAO().read(cS.getCustomerId());
						transactionCustomer.addCredits(cS.getCreditsNumber());
						sequenceOk = (AppStoreDAO.getCustomerDAO().update(transactionCustomer) != null);
					}
				} else {
					sequenceOk = false;
				}

				if (sequenceOk) {
					AppStoreDAO.commitInstructionsSeq();
				} else {
					AppStoreDAO.rollbackInstructionsSeq();
				}
			}
			break;
		case VideoGames:
			VideoGame vg = getVideoGameData(scanner, null);
			if (AppStoreDAO.getVideoGameDAO() != null) {
				AppStoreDAO.getVideoGameDAO().create(vg);
				if (vg.getId() != null) {
					System.out.println("Video game recorded with ID : " + vg.getId());
				}
			}

			break;
		case PurchasedVideoGames:
			PurchasedVideoGame pvg = getPurchasedVideoGameData(scanner, null);
			if (AppStoreDAO.getPurchasedVideoGameDAO() != null) {
				boolean sequenceOk = true;
				AppStoreDAO.beginInstructionsSeq();

				AppStoreDAO.getPurchasedVideoGameDAO().create(pvg);
				sequenceOk = (pvg.getId() != null);
				sequenceOk = (sequenceOk && (AppStoreDAO.getCustomerDAO() != null));

				if (sequenceOk) {
					Customer customer = AppStoreDAO.getCustomerDAO().read(pvg.getCustomerID());
					if (customer == null) {
						sequenceOk = false;
					} else {
						customer.substractCredits(pvg.getPurchasePrice());
						sequenceOk = (AppStoreDAO.getCustomerDAO().update(customer) != null);
					}
				}

				if (sequenceOk) {
					System.out.println("Transaction added with ID : " + pvg.getId());
					AppStoreDAO.commitInstructionsSeq();
				} else {
					AppStoreDAO.rollbackInstructionsSeq();
				}

			}

			break;
		default:

		}
	}

	/**
	 * Display CRUD Sub-menu regarding MenuType given in parameter
	 * 
	 * @param scanner
	 *            Scanner to use for display
	 * @param menu
	 *            menuType to display
	 */
	private static void crudMenu(Scanner scanner, MenuType menu) {
		int userChoice = -1;
		do {
			System.out.println();
			System.out.println("********* Virtual APPStore Application ******** ");
			System.out.println("             " + menu + " operations");
			System.out.println();
			System.out.println("1 - List all " + menu + "s");
			System.out.println("2 - Add " + menu);
			System.out.println("3 - Delete a " + menu);
			System.out.println("");
			System.out.println("0 - Previous menu");
			userChoice = Utils.getUserChoice(scanner, 0, 3, false);

			switch (userChoice) {
			case 1:
				listAll(menu);
				break;
			case 2:
				addMenu(scanner, menu);
				break;
			case 3:
				deleteMenu(scanner, menu);
				break;
			}

		} while (userChoice != 0);

	}

	/**
	 * This method display main menu of the application
	 * 
	 * @param scanner
	 *            Scanner to use to display menu
	 */
	private static void mainMenu(Scanner scanner) {

		int userChoice = -1;
		do {
			System.out.println();
			System.out.println("********* Virtual APPStore Application ******** ");
			System.out.println("");
			System.out.println("1 - Customer management");
			System.out.println("2 - Videogames management");
			System.out.println("3 - Purchase video game management");
			System.out.println("4 - Credit Sales management");
			System.out.println("");
			System.out.println("0 - Exit program");
			userChoice = Utils.getUserChoice(scanner, 0, 4, false);

			switch (userChoice) {
			case 1:
				crudMenu(scanner, MenuType.Customers);
				break;
			case 2:
				crudMenu(scanner, MenuType.VideoGames);
				break;
			case 3:
				crudMenu(scanner, MenuType.PurchasedVideoGames);
				break;
			case 4:
				crudMenu(scanner, MenuType.CreditSales);
				break;
			}

		} while (userChoice != 0);
	}

	public static void main(String[] args) {

		System.out.println("Initialization....");

		System.out.println("Application launching...");

		mainMenu(scanner);

		if (AppStoreDAO.getConnection() != null) {
			AppStoreDAO.close();
		}
		scanner.close();
		System.out.println("Connections closed");

	}
}
