/*
This program is coded is Java
Elijah Kern, Eric McMahon, Febuary 21 2025, CMP_SCI 4500 Intro To Software Profession 
This program will take in a CSV and print names in the order listed to the Names.txt file and dates to the DatedData.txt. It will then create a PresentAbsent.txt that will print to it what days did and didn’t have a value.

fileUser used to store user input 

used: https://introcs.cs.princeton.edu/java/11cheatsheet/ to refresh knowledge on java 
used: https://devmountain.com/blog/what-is-github-and-how-do-you-use-it/ to relearn how to use github with other developers 
used: https://www.w3schools.com/ figure out do file inputs with java
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main {
    //Prints opening message and stores nex line of input from user in fileUser
    public static void main(String[] args) {
        System.out.println("This program will take in a CSV and print names in the order listed to the Names.txt file and dates to the DatedData.txt. It will then create a PresentAbsent.txt that will print to it what days did and didn’t have a value.");
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Please input a file");
        String fileUser = consoleScanner.nextLine();
        validateInput(fileUser, consoleScanner);
    }
        public static boolean readCSV(File file) {
        int numberOfColumns = 0;
        int c = 0;
        
        //checks if it can find file, if not return false
        if (!file.exists() || !file.isFile()) {
            System.out.println("Error: File not found or incorrect directory.");
            return false;
        }
        //checks if first line of .CSV is , if not return false
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                String firstLine = scanner.nextLine();
                if (!firstLine.startsWith(",")) {
                    System.out.println("Error: First character of the first line must be a comma.");
                    return false;
                }
                
                numberOfColumns = firstLine.split(",").length;
            }
            
            // Further processing of the CSV file can be done here
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to read the file.");
            return false;
        }
        
        return true;
    }
    //Validates user input based on certain parameters described below
    public static void validateInput(String fileUser, Scanner consoleScanner){
        if(fileUser.length() < 4){
            System.out.println("The file must be at least 5 characters including \".CSV\" as a suffix.");
            fileUser = consoleScanner.nextLine();
            validateInput(fileUser, consoleScanner); 
        } else if(!fileUser.substring(fileUser.length()-4).equals(".CSV")){
            System.out.println("Please input a CSV file. It must have \".CSV\" as a suffix.");
            fileUser = consoleScanner.nextLine();
            validateInput(fileUser, consoleScanner);    
        } else if(((fileUser.length()-4) == 0)) {
            System.out.println("Please input a CSV file. It must have more characters than just \".CSV\"");
            fileUser = consoleScanner.nextLine();
            validateInput(fileUser, consoleScanner); 
        } else {
            File file = new File(fileUser);
            readCSV(file);
        }
    }
    
    
}


