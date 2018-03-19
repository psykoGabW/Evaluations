package co.simplon.gwi.JDBCEval;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 *
 */
public class App 
{
	final static DateTimeFormatter DATE_FR_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        AppStoreDAO appStore = new AppStoreDAO();
        
        Customer cTest = new Customer(null,"Tintin","Unknown", "tintin.hotmail@gr", "Titi2", LocalDate.parse("11/04/1977",DATE_FR_PATTERN), 0);
		appStore.createCustomer(cTest);
		
		if (cTest.getId() == null) {
			System.out.println("Creation KO");
		}
		else {
			System.out.println("Creation Ok");
		}
        
        System.out.println("Fermeture Connection");
        appStore.close();
        
        
    }
}
