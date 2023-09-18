package sample;

import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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


    ObservableList<Customer> customerList = DBCustomers.getAllCountries();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        /*
        //Option 1: add one object at a time
        Fruit apple = new Fruit("apple", 10, now.plusDays(1));
        fruitTable.getItems().add(apple);
        Fruit banana = new Fruit("banana", 20, now.plusWeeks(1));
        fruitTable.getItems().add(banana);

        //Option 2: add an entire list at once
        fruitList.add(apple);
        fruitList.add(banana);
        fruitTable.getItems().addAll(fruitList);

         */

    }

    public void viewAppointment(ActionEvent event){
        DBUtils.changeScene(event, "view_appointments.fxml", "See Appointments", null, null);
    }


}
