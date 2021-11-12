import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ContactsManager {


    public static void main(String[] args) {
        try {
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
            TreeMap<String, Long> contactsMap = new TreeMap<>();
            List<String> contactsList = List.of();
            boolean useManager = true;

            do {
                System.out.println("Hello, welcome to your Contacts Manager, choose from the follow options: ");
                System.out.println("1. View All Contacts");
                System.out.println("2. Add a new contact");
                System.out.println("3. Search a contact by name");
                System.out.println("4. Delete an existing contact");
                System.out.println("5. Exit");
                System.out.println("Enter an option (1, 2, 3, 4 or 5):");
                int userChoice = scanner.nextInt();
                switch (userChoice) {
                    case 1:
                        List<String> printList = Files.readAllLines(textPath);
                        for (int i = 0; i < printList.size(); i++) {
                            System.out.println(printList.get(i));
                        }
                        break;
                    case 2:
                        System.out.println("What is the first name of your contact?");
                        String contact = scanner.next();

                        System.out.println("What's their number?");
                        long number = scanner.nextLong();

                        contactsMap.put(contact, number);
                        System.out.println("contacts = " + contactsMap);

                        List<String> test = List.of("Name: " + contact + " & Phone Number: " + number);
                        Files.write(textPath, test, StandardOpenOption.APPEND);

                        Files.readAllLines(textPath);
                        break;
                    case 3:
                        System.out.println("Who would you like to search for?");
                        String search = scanner.next();
                        contactsList = Files.readAllLines(textPath);
                        for (String line : contactsList) {
                            if (line.contains(search)) {
                                System.out.println(line);
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Who would you like to delete?");
                        String delete = scanner.next();
                        contactsList = Files.readAllLines(textPath);
                        List<String> replacement = new ArrayList<>();
                        try {
                            for (String line : contactsList) {
                                if (line.contains(delete)) {
                                    continue;
                                } else {
                                    replacement.add(line);
                                }
                            }
                            Files.write(textPath, replacement);
                        } catch (ConcurrentModificationException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Deleted: " + delete + "! View your list to see the changes.");
                        break;
                    case 5:
                        System.out.println("Ok, bye.");
                        return;
                    default:
                        System.out.println("default");
                        break;
                }
                System.out.println("Do you want to continue?");
                useManager = scanner.next().equalsIgnoreCase("y");
            } while (useManager);
            System.out.println("Cool. Peace out.");
        } catch (ConcurrentModificationException | IOException e) {
            System.out.println("It's taken care of!");
        }
    }
}
