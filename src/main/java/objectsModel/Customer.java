package objectsModel;

public class Customer {
	
	private String email;
	private String password;	
	private String firstName;	
	private String lastName;	
	private String phone;	
	private boolean news_and_marketing;	
	private String customer_id;
	
	public Customer(String email, String password, String firstName, String lastName, String phone,
			boolean news_and_marketing, String customer_id) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.news_and_marketing = news_and_marketing;
		this.customer_id = customer_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isNews_and_marketing() {
		return news_and_marketing;
	}

	public void setNews_and_marketing(boolean news_and_marketing) {
		this.news_and_marketing = news_and_marketing;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	
}
