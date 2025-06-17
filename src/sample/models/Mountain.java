/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.models;

import java.io.Serializable;

/**
 *
 * @author hoadoan
 */
public class Mountain implements Serializable{
    private static final long serialVersionUID = 8362997074013012313L;
    private String code;
    private String name;
    private String provice;
    private String description;
        
    public Mountain() {
    }

    public Mountain(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object obj) {
        Mountain mt= (Mountain) obj;
        return this.getCode().equals(mt.getCode());
    }
    
    public Mountain(String code, String name, String provice, String description) {
        this.code = code;
        this.name = name;
        this.provice = provice;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
