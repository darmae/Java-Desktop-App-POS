package beans;


public class UserBean {

	private int userID;
	private String username;
	private String password;
	private int key_value;
	private String user_fullname;
//	private UserTypes user_type;
	private int user_type;
	public Boolean isValid=true;
	
	public int getId() {
		return userID;
	}
	public void setId(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return user_fullname;
	}
	public void setFullname(String user_fullname) {
		this.user_fullname = user_fullname;
	}
	public int getUsertype() {
		return user_type;
	}
	public void setUsertype(int user_type) {
		this.user_type = user_type;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getKey_value() {
		return key_value;
	}
	public void setKey_value(int key_value) {
		this.key_value = key_value;
	}
}
