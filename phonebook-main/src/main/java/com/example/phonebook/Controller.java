package com.example.phonebook;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonNew;

    @FXML
    private Button buttonReset;
    @FXML
    private Button buttonClean;


    @FXML
    private TableColumn<Employee, String> columnName;

    @FXML
    private TableColumn<Employee, String> columnSurname;
    @FXML
    private TableColumn<Employee, String> columnBirthDay;
    @FXML
    private TableColumn<Employee, String> columnTelefon;
    @FXML
    private TableColumn<Employee, String> columnAdress;

    @FXML
    private TableView<Employee> tablePersons;

    @FXML
    private TextField textFieldAddress;

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldTelephone;
    @FXML
    private TextField textFieldBirthDay;
    @FXML
    private Stage mainStage;
    @FXML
    private String absolutePath;
    @FXML
    private File selectedFile;
    @FXML
    private String fileName;
    @FXML
    private ObservableList<Employee> persons = FXCollections.observableArrayList();
    private EmployeeMap empMap = new EmployeeMap();
    @FXML
    private Employee employee = new Employee();

    @FXML
    private Label warming = new Label();
    @FXML
    private int mapKey = 0;


    @FXML
    public void setTablePersons() {
        allPersons();
        columnName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
        columnBirthDay.setCellValueFactory(new PropertyValueFactory<Employee, String>("birthday"));
        //columnAdress.setCellValueFactory(new PropertyValueFactory<Employee, String>("adress"));
        columnTelefon.setCellValueFactory(new PropertyValueFactory<Employee, String>("telephoneNumber"));

        tablePersons.setItems(persons);
        tablePersons.refresh();

    }

    @FXML
    public void allPersons() {
        for (int i = 0; i < persons.size(); i++) {
            empMap.addEmployee(mapKey, persons.get(i));
        }

    }

    @FXML
    public void showPerson(Employee person) {
        setTablePersons();
        if (person != null) {
            textFieldName.setText(person.getName());
            textFieldSurname.setText(person.getSurname());
            textFieldBirthDay.setText(person.getBirthday());
            textFieldAddress.setText(person.getAddress());
            textFieldTelephone.setText(person.getTelephoneNumber());
        } else {
            cleanTabelle();
        }

    }

    @FXML
    public void cleanTabelle() {
        textFieldName.setText("");
        textFieldSurname.setText("");
        textFieldBirthDay.setText("");
        textFieldAddress.setText("");
        textFieldTelephone.setText("");

        tablePersons.setItems(persons);
        tablePersons.refresh();

        warming.setText("Tablo temizlendi");

    }

    @FXML
    public void addNewPerson() throws IOException, JAXBException {


        if (textFieldName.getText().equals("") &&
                textFieldSurname.getText().equals("") &&
                textFieldBirthDay.getText().equals("") &&
                textFieldAddress.getText().equals("") &&
                textFieldTelephone.getText().equals("")) {

            warming.setText("Lütfen bir değer giriniz!!!");

        } else {

            Employee employee = new Employee(textFieldName.getText(), textFieldSurname.getText(), textFieldBirthDay.getText(),
                    textFieldAddress.getText(), textFieldTelephone.getText());

            persons.add(persons.size(), employee);


            tablePersons.setItems(persons);
            tablePersons.refresh();

            allPersons();
            cleanTabelle();

        }


    }

    @FXML
    void updatePerson() throws IOException, JAXBException {
        if (
                textFieldName.getText().equals("") &&
                        textFieldSurname.getText().equals("") &&
                        textFieldBirthDay.getText().equals("") &&
                        textFieldAddress.getText().equals("") &&
                        textFieldTelephone.getText().equals("")) {

            warming.setText("Lütfen bir değer giriniz!!!");

        } else if (tablePersons.getSelectionModel().getSelectedIndex() != -1) {
            int position = tablePersons.getSelectionModel().getSelectedIndex();
            System.out.println(position);

            Employee employee = new Employee(
                    textFieldName.getText(),
                    textFieldSurname.getText(),
                    textFieldBirthDay.getText(),
                    textFieldAddress.getText(),
                    textFieldTelephone.getText()
            );

            persons.remove(persons.get(position));
            persons.add(position, employee);


            tablePersons.setItems(persons);
            tablePersons.getColumns();
            tablePersons.refresh();


            mix();

            warming.setText("Kişi Güncellendi");


        }


    }

    @FXML
    void deletePerson() throws JAXBException {
        if (tablePersons.getSelectionModel().getSelectedIndex() != -1) {
            persons.remove(tablePersons.getSelectionModel().getSelectedIndex());
        }

        warming.setText("Kişi Silindi");

        System.out.println("MapSize: " + empMap.getEmployeeMap().size() + "-- PersonsSize: " + persons.size());

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTablePersons();
        tablePersons.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showPerson(newValue));
    }

    @FXML
    public void openXML() throws JAXBException, IOException {


        JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeMap.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();



        empMap = (EmployeeMap) jaxbUnmarshaller.unmarshal(new File(fileFinder()));

        persons.clear();

        persons.addAll(empMap.getEmployeeMap().values());

        empMap.getEmployeeMap().clear();

        tablePersons.setItems(persons);
        tablePersons.refresh();


    }


    @FXML
    public String fileFinder() throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\murat\\Downloads\\phonebook-main\\phonebook-main\\src\\main\\resources"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        selectedFile = fileChooser.showOpenDialog(mainStage);
        absolutePath = selectedFile.getAbsolutePath();
        fileName = selectedFile.getName();

        return absolutePath;
    }


    @FXML
    public void mix() throws JAXBException, IOException {
        empMap.getEmployeeMap().clear();
        for (int i = 0; i < persons.size(); i++) {
            empMap.getEmployeeMap().put(i, persons.get(i));
        }


        JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeMap.class);

        int key = 0;
        if (empMap.getEmployeeMap().values().size() != 0) {
            for (Employee employee : empMap.getEmployeeMap().values()) {
                employee.setName(empMap.getEmployeeMap().get(key).getName());
                employee.setSurname(empMap.getEmployeeMap().get(key).getSurname());
                employee.setBirthday(empMap.getEmployeeMap().get(key).getBirthday());
                employee.setAddress(empMap.getEmployeeMap().get(key).getAddress());
                employee.setTelephoneNumber(empMap.getEmployeeMap().get(key).getTelephoneNumber());
                key++;
            }

        } else {
            warming.setText("dosyada veri yok yazdırılamadı.");
        }


        System.out.println("");

        // -marschalling
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        jaxbMarshaller.marshal(empMap, System.out);
        jaxbMarshaller.marshal(empMap, new File("C:\\Users\\murat\\Downloads\\phonebook-main\\phonebook-main\\src\\main\\resources\\com\\example\\phonebook\\employees6.xml"));

     //   Gson json = new Gson();
     //   String response = json.toJson(empMap);


     //   FileWriter fileWriter=new FileWriter("C:\\Users\\murat\\Downloads\\phonebook-main\\phonebook-main\\src\\main\\resources\\deneme.json");

     //   fileWriter.write(response);
     //   fileWriter.close();

    }


}