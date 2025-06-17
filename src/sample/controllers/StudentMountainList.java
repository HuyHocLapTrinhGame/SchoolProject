/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import sample.models.I_List;
import sample.models.Mountain;
import sample.models.Student;
import sample.models.StudentMountain;
import sample.utils.Utils;

/**
 *
 * @author hoadoan
 */
public class StudentMountainList extends ArrayList<StudentMountain> implements I_List {
    
    @Override
    public boolean create() {
        boolean result = false;
        try {
            boolean checkDuplicate = true;
            Student student = new Student();
            do {
                student.setInforStudent();
                if (this.indexOf(new StudentMountain(student)) == -1) {
                    checkDuplicate = false;
                } else {
                    System.out.println("Duplicate Student roi !");
                }
            } while (checkDuplicate);
//            tới đây là đã nhập xong sinh viên( sinh viên chưa đăng ký). 
//            load thông tin núi từ file 
            ArrayList<Object> listMountain = Utils.readListOjectFromFile("mountainList.bin");
            boolean countinous = true;
            String mountainCode = "";
            do {
                mountainCode = Utils.getString("Input mountain code(1 to 99):");
                if (listMountain.indexOf(new Mountain(mountainCode)) != -1) {
                    countinous = false;
                }
            } while (countinous);
            double fee = Utils.BASE_FEE;
            String first3Character = student.getPhone().substring(0, 3);
            if (first3Character.equals("098") || first3Character.equals("097")
                    || first3Character.equals("090") || first3Character.equals("091")) {
                fee = Utils.BASE_FEE * Utils.DISCOUNT_FEE / 100;
            }
            this.add(new StudentMountain(student, mountainCode, fee));
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void display() {
        if(this.isEmpty()){
            System.out.println("No students have registered yet.");
        }
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("| Student ID   | Name            | Phone         | emal               | Mountain Code | Fee       |");
        for (StudentMountain mt : this) {
            System.out.println(mt.toString());
        }
        System.out.println("---------------------------------------------------------------------------------------------------");

    }
    
    @Override
    public boolean saveToFile(String path) {
        boolean check = false;
        try {
            Utils.writeListObjectToFile(path, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public StudentMountain delete() {
        String idWantToFind = Utils.getString("input ID want to delete: ");
        String idString = idWantToFind.toUpperCase();
        int indexOfId= this.indexOf(new StudentMountain(new Student(idString)));
        if(indexOfId == -1){
            System.out.println("This student has not registered yet.");
            return null;
        }
        StudentMountain deleteSm = this.get(indexOfId);
        Utils.showDeleteItem(this,"Student Details:");
        boolean confirmToDelete= Utils.confirmYesNo("Do you want to delete this student?");
        if(confirmToDelete){
            this.remove(indexOfId);
            System.out.println("The registration has been successfully deleted.");
        }
        return deleteSm;
    }

    @Override
    public List<StudentMountain> search() {
        if(this ==  null){
            System.out.println("emty list");
            return this;
        }
        String nameToSearch = Utils.getString("input name want to search: ");
        List<StudentMountain> container = new ArrayList<>();
        for(StudentMountain item : this){
            if(item.getStudent().getName().toUpperCase().equals(nameToSearch.toUpperCase())){
                container.add(item);
            }
        }
        
        Utils.showItem(container, "Name list: ");
        return container;
    }

    @Override
    public List<StudentMountain> filter() {
        if(this.isEmpty()){
            System.out.println("emty list");
            return this;
        }
        String campusHeader = Utils.getString("input header of campus(HE,SE,DE,GE,CE)");
        List<StudentMountain> container = new ArrayList<>();
        for(StudentMountain item : this){
            if(item.getStudent().getId().substring(0,2).equalsIgnoreCase(campusHeader)){
                container.add(item);
            }
        }
        if(container.isEmpty()){
            System.out.println("No students have registered under this campus.");
        }
        else{
            Utils.showCampus(container, campusHeader.toUpperCase());
        }
        return container;
        
    }

    @Override
    public StudentMountain update() {
        String idToUpdate = Utils.getString("Input ID want to update: ");
        String idFound = idToUpdate.toUpperCase();
        int index = this.indexOf(new StudentMountain(new Student(idFound)));
        if(index==-1){
            System.out.println("This student has not registered yet.");
            return null;
        }
        else{
            StudentMountain sm = this.get(index);
            String newName = Utils.getUpdate("Input new name (enter to skip ):");
            String newPhone = Utils.getUpdate("input new phone number(enter to skip):");
            String newEmail = Utils.getUpdate("input new email(enter to skip):");
            String newMountainCode = Utils.getUpdate("input new mountain code(enter to skip):");
          
            if(!newName.isEmpty()){
                sm.getStudent().setName(newName);
            }
            if(!newPhone.isEmpty() && Utils.isValidatePhone(newPhone)){
                sm.getStudent().setPhone(newPhone);
                
            }
            if((!newEmail.isEmpty() && Utils.isValidateEmail(newEmail))){
                sm.getStudent().setEmail(newEmail);
            }
            if(!newMountainCode.isEmpty()){
                sm.setMountainCode(newMountainCode);
            }
            System.out.println("Update successful");
            return sm;
        }

    }

    @Override
    public List<StudentMountain> counter() {
        if(this.isEmpty()){
            System.out.println("No students have registered yet");
            return null;
        }
        Map<String,Long> moutainCount = this.stream().collect(Collectors.groupingBy(StudentMountain::getMountainCode,Collectors.counting()));
        Map<String,Double> moutainFee = this.stream().collect(Collectors.groupingBy(StudentMountain::getMountainCode,Collectors.summingDouble(StudentMountain::getFee)));
        Utils.showMoutainCode(moutainCount,moutainFee);
        return this;
    }

    @Override
    public Student getFirstStudent() {
        StudentMountain st = this.get(0);
        Student s = st.getStudent();
//        Utils.output(s);
        return s;
    }
}
