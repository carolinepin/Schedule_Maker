package sample;

import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.ResourceBundle;

import DBAccess.*;
import Models.Appointment;
import Models.Contact;
import helper.timeZoneTranslator;
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

public class ViewAppointments implements Initializable {



    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, Integer> AppointmentID;

    @FXML
    private TableColumn<Appointment, String> Title;

    @FXML
    private TableColumn<Appointment, String> Description;

    @FXML
    private TableColumn<Appointment, String> Location;

    @FXML
    private TableColumn<Appointment, String> Type;

    @FXML
    private TableColumn<Appointment, LocalDateTime> Start;

    @FXML
    private TableColumn<Appointment, LocalDateTime> End;


    @FXML
    private TableColumn<Appointment, LocalDateTime> CreatedDate;

    @FXML
    private TableColumn<Appointment, String> CreatedBy;

    @FXML
    private TableColumn<Appointment, LocalDateTime> UpdatedDate;

    @FXML
    private TableColumn<Appointment, String> UpdatedBy;

    @FXML
    private TableColumn<Appointment, Integer> UserID;
    @FXML
    private TableColumn<Appointment, Integer> CustomerID;
    @FXML
    private TableColumn<Appointment, Integer> ContactID;

    @FXML
    private Button addButton;

    @FXML
    private Label appIDTB;


    @FXML
    private Button clearButton;

    @FXML
    private ComboBox<String> contactCB;

    @FXML
    private ComboBox<String> customerCB;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField descTB;

    @FXML
    private ComboBox<String> endCB;

    @FXML
    private TextField locTB;

    @FXML
    private ComboBox<String> startCB;

    @FXML
    private TextField titleTB;

    @FXML
    private TextField typeTB;

    @FXML
    private Button updateButton;

    @FXML
    private ComboBox<String> userCB;

    private int index;


    ObservableList<Appointment> appointmentList = DBAppointments.getAllAppointments();
    ObservableList<String> contactList = DBContacts.getAllContactIDs();
    ObservableList<String> userList = DBUsers.getAllUserIDs();
    ObservableList<String> customerList = DBCustomers.getAllCustomerIDs();

