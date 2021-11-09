import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {



    public static void main(String[] args) throws IOException {
        String directory = "./src/text";
        String fileName = "contactsList.txt";
        Path textDirectory = Paths.get(directory);
        Path textPath = Paths.get(directory, fileName);

            if (Files.notExists(textDirectory)) {
                Files.createDirectory(textDirectory);
            }

            if (Files.notExists(textPath)) {
                Files.createFile(textPath);
            }

        Scanner scanner = new Scanner(System.in);
        //Checks to see if we have the text file in our system
        System.out.println(Files.exists(textPath));
        List<String> contacts = Arrays.asList("Joanna");
        Files.write(textPath, contacts);


        System.out.println("Hello, welcome to your Contacts Manager, choose from the follow options: ");
        System.out.println("1. View All Contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
        int userChoice = scanner.nextInt();

        switch (userChoice){
            case 1:
                try {
                List<String> printList = Files.readAllLines(textPath);
                    System.out.println(printList);
                } catch (IOException e){
                System.out.println("Could not find your file..");
                e.printStackTrace();
            }

        }


    }
}
