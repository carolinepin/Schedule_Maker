package sample;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import DBAccess.DBAppointments;
import DBAccess.DBCustomers;
import Models.Appointment;
import Models.Contact;
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


    ObservableList<Appointment> appointmentList = DBAppointments.getAllAppointments();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
}