    public static ObservableList<String> getTimes(){
        ObservableList<String> allTimes = FXCollections.observableArrayList();
        ZoneId businessZone = ZoneId.of("America/New_York");
        LocalTime businessStart = LocalTime.of(8, 00, 00);
        LocalTime businessEnd = LocalTime.of(22,00,00);
        LocalDate businessDay = LocalDate.now();
        LocalDateTime businessStartDay = LocalDateTime.of(businessDay,businessStart);
        LocalDateTime businessEndDay = LocalDateTime.of(businessDay,businessEnd);
        ZonedDateTime businessStartZoned = ZonedDateTime.of(businessStartDay, businessZone);
        ZonedDateTime businessEndZoned = ZonedDateTime.of(businessEndDay, businessZone);
        ZonedDateTime businessStartZoneTranslated = ZonedDateTime.ofInstant(businessStartZoned.toInstant(), ZoneId.systemDefault());
        ZonedDateTime businessEndZoneTranslated = ZonedDateTime.ofInstant(businessEndZoned.toInstant(), ZoneId.systemDefault());
        int startHour = businessStartZoneTranslated.getHour();
        int endHour = businessEndZoneTranslated.getHour();
        for (int i = startHour; i <= endHour; i++){
            String time1 = Integer.toString(i) + ":00";
            String time2 = Integer.toString(i) + ":30";
            allTimes.add(time1);
            System.out.println(time1);
            allTimes.add(time2);
            System.out.println(time2);
            startHour++;

        }
        return allTimes;

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> timeList = getTimes();
        addButton.setDisable(true);
        deleteButton.setDisable(true);
        updateButton.setDisable(true);


        Title.setCellValueFactory(cellData -> {
            return new ReadOnlyStringWrapper(cellData.getValue().getTitle());
        });
        AppointmentID.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getAppointmentID());
        });
        Description.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getDescription());
        });
        Location.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getLocation());
        });

        Type.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getType());
        });

        Start.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getStart());
        });

        End.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getEnd());
        });

        CreatedDate.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCreatedate());
        });
        CreatedBy.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCreatedby());
        });

        UpdatedDate.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getUpdateddate());
        });
        UpdatedBy.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getUpdatedby());
        });

        CustomerID.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getCustomerID());
        });
        UserID.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getUserID());
        });
        ContactID.setCellValueFactory(cellData -> {
            return new ReadOnlyObjectWrapper(cellData.getValue().getContactID());
        });


        appointmentTable.getItems().addAll(appointmentList);
        contactCB.setItems(contactList);
        userCB.setItems(userList);
        customerCB.setItems(customerList);
        startCB.setItems(timeList);
        endCB.setItems(timeList);

        LocalDateTime now = LocalDateTime.now();
    }

    @FXML
    public void viewCustomers(ActionEvent event){
        DBUtils.changeScene(event, "view_customers.fxml", "See Customers", null, null);
    }


    @FXML
    void getItem(MouseEvent event) {

        index = appointmentTable.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }

        titleTB.setText(Title.getCellData(index).toString());
        descTB.setText(Description.getCellData(index).toString());
        locTB.setText(Location.getCellData(index).toString());
        typeTB.setText(Type.getCellData(index).toString());
        datePicker.setValue(Start.getCellData(index).toLocalDate());
        customerCB.setValue(Integer.toString(CustomerID.getCellData(index)));
        userCB.setValue(Integer.toString(UserID.getCellData(index)));
        contactCB.setValue(Integer.toString(ContactID.getCellData(index)));

        startCB.setValue(Integer.toString(Start.getCellData(index).getHour())+":"+String.format("%02d", Start.getCellData(index).getMinute()));
        endCB.setValue(Integer.toString(End.getCellData(index).getHour())+":"+String.format("%02d",End.getCellData(index).getMinute()));

        appIDTB.setText(Integer.toString(AppointmentID.getCellData(index)));

        updateButton.setDisable(false);
        deleteButton.setDisable(false);
        addButton.setDisable(true);


    }


    @FXML
    void clearSelection(ActionEvent event){
        titleTB.clear();
        descTB.clear();
        locTB.clear();
        typeTB.clear();
        datePicker.setValue(null);
        customerCB.setValue(null);
        userCB.setValue(null);
        contactCB.setValue(null);
        appIDTB.setText("Unassigned");
        startCB.setValue(null);
        endCB.setValue(null);

        addButton.setDisable(true);
        updateButton.setDisable(true);
        deleteButton.setDisable(true);
    }


    @FXML
    void updateApp(ActionEvent event) throws SQLException {
        String[] startStrings=startCB.getValue().split(":");
        LocalTime startTime = LocalTime.of(Integer.parseInt(startStrings[0]),Integer.parseInt(startStrings[1]),00);
        String[] endStrings=endCB.getValue().split(":");
        LocalTime endTime = LocalTime.of(Integer.parseInt(endStrings[0]),Integer.parseInt(endStrings[1]),00);
        LocalDate myLD = datePicker.getValue();

        LocalDateTime StartTimeDate = LocalDateTime.of(myLD,startTime);
        LocalDateTime EndTimeDate = LocalDateTime.of(myLD,endTime);

        int timeValid = timeZoneTranslator.timeCheck(StartTimeDate, EndTimeDate);
        switch (timeValid) {
            case 1:

                int ra = DBAppointments.update(AppointmentID.getCellData(index), titleTB.getText(),
                    descTB.getText(), locTB.getText(), typeTB.getText(), StartTimeDate, EndTimeDate, Integer.parseInt(customerCB.getValue()),
                    Integer.parseInt(userCB.getValue()), Integer.parseInt(contactCB.getValue()));

                System.out.println("Updated these many lines" + ra);

                appointmentList = DBAppointments.getAllAppointments();
                appointmentTable.getItems().clear();
                appointmentTable.getItems().addAll(appointmentList);
                break;
            case 0:
                Alert invalid = new Alert(Alert.AlertType.ERROR);
                invalid.setContentText("Invalid Time Entry: start time must be before end time");
                invalid.show();
                break;
            case 3:
                Alert overlap = new Alert(Alert.AlertType.ERROR);
                overlap.setContentText("Invalid Time Entry: this appointment overlaps another, please try a different time");
                overlap.show();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + timeValid);
        }



    }
    //--------------------------------NEED TO EDIT-------------------------------------------------
/*
    @FXML
    void addCustomer(ActionEvent event) throws SQLException {
        String[] startStrings=startCB.getValue().split(":");
        LocalTime startTime = LocalTime.of(Integer.parseInt(startStrings[0]),Integer.parseInt(startStrings[1]),00);
        String[] endStrings=endCB.getValue().split(":");
        LocalTime endTime = LocalTime.of(Integer.parseInt(endStrings[0]),Integer.parseInt(endStrings[1]),00);
        LocalDate myLD = datePicker.getValue();

        LocalDateTime StartTimeDate = LocalDateTime.of(myLD,startTime);
        LocalDateTime EndTimeDate = LocalDateTime.of(myLD,endTime);

        int timeValid = timeZoneTranslator.timeCheck(StartTimeDate, EndTimeDate);
        switch (timeValid) {
            case 1:


                int ra = DBCustomers.add(nameField.getText(),
                        adrField.getText(), postField.getText(), phoneField.getText(), division);
                System.out.println("Updated these many lines" + ra);

                customerList = DBCustomers.getAllCustomers();
                customerTable.getItems().clear();
                customerTable.getItems().addAll(customerList);
                break;
            case 0:
                Alert invalid = new Alert(Alert.AlertType.ERROR);
                invalid.setContentText("Invalid Time Entry: start time must be before end time");
                invalid.show();
                break;
            case 3:
                Alert overlap = new Alert(Alert.AlertType.ERROR);
                overlap.setContentText("Invalid Time Entry: this appointment overlaps another, please try a different time");
                overlap.show();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + timeValid);
        }
        }


    }
    /*
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


    //-----------------------------------------------------------------------------------

 */
}
