package co.simplon.gwi.JDBCEval;

import java.time.LocalDateTime;

public class PurchasedVideoGame {

	private Integer id; 	
	private Integer customerID;
	private Integer videoGameID;
	private LocalDateTime purchaseTime;
	private double purchasePrice;
	
	public PurchasedVideoGame() {
		
	}
		
	public PurchasedVideoGame(Integer id, Integer customerID, Integer videoGameID, LocalDateTime purchaseTime,
			double purchasePrice) {
		super();
		this.id = id;
		this.customerID = customerID;
		this.videoGameID = videoGameID;
		this.purchaseTime = purchaseTime;
		this.purchasePrice = purchasePrice;
	}
	
	public void copyFrom(PurchasedVideoGame pvg) {
		this.id = new Integer(pvg.id);
		this.customerID = new Integer(pvg.customerID);
		this.videoGameID = new Integer(pvg.videoGameID);
		this.purchaseTime = pvg.purchaseTime;
		this.purchasePrice = pvg.purchasePrice;
		
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
