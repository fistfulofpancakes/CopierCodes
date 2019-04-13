package com.realogy.copiercodes;

import java.io.*;
import java.util.Scanner;

public class AddUser {
    private int code;
    private String firstName;
    private String lastName;
    private Scanner sc = new Scanner(System.in);
    //private File file = new File("files/codelist.csv");
    private Scanner fileScan = new Scanner("files/codelist.csv");

    public AddUser() {
        System.out.print("Enter the user's first name: ");
        firstName = clean(sc.next());
        System.out.print("Enter the user's last name: ");
        lastName = clean(sc.next());

        code = (int)(Math.random()*((99999 - 10000) + 1)) + 10000;

        try (BufferedWriter pw = new BufferedWriter(new FileWriter("files/codelist.csv",true))) {
            pw.newLine();
            pw.write(lastName + "," + firstName + "," + code);
        } catch (IOException e) {e.printStackTrace();}
        //TODO Break the line into three parts separated by commas, then try to match the new code to the existing codes.
        try (BufferedReader br = new BufferedReader(new FileReader("files/codelist.csv"))) {
            while (true) {
                String line = br.readLine();
                if (line != null) {
                    System.out.println(line);
                } else {
                    break;
                }
            }
        } catch (IOException e) {e.printStackTrace();}

        cls();

        System.out.println(firstName + " " + lastName + "'s copier code is " + code + ".");

        try {
            System.out.println("Press enter to close.");
            System.in.read();
        } catch (IOException e) {e.printStackTrace();}
    }

    public String clean(String name) {
        String cleanName = name.trim();
        cleanName = cleanName.substring(0,1).toUpperCase() + cleanName.substring(1).toLowerCase();
        return cleanName;
    }

    public void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {e.printStackTrace();}
    }
}
