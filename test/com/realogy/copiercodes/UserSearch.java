package com.realogy.copiercodes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserSearch {
    private String name;
    private Scanner sc = new Scanner(System.in);
    private String source = "codelist.csv";

    public UserSearch() {
        System.out.print("Enter user's first or last name: ");
        name = clean(sc.next());

        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            while (true) {
                String line = br.readLine();
                if (line != null) {
                    String[] listArray = line.split(",");
                    for (String s:listArray ) {
                        if (s.equals(name)) {
                            System.out.println(line);
                        }
                    }

                } else {
                    break;
                }
            }
        } catch (IOException e) {e.printStackTrace();}

        System.out.println("Would you like to search for another user?" );
        name = sc.next().toLowerCase();

        if (name.contains("y")) {
            UserSearch search = new UserSearch();
        } else {
            System.exit(0);
        }
    }

    public String clean(String name) {
        String cleanName = name.trim();
        cleanName = cleanName.substring(0,1).toUpperCase() + cleanName.substring(1).toLowerCase();
        return cleanName;
    }

    private void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
