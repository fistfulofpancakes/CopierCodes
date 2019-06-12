package test.realogy.copiercodes;

import com.realogy.copiercodes.AddUser;
import com.realogy.copiercodes.ChangeCode;
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
                AddUser add = new AddUser();
                break;
            case "2":
                UserSearch search = new UserSearch();
                break;
            case "9":
                ChangeCode change = new ChangeCode();
                break;
            default:
                System.out.println("Not an available option.");
        }
    }
}
