package gui;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MarcaFormController {

	//componentes FXML
		@FXML
		private TextField txtId;
		@FXML
		private TextField txtDesc;
		@FXML
		private Button btnGravar;
		@FXML
		private Button btnCancelar;	
		
	
	public void onBtnGravarAction() {
		System.out.println("gravando no bando de dados");
	}	
	
	public void onBtnCancelarAction() {
		System.out.println("cancelado");
	}
	
	
}
