package gui;

import java.net.URL;
import java.util.ResourceBundle;


import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.entities.Marca;
import model.entities.Modelo;
import model.entities.Veiculo;
import model.services.VeiculoService;

public class VeiculoFormController implements Initializable {
	
// Inje��es de Depend�ncia
	
	private Veiculo entity;
	
	private VeiculoService service;
	
// Inje��es de Depend�ncia	
	
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
	
		
//m�todos de inje��o
		
		//recebe obj do parent pane
		public void setVeiculo(Veiculo veic) {
			this.entity = veic;
		}

		//recebe service do parent pane
		public void setVeiculoService(VeiculoService servico) {
			this.service = servico;
		}
		
//m�todos de inje��o
		
		@FXML
		public void onBtnAddModelo(){
			System.out.println("Add modelo");
		}
		
		@FXML
		public void onBtnGravarAction() {
			entity  = getFormData();
			service.saveOrUpdate(entity);
		}
		
		//pega dados do Form 
		private Veiculo getFormData() {
			Veiculo obj = new Veiculo();
			obj.setId(Utils.tryParseToInt(txtId.getText()));
			obj.setDescricao(txtDescricao.getText());
			obj.setModelo(new Modelo(1,"Truck",new Marca(1,"Wolksvagem"))/*a��o tempor�ria*/);
			obj.setAno(txtAno.getText());
			obj.setChassi(txtChassi.getText());
			obj.setKmRodado(Utils.tryParseToDouble(txtKmRodado.getText()));
			obj.setRenavam(txtRenavam.getText());
			obj.setPlaca(txtPlaca.getText());
			
			return obj;
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
			
			//regras de neg�cio da classe Constraints de gui/utils
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
		
		
		public void updateFormData(){ // atualizar form ao abrir edi��o de veiculo cadastrado
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
