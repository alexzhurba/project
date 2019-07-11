package utils;

import java.net.URI;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;

import chatandlogin.User;

public class HttpUtils {
	public static void addCors(HttpExchange exchange) {
		exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET");
		exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
	}

	public static String getQuery(HttpExchange exchange) {
		URI requestURI = exchange.getRequestURI();
		System.out.println(requestURI.getQuery());

		String query = requestURI.getQuery();
		System.out.println(query);

		return query;
	}

	public static String userRegistr(HttpExchange exchange) {
		String messange = "";
		URI requestURI = exchange.getRequestURI();
		String query = HttpUtils.getQuery(exchange);
		String[] strQuery = query.split("login=|&password=");
		String userInfo = strQuery[1] + ":" + strQuery[2];
		ArrayList<User> usersData = User.usersData;

		User newUser = new User(strQuery[1], strQuery[2]);

		if (usersData.contains(newUser)) {

			messange += "Пользователь с таким логином уже существует";
			return messange;
		} else
			messange += strQuery[1] + " Welcome to chat!";
		usersData.add(newUser);
		return messange;

	}
	
	public static String userCheck(HttpExchange exchange) {
		String messange = "";
		URI requestURI = exchange.getRequestURI();
		String query = HttpUtils.getQuery(exchange);
		String[] strQuery = query.split("login=|&password=");
		String userInfo = strQuery[1] + ":" + strQuery[2];
		ArrayList<User> usersData = User.usersData;
		User newUser = new User(strQuery[1], strQuery[2]);
		if (newUser.toString().equals(userInfo)) {

			messange += strQuery[1] + " Welcome to chat!";
			return messange;
			
		} else
			messange += "Неверный пароль";
		usersData.add(newUser);
		return messange;

	}
}
