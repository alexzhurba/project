package client_server;

import java.util.Arrays;

public class Test2 {

	public static void main(String[] args) {
		String text = "name=2&message=3&id=SEND";
		String[] strQuery = text.split("&message=|name=|&id=");
		System.out.println(Arrays.toString(strQuery));

	}

}
