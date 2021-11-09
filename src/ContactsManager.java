import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContactsManager {



    public static void main(String[] args) {
        String directory = "./src/text";
        String fileName = "contactsList.txt";
        Path textDirectory = Paths.get(directory);
        Path textFile = Paths.get(directory, fileName);

        try {
            if (Files.notExists(textDirectory)) {
                Files.createDirectory(textDirectory);
            }
        } catch (IOException e) {
            System.out.println(("Oops, we didn't find the file correctly..."));
            e.printStackTrace();
        }

        try {
            if (Files.notExists(textFile)) {
                Files.createFile(textFile);
            }
        } catch (IOException e) {
            System.out.println(("Oops, we didn't find the file correctly..."));
            e.printStackTrace();
        }

        //Checks to see if we have the text file in our system
        System.out.println(Files.exists(textFile));


        System.out.println("Hello, welcome to your Contacts Manager, choose from the follow options: ");
        System.out.println("1. View All Contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");


    }
}
