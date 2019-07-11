package client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;

public class Test {

	public static void main(Al[] args) throws IOException {
		String wr = "запись cyjd";
		/*FileOutputStream fos = new FileOutputStream("E://EclipseInOutput//data.txt",true);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));*/
		
		FileWriter fw;
		File file = new File("C:\\Users\\Alexey\\Desktop\\112.txt");
		try {

			fw = new FileWriter(file,true);
			fw.write(wr);
			fw.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		
		/*try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			String strAsYaml;
			do {
				
				// считывание строки из файла
				wr = br.readLine();

					} 
					while (fis.available() != 0);
					br.close();
					}finally {
						
					}*/
	}

}


class TestDemo{
	
	
}
