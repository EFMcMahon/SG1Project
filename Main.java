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
    public static void main(String[] args) throws IOException {
        System.out.println("This program will take in a CSV and print names in the order listed to the Names.txt file and dates to the DatedData.txt. It will then create a PresentAbsent.txt that will print to it what days did and didn’t have a value.");
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Please input a file");
        String fileUser = consoleScanner.nextLine();
        validateInput(fileUser, consoleScanner);
    }

    public static boolean readCSV(File file) throws IOException 
    {
        int lineNumber = 0;

        if (!file.exists() || !file.isFile()) 
        {
            System.out.println("Error: File not found or incorrect directory.");
            return false;
        }
        
        //BufferedWriters for Dated.Data and PresentAbsent
        BufferedWriter dateWriter = new BufferedWriter(new FileWriter("DatedData.txt"));
        BufferedWriter presentAbsentWriter = new BufferedWriter(new FileWriter("PresentAbsent.txt"));

        //Reads file line by line
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            line = reader.readLine();
            lineNumber++;

        //Only first line is read for names
            if (lineNumber == 1) 
            {
                FileWriter names = new FileWriter("Names.txt");
                String[] nameList = line.substring(1).split(",");
                BufferedWriter nameWriter = new BufferedWriter(new FileWriter("Names.txt"));
                for (String name : nameList) 
                {
                    nameWriter.write(name);
                    nameWriter.newLine();
                }
                nameWriter.close();
                names.close();
            }

            //Read the rest of the lines until the line is empty or null 
            while ((line = reader.readLine()) != null) 
            {
                if (line.trim().isEmpty()) 
                {
                    break;
                }
                
                //splits line by commas
                String[] dateList = line.split(",");
                if (dateList.length > 0) 
                {
                    //writes onyl date in DatedData 
                    dateWriter.write(dateList[0]);
                    dateWriter.newLine();
                    
                    //List that stores converted values to 1's and 0's
                    List<String> presentAbsentList = new ArrayList<>();
                    
                    for (int i = 1; i < dateList.length; i++) 
                    {
                        String value = dateList[i].trim();
                        if (!value.isEmpty()) 
                        {
                            double num = Double.parseDouble(value);
                            presentAbsentList.add(num != 0 ? "1" : "0");
                        }
                    }
                    //joins values with commas and write to presentabsent
                    presentAbsentWriter.write(String.join(", ", presentAbsentList));
                    presentAbsentWriter.newLine();
                }
            }
        } catch (FileNotFoundException e) 
        {
            System.out.println("Error: Unable to read the file.");
            return false;
        }

        dateWriter.close();
        presentAbsentWriter.close();
        return true;
    }
    //
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
