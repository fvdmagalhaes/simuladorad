package Controle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saida {
	public static String saida;
	
	public static void escrever(){
		FileWriter arquivo;  
		 try {  
			arquivo = new FileWriter(new File("Arquivo.txt"));  
			arquivo.write(Saida.saida);  
			arquivo.close();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} catch (Exception e2) {  
			e2.printStackTrace();  
		}  
	}
}
