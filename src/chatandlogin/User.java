package chatandlogin;

import java.util.ArrayList;

public class User {
	
	public String userLogin;
	public String userPassword;
	public static ArrayList<User> usersData = new ArrayList<>();
	
	public ArrayList<User> getUsersData() {
		return usersData;
	}




	public void setUsersData(ArrayList<User> usersData) {
		this.usersData = usersData;
	}




	public User(String userLogin, String userPassword) {
		this.userLogin = userLogin;
		this.userPassword = userPassword;
	}
	

	

	public String getUserLogin() {
		return userLogin;
	}




	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}




	public String getUserPassword() {
		return userPassword;
	}




	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}




	@Override
	public String toString() {
		String str = userLogin+":"+userPassword;
		return str;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userLogin == null) {
			if (other.userLogin != null)
				return false;
		} else if (!userLogin.equals(other.userLogin))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		return true;
	}
	
	

}
