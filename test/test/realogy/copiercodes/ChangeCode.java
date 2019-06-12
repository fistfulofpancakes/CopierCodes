package test.realogy.copiercodes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChangeCode {
    private String input;
    private Scanner sc = new Scanner(System.in);
    private String source = "codelist.csv";
    int a = 1;
    Map<Integer, String[]> matches = new HashMap<>();
    ArrayList<String> fileLines = new ArrayList<>();

    public ChangeCode() {

        fileToLines();

        System.out.print("Enter user's first or last name: ");
        input = clean(sc.next());

        for (int i = 0; i < fileLines.size(); i++) {
            String line = fileLines.get(i);
            if (line != null) {
               if (!line.contains("DNU") && line.contains(input)) {
                   String[] lineArr = line.split(",");
                   matches.put(a, lineArr);
                   System.out.println(line + a);
                   a++;
               }
            } else {
               break;
            }
        }
        System.out.print("Whose code would you like to change?: ");

        int b = sc.nextInt();
        changing(b);

        System.exit(0);
    }

    public void changing(int num) {
        String[] array = matches.get(num);

        int a = 0;
        for (String s : fileLines) {
            if (s.contains(array[0])) {
                fileLines.set(a, s.concat(",DNU"));
            }
            a++;
        }

        int code = (int)(Math.random()*((99999 - 10000) + 1)) + 10000;

        for (String s : fileLines) {
            if (s.contains(String.valueOf(code))) {
                code = (int)(Math.random()*((99999 - 10000) + 1)) + 10000;
            }
        }

        fileLines.add(code + "," + array[1] + "," + array[2] +"," + array[3]);

        linesToFile();

        System.out.println("The new code for " + array[1] + " " + array[2] + " is " + code + ".");
    }

    public void fileToLines() {
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
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

    public void linesToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(source))) {
            for (String s:fileLines) {
                bw.write(s);
                bw.newLine();
            }
        } catch (Exception e){e.getStackTrace();}
    }

    public String clean(String name) {
        String cleanName = name.trim();
        cleanName = cleanName.substring(0,1).toUpperCase() + cleanName.substring(1).toLowerCase();
        return cleanName;
    }
}
