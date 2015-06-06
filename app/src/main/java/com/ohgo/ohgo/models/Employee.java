package com.ohgo.ohgo.models;

import com.parse.ParseClassName;

import com.parse.ParseObject;

/**
 * Created by Rick on 05/06/15.
 */

@ParseClassName("Employeee")
public class Employee extends ParseObject{

    public static final String CLASS_NAME = "Employee";
    private String employeeId;
    private String userId;
    private String name;


    public Employee(){}

    public Employee(String theClassName, String userId, String name) {
        super(theClassName);
        this.userId = userId;
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        put("userId", userId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        put("name", name);
    }
}
