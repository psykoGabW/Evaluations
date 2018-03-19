package co.simplon.gwi.JDBCEval;

import java.time.LocalDateTime;

public class CreditSale {

	private Integer id;

	private double creditsNumber;
	private int customerId;
	private LocalDateTime saleDateTime;

	/*
	 * pk_id SERIAL PRIMARY KEY, number DECIMAL(5,2) NOT NULL, fk_customer_id
	 * INTEGER NOT NULL, sale_time TIMESTAMP WITH TIME ZONE NOT NULL, CONSTRAINT
	 * CONSTRAINT_CREDIT_SALE_FK_CUSTOMER FOREIGN KEY (fk_customer_id) REFERENCES
	 * APPStore.TB_customer(pk_id)
	 */

	public Integer getId() {
		return id;
	}



	public void setId(int id) {
		this.id = new Integer(id);
	}



	


	public double getCreditsNumber() {
		return creditsNumber;
	}



	public void setCreditsNumber(double creditsNumber) {
		this.creditsNumber = creditsNumber;
	}



	public int getCustomerId() {
		return customerId;
	}



	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}



	public LocalDateTime getSaleDateTime() {
		return saleDateTime;
	}



	public void setSaleDateTime(LocalDateTime saleDateTime) {
		this.saleDateTime = saleDateTime;
	}

	/**
	 * 
	 * @param id
	 * @param creditsNumber
	 * @param customerId
	 * @param saleDateTime
	 */
	public CreditSale(Integer id, double creditsNumber, int customerId, LocalDateTime saleDateTime) {
		// TODO Auto-generated constructor stub
		this.creditsNumber = creditsNumber;
		this.customerId = customerId;
		this.saleDateTime = saleDateTime;		
	}


	@Override
	public String toString() {
		return "CreditSale [id=" + id + ", creditsNumber=" + creditsNumber + ", customerId=" + customerId
				+ ", saleDateTime=" + saleDateTime + "]";
	}

}
