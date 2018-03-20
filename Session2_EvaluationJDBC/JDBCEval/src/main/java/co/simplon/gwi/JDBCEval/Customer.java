package co.simplon.gwi.JDBCEval;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

	public Customer() {
		
	}
	
	public void setId(int id) {
		this.id = new Integer(id);
	}	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setKnickname(String knickname) {
		this.knickname = knickname;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public void setCredits(double credits) {
		this.credits = credits;
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", knickname=" + knickname + ", birthdate=" + birthdate + ", credits=" + credits + "]";
	}
	
	
	public void copyFrom(Customer c) 
	{
		this.id = c.id;
		this.firstName = c.firstName;
		this.lastName = c.lastName;
		this.email = c.email;
		this.knickname = c.knickname;
		this.birthdate = c.birthdate;
		this.credits = c.credits;		
	}	
	
	public void addCredits(Double credits) {
		this.credits += credits;
	}
		
	public void substractCredits(Double credits) {
		this.credits -= credits;
	}
}
