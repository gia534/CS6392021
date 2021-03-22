package com.gia.employees;

public class Employee {
    private String firstName;
    private String lastName;

    Employee(){ }
    Employee(String fName, String lName){
        this.firstName = fName;
        this.lastName = lName;

    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
