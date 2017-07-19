package beans;

public class ProductBean {

	private int id;
	private String name;
	private double price;
	private int quantity;
	public Boolean isValid = true;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int prodID) {
		this.id = prodID;
	}
	public String getName() {
		return name;
	}
	public void setName(String prodName) {
		this.name = prodName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double prodPrice) {
		this.price = prodPrice;
	}
	
}
