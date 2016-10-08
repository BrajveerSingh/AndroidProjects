package in.co.thingsdata.login.login;

public class Login {
	private final long id;
	private final String userName;

	public Login(long id, String userName) {
		this.id = id;
		this.userName = userName;
	}
	public long getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
}
