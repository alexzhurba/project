package client_server_nohtml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args) {
		/*String str = "";
		File file = new File("D:\\workspace\\Connection\\src\\html\\index.html");

		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
		
			do {
				str += br.readLine();
			} while (fis.available() != 0);
			br.close();
		} catch (IOException e2) {

			e2.printStackTrace();
		}
		
		System.out.println(str);*/
		
		try(FileReader reader = new FileReader("D:\\\\workspace\\\\Connection\\\\src\\\\html\\\\index.html"))
        {
           // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                 
                System.out.print((char)c);
            } 
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        }   
    } 

	}


