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
import model.dto.OrderDetailsInfoDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class OrderDetailsInfoFormController implements Initializable {
    ObservableList<OrderDetailsInfoDTO> orderDetailsInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderID;

    @FXML
    private TableColumn<?, ?> colOrderQty;

    @FXML
    private TableView<OrderDetailsInfoDTO> tblOrderDetailsInfo;

    @FXML
    private TextField txtDiscount;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtOrderID;

    @FXML
    private TextField txtOrderQty;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderID"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        tblOrderDetailsInfo.setItems(orderDetailsInfoDTOS);

        tblOrderDetailsInfo.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                txtOrderID.setText(newValue.getOrderID());
                txtItemCode.setText(newValue.getItemCode());
                txtOrderQty.setText(String.valueOf(newValue.getOrderQty()));
                txtDiscount.setText(String.valueOf(newValue.getDiscount()));
            }
        });

        try {
            loadTableOrderDetails();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void loadTableOrderDetails() throws SQLException {
        orderDetailsInfoDTOS.clear();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","1234");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orderdetail");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                orderDetailsInfoDTOS.add(new OrderDetailsInfoDTO(
                        resultSet.getString("OrderID"),
                        resultSet.getString("ItemCode"),
                        resultSet.getInt("Discount"),
                        resultSet.getInt("OrderQty")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String orderID = txtOrderID.getText();
        String itemCode = txtItemCode.getText();
        Integer orderQty = Integer.valueOf(txtOrderQty.getText());
        Integer discount = Integer.valueOf(txtDiscount.getText());

        OrderDetailsInfoDTO orderDetailsInfoDTO = new OrderDetailsInfoDTO(orderID,itemCode,orderQty,discount);

        orderDetailsInfoDTOS.add(orderDetailsInfoDTO);

        clearFiled();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        OrderDetailsInfoDTO aeOrderDetailsInfoDTO = tblOrderDetailsInfo.getSelectionModel().getSelectedItem();
        orderDetailsInfoDTOS.remove(aeOrderDetailsInfoDTO);

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) throws SQLException {

        loadTableOrderDetails();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        OrderDetailsInfoDTO selectItem = tblOrderDetailsInfo.getSelectionModel().getSelectedItem();

        selectItem.setOrderID(txtOrderID.getText());
        selectItem.setItemCode(txtItemCode.getText());
        selectItem.setOrderQty(Integer.valueOf(txtOrderQty.getText()));
        selectItem.setDiscount(Integer.valueOf(txtDiscount.getText()));

        tblOrderDetailsInfo.refresh();

        clearFiled();

    }

    public void clearFiled(){
        txtOrderID.clear();
        txtItemCode.clear();
        txtOrderQty.clear();
        txtDiscount.clear();
    }


}
