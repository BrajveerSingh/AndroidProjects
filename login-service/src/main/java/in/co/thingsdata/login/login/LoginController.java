package in.co.thingsdata.login.login;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.co.thingsdata.login.login.service.LoginService;

@RestController
public class LoginController {
    
    private static final String template = "Login, %s!";
    private final AtomicLong counter = new AtomicLong();
  //http://localhost:8080/login
    @RequestMapping("/login")
    public Login login(@RequestParam(value="userName", defaultValue="User") String userName, @RequestParam(value="password", defaultValue="test123") String password) {
        if(doAuthentication(userName, password)){
        	return new Login(counter.incrementAndGet(),
                String.format(template, "Successful"));
        }else {
        	return new Login(counter.incrementAndGet(),
                    String.format(template, "Failed"));
        }
    }

	private boolean doAuthentication(String userName, String password) {
		// TODO call LoginService class to autheticate user
		LoginService loginService = new LoginService();
		return loginService.authenticateUser(userName, password);
	}
}