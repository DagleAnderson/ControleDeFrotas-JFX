package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSeparatorUI;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class VeiculoFormController implements Initializable {

		@FXML
		private TextField txtId;
		@FXML
		private TextField txtDescricao;
		@FXML
		private TextField txtAno;
		@FXML
		private TextField txtModelo;
		@FXML
		private TextField txtMarca;
		@FXML
		private Button btnGravar;
		@FXML
		private Button btnCancelar;
		
		
		@FXML
		public void onBtnGravarAction() {
			System.out.println("gravar");
		}
		
		@FXML
		public void onBtnCancelarAction() {
			System.out.println("cancelar");
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			this.initicalizeNodes();
		}
		
		//Controlar tipo e quantidade dos nodes(campos)
		public void initicalizeNodes() {
			Constraints.setTextFieldInteger(txtId);
			Constraints.setTextFieldMaxLength(txtDescricao, 30);
			Constraints.setTextFieldMaxLength(txtMarca, 25);
			Constraints.setTextFieldMaxLength(txtModelo, 25);
			Constraints.setTextFieldMaxLength(txtAno, 8);
			
		}
}
