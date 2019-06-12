package com.realogy.copiercodes;

import java.io.*;
import java.util.Scanner;

public class AddUser {

    public AddUser(){
        int code;
        String firstName;
        String lastName;
        String office;
        Scanner sc = new Scanner(System.in);
        File source = new File("codelist.csv");

        System.out.print("Enter the user's first name: ");
        firstName = clean(sc.next());
        System.out.print("Enter the user's last name: ");
        lastName = clean(sc.next());
        System.out.println("Enter the user's office: ");
        System.out.println("1.) Camp Hill");
        System.out.println("2.) Carlisle");
        System.out.println("3.) Chambersburg");
        System.out.println("4.) Dillsburg");
        System.out.println("5.) Greencastle");
        System.out.println("6.) Harrisburg");
        System.out.println("7.) Hershey");
        System.out.println("8.) Kutztown");
        System.out.println("9.) Lancaster");
        System.out.println("10.) Lebanon");
        System.out.println("11.) New Bloomfield");
        System.out.println("12.) Waynesboro");
        System.out.println("13.) Wyomissing");
        System.out.println("14.) York");
        office = office(sc.nextInt());

        code = (int)(Math.random()*((99999 - 10000) + 1)) + 10000;


        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            while (true) {
                String line = br.readLine();
                if (line != null) {
                    String[] listArray = line.split(",");
                    for (String s:listArray ) {
                        if (s.equals(String.valueOf(code))) {
                            code = (int)(Math.random()*((99999 - 10000) + 1)) + 10000;
                        }
                    }

                } else {
                    break;
                }
            }
        } catch (IOException e) {e.printStackTrace();}

        try (BufferedWriter pw = new BufferedWriter(new FileWriter(source,true))) {
            pw.newLine();
            pw.write(lastName + "," + firstName + "," + code + "," + office);
        } catch (IOException e) {e.printStackTrace();}

        System.out.println(firstName + " " + lastName + "'s copier code is " + code + ".");
        System.out.println();
        System.out.println("Would you like to add another user? (Y/N)");
        String s = sc.next().toLowerCase();

        if(s.contains("y")) {
            AddUser add = new AddUser();
        } else {
            System.exit(0);
        }
    }

    private String clean(String name) {
        String cleanName = name.trim();
        cleanName = cleanName.substring(0,1).toUpperCase() + cleanName.substring(1).toLowerCase();
        return cleanName;
    }

    private String office(int officeCode) {
        String office = null;
        switch (officeCode) {
            case 1: office = "Camp_Hill";
            break;
            case 2: office = "Carlisle";
            break;
            case 3: office = "Chambersburg";
            break;
            case 4: office = "Dillsburg";
            break;
            case 5: office = "Greencastle";
            break;
            case 6: office = "Harrisburg";
            break;
            case 7: office = "Hershey";
            break;
            case 8: office = "Kutztown";
            break;
            case 9: office = "Lancaster";
            break;
            case 10: office = "Lebanon";
            break;
            case 11: office = "New_Bloomfield";
            break;
            case 12: office = "Waynesboro";
            break;
            case 13: office = "Wyomissing";
            break;
            case 14: office = "York";
            break;
        }
        return office;
    }

    private void cls() {
    try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception e) {e.printStackTrace();}
}
}
