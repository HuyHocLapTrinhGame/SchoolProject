/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.models;

import java.util.List;

/**
 *
 * @author hoadoan
 */
public interface I_List {
    boolean create();
    StudentMountain update();
    StudentMountain delete();
    void display();
    List<StudentMountain> search();
    List<StudentMountain> filter();
    List<StudentMountain> counter();
    boolean saveToFile(String path);
    Student getFirstStudent();
}
