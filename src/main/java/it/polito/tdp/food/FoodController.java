/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Arco;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	String raw = txtPassi.getText();
    	int n;
    	try {
    		n = Integer.parseInt(raw);
    		
    	}
    	catch (NumberFormatException e) {
    		txtResult.appendText("Il parametro inserito non è un numero\n");
    		return;
    	}
    	if (boxPorzioni.getItems().isEmpty()) {
    	    txtResult.setText("Prima crea il grafico");
    	    return;
    	}
    	if (boxPorzioni.getValue() == null) {
    		txtResult.setText("Devi scegliere un'opzione");
    	    return;
    	}
    	txtResult.setText("Cerco cammino peso massimo...");
    	
    	String value = boxPorzioni.getValue();
    	
    	List <String> lista = model.getListaPesoMax(n, value);
    	int peso = model.getPesoMax();
    	
    	if (peso != -1) {
    		txtResult.setText("Trovato cammino di peso "+peso+"\n");
    		for (String s : lista) {
    			txtResult.appendText(s+"\n");
    		}
    	}
    	else
    		txtResult.setText("Impossibile trovare il cammino richiesto");
    }

    @FXML
    void doCorrelate(ActionEvent event) {  	
    	if (boxPorzioni.getItems().isEmpty()) {
    	    txtResult.setText("Prima crea il grafico");
    	    return;
    	}
    	if (boxPorzioni.getValue() == null) {
    		txtResult.setText("Devi scegliere un'opzione");
    	    return;
    	}
    	String value = boxPorzioni.getValue();
    	
    	txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...");
    	
    	List <Arco> vicini= model.getCorrelate(value);
    	
    	txtResult.setText("Trovati "+vicini.size()+" vicini:\n");
    	for (Arco a : vicini) {
    		txtResult.appendText(a.getVertice1()+" - "+a.getPeso()+"\n");
    	}
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {   	
    	String raw = txtCalorie.getText();
    	int n;
    	try {
    		n = Integer.parseInt(raw);
    		
    	}
    	catch (NumberFormatException e) {
    		txtResult.appendText("Il parametro inserito non è un numero\n");
    		return;
    	}
    	
    	txtResult.clear();
    	txtResult.appendText("\nCreazione grafo...");
    	model.creaGrafo(n);
    	
    	boxPorzioni.getItems().clear();
    	boxPorzioni.getItems().addAll(model.getVertex(n));
    	
    	txtResult.clear();
    	txtResult.appendText("Grafo creato!");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
