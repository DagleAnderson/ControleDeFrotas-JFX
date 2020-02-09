package gui;

import java.net.URL;
import java.util.ResourceBundle;


import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entities.Veiculo;

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
		private TextField txtKmRodado;
		@FXML
		private TextField txtPlaca;
		@FXML
		private TextField txtChassi;
		@FXML
		private TextField txtRenavam;
		
		
		@FXML
		private Button btnGravar;
		@FXML
		private Button btnCancelar;
		
		private Veiculo entity;
		
		
		public void setVeiculo(Veiculo veic) {
			this.entity = veic;
		}
		
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
			
			//regras de negócio da classe Constraints de gui/utils
			Constraints.setTextFieldInteger(txtId);
			Constraints.setTextFieldMaxLength(txtDescricao, 30);
			Constraints.setTextFieldMaxLength(txtMarca, 25);
			Constraints.setTextFieldMaxLength(txtModelo, 25);
			Constraints.setTextFieldMaxLength(txtAno, 8);
			Constraints.setTextFieldMaxLength(txtKmRodado, 8);
			Constraints.setTextFieldMaxLength(txtPlaca, 8);
			Constraints.setTextFieldMaxLength(txtChassi, 8);
			Constraints.setTextFieldMaxLength(txtRenavam, 8);
					
		}
		
		public void updateFormData(){ // atualizar form ao abrir edição de veiculo cadastrado
			if(entity == null) {
				throw new IllegalStateException("Entity new null");
			}
			
			txtId.setText(String.valueOf(entity.getId()));
			txtDescricao.setText(entity.getDescricao());
			txtModelo.setText(entity.getModelo().getDescricao());
			txtMarca.setText(entity.getModelo().getMarca().getDescricao());
			txtAno.setText(entity.getAno());
			txtKmRodado.setText(String.valueOf(entity.getKmRodado()));
			txtPlaca.setText(entity.getPlaca());
			txtChassi.setText(entity.getChassi());
			txtRenavam.setText(entity.getRenavam());	
		}
}
