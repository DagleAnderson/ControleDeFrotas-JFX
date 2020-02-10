package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import gui.util.Constraints;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.entities.Modelo;
import model.entities.Veiculo;

public class VeiculoFormController implements Initializable {
	
// Injeções de Dependência
	
	private Veiculo entity;
	
	//private VeiculoService service;
	
// Injeções de Dependência	
	
		@FXML
		private TextField txtId;
		@FXML
		private TextField txtDescricao;
		@FXML
		private TextField txtAno;
		@FXML
		private ComboBox<Modelo> comboModelo;
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
		private Button btnAddModelo;
		@FXML
		private Button btnGravar;
		@FXML
		private Button btnCancelar;
		
		private ObservableList<Modelo> obsListMedelo;
	
		
//métodos de injeção

		public void setVeiculo(Veiculo veic) {
			this.entity = veic;
		}

		/*public void setVeiculoService(VeiculoService servico) {
			this.service = servico;
		}*/
		
//métodos de injeção
		
		@FXML
		public void onBtnAddModelo(){
			System.out.println("Add modelo");
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
			this.initicalizeNodes();
		}
		
		//Controlar tipo e quantidade dos nodes(campos)
		public void initicalizeNodes() {
			
			//regras de negócio da classe Constraints de gui/utils
			Constraints.setTextFieldInteger(txtId);
			Constraints.setTextFieldMaxLength(txtDescricao, 30);
			Constraints.setTextFieldMaxLength(txtMarca, 25);
			//Constraints.setTextFieldMaxLength(comboModelo, 25);
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
			//comboModelo.setItems();
			txtMarca.setText(entity.getModelo().getMarca().getDescricao());
			txtAno.setText(entity.getAno());
			txtKmRodado.setText(String.valueOf(entity.getKmRodado()));
			txtPlaca.setText(entity.getPlaca());
			txtChassi.setText(entity.getChassi());
			txtRenavam.setText(entity.getRenavam());	
		}
}
