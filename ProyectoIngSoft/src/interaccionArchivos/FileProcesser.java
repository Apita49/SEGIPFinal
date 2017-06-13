package interaccionArchivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcesser {
	private static FileProcesser fp;

	private FileProcesser() {
	};

	public static FileProcesser getInstance() {
		if (fp == null) {
			fp = new FileProcesser();
		}
		return fp;
	}

	public String processFile(String direccion) {
		try {
			FileReader fr = new FileReader(direccion);
			BufferedReader br = new BufferedReader(fr);
			String text = "";
			String line = br.readLine();
			while (line != null) {
				text += line;
				line = br.readLine();
			}
			br.close();
			return text;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
