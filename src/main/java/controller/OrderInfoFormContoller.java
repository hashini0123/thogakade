package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.dto.OrderInfoDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.String.*;

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

        tblOrderInfo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{

            if(newValue != null){
                txtOrderID.setText(newValue.getOrderID());
                txtOrderDate.setText(String.valueOf(newValue.getOrderDate()));
                txtCustID.setText(newValue.getCustID());

            }
                });

        try {
            loadTableOrder();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTableOrder() throws SQLException {
        orderInfoDTOS.clear();

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orderdetail");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                orderInfoDTOS.add(new OrderInfoDTO(
                        resultSet.getString("Order ID"),
                        resultSet.getDate("OrderDate"),
                        resultSet.getString("CustID")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}

