package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.CustomerInfoDTO;

import java.io.IOException;

public class CustomerInfoFormController {

    ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList(

            new CustomerInfoDTO ("C001", "Dialy","Red row", 190.0),
            new CustomerInfoDTO ("C002", "Baby Item","Saop", 250.0),
            new CustomerInfoDTO ("C003", "Spaicy","Red Chilli", 300.0),
            new CustomerInfoDTO ("C004", "Dialy","Snack", 100.0)


    );


    Stage stage = new Stage();

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<CustomerInfoDTO> tblCustomerInfo;

    @FXML
    void btnReloadOnAction(ActionEvent event) throws IOException {

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tblCustomerInfo.setItems(customerInfoDTOS);



        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(""))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }

}

