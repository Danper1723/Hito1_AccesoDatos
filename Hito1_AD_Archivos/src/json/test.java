package json;

import java.io.FileReader;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class test {

	public static void main(String[] args) throws java.io.IOException{
		JsonParser parser = new JsonParser();
	    FileReader fr = new FileReader(".\\src\\json\\json.json");
	    JsonElement datos = parser.parse(fr);
	    dumpJSONElement(datos);
	}
	
	public static void dumpJSONElement(JsonElement elemento) {
	    if (elemento.isJsonObject()) {
	        System.out.println("Es objeto");
	        JsonObject obj = elemento.getAsJsonObject();
	        java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
	        java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
	        while (iter.hasNext()) {
	            java.util.Map.Entry<String,JsonElement> entrada = iter.next();
	            System.out.println("Clave: " + entrada.getKey());
	            System.out.println("Valor:");
	            dumpJSONElement(entrada.getValue());
	        }
	 
	    } else if (elemento.isJsonPrimitive()) {
	        System.out.println("Es primitiva");
	        JsonPrimitive valor = elemento.getAsJsonPrimitive();
	        if (valor.isString()) {
	            System.out.println("Es texto: " + valor.getAsString());
	        }
	    }
	}
}
