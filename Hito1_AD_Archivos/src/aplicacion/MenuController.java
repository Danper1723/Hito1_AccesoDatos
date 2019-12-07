package aplicacion;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController {
	
	//FUNCION QUE SE APLICA AL BOTON DE TXT PARA ABRIR LA VENTANA DE TXT
	public void abrir_txt() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\txt\\Txt.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Texto");
        stage.setScene(new Scene(root1,600,400));  
        stage.show();
	}
	
	//FUNCION QUE SE APLICA AL BOTON DE JSON PARA ABRIR LA VENTANA DE JSON
	public void abrir_json() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\json\\Json.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Json");
        stage.setScene(new Scene(root1,600,400));  
        stage.show();
	}
	
	//FUNCION QUE SE APLICA AL BOTON DE XML PARA ABRIR LA VENTANA DE XML
	public void abrir_xml() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("..\\xml\\Xml.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Xml");
        stage.setScene(new Scene(root1,600,400));  
        stage.show();
	}
}
