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
import model.dto.CustomerInfoDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerInfoFormController implements Initializable {
    CustomerController customerController = new CustomerController();

    ObservableList<CustomerInfoDTO> customerInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustAddress;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colCustName;

    @FXML
    private TableColumn<?, ?> colCustTitle;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<CustomerInfoDTO> tblCustomerInfo;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCustAdderss;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtCustName;

    @FXML
    private TextField txtCustTitle;

    @FXML
    private TextField txtDOB;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        tblCustomerInfo.setItems(customerInfoDTOS);

        tblCustomerInfo.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {

            if(newValue != null){
                txtCustId.setText(newValue.getCustId());
                txtCustTitle.setText(newValue.getCustTitle());
                txtCustName.setText(newValue.getCustName());
                txtDOB.setText(String.valueOf(newValue.getDOB()));
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtCustAdderss.setText(newValue.getCustAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());

            }
        }));

        loadTable();
    }

    private void loadTable() {
        customerController.getAllCustomers();
        customerInfoDTOS.clear();

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        tblCustomerInfo.setItems(customerController.getAllCustomers() );
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String custId = txtCustId.getText();
        String custTitle = txtCustTitle.getText();
        String custName = txtCustName.getText();
        Date DOB = Date.valueOf(txtDOB.getText());
        double salary = Double.parseDouble(txtSalary.getText());
        String custAddress = txtCustAdderss.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalcode = txtPostalCode.getText();

        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO(custId, custTitle, custName, DOB,salary, custAddress, city,province,postalcode  );

        customerInfoDTOS.add(customerInfoDTO);

        clearField();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        CustomerInfoDTO seCustomerInfoDTO = tblCustomerInfo.getSelectionModel().getSelectedItem();
        customerInfoDTOS.remove(seCustomerInfoDTO);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CustomerInfoDTO selectItem = tblCustomerInfo.getSelectionModel().getSelectedItem();

        selectItem.setCustId(txtCustId.getText());
        selectItem.setCustTitle(txtCustTitle.getText());
        selectItem.setCustName(txtCustName.getText());
        selectItem.setDOB(Date.valueOf(txtDOB.getText()));
        selectItem.setSalary(Double.parseDouble(txtSalary.getText()));
        selectItem.setCity(txtCity.getText());
        selectItem.setProvince(txtProvince.getText());
        selectItem.setPostalCode(txtPostalCode.getText());

        tblCustomerInfo.refresh();

        clearField();

    }

    public void clearField(){
        txtCustId.clear();
        txtCustTitle.clear();
        txtCustName.clear();
        txtDOB.clear();
        txtSalary.clear();
        txtCustAdderss.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
    }
}