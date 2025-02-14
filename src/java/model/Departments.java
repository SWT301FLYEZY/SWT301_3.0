/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Departments {
    private int id;
    private String name;
    private String type;
    private ArrayList<Employees> emps = new ArrayList<>();

    public int getId() {
        return id;
    }

    public ArrayList<Employees> getEmps() {
        return emps;
    }

    public void setEmps(ArrayList<Employees> emps) {
        this.emps = emps;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
