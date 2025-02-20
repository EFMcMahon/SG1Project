/*
This program is coded is Java
Elijah Kern, Eric McMahon, Febuary 21 2025, CMP_SCI 4500 Intro To Software Profession 
This program will take in a CSV and print names in the order listed to the Names.txt file and dates to the DatedData.txt. It will then create a PresentAbsent.txt that will print to it what days did and didn’t have a value.

fileUser used to store user input 

used: https://introcs.cs.princeton.edu/java/11cheatsheet/ to refresh knowledge on java 
used: https://devmountain.com/blog/what-is-github-and-how-do-you-use-it/ to relearn how to use github with other developers 
used: https://www.w3schools.com/ figure out do file inputs with java
*/
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main {
    //Prints opening message and stores nex line of input from user in fileUser
    public static void main(String[] args) throws IOException {
        System.out.println("This program will take in a CSV and print names in the order listed to the Names.txt file and dates to the DatedData.txt. It will then create a PresentAbsent.txt that will print to it what days did and didn’t have a value.");
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Please input a file");
        String fileUser = consoleScanner.nextLine();
        validateInput(fileUser, consoleScanner);
    }
        public static boolean readCSV(File file) throws IOException {
        FileWriter dates = new FileWriter("DatedData.txt");
        int numberOfColumns = 0;
        int lineNumber = 0;
        int count = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int lineCount = 0;
        while(reader.readLine() != null) lineCount++;
        reader.close();
        System.out.println(lineCount);
        
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
            
            while (lineNumber < lineCount) {
                // This if statement is built to read the first line of the CSV and print unique names to the Names.txt file
                if (lineNumber == 1) {
                    FileWriter names = new FileWriter("Names.txt");
                    BufferedReader lineReader = new BufferedReader(new FileReader(file));
                    String columnNames = lineReader.readLine();
                    String[] nameList = columnNames.substring(1).split(",");
                    BufferedWriter nameWriter = new BufferedWriter(new FileWriter("Names.txt"));
                    for (String name : nameList) {
                        // TODO need to make sure non unique names are overwritten and not duplicated
                        nameWriter.write(name);
                        nameWriter.newLine();
                    }
                    lineReader.close();
                    nameWriter.close();
                    names.close();  
            } else if (lineNumber > 1){
                
                BufferedReader dateReader = new BufferedReader(new FileReader(file));
                String listedDates = dateReader.readLine();
                while (count < lineNumber) {
                    listedDates = dateReader.readLine();
                    count++;
                }
                String[] dateList = listedDates.split(",");
                
                BufferedWriter dateWriter = new BufferedWriter(new FileWriter("DatedData.txt"));
                // TODO Only the last date is being printed because it the date is getting overwritten each time
                dateWriter.write(dateList[0]);
                dateWriter.newLine();
                dateReader.close();
                dateWriter.close();
                dates.close();
            } 
                count = 0;
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to read the file.");
            return false;
        }
        
        return true;
    }
    //Validates user input based on certain parameters described below
    public static void validateInput(String fileUser, Scanner consoleScanner) throws IOException{
        if(fileUser.length() < 4){
            System.out.println("The file must be at least 5 characters including \".CSV\" as a suffix.");
            fileUser = consoleScanner.nextLine();
            validateInput(fileUser, consoleScanner); 
        } else if(!fileUser.substring(fileUser.length()-4).contains(".CSV")){
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


