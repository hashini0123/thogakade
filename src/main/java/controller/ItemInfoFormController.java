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
import model.dto.ItemInfoDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ItemInfoFormController implements Initializable {

    ObservableList<ItemInfoDTO> itemInfoDTOS = FXCollections.observableArrayList();

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableView<ItemInfoDTO> tblItemInfo;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtPackSize;

    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TableColumn<?, ?> txtUnitPrice1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblItemInfo.setItems(itemInfoDTOS);

        tblItemInfo.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {

            if(newValue != null) {
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(newValue.getDescription());
                txtPackSize.setText(newValue.getPackSize());
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtQtyOnHand.setText(String.valueOf(newValue.getQtyOnHand()));

            }

        }));

        try {
            loadTableItem();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void loadTableItem() throws SQLException {
        itemInfoDTOS.clear();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "1234");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            itemInfoDTOS.add(new ItemInfoDTO(
                    resultSet.getString("ItemCode"),
                    resultSet.getString("Description"),
                    resultSet.getString("PackSize"),
                    resultSet.getDouble("UnitPrice"),
                    resultSet.getInt("QtyOnHand")
            ));
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {

        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        Double unitPrice = Double.valueOf(txtUnitPrice.getText());
        Integer qtyOnHand = Integer.valueOf(txtQtyOnHand.getText());

        ItemInfoDTO itemInfoDTO = new ItemInfoDTO(itemCode, description, packSize, unitPrice, qtyOnHand);

        itemInfoDTOS.add(itemInfoDTO);

        clearField();


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        ItemInfoDTO reItemInfoDTO = tblItemInfo.getSelectionModel().getSelectedItem();

        itemInfoDTOS.remove(reItemInfoDTO);

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) throws SQLException {

        loadTableItem();


    }

    @FXML
    void btnUpadateOnAction(ActionEvent event) {

        ItemInfoDTO selectItem = tblItemInfo.getSelectionModel().getSelectedItem();

        selectItem.setItemCode(txtItemCode.getText());
        selectItem.setDescription(txtDescription.getText());
        selectItem.setPackSize(txtPackSize.getText());
        selectItem.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
        selectItem.setQtyOnHand(Integer.parseInt(txtQtyOnHand.getText()));

        tblItemInfo.refresh();

        clearField();


    }

    public void clearField(){
        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
    }


}


