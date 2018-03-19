package co.simplon.gwi.JDBCEval;

import java.time.LocalDateTime;

public class PurchasedVideoGame {

	/*
	 CREATE TABLE APPStore.TB_purchased_videogame(
  fk_customer_id  INTEGER NOT NULL REFERENCES APPStore.TB_customer(pk_id),
  fk_videogame_id INTEGER NOT NULL REFERENCES APPStore.TB_videogame(pk_id),
  purchase_time   TIMESTAMP WITH TIME ZONE NOT NULL,
  credits_price    DECIMAL(5,2) NOT NULL CHECK (credits_price >=0), -- credits_price is store because price of TB_VIDEOGAME may change in time.
  CONSTRAINT CONSTRAINT_PURCHASED_GAME_PK PRIMARY KEY(FK_Customer_id, FK_Videogame_id)
)	 
	 */
	
	private Integer customerID;
	private Integer videoGameID;
	private LocalDateTime purchaseTime;
	private double purchasePrice;
	
		
	public PurchasedVideoGame(Integer customerID, Integer videoGameID, LocalDateTime purchaseTime,
			double purchasePrice) {
		super();
		this.customerID = customerID;
		this.videoGameID = videoGameID;
		this.purchaseTime = purchaseTime;
		this.purchasePrice = purchasePrice;
	}

	@Override
	public String toString() {
		return "PurchasedVideoGame [customerID=" + customerID + ", videoGameID=" + videoGameID + ", purchaseTime="
				+ purchaseTime + ", purchasePrice=" + purchasePrice + "]";
	}



	public Integer getCustomerID() {
		return customerID;
	}



	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}



	public Integer getVideoGameID() {
		return videoGameID;
	}



	public void setVideoGameID(Integer videoGameID) {
		this.videoGameID = videoGameID;
	}



	public LocalDateTime getPurchaseTime() {
		return purchaseTime;
	}



	public void setPurchaseTime(LocalDateTime purchaseTime) {
		this.purchaseTime = purchaseTime;
	}



	public double getPurchasePrice() {
		return purchasePrice;
	}



	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}



	public PurchasedVideoGame() {
		// TODO Auto-generated constructor stub
	}

}
