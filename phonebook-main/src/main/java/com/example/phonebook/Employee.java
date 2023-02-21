package com.example.phonebook;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)

public class Employee {

    private String name;
    private String surname;

    private String telephoneNumber;
    private String birthday;
    private String address;

    public Employee(String name, String surname, String birthday, String address, String telephoneNumber) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.address = address;
        this.telephoneNumber = telephoneNumber;

    }


    public Employee() {
    }


    @FXML
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @FXML
    public String getBirthday() {
        return birthday;
    }

    @FXML
    public String getName() {
        return name;
    }

    @FXML
    public void setName(String name) {
        this.name = name;
    }

    @FXML
    public String getSurname() {
        return surname;
    }

    @FXML
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @FXML
    public String getAddress() {
        return address;
    }

    @FXML
    public void setAddress(String address) {
        this.address = address;
    }

    @FXML
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @FXML
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}