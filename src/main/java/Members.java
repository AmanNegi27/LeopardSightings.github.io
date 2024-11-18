
public class Members {
	private String name, mobileNumber, address, dateOfRegisteration,password, userName;

	public Members(String name, String mobileNumber, String address, String dateOfRegisteration, String password, String userName) {
		super();
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.dateOfRegisteration = dateOfRegisteration;
		this.password = password;
		this.userName= userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfRegisteration() {
		return dateOfRegisteration;
	}

	public void setDateOfRegisteration(String dateOfRegisteration) {
		this.dateOfRegisteration = dateOfRegisteration;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
