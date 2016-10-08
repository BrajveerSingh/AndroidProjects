package in.co.thingsdata.login.login.service;

public class LoginService {
    private String userName = "Rahul";
    private String password = "testRahul123";
	public boolean authenticateUser(String userName, String password) {
		//TODO: Retrieve password from DB for userName and authenticate
		//Use MySql for this . storedPassword= (Select password from login where userName="userName"
		//if storedPassword and password matches then allow login to user.
		
		//Check if client sent password encrypted then decrypt the password. Decrypt stored password and match equality.
		if (userName.equals(this.userName) && password.equals(this.password)) {
			return true;
		}
		return false;
	}

}
