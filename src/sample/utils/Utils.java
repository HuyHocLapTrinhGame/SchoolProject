/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import sample.models.Student;
import sample.models.StudentMountain;


/**
 *
 * @author hd
 */
public class Utils {

    public final static int MIN = 0;
    public final static int MAX = 3000;
    public final static int BASE_FEE = 6000000;
    public final static int DISCOUNT_FEE = 35;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String fomatMountain = "^\\d{2}$";
    private static final String PHONE_REGEX = "^(0?)(3[2-9]|5[689]|7(?:0|[6-9])|8[1-9]|9\\d)\\d{7}$";
    public static boolean isValidateID(String id){
        return id.toUpperCase().matches("(?i)^(SE|HE|DE|QE|CE)\\d{6}$");
//        boolean check = false;
//        check = id.matches("^(SE|HE|DE|QE|CE)\\d{6}$");
//        return check;
    }
    
    public static boolean isValidateEmail(String email){
        return email.matches(EMAIL_REGEX);
    }
    
    public static boolean isValidatePhone(String phone){
        return phone.matches(PHONE_REGEX);
    }
    public static String getUpdate(String s){
        Scanner sc = new Scanner(System.in);
        System.out.println(s);
        String result = sc.nextLine();
        
        return result;
    }
    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }
    
    public static String updateString(String welcome, String oldData) {
        String result = oldData;
        Scanner sc = new Scanner(System.in);
        System.out.print(welcome);
        String tmp = sc.nextLine();
        if (!tmp.isEmpty()) {
            result = tmp;
        }
        return result;
    }

    public static int getInt(String welcome, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static int updateInt(String welcome, int min, int max, int oldData) {
        boolean check = true;
        int number = oldData;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(welcome);
                String tmp = sc.nextLine();
                if (tmp.isEmpty()) {
                    check = false;
                } else {
                    number = Integer.parseInt(tmp);
                    check = false;
                }
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = false;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = true;
        }
        return result;
    }
    public static boolean writeListObjectToFile(String path, List<StudentMountain> list) {
        boolean result = false;
        try {
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            try {

                for (Object sm : list) {
                    oos.writeObject(sm);
                }
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    oos.close();
                }
            }
            if (file != null) {
                file.close();
            }
        } catch (Exception e) {
        }
        System.out.println("Registration data has been successfully saved to "+path);
        return result;
    }

    public static ArrayList<Object> readListOjectFromFile(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Object> list = new ArrayList();
        try {
            Object obj = null;
            while (fis.available() > 0) {
                obj = (Object) ois.readObject();
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                ois.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return list;
    }
    
    public static void showDeleteItem(List<StudentMountain> list , String s){
        System.out.println(s);
        System.out.println("-----------------------------------------------------");
        for(StudentMountain item :list){
            System.out.print("Student ID: "); System.out.println(item.getStudent().getId());
            System.out.print("Name      : "); System.out.println(item.getStudent().getName());
            System.out.print("Phone     : "); System.out.println(item.getStudent().getPhone());
            System.out.print("Mountain  : "); System.out.println(item.getMountainCode());
            System.out.print("Fee       : "); System.out.println(item.getFee());
        }
        System.out.println("-----------------------------------------------------");
    }
    
 
    
    public static void showItem(List<StudentMountain> list,String title){
       if(list.isEmpty()){
           System.out.println("No one matches the search criteria!");
       }
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("|Student ID    | Name            | Phone         | Peak Code  | Fee         ");
        for(StudentMountain item :list){
            System.out.printf("| %-12s | %-15s | %-13s | %10s | %.2f |\n",
                item.getStudent().getId(),
                item.getStudent().getName(),
                item.getStudent().getPhone(),
                item.getMountainCode(),
                item.getFee());        
        }
        System.out.println("----------------------------------------------------------------------------");
    }
    
    public static void showCampus(List<StudentMountain> list, String s){
        if(s=="HE"){
            System.out.println("Registered Students Under Ha Noi Campus (HE):");
        }
        else if(s=="SE"){
            System.out.println("Registered Students Under HCM Campus (SE):");
        }
        else if(s=="QE"){
            System.out.println("Registered Students Under Quy Nhon Campus (QE):");
        }
        else if(s=="CE"){
            System.out.println("Registered Students Under Can Tho Campus (CE):");
        }
        else{
            System.out.println("Registered Students Under Da Nang Campus (DE):");
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("|Student ID    | Name            | Phone         |Mountain    | Fee         ");
        for(StudentMountain item :list){
            System.out.printf("| %-12s | %-15s | %-13s | %10s | %.2f |\n",
                item.getStudent().getId(),
                item.getStudent().getName(),
                item.getStudent().getPhone(),
                item.getMountainCode(),
                item.getFee());        
        }
        System.out.println("----------------------------------------------------------------------------");
    }
    
    public static void showMoutainCode(Map<String,Long> counter, Map<String,Double> fee){
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Peak Name    | Number of Participants | Total Cost              ");
        System.out.println("-----------------------------------------------------------------");
        for(Map.Entry<String,Long> entry : counter.entrySet()){
            System.out.printf("%-13s|%-24d|",entry.getKey(),entry.getValue());
            
        }
        for (Map.Entry<String, Double> entrySet : fee.entrySet()) {
            System.out.printf("%.2f\n",entrySet.getValue());
        }
        System.out.println("-----------------------------------------------------------------");
    }
    
    public static void output(Student s){
        System.out.println(s);
    }
}
