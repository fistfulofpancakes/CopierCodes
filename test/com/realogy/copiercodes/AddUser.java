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
        System.out.print("Enter the user's office: ");
        office = clean(sc.next());

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

    private void cls() {
    try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception e) {e.printStackTrace();}
}
}
