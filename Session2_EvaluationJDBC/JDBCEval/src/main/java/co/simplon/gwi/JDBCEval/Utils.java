package co.simplon.gwi.JDBCEval;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Utils {

	/**
	 * 
	 * @param minAcceptedValue
	 *            (int >= 0)
	 * @param maxAcceptedValue
	 *            (int >= 0)
	 * @param isEmptyAccepted
	 *            (boolean) if true, user can input nothing and we return -1;
	 * @return -1 if isEmptyAccepted && user input "" int between minAcceptedValue
	 *         and maxAcceptedValue
	 */
	public static int getUserChoice(Scanner scanner, int minAcceptedValue, int maxAcceptedValue,
			boolean isEmptyAccepted) {
		int userChoice = -1;
		if (scanner == null) {
			return userChoice;
		}
		String literalChoice = "";
		// get User choice
		do {
			try {
				System.out.println("Your choice : ");
				literalChoice = scanner.nextLine();
				userChoice = Integer.parseInt(literalChoice);
			} catch (NumberFormatException n) {
				userChoice = -1;
			} finally {
				if ((!(userChoice >= minAcceptedValue && userChoice <= maxAcceptedValue))
						|| (literalChoice.isEmpty() && !isEmptyAccepted)) {
					System.out.println(
							"Please enter a number in [ " + minAcceptedValue + " ; " + maxAcceptedValue + " ]");
				}
			}
		} while (userChoice == -1 || (isEmptyAccepted && literalChoice.isEmpty()));

		if (literalChoice.isEmpty() && isEmptyAccepted) {
			userChoice = -1;
		}
		return userChoice;
	}

	public static Integer getInteger(Scanner scanner, boolean isEmptyAccepted) {
		Integer intUserInput = null;
		if (scanner == null) {
			return intUserInput;
		}
		String userInput = "";
		// get User choice
		do {
			try {
				userInput = scanner.nextLine().trim();
				if (!userInput.isEmpty()) {
					intUserInput = Integer.parseInt(userInput);
				}
			} catch (NumberFormatException n) {
				intUserInput = null;
			} finally {
				if (intUserInput == null && !userInput.isEmpty() && !isEmptyAccepted) {
					System.out.println(
							"Please enter an Integer value !");
					System.out.print(">");
					
				}
			}
		} while (intUserInput == null || (isEmptyAccepted && userInput.isEmpty()));

		return intUserInput;
	}

	public static Double getDouble(Scanner scanner, boolean isEmptyAccepted) {
		Double doubleInput = null;
		if (scanner == null) {
			return doubleInput;
		}
		String userInput = "";
		// get User choice
		do {
			try {
				userInput = scanner.nextLine().trim();
				if (!userInput.isEmpty()) {
					doubleInput = Double.parseDouble(userInput);
				}
			} catch (NumberFormatException n) {
				doubleInput = null;
			} finally {
				if (doubleInput == null && !userInput.isEmpty() && !isEmptyAccepted) {
					System.out.println(
							"Please enter a Double value !");
					System.out.print(">");
					
				}
			}
		} while (doubleInput == null || (isEmptyAccepted && userInput.isEmpty()));

		return doubleInput;
	}
	
	public static LocalDate getLocalDate(Scanner scanner,DateTimeFormatter dTF, boolean isEmptyAccepted) {
		LocalDate localDate = null;
		if (scanner == null) {
			return localDate;
		}
		String userInput = "";
		// get User choice
		do {
			try {
				userInput = scanner.nextLine().trim();
				if (!userInput.isEmpty()) {
					localDate = LocalDate.parse(userInput, dTF);
				}
			} catch (DateTimeParseException n) {
				localDate = null;
			} finally {
				if (localDate == null && !userInput.isEmpty() && !isEmptyAccepted) {
					System.out.println(
							"Please enter Date in format : " + dTF.toString()+ " ! ");
					System.out.print(">");
					
				}
			}
		} while (localDate == null || (isEmptyAccepted && userInput.isEmpty()));

		return localDate;
	}

	public static LocalDateTime getLocalDateTime(Scanner scanner,DateTimeFormatter dTF, boolean isEmptyAccepted) {
		LocalDateTime localDateTime = null;
		if (scanner == null) {
			return localDateTime;
		}
		String userInput = "";
		// get User choice
		do {
			try {
				userInput = scanner.nextLine().trim();
				if (!userInput.isEmpty()) {
					localDateTime = LocalDateTime.parse(userInput, dTF);
				}
			} catch (DateTimeParseException n) {
				localDateTime = null;
			} finally {
				if (localDateTime == null && !userInput.isEmpty() && !isEmptyAccepted) {
					System.out.println(
							"Please enter Date Time in format : " + dTF.toString()+ " ! ");
					System.out.print(">");
					
				}
			}
		} while (localDateTime == null || (isEmptyAccepted && userInput.isEmpty()));

		return localDateTime;
	}
}
