/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.models;

import java.io.Serializable;
import sample.utils.Utils;

/**
 *
 * @author hoadoan
 */
public class Student implements Serializable {

    private String id;
    private String name;
    private String phone;
    private String email;

    public Student() {
    }

    public Student(String id) {
        this.id = id;
    }

    public Student(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        Student st = (Student) obj;
        return st.getId().equals(st.getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        
        return String.format("| %-12s | %-15s | %-13s |%20s", getId(),getName(),getPhone(),getEmail());
    }

    public void setId() {
        boolean countinous = true;
        do {
            String newId = Utils.getString("Input Student ID: ");
            String newUpId = newId.toUpperCase();
            if (Utils.isValidateID(newUpId)) {
                this.id = newUpId;
                countinous = false;
            }
        } while (countinous);
    }

    public void setName() {
        boolean countinous = true;
        do {
            String newName = Utils.getString("Input Student Name: ");
            if (newName.length() > 1 && newName.length() < 21) {
                this.name = newName;
                countinous = false;
            }
        } while (countinous);
    }

    public void setPhone() {
        boolean countinous = true;
        do {
            String newPhone = Utils.getString("Input Student phone: ");
            if (Utils.isValidatePhone(newPhone)) {
                this.phone = newPhone;
                countinous = false;
            }
        } while (countinous);
    }

    public void setEmail() {
        boolean countinous = true;
        do {
            String newEmail = Utils.getString("Input Student êmail: ");
            if (Utils.isValidateEmail(newEmail)) {
                this.email = newEmail;
                countinous = false;
            }
        } while (countinous);
    }
    
    public Student setInforStudent(){
        setId();
        setName();
        setPhone();
        setEmail();
        return this;
    }
}
