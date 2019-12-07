package json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import persona.Persona;

public class JsonController {
	
	@FXML public Label salida;
	@FXML public Label error;
	@FXML public TextField nom;
	@FXML public TextField apell1;
	@FXML public TextField apell2;
	
	String n, a1, a2;
	int cont = 0;
	ArrayList <Persona> personas = new ArrayList<Persona>();
	
	String cadena = "";
	
	//FUNCIONES PARA LEER JSON
	public void LeerJson()throws java.io.IOException {
		JsonParser parser = new JsonParser();
	    FileReader fr = new FileReader(".\\src\\json\\json.json");
	    JsonElement datos = parser.parse(fr);
	    FormatJson(datos);
	    cadena = "";
	    if (datos.isJsonObject()) {
	        JsonObject obj = datos.getAsJsonObject();
	        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
	        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
	        while (iter.hasNext()) {
	            java.util.Map.Entry<String,JsonElement> entrada = iter.next();
	            FormatJson(entrada.getValue());
	            //System.out.println(entrada);
	        }
	    }
	    
	    cadena = "";
	}
	
	public void FormatJson(JsonElement elemento) {
		if (elemento.isJsonObject()) {
	        JsonObject obj = elemento.getAsJsonObject();
	        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
	        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
	        while (iter.hasNext()) {
	            java.util.Map.Entry<String,JsonElement> entrada = iter.next();	            
	            if(entrada.getKey().equals("Apellido2")) {

	            	cadena = cadena + entrada.getKey() + ": " + entrada.getValue() + "\n";
	            }else {
	            	cadena = cadena + entrada.getKey() + ": " + entrada.getValue() + " || ";
	            }
	            salida.setText(cadena);
	            FormatJson(entrada.getValue());
	        } 
	    } else if (elemento.isJsonArray()) {
            JsonArray array = elemento.getAsJsonArray();
            java.util.Iterator<JsonElement> iter = array.iterator();
            while (iter.hasNext()) {
                JsonElement entrada = iter.next();
                FormatJson(entrada);
            }
	    }
	}
	
	//FUNCIONES PARA ESCRIBIR JSON
	
	public void PruebaEscribir()throws java.io.IOException  {
		//LEEMOS EL JSON
		
		JsonParser parser = new JsonParser();
	    FileReader fr = new FileReader(".\\src\\json\\json.json");
	    JsonElement datos = parser.parse(fr);
	    if (datos.isJsonObject()) {
	        JsonObject obj = datos.getAsJsonObject();
	        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
	        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
	        while (iter.hasNext()) {
	            java.util.Map.Entry<String,JsonElement> entrada = iter.next();
	            PruebaEscribir2(entrada.getValue());
	            
	        }
	    }
	    
	    //DISEÑAR ERROR********************************************************
	    if(nom.getText().equals("") || apell1.getText().equals("") || apell2.getText().equals("")) {
            error.setText("CAMPOS VACIOS");
	    }else {
	    	error.setText("");
		    n = nom.getText();
		    a1 = apell1.getText();
		    a2 = apell2.getText();
		    
		    Persona p = new Persona(n ,a1, a2);
		    personas.add(p);
	    }
	    //ACABO DISEÑO ERROR*******************************************
	    
	    String almacentemp = null;
	    String format = "{Persona:[\n";
	    
	    for (int i = 0; i < personas.size(); i++) {
			if(i<personas.size()-1) {
				almacentemp = "{" + "'Nombre':" + personas.get(i).getNombre() + ", 'Apellido1':" + personas.get(i).getApellido1() + ", 'Apellido2':" + personas.get(i).getApellido2() + "}";
				format = format + almacentemp + ",\n";
			}else {
				almacentemp = "{" + "'Nombre':" + personas.get(i).getNombre() + ", 'Apellido1':" + personas.get(i).getApellido1() + ", 'Apellido2':" + personas.get(i).getApellido2() + "}";
				format = format + almacentemp + "\n]}";
			}
		}//CIERRA EL FOR
	    
	    FileWriter filewriter = null;
	    File archivo = new File(".\\src\\json\\json.json");
	    try {
	    	filewriter = new FileWriter(archivo);
	    	BufferedWriter bfwriter = new BufferedWriter(filewriter);
	    	bfwriter.write(format);
	    	bfwriter.close();
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    
	    personas.clear();
	    nom.setText("");
	    apell1.setText("");
	    apell2.setText("");
	    	
	}
	
	public void PruebaEscribir2(JsonElement datos)throws java.io.IOException  {
		if (datos.isJsonObject()) {
	        JsonObject obj = datos.getAsJsonObject();
	        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
	        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
	        while (iter.hasNext()) {
	            java.util.Map.Entry<String,JsonElement> entrada = iter.next();
	            
	            if(entrada.getKey().equals("Nombre")) {
	            	n = entrada.getValue().toString();
	            	cont++;	
	            }else if(entrada.getKey().equals("Apellido1")){
					a1 = entrada.getValue().toString();
					cont++;
				}else if(entrada.getKey().equals("Apellido2")){
					a2 = entrada.getValue().toString();
					cont++;
				}
	            
	            if(cont==3) {
	            	Persona p = new Persona(n,a1,a2);
	            	personas.add(p);
	            	cont = 0; 	
	            }
	            
	            PruebaEscribir2(entrada.getValue());
	            
	        }
	    }else if(datos.isJsonArray()) {
	    	JsonArray array = datos.getAsJsonArray();
	    	Iterator<JsonElement> iterator = array.iterator();
	    	while(iterator.hasNext()) {
	    		JsonElement entrada = iterator.next();
	    		PruebaEscribir2(entrada);
	    	}
	    }
		
	}
}

/*
 
 {Persona:[
{"Nombre": "Daniel", "Apellido1": "Perez", "Apellido2": "Moreno"},
{"Nombre": "Daniel", "Apellido1": "Perez", "Apellido2": "Moreno"}
]}
 
 */
