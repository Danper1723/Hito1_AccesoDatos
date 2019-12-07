package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import persona.Persona;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class XmlController {
	
	@FXML public Label salida;
	@FXML public Label error;
	@FXML public TextField nom;
	@FXML public TextField apell1;
	@FXML public TextField apell2;
	
	Persona persona = new Persona();
	
	static final String ITEM = "item";
	static final String NOMBRE = "nombre";
	static final String APELLIDO1 = "apellido1";
	static final String APELLIDO2 = "apellido2";
	
	//FUNCION PARA LEER JSON
	public List<Persona> LeerXml() {
		String fin = "";
		List<Persona> items = new ArrayList<Persona>();
		try {
			
			XMLInputFactory factoria = XMLInputFactory.newInstance();
			InputStream entrada = new FileInputStream(".\\src\\xml\\xml.xml");
			
			XMLEventReader lector = factoria.createXMLEventReader(entrada);
			
			Persona item = null;
			while (lector.hasNext()) {
                XMLEvent event = lector.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    
                    if (startElement.getName().getLocalPart().equals(ITEM)) {
                        item = new Persona();
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                        }
                    }
					

                    
                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(NOMBRE)) {
                            event = lector.nextEvent();
                            item.setNombre(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(APELLIDO1)) {
                            event = lector.nextEvent();
                            item.setApellido1(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart().equals(APELLIDO2)) {
                            event = lector.nextEvent();
                            item.setApellido2(event.asCharacters().getData());
                            continue;
                        }
                    }
                }
                
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(ITEM)) {
                        items.add(item);
                    }
                }
			}
			
			for (int i = 0; i < items.size(); i++) {
				//System.out.println(items.get(i));
				Persona a = items.get(i);
				String b = a.toString();
				fin = fin + "\n" + b;
			}
			salida.setText(fin);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}//CIERRA EL METODO LEER
	
	
	//METODOS ESCRIBIR XML
	
	public void Anadir() {
        if(nom.getText().equals("") || apell1.getText().equals("") || apell2.getText().equals("")) {
            error.setText("CAMPOS VACIOS");
        }else {
        	persona.setNombre(nom.getText());
        	persona.setApellido1(apell1.getText());
        	persona.setApellido2(apell2.getText());
           
            Anadir2(persona);
        }
    }
    public void Anadir2(Persona persona) {
        error.setText("");
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(".\\src\\xml\\xml.xml"));
           
            //PREPARAMOS EL DOCUMENTO XML
            doc.getDocumentElement().normalize();
            Node nodoRaiz = doc.getDocumentElement();
           
            //CREA LA ETIQUETA PADRE
            Element nuevoItem = doc.createElement("item");
           
            //CREA LAS ETIQUETAS HIJAS
            Element nuevoNombre = doc.createElement("nombre");
            nuevoNombre.setTextContent(persona.getNombre());
           
            Element nuevoApellido1 = doc.createElement("apellido1");
            nuevoApellido1.setTextContent(persona.getApellido1());
           
            Element nuevoApellido2 = doc.createElement("apellido2");
            nuevoApellido2.setTextContent(persona.getApellido2());
            
            //AGREGA LAS ETIQUETAS HIJAS
            nuevoItem.appendChild(nuevoNombre);
            nuevoItem.appendChild(nuevoApellido1);
            nuevoItem.appendChild(nuevoApellido2);
           
            //RELACIONA LAS ETIQUETAS CON EL NODO RAIZ
            nodoRaiz.appendChild(nuevoItem);
           
            //PREPARA LA ESTRUCTURA PARA UN FICHERO XML
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(".\\src\\xml\\xml.xml"));
            transformer.transform(source, result);
           
           
        }catch(ParserConfigurationException parseE) {
            System.out.println(parseE.getMessage());
        }catch(SAXException saxE) {
            System.out.println(saxE.getMessage());
        }catch(IOException ioE) {
            System.out.println(ioE.getMessage());
        }catch(TransformerConfigurationException transE) {
            System.out.println(transE.getMessage());
        }catch(TransformerException transforE) {
            System.out.println(transforE.getMessage());
        }
       
        nom.setText("");
        apell1.setText("");
        apell2.setText("");
    }
	
}

/*
 
 <?xml version="1.0" encoding="UTF-8"?>
<config>
    <item>
        <nombre>Daniel</nombre>
        <apellido1>Perez</apellido1>
        <apellido2>Moreno</apellido2>
    </item>
    
    <item>
        <nombre>Francisco</nombre>
        <apellido1>Aliseda</apellido1>
        <apellido2>Polanco</apellido2>
    </item>
</config>
 
 */
