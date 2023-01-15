package com.gridu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();

        String mode = "enc";
        int key = 0;
        String data = "";
        String alg = "shift";
        String fileName = "";

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode" -> mode = args[i + 1];
                case "-key" -> key = Integer.parseInt(args[i + 1]);
                case "-data" -> data = args[i + 1];
                case "-alg" -> alg = args[i + 1];
                case "-in" -> data = main.readFile(args[i + 1]);
                case "-out" -> fileName = args[i + 1];
            }
        }

        if (mode.equals("enc") && alg.equals("shift") && fileName.equals("")) {
            System.out.println(main.encryptShift(data, key));
        } else if (mode.equals("dec") && alg.equals("shift") && fileName.equals("")) {
            System.out.println(main.decryptShift(data, key));
        } else if (mode.equals("enc") && alg.equals("unicode") && fileName.equals("")) {
            System.out.println(main.encryptUnicode(data, key));
        } else if (mode.equals("dec") && alg.equals("unicode") && fileName.equals("")) {
            System.out.println(main.decryptUnicode(data, key));
        } else if (!fileName.equals("")) {
            main.writeFile(data, mode, alg, key, fileName);
        }
    }

    public String encryptUnicode(String data, int key) {
        StringBuilder text = new StringBuilder();
        for (char c : data.toCharArray()) {
            text.append((char) (c + key));
        }
        return text.toString();
    }

    public String decryptUnicode(String data, int key) {
        return encryptUnicode(data, -key);
    }

    private static int modulo(int x) {
        return (x % 26 + 26) % 26;
    }

    public String encryptShift(String data, int key) {
        StringBuilder text = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                int shift = Character.isUpperCase(c) ? 65 : 97;
                text.append((char) (modulo(c - shift + key) + shift));
            } else {
                text.append(c);
            }
        }
        return text.toString();
    }

    public String decryptShift(String data, int key) {
        return encryptShift(data, -key);
    }

    public String readFile(String fileName) {
        File file = new File(fileName);
        String data = "";
        try (Scanner scanner = new Scanner(file)) {
            data = scanner.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
        return data;
    }

    public void writeFile(String data, String mode, String alg, int key, String fileName) {
        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file)) {
            if (mode.equals("enc")) {
                if (alg.equals("unicode")) {
                    data = encryptUnicode(data, key);
                } else {
                    data = encryptShift(data, key);
                }
            } else if (mode.equals("dec")) {
                if (alg.equals("unicode")) {
                    data = decryptUnicode(data, key);
                } else {
                    data = decryptShift(data, key);
                }
            }
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}