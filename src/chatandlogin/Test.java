package chatandlogin;

import java.net.URI;
import java.util.ArrayList;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.Authenticator.Failure;
import com.sun.net.httpserver.Authenticator.Result;
import com.sun.net.httpserver.Authenticator.Success;

import utils.HttpUtils;

public class Test {

	public static void main(String[] args) {
		User user = new User("Алексей", "1111");
		ArrayList<User> list = new ArrayList<>();
		list.add(user);
		System.out.println("Size before "+list.size());
		String test = "";
	
		if(list.contains(user)) {
			test+="Takoy uje est";
		}
		
		
		
		System.out.println("Size after "+list.size());
		System.out.println("Stringr "+test);
		

	}

}


/*class Auth extends Authenticator {

	@Override
	public Result authenticate(HttpExchange exchange) {
		URI requestURI = exchange.getRequestURI();
		HttpUtils.addCors(exchange);
		String query = HttpUtils.getQuery(exchange);
		if (query != null) {
			String[] strQuery = query.split("login=|&password=");
			String userInfo = strQuery[1] + ":" + strQuery[2];
			System.out.println("UserInfo " + userInfo);
			User newUser = null;
			ArrayList<User> usersData = User.usersData;
			
			for (int i = 0; i < usersData.size(); i++) {
				User user = usersData.get(i);
				if (user.toString().equals(userInfo)) {
					messageToRgstrForm += REPETACCOUNT;
					return new Failure(403);
				}
				newUser = new User(strQuery[1], strQuery[2]);
				usersData.add(newUser);
				messageToRgstrForm += strQuery[1] + WELCOME;
				return new Success(new HttpPrincipal(strQuery[1], strQuery[2]));

			}
			
		
		return null;
	}
		return null;
	
}

}*/
