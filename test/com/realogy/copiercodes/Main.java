package com.realogy.copiercodes;

import com.realogy.copiercodes.AddUser;
import com.realogy.copiercodes.UserSearch;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("Please select an option:");
        System.out.println("1.) Add user");
        System.out.println("2.) Search for user");
        String s = sc.next();

        switch (s) {
            case "1":
                com.realogy.copiercodes.AddUser add = new AddUser();
                break;
            case "2":
                com.realogy.copiercodes.UserSearch search = new UserSearch();
                break;
            default:
                System.out.println("Not an available option.");
        }
    }
}
