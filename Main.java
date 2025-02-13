import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("This program will take in a CSV and print names in the order listed to the Names.txt file and dates to the DatedData.txt. It will then create a PresentAbsent.txt that will print to it what days did and didn’t have a value.");
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Please input a file");
        String fileUser = consoleScanner.nextLine();
        validateInput(fileUser, consoleScanner);
    }
    public static void readCSV(File file) {
        
    }
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

