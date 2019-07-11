package client_server_nohtml;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;



import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import chatandlogin.User;
import utils.HttpUtils;

public class Server_NoHtml {

	static String hystory = "";
	static String messageToRgstrForm = "";
	static String ERROR = "Иди нахрен";


	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8500), 2);
		server.createContext("/api/post-msg", (HttpHandler) new PostMessangeHandler());
		server.createContext("/api/get-hystory", (HttpHandler) new GetHystoryHandler());
		server.createContext("/loginVertification", (HttpHandler) new UserRegistrationHandler());
		server.createContext("/loginVertification", (HttpHandler) new UserCheckHandler());
		server.createContext("/", (HttpHandler) new StaticHandler());
		

		server.setExecutor(null); // creates a default executor
		server.start();

	}

	static class PostMessangeHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			HttpUtils.addCors(exchange);
			//URI requestURI = exchange.getRequestURI();
			String query = HttpUtils.getQuery(exchange);
			String[] strQuery = query.split("&message=|name=|&password=");
			String newMessage = strQuery[1] + ":" + strQuery[2];
			//hystory += newMessage + "\n";

			String checkPassword = strQuery[1] + ":" + strQuery[3];
			System.out.println(checkPassword);
			
			User user = new User(strQuery[1], strQuery[3]);
			ArrayList<User> usersData = User.usersData;
			
			if(usersData.contains(user)) {
			hystory += newMessage + "\n";

			}else {
				HttpUtils.addCors(exchange);
				
				OutputStream os = exchange.getResponseBody();
				exchange.sendResponseHeaders(401, " Tb| cyka udu B }|{oy||y".getBytes().length);
				os.write("1".getBytes());
				os.close();
				return;
			}
			exchange.sendResponseHeaders(200, 0);

			OutputStream os = exchange.getResponseBody();
			os.close();

		}

	}

	static class GetHystoryHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			HttpUtils.addCors(exchange);
			String query = HttpUtils.getQuery(exchange);
			OutputStream os = exchange.getResponseBody();
			exchange.sendResponseHeaders(200, hystory.getBytes().length);
			os.write(hystory.getBytes());
			os.close();
		}

	}

	static class UserRegistrationHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			HttpUtils.addCors(exchange);
			String messasnge = HttpUtils.userRegistr(exchange);
			//System.out.println(messasnge);

			OutputStream os = exchange.getResponseBody();
			exchange.sendResponseHeaders(200, messasnge.getBytes().length);
			os.write(messasnge.getBytes());
			os.close();

		}

	}

	static class UserCheckHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {

			HttpUtils.addCors(exchange);
			String messasnge = HttpUtils.userCheck(exchange);
			System.out.println(messasnge);

			OutputStream os = exchange.getResponseBody();
			exchange.sendResponseHeaders(200, messasnge.getBytes().length);
			os.write(messasnge.getBytes());
			os.close();

		}

	}
	
	
	 static class StaticHandler implements HttpHandler {
	        @Override
	        public void handle(HttpExchange exchange) throws IOException {

	        	URI requestURI = exchange.getRequestURI();
	        	String query = requestURI.getQuery();
	        	System.out.println(requestURI.getQuery());
	        	
	        	File file = null;
	        	
	        	String str = "";
	        	
	        		if(query == null) {
	        			file = new File("D:\\workspace\\Connection\\src\\html\\index.html");
	        		}else if(query.equals("registration_form.html")){
	        			file = new File("D:\\workspace\\Connection\\src\\html\\registration_form.html");
	        		}else if(query.equals("chat_window.html")){
	        			file = new File("D:\\workspace\\Connection\\src\\html\\chat_window.html");
	        		}else {
	        			System.out.println("No go if");
	        		}
	    		

	    		
	        	
	        	
	        	
	        	
	        	int c = 0;
	    		try (FileReader reader = new FileReader(file)) {
	    			// читаем посимвольно

	    			while ((c = reader.read()) != -1) {

	    				str += (char) c;
	    			}
	    		} catch (IOException ex) {

	    			System.out.println(ex.getMessage());
	    		}
	    		
	    		OutputStream os = exchange.getResponseBody();
	    		exchange.sendResponseHeaders(200, str.getBytes().length);
	    		os.write(str.getBytes());
	    		os.close();

	        }
	    }

}




/*static String hystory = "";
static String messageToRgstrForm = "";



public static void main(String[] args) throws Exception {
	HttpServer server = HttpServer.create(new InetSocketAddress(8500), 2);
	//server.createContext("/api/post-msg", (HttpHandler) new PostMessangeHandler());
	server.createContext("/api/get-hystory", (HttpHandler) new GetHystoryHandler());
	server.createContext("/loginVertification", (HttpHandler) new UserRegistrationHandler());
	server.createContext("/", (HttpHandler) new UserCheckHandler());

	server.setExecutor(null); // creates a default executor
	server.start();

}

static class PostMessangeHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		URI requestURI = exchange.getRequestURI();
		System.out.println(requestURI.getQuery());
		String query = HttpUtils.getQuery(exchange);
		HttpUtils.addCors(exchange);
		String[] strQuery = query.split("&message=|name=|&password=");
		
		String newMessage = strQuery[1] + ":" + strQuery[2];
		
		
		String checkPassword = strQuery[1] + ":" + strQuery[3];
		System.out.println(checkPassword);
		
		User user = new User(strQuery[1], strQuery[3]);
		ArrayList<User> usersData = User.usersData;
		
		if(usersData.contains(user)) {
		hystory += newMessage + "\n";

		}else {
			HttpUtils.addCors(exchange);
			
			OutputStream os = exchange.getResponseBody();
			exchange.sendResponseHeaders(401, " Tb| cyka udu B }|{oy||y".getBytes().length);
			os.write("1".getBytes());
			os.close();
			return;
		}
		exchange.sendResponseHeaders(200, 0);

		OutputStream os = exchange.getResponseBody();
		os.close();

	}

}

static class GetHystoryHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		HttpUtils.addCors(exchange);
		String query = HttpUtils.getQuery(exchange);
		OutputStream os = exchange.getResponseBody();
		exchange.sendResponseHeaders(200, hystory.getBytes().length);
		os.write(hystory.getBytes());
		os.close();
	}

}

static class UserRegistrationHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		//String messasnge = HttpUtils.userRegistr(exchange);
		String messasnge = "";
	File file = new File("D:\\workspace\\Connection\\src\\html\\registrPost.html");

	int c = 0;
	try (FileReader reader = new FileReader(file)) {
		// читаем посимвольно

		while ((c = reader.read()) != -1) {

			messasnge += (char) c;
		}
	} catch (IOException ex) {

		System.out.println(ex.getMessage());
	}
	
	System.out.println(messasnge);
	URI requestURI = exchange.getRequestURI();
	String query = HttpUtils.getQuery(exchange);
	System.out.println("getQuery "+query);
	
	
	
	OutputStream os = exchange.getResponseBody();
	exchange.sendResponseHeaders(200, messasnge.getBytes().length);
	os.write(messasnge.getBytes());
	os.close();

	}

}

static class UserCheckHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		String str = "";
		File file = new File("D:\\workspace\\Connection\\src\\html\\index.html");

		int c = 0;
		try (FileReader reader = new FileReader(file)) {
			// читаем посимвольно

			while ((c = reader.read()) != -1) {

				str += (char) c;
			}
		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}
		
		OutputStream os = exchange.getResponseBody();
		exchange.sendResponseHeaders(200, str.getBytes().length);
		os.write(str.getBytes());
		os.close();

	}

}

}*/





