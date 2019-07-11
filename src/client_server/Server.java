package client_server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLEncoder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.ssl.internal.www.protocol.https.Handler;

import utils.HttpUtils;

public class Server {

	static String hystory = "";
	static String hystory2 = "";
	static String hystory3 = "";

	public static void main(String[] args) throws Exception {

		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 2);
		server.createContext("/api/post-msg", (HttpHandler) new PostMsessageHandler());
		server.createContext("/api/get-hystory", (HttpHandler) new GetHystoryHandler());

		server.setExecutor(Executors.newFixedThreadPool(2)); // creates a default executor
		server.start();

	}


	static class PostMsessageHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			URI requestURI = exchange.getRequestURI();
			System.out.println(requestURI.getQuery());

			String query = HttpUtils.getQuery(exchange);

			if(query != null) {
				String[] strQuery = query.split("&message=|name=|&id=");
				String chatID = strQuery[3];
				String newMessage = strQuery[1] + ":" + strQuery[2];
				switch(chatID) {
				case"chat1":

					hystory += newMessage + "\n";
					break;
				case"chat2":

					hystory2 += newMessage + "\n";
					break;
				case"chat3":

					hystory3 += newMessage + "\n";
					break;

				default:
					System.out.println("unknown command");

				}
				/*String response = strQuery[1] + ":" + strQuery[2];
				responses += response + "\n";*/
				System.out.println(newMessage);

			}

			HttpUtils.addCors(exchange);
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

			String responce = "";

			String[] strQuery = query.split("&message=|name=|&id=");
			String chatID = strQuery[3];

			OutputStream os = exchange.getResponseBody();
			switch(chatID) {
			case"chat1":
				responce = hystory;
				break;
			case"chat2":
				responce = hystory2;
				break;
			case"chat3":
				responce = hystory3;
				break;
			default:
				System.out.println("unknown command");
			}

			exchange.sendResponseHeaders(200, responce.getBytes().length);
			os.write(responce.getBytes());
			os.close();
			//	os.write(responses.getBytes());
			//System.out.println("Responses"+responses);
			//System.out.println("Responses2"+responses2);
			//	os.close();
		}
	}

}
