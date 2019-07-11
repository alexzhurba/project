package chatandlogin;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import utils.HttpUtils;

public class Server {

	static String hystory = "";
	static String messageToRgstrForm = "";
	static String ERROR = "Иди нахрен";


	public static void main(String[] args) throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8500), 2);
		server.createContext("/api/post-msg", (HttpHandler) new PostMessangeHandler());
		server.createContext("/api/get-hystory", (HttpHandler) new GetHystoryHandler());
		server.createContext("/loginVertification", (HttpHandler) new UserRegistrationHandler());
		server.createContext("/loginVertification", (HttpHandler) new UserCheckHandler());

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
			URI requestURI = exchange.getRequestURI();
			HttpUtils.addCors(exchange);
			String messasnge = HttpUtils.userRegistr(exchange);
			System.out.println(messasnge);

			OutputStream os = exchange.getResponseBody();
			exchange.sendResponseHeaders(200, messasnge.getBytes().length);
			os.write(messasnge.getBytes());
			os.close();

		}

	}

	static class UserCheckHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			URI requestURI = exchange.getRequestURI();
			HttpUtils.addCors(exchange);
			String messasnge = HttpUtils.userCheck(exchange);
			System.out.println(messasnge);

			OutputStream os = exchange.getResponseBody();
			exchange.sendResponseHeaders(200, messasnge.getBytes().length);
			os.write(messasnge.getBytes());
			os.close();

		}

	}

}
