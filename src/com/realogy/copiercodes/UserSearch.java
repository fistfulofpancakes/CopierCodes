package com.realogy.copiercodes;

import java.util.Scanner;

public class UserSearch {
    private String name;
    private Scanner sc = new Scanner(System.in);

    public UserSearch() {
        System.out.print("Enter user's last name:");
        name = clean(sc.next());
    }

    public String clean(String name) {
        String cleanName = name.trim();
        cleanName = cleanName.substring(0,1).toUpperCase() + cleanName.substring(1).toLowerCase();
        return cleanName;
    }
}
