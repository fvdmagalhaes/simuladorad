package Controle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Saida {
	public static String saida;
	
	public static void escreve(){
	
		FileWriter arquivo;           
		
		try {  
			arquivo = new FileWriter(new File("resultado.txt"),true);  
			arquivo.write(saida);  
			arquivo.close();  
		}catch (IOException e) {  
			e.printStackTrace();  
		}catch (Exception e) {  
		    e.printStackTrace();  
		}  
	}
	
	public static void criaArquivo(){
		
		FileWriter arquivo;           
		
		try {  
			arquivo = new FileWriter(new File("resultado.txt"));    
			arquivo.close();  
		}catch (IOException e) {  
			e.printStackTrace();  
		}catch (Exception e) {  
		    e.printStackTrace();  
		}  
	}
}
