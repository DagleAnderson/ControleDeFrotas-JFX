package gui;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DBException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.MaskFieldUtils;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.Endereco;
import model.entities.Motorista;
import model.exceptions.ValidationException;
import model.services.MotoristaService;

public class MotoristaFormController implements Initializable{
	
	private Motorista entity;
	private Endereco entityEndereco;
	
	private MotoristaService service;
	
	//Referencias de Campos Motoristas
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtSobrenome;
	@FXML
	private DatePicker dpDataNasc;
	@FXML
	private TextField txtCpf;
	@FXML
	private TextField txtCnh;
	@FXML
	private TextField txtTelefone;
	@FXML
	private TextField txtEmail;
	
	//Referencias de Campos Endereco
	@FXML
	private TextField txtCidade;
	@FXML
	private TextField txtUf;
	@FXML
	private TextField txtBairro;
	@FXML
	private TextField txtRua;
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtCep;
	@FXML
	private TextField txtComplemento;

	
	//buttons
	@FXML
	private Button btnGravar;
	@FXML
	private Button btnCancelar;
	
	
//injeção de dependecia
	public void setMotorista(Motorista motor) {
		this.entity = motor;
		this.entityEndereco = motor.getEndereco();
	}
	
	public void setMotoristaService(MotoristaService service) {
		this.service = service;
	}

//*********************
	
	@FXML
	public void onBtnGravarAction(ActionEvent event){
		try {
			if(this.entity == null){
				throw new IllegalArgumentException("Entity was null");
			}
			if(this.service == null){
				throw new IllegalArgumentException("Service was null");
			}
			
		     this.entityEndereco = getFormDataEnd();
			 this.entity= getFormDataMotor(entityEndereco);


			 this.service.saveOrUpdate(entity);
		}catch(ValidationException e){
			this.setErroMenssage(e.getErrors());
			Alerts.showAlert("Alerta", "Campos obrigatórios não informados", e.getMessage(), AlertType.ERROR);
		}catch(DBException e) {
			Alerts.showAlert("Alerta", "Erro ao gravar dados de Modelo", e.getMessage(), AlertType.ERROR);
		}
	}
	

	@FXML
	public void onBtnCancelarAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}


	private Motorista getFormDataMotor(Endereco endereco) {
		
	  ValidationException exception = new ValidationException("validation error");
		
		Motorista obj = new Motorista();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		if(txtNome.getText() == null || txtNome.getText().trim().equals("")){exception.addError("nome","");};
			obj.setNome(txtNome.getText());
		
		if(txtSobrenome.getText()==null || txtSobrenome.getText().trim().equals("")){exception.addError("sobrenome","");};
			obj.setSobreNome(txtSobrenome.getText());
		
			//particularidade datePicker para pegar valor do campo
			if( dpDataNasc.getValue()  == null) {
				obj.setDataNasc(null);
			}else{
				Instant instant = Instant.from(dpDataNasc.getValue().atStartOfDay(ZoneId.systemDefault()));
			    Date dataNasc = Date.from(instant);
			    obj.setDataNasc(dataNasc);
			}
		
		if(txtCpf.getText() == null || txtCpf.getText().trim().equals("")) {exception.addError("cpf","");};
			obj.setCpf(txtCpf.getText());
		obj.setCnh(txtCnh.getText());
		obj.setTelefone(txtTelefone.getText());
		obj.setEmail(txtEmail.getText());
		obj.setEndereco(endereco);
		if(exception.getErrors().size() > 0){
			throw exception;
		}
			
		return obj;
	}
	
	private Endereco getFormDataEnd() {
			Endereco end = new Endereco();				
			if(txtCidade.getText() != null) {
				end.setCidade(txtCidade.getText());
				
			}else {end.setCidade(" ");}
			
			if(txtUf.getText() != null) {
				end.setUf(txtUf.getText());
			}else {end.setUf(" ");}
			
			if(txtBairro.getText() != null) {
				end.setBairro(txtBairro.getText());
			}else {end.setBairro(" ");}
			
			if(txtRua.getText() != null) {
				end.setRua(txtRua.getText());
			}else {end.setRua(" ");}
			if(txtCidade.getText() != null) {
				end.setNumero(txtNumero.getText());
			}else {end.setCidade(" ");}
			
			if(txtCep.getText() != null) {
				end.setCep(txtCep.getText());
			}else {end.setCep(" ");}
			
			if(txtComplemento.getText() != null) {
				end.setComplemento(txtComplemento.getText());
			}else {end.setComplemento(" ");}	
				
	return end;
}


	
	private void setErroMenssage(Map<String,String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("nome")){
			txtNome.setStyle("-fx-border-color:#f00");
		}
		if(fields.contains("sobrenome")){
			txtSobrenome.setStyle("-fx-border-color:#f00");
		}
		if(fields.contains("cpf")){
			txtCpf.setStyle("-fx-border-color:#f00");
		}
		
	}
	

	public void updateFormData() {
		txtId.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getNome());
		txtSobrenome.setText(entity.getSobreNome());
		if(entity.getDataNasc() != null) {
			dpDataNasc.setValue(LocalDate.ofInstant(entity.getDataNasc().toInstant(), ZoneId.systemDefault()));
		}else {
			entity.setDataNasc(null);
		}
		txtCpf.setText(entity.getCpf());
		txtCnh.setText(entity.getCnh());
		txtTelefone.setText(entity.getTelefone());
		txtEmail.setText(entity.getEmail());	
		
		//Endereco
		txtCidade.setText(entity.getEndereco().getCidade());
		txtUf.setText(entity.getEndereco().getUf());
		txtBairro.setText(entity.getEndereco().getBairro());
		txtRua.setText(entity.getEndereco().getRua());
		txtNumero.setText(entity.getEndereco().getNumero());
		txtCep.setText(entity.getEndereco().getCep());
		txtComplemento.setText(entity.getEndereco().getComplemento());
	}
		
	public void MaskInitialize(){
		//MaskFieldUtils.dateField((TextField) dpDataNasc);
		MaskFieldUtils.cpfField(txtCpf);
		MaskFieldUtils.foneField(txtTelefone);
		//MaskFieldUtils.cepField(txtCep);
			
	}
	
	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtNome, 15);
		Constraints.setTextFieldMaxLength(txtSobrenome, 20);
		Utils.formatDatePicker(dpDataNasc, "dd/MM/yyyy");
		
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.MaskInitialize();
		this.initializeNodes();
		
	}

	
	
	
	
}
