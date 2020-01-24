package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemMotorista;
	@FXML
	private MenuItem menuItemVeiculo;
	@FXML
	private MenuItem menuItemMarca;
	
	@FXML
	private MenuItem menuItemGViagens;
	
	@FXML
	private MenuItem menuItemSobre;
	
	
	@FXML
	public void onMenuItemMotoristaAction() {
		System.out.println("Motorista");
	}
	
	@FXML
	public void onMenuItemVeiculoAction() {
		System.out.println("Veículo");
	}
	
	
	@FXML
	public void onMenuItemMarcaAction() {
		System.out.println("Marca");
	}
	
	@FXML
	public void onMenuItemViagensAction() {
		System.out.println("Viagens");
	}
	
	@FXML
	public void onMenuItemSobreAction() {
		System.out.println("Sobre");
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

}
