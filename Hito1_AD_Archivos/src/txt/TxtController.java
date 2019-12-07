package txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TxtController {
	
	@FXML public Label salida;
	@FXML public Label error;
	@FXML public TextField nom;
	@FXML public TextField apell1;
	@FXML public TextField apell2;

	//FUNCION PARA LEER TXT
	public void LeerTxt() {
	      File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      String documento = "";

	      try {
	    	  //APERTURA Y CREACION DEL FICHERO DE BUFFEREDREADER PARA PODER LEER LINEA POR LINEA
	    	  archivo = new File (".\\src\\txt\\txt.txt");
	    	  fr = new FileReader (archivo);
	    	  br = new BufferedReader(fr);

	    	  // LECTURA DEL FICEHERO
	    	  String linea;
	    	  do {
	    		  linea=br.readLine();
	    		  documento = documento + linea + "\n";
	        	
	    		  if(linea!=null) {
	    			  salida.setText(documento);
	    		  }
	        	
	    	  }while(linea!=null);
	         
	      	}
	      	catch(Exception e){
	      		e.printStackTrace();
	      	}finally{
	      		//CIERRE DEL FICHERO
	      		try{                    
	      			if( null != fr ){   
	      				fr.close();     
	      			}                  
	      		}catch (Exception e2){ 
	      			e2.printStackTrace();
	      		}
	      	}
	   }

	
	//FUNCION PARA ESCRIBIR TXT
	public void EscribirTxt() {
		
		if(nom.getText().equals("") || apell1.getText().equals("") || apell2.getText().equals("")) {
            error.setText("CAMPOS VACIOS");
        }else {
        	error.setText("");
			FileWriter fichero = null;
	        PrintWriter pw = null;
	        try{
	            fichero = new FileWriter(".\\src\\txt\\txt.txt",true);
	            pw = new PrintWriter(fichero);
	            
	            String nombre = nom.getText();
	            String apellido1 = apell1.getText();
	            String apellido2 = apell2.getText();
	            
	            pw.println("Nombre: " + nombre + " || Apellido 1: " + apellido1 + " || Apellido 2: " + apellido2);
	            
	            nom.setText("");
	            apell1.setText("");
	            apell2.setText("");
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
		          if (null != fichero)
		              fichero.close();
		          } catch (Exception e2) {
		              e2.printStackTrace();
		          }
	        }
        }
	}

}
