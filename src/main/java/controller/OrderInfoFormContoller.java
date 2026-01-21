package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.OrderInfoDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderInfoFormContoller implements Initializable {

    ObservableList<OrderInfoDTO> orderInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colCustID;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableView<OrderInfoDTO> tblOrderInfo;

    @FXML
    private TextField txtCustID;

    @FXML
    private TextField txtOrderDate;

    @FXML
    private TextField txtOrderID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustID.setCellValueFactory(new PropertyValueFactory<>("custID"));

        tblOrderInfo.setItems(orderInfoDTOS);
    }

    @FXML
    void BtnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}

