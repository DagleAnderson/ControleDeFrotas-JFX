package gui;

import java.net.URL;
import java.time.Instant;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Motorista;
import model.exceptions.ValidationException;
import model.services.MotoristaService;

public class MotoristaFormController implements Initializable{
	
	private Motorista entity;
	
	private MotoristaService service;
	
	//campos
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


	
	//buttons
	@FXML
	private Button btnGravar;
	@FXML
	private Button btnCancelar;
	
	
//injeção de dependecia
	public void setMotorista(Motorista motor) {
		this.entity = motor;
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
			 this.entity= getFormDataMotor();
			//Endereco objEnd = getFormDataEnd(objMotor);
			 this.service.saveOrUpdate(entity);
		}catch(ValidationException e){
			this.setErroMenssage(e.getErrors());
			Alerts.showAlert("Alerta", "Campos obrigatórios não informados", e.getMessage(), AlertType.ERROR);
		}catch(DBException e) {
			Alerts.showAlert("Alerta", "Erro ao Salvar Novo Modelo", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private Motorista getFormDataMotor() {
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
				obj.setDataNasc(Date.from(instant));
			}
		
		if(txtCpf.getText() == null || txtCpf.getText().trim().equals("")) {exception.addError("cpf","");};
			obj.setCpf(txtCpf.getText());
			obj.setCnh(txtCnh.getText());
			obj.setTelefone(txtTelefone.getText());
		obj.setEmail(txtEmail.getText());
		
		if(exception.getErrors().size() > 0){
			throw exception;
		}
			
		return obj;
	}

	@FXML
	public void onBtnCancelarAction(ActionEvent event) {
		
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
