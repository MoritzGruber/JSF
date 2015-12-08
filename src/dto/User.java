package dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	private int userId;
	private String name;
	private String password;
	private String street;
	private String number;
	private String zipCode;
	private String city;
	private boolean isAdmin;
	private UserAccount userAccount;
	
	public User(int userId, String name, String password, 
			String street, String number,
			String zipCode, String city) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.city = city;
		userAccount = new UserAccount(this, "1234-6789");
	}
	
	public String toString() {
		return "id=" + userId + ", name=" + name + ", pwd=" + password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	public int getUserId() {
		return userId;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
