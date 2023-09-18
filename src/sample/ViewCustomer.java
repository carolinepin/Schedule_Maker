package sample;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import DBAccess.DBCustomers;
import Models.Customer;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ViewCustomer implements Initializable {

    @FXML
    private Button appointButton;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, Integer> custID;

    @FXML
    private TableColumn<Customer, String> Name;

    @FXML
    private TableColumn<Customer, String> Address;

    @FXML
    private TableColumn<Customer, String> Postal_Code;

    @FXML
    private TableColumn<Customer, String> Phone;


    @FXML
    private TableColumn<Customer, LocalDateTime> CreatedDate;

    @FXML
    private TableColumn<Customer, String> CreatedBy;

    @FXML
    private TableColumn<Customer, LocalDateTime> UpdatedDate;

    @FXML
    private TableColumn<Customer, String> UpdatedBy;

    @FXML
    private Button addButton;

    @FXML
    private TextField adrField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField postField;

    @FXML
    private Button reportButton;

    @FXML
    private Button updateButton;

    @FXML
    private Text displayID;

    @FXML
    private Button deleteButton;

    private int index;






    ObservableList<Customer> customerList = DBCustomers.getAllCustomers();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        deleteButton.setDisable(true);
        updateButton.setDisable(true);
        Name.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getName());
        });
        custID.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerId());
        });
        Address.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getAddress());
        });
        Postal_Code.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getZip());
        });

        Phone.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getPhone());
        });

        CreatedDate.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCT());
        });
        CreatedBy.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCreatedBy());
        });

        UpdatedDate.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getUT());
        });
        UpdatedBy.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getUpdatedBy());
        });
        customerTable.getItems().addAll(customerList);

        LocalDateTime now = LocalDateTime.now();


    }

    public void viewAppointment(ActionEvent event){
        DBUtils.changeScene(event, "view_appointments.fxml", "See Appointments", null, null);
    }

    @FXML
    void getItem(MouseEvent event) {

        index = customerTable.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }

        nameField.setText(Name.getCellData(index).toString());
        adrField.setText(Address.getCellData(index).toString());
        postField.setText(Postal_Code.getCellData(index).toString());
        phoneField.setText(Phone.getCellData(index).toString());
        displayID.setText(custID.getCellData(index).toString());

        updateButton.setDisable(false);
        deleteButton.setDisable(false);
        addButton.setDisable(true);


    }


    @FXML
    void clearSelection(ActionEvent event){
        nameField.clear();
        adrField.clear();
        postField.clear();
        phoneField.clear();
        displayID.setText("Unassigned");

        addButton.setDisable(false);
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    @FXML
    void updateCustomer(ActionEvent event) throws SQLException {
        int ra = DBCustomers.update(custID.getCellData(index), nameField.getText(),
                adrField.getText(), postField.getText(), phoneField.getText());
        System.out.println("Updated these many lines" + ra);

        customerList = DBCustomers.getAllCustomers();
        customerTable.getItems().clear();
        customerTable.getItems().addAll(customerList);


    }


}
