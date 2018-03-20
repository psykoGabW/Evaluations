package co.simplon.gwi.JDBCEval;

public class VideoGame {

	private Integer id;
	
	private String name;
	private Double price;
	private Integer pegiClassification;
	private String shortDescription;
	private String fullDescription;
	private String webSiteURL;
	
	
	
	@Override
	public String toString() {
		return "VideoGame [id=" + id + ", name=" + name + ", price=" + price + ", pegiClassification="
				+ pegiClassification + ", shortDescription=" + shortDescription + ", fullDescription=" + fullDescription
				+ ", webSiteURL=" + webSiteURL + ", getId()=" + getId() + ", getName()=" + getName() + ", getPrice()="
				+ getPrice() + ", getPegiClassification()=" + getPegiClassification() + ", getShortDescription()="
				+ getShortDescription() + ", getFullDescription()=" + getFullDescription() + ", getWebSiteURL()="
				+ getWebSiteURL() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
		
	
	public VideoGame(Integer id, String name, Double price, Integer pegiClassification, String shortDescription,
			String fullDescription, String webSiteURL) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.pegiClassification = pegiClassification;
		this.shortDescription = shortDescription;
		this.fullDescription = fullDescription;
		this.webSiteURL = webSiteURL;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Integer getPegiClassification() {
		return pegiClassification;
	}



	public void setPegiClassification(Integer pegiClassification) {
		this.pegiClassification = pegiClassification;
	}



	public String getShortDescription() {
		return shortDescription;
	}



	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}



	public String getFullDescription() {
		return fullDescription;
	}



	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}



	public String getWebSiteURL() {
		return webSiteURL;
	}



	public void setWebSiteURL(String webSiteURL) {
		this.webSiteURL = webSiteURL;
	}



	public VideoGame() {
		// TODO Auto-generated constructor stub
	}
	
	public void copyFrom(VideoGame vg) {
		this.id = vg.id;
		this.name = vg.name;
		this.price = vg.price;
		this.pegiClassification = vg.pegiClassification;
		this.shortDescription = vg.shortDescription;
		this.fullDescription = vg.fullDescription;
		this.webSiteURL = vg.webSiteURL;
	}


}
