package com.realogy.copiercodes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Main {

    private static String file = "codelist.csv";
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> fileLines = new ArrayList<>();
    private static Map<Integer, String[]> matches = new HashMap<>();

    public static void main(String[] args){

        System.out.println("Running test files.");

        fileToArray();

        Scanner sc = new Scanner(System.in);
        System.out.println("Please select an option:");
        System.out.println("1.) Add user");
        System.out.println("2.) Search for user");
        String s = sc.next();

        switch (s) {
            case "1":
                addUser();
                break;
            case "2":
                searchUser();
                break;
            case "9":
                changeCode();
                break;
            default:
                System.out.println("Not an available option.");
        }
    }

    private static void fileToArray() {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (true) {
                String line = br.readLine();
                if (line != null) {
                    fileLines.add(line);
                } else {
                    break;
                }
            }
        } catch (Exception e){e.getStackTrace();}
    }

    private static void arrayToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String s:fileLines) {
                bw.write(s);
                bw.newLine();
            }
        } catch (Exception e){e.getStackTrace();}
    }

    private static void addUser() {
        String firstName;
        String lastName;
        String office;
        Scanner sc = new Scanner(System.in);

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
        office = officeOptions(sc.next());

        int code = codeCreation();

        fileLines.add(code + "," + firstName + "," + lastName + "," + office);

        arrayToFile();

        System.out.println(firstName + " " + lastName + "'s copier code is " + code + ".");
        System.out.println();
        System.out.println("Would you like to add another user? (Y/N)");
        String s = sc.next().toLowerCase();

        if(s.contains("y")) {
            addUser();
        } else {
            System.exit(0);
        }
    }

    private static String officeOptions(String officeCode) {
        String office;
        switch (officeCode) {
            case "1":
                office = "Camp_Hill";
                break;
            case "2":
                office = "Carlisle";
                break;
            case "3":
                office = "Chambersburg";
                break;
            case "4":
                office = "Dillsburg";
                break;
            case "5":
                office = "Greencastle";
                break;
            case "6":
                office = "Harrisburg";
                break;
            case "7":
                office = "Hershey";
                break;
            case "8":
                office = "Kutztown";
                break;
            case "9":
                office = "Lancaster";
                break;
            case "10":
                office = "Lebanon";
                break;
            case "11":
                office = "New_Bloomfield";
                break;
            case "12":
                office = "Waynesboro";
                break;
            case "13":
                office = "Wyomissing";
                break;
            case "14":
                office = "York";
                break;
            default:
                System.out.println("That's not an available number.");
                System.out.println("Please select user's office:");
                office = officeOptions(sc.next());
        }
        return office;
    }

    private static void searchUser() {
        search();

        System.out.println("Would you like to search for another user? (Y/N)");

        String input = sc.next().toLowerCase();

        if (input.contains("y")) {
            searchUser();
        } else {
            System.exit(0);
        }
    }

    private static void changeCode() {
        String input;
        int intput = 0;
        search();

        System.out.print("Whose code would you like to change?: ");

        while (true) {
            try {
                intput = sc.nextInt();
                if (intput > 0 && intput <= matches.size()) {
                    break;
                } else {
                    System.out.println("Not a valid selection.");
                    System.out.print("Whose code would you like to change?: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not a valid selection.");
                System.out.print("Whose code would you like to change?: ");
                sc.nextLine();
            }
        }

        String[] array = matches.get(intput);

        int a = 0;
        for (String s : fileLines) {
            if (s.contains(array[0])) {
                fileLines.set(a, s.concat(",DNU"));
            }
            a++;
        }

        int code = codeCreation();

        fileLines.add(code + "," + array[1] + "," + array[2] +"," + array[3]);
        arrayToFile();

        System.out.println("The new code for " + array[1] + " " + array[2] + " is " + code + ".");
        System.out.println();
        System.out.println("Change another code (Y/N)");

        input = sc.next();

        if(input.contains("y")) {
            matches.clear();
            changeCode();
        } else {
            System.exit(0);
        }
    }

    private static void search() {
        String input;
        int i = 1;

        System.out.print("Enter user's first or last name: ");
        input = clean(sc.next());

        for (String s : fileLines) {
            if (!s.contains("DNU") && s.contains(input)) {
                String[] arr = s.split(",");
                matches.put(i, arr);
                System.out.println(i + ".) " + arr[3] + ": " + arr[1] + " " + arr[2] + " - " + arr[0]);
                i++;
            }
        }

        if (i == 1) {
            System.out.println("No user found. Search again? (Y/N)");
            input = sc.next().toLowerCase();

            if (input.contains("y")) {
                search();
            } else {
                System.exit(0);
            }
        }
    }

    private static int codeCreation() {
        int code = (int)(Math.random()*((99999 - 10000) + 1)) + 10000;
        for (String s : fileLines) {
            if (s.contains(String.valueOf(code))) {
                codeCreation();
            }
        }
        return code;
    }

    private static String clean(String name) {
        String cleanName;

        if (name.contains("-")) {
            String[] last = name.split("-");
            last[0] = last[0].trim();
            last[1] = last[1].trim();
            cleanName = last[0].substring(0, 1).toUpperCase() + last[0].substring(1).toLowerCase() + "-" + last[1].substring(0, 1).toUpperCase() + last[1].substring(1).toLowerCase();
        } else {
            cleanName = name.trim();
            cleanName = cleanName.substring(0, 1).toUpperCase() + cleanName.substring(1).toLowerCase();
        }
        return cleanName;
    }

    private void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {e.printStackTrace();}
    }
}
