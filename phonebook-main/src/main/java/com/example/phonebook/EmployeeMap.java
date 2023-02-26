package com.example.phonebook;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
@XmlRootElement (name="employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeMap {
  private Employee employee = new Employee();
  private Map<Integer, Employee> employeeMap = new HashMap<>();

  public Map<Integer, Employee> getEmployeeMap() {
    return employeeMap;
  }

  public void setEmployeeMap(Map<Integer, Employee> employeeMap) {
    this.employeeMap = employeeMap;
  }

  public void addEmployee(int i, Employee e) {
    employeeMap.put(i, e);

  }

}
