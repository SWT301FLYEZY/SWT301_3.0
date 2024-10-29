/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Employees {

    private int id;
    private String name;
    private Departments did;
    private String phonenumber;
    private String address;
    private Salaries sid;
    private boolean gender;
    private Date dob;
    private Departments dept;
    private Salaries sals;
    

    public Departments getDept() {
        return dept;
    }

    public void setDept(Departments dept) {
        this.dept = dept;
    }

    public Salaries getSals() {
        return sals;
    }

    public void setSals(Salaries sals) {
        this.sals = sals;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Departments getDid() {
        return did;
    }

    public void setDid(Departments did) {
        this.did = did;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Salaries getSid() {
        return sid;
    }

    public void setSid(Salaries sid) {
        this.sid = sid;
    }
    
    
    



    
}
