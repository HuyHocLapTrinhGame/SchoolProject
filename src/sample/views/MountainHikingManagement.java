package sample.views;

import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import sample.controllers.Menu;
import sample.controllers.StudentMountainList;
import sample.models.I_List;
import sample.models.I_Menu;
import sample.models.Student;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa Doan
 */
public class MountainHikingManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        menu.addItem("1. New Registration");
        menu.addItem("2. Update Registration Information");
        menu.addItem("3. Display Registered List");
        menu.addItem("4. Delete Registration Information");
        menu.addItem("5. Search Participants by Name");
        menu.addItem("6. Filter Data by Campus");
        menu.addItem("7. Statistics of Registration Numbers by Location");
        menu.addItem("8. Save Data to File");
        menu.addItem("9. get first student");
        menu.addItem("10. Exit the Program");
        int choice;
        boolean cont = true;
        I_List list = new StudentMountainList();
        
        do {
            menu.showMenu();
            choice = menu.getChoice();
            if(choice>9 || choice <1 ){
                System.out.println("This function is not available.");
                return;
            }
            switch (choice) {
                case 1:
                    list.create();
                    break;
                case 2:
                    list.update();
                    break;
                case 3:
                    list.display();
                    break;
                case 4:
                    list.delete();
                    break;
                case 5:
                    list.search();
                    break;
                case 6: 
                    list.filter();
                    break;
                case 7:
                    list.counter();
                    break;
                case  8:
                    list.saveToFile("studentMountainList.bin");
                    break;
                case 9:
                    Student s = list.getFirstStudent();
                    System.out.println(s.toString());
                    
                    break;
                case 10:
                    cont = menu.confirmYesNo("Do you want to save the changes before exiting? (Y/N)");
                    if (cont == true) {
                        cont = false;
                    }
                    break;
            }
        } while (choice >= 0 && choice <= 9 && cont);
    }
}
