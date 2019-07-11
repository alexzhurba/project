import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestConnection {

	public static void main(String[] args) throws IOException {
		
		 	String numOne = "1";
	        String numTwo = "2";
	        String operation = "plus";
	        String responce;
	        
	        String urlrequest = "http://192.168.0.126/server/server.php?numOne=5&numTwo=8&oper=plus";

	        URL url = new URL(urlrequest);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("GET");
	        
	        int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());

	}

}
