package co.simplon.gwi.JDBCEval;

import java.time.LocalDate;
import java.util.Date;

public class Customer {

	private Integer id = null ; 

	private String firstName;
	private String lastName;
	private String email;
	private String knickname;
	
	private LocalDate birthdate;
	
	private double credits;

	/**
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param knickName
	 * @param birthdate
	 * @param credits
	 */
	public Customer(Integer id, String firstName, String lastName, String email, String knickName, LocalDate birthdate, double credits) {
	
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.knickname = knickName;
		this.birthdate = birthdate;
		this.credits = credits;
		
	}

	public void setId(int id) {
		this.id = new Integer(id);
	}
	
	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getKnickname() {
		return knickname;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public double getCredits() {
		return credits;
	}
	
	
		
}
