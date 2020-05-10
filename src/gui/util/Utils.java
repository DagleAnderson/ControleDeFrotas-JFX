package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {
		//Pega o Palco pai para passar como parâmetro nas telas de diálogo;
		/* event.getSource retorna um Object -> faz-se um cast para um tipo Node 
			-> faz um get da Scene -> faz-se um cast para um Stage .(Segue Arquitetura JavaFX)*/
		return  (Stage) ((Node) event.getSource()).getScene().getWindow();
	}
	
	public static Integer tryParseToInt( String str) {
		try {
		return Integer.parseInt(str);
		}catch (NumberFormatException e) {
			return null;
		}
	}
	
	public static Double tryParseToDouble( String str) {
		try {
		return Double.parseDouble(str);
		}catch (NumberFormatException e) {
			return null;
		}
	}
	
	
	

}
