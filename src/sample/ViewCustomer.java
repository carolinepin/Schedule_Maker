package sample;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import DBAccess.DBCountriesAndFD;
import DBAccess.DBCustomers;
import Models.Country;
import Models.Customer;
import Models.FL_Divisions;
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

import javax.swing.plaf.nimbus.State;

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
    private TableColumn<Customer, Integer> StateProvince;

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
    private Button confirmButton;

    @FXML
    private Button confirmButton1;

    @FXML
    private Text displayID;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox<String> fdCB;

    @FXML
    private ComboBox<String> countCB;

    @FXML
    private Label divIDLabel;

    private int index;
    private String username;



    ObservableList<Customer> customerList = DBCustomers.getAllCustomers();
    ObservableList<String> countryList = DBCountriesAndFD.getAllCountries();
    ObservableList<String> divisionList = DBCountriesAndFD.getAllFL_Divisions("UK");




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addButton.setDisable(true);

        deleteButton.setDisable(true);
        updateButton.setDisable(true);
        fdCB.setDisable(true);
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
        StateProvince.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getDivisionID());
        });

        customerTable.getItems().addAll(customerList);
        countCB.setItems(countryList);
        fdCB.setItems(divisionList);


        LocalDateTime now = LocalDateTime.now();


    }
    @FXML
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
        divIDLabel.setText(StateProvince.getCellData(index).toString());

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
        countCB.setValue(null);
        fdCB.setValue(null);
        fdCB.setDisable(true);
        divIDLabel.setText("N/A");

        addButton.setDisable(true);
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    @FXML
    void updateCustomer(ActionEvent event) throws SQLException {
        int division = DBCountriesAndFD.getDivID(fdCB.getValue().toString());
        int ra = DBCustomers.update(custID.getCellData(index), nameField.getText(),
                adrField.getText(), postField.getText(), phoneField.getText(), division);
        System.out.println("Updated these many lines" + ra);

        customerList = DBCustomers.getAllCustomers();
        customerTable.getItems().clear();
        customerTable.getItems().addAll(customerList);


    }
    @FXML
    void addCustomer(ActionEvent event) throws SQLException {
        int division = 0;
        if (fdCB.getValue() != null)
            division = DBCountriesAndFD.getDivID(fdCB.getValue().toString());
        //System.out.println("THE DIVISION YOU ENTERED IS THIS:  -----  " + division);

        int ra = DBCustomers.add(nameField.getText(),
                adrField.getText(), postField.getText(), phoneField.getText(), division);
        System.out.println("Updated these many lines" + ra);

        customerList = DBCustomers.getAllCustomers();
        customerTable.getItems().clear();
        customerTable.getItems().addAll(customerList);


    }
    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException {
        int ra = DBCustomers.delete(custID.getCellData(index));
        System.out.println("Updated these many lines" + ra);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Customer " + nameField.getText() + " was successfully deleted.");
        //just does everything clearSelection() does, but I couldn't figure out how to call it without an action
        nameField.clear();
        adrField.clear();
        postField.clear();
        phoneField.clear();
        //countCB.setValue(null);
        //fdCB.setValue(null);
        //fdCB.setDisable(true);
        displayID.setText("Unassigned");
        divIDLabel.setText("N/A");

        addButton.setDisable(false);
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
        //end clearSelection()

        customerList = DBCustomers.getAllCustomers();
        customerTable.getItems().clear();
        customerTable.getItems().addAll(customerList);


        alert.show();


    }
    @FXML
    public void setDivLabel(ActionEvent event) throws SQLException {
        int division = DBCountriesAndFD.getDivID(fdCB.getValue().toString());
        String stringDiv = Integer.toString(division);
        divIDLabel.setText(stringDiv);
        addButton.setDisable(false);

    }

    @FXML
    public void divisionSelection(ActionEvent event){
        //this method would not activate on the division combo box for some reason
        //I needed to add a "confirm" button to update the div drop down menu
        //I suspect that it is because there is a race condition, where the drop down is displayed before this runs?
        //Maybe something about it fdCB altering itself doesn't work (because originally I tried to have fdCB call itself
        fdCB.setDisable(false);
        ObservableList<String> divisionList = DBCountriesAndFD.getAllFL_Divisions(countCB.getValue().toString());
        fdCB.setItems(divisionList);


    }


}
