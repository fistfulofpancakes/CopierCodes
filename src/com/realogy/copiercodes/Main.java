package com.realogy.copiercodes;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select an option:");
        System.out.println("1.) Add user");
        System.out.println("2.) Search for user");
        String s = sc.next();
        if (s.equals("1")) {
            AddUser add = new AddUser();
        } else if (s.equals("2")) {
            UserSearch search = new UserSearch();
        } else {
            System.out.println("Do what now?");
        }
    }


}
