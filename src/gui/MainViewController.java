package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.ModeloService;
import model.services.VeiculoService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemMotorista;
	@FXML
	private MenuItem menuItemVeiculo;
	@FXML
	private MenuItem menuItemModelo;
	
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
		LoadView("/gui/VeiculoList.fxml",(VeiculoListController controller)->{
			controller.setVeiculoService(new VeiculoService());
			controller.updateTableView();
		});
	}
	
	
	@FXML
	public void onMenuItemModeloAction() {
		LoadView("/gui/ModeloList.fxml",(ModeloListController controller)->{
			controller.setModeloService(new ModeloService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemViagensAction() {
		System.out.println("Viagens");
	}
	
	@FXML
	public void onMenuItemSobreAction() {
		LoadView("/gui/Sobre.fxml",f->{});
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	//Carregar nova Tela
	private <T>void LoadView(String absoluteName,Consumer<T> initializingAction ) {
		
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		VBox newVBox = loader.load();
		
		Scene scene = Main.getScene();
		VBox mainVbox = (VBox)((ScrollPane) scene.getRoot()).getContent();
		
	    Node mainMenu = mainVbox.getChildren().get(0);
	    mainVbox.getChildren().clear();
	    mainVbox.getChildren().add(mainMenu);
	    mainVbox.getChildren().addAll(newVBox.getChildren());
	    
	    T controller = loader.getController();
		initializingAction.accept(controller);
		
		}catch(IOException e) {
			Alerts.showAlert("IOException","Erro ao carregar View",e.getMessage(), AlertType.ERROR);
		}
		
	}

}
