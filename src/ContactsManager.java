import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ContactsManager {

    public static void executeApp() throws IOException {
        String directory = "./src/text";
        String fileName = "contactsList.txt";
        Path textDirectory = Paths.get(directory);
        Path textPath = Paths.get(directory, fileName);
        Scanner scanner = new Scanner(System.in);
        TreeMap<String, String> contactsMap = new TreeMap<>();
        List<String> contactsList;
        boolean useManager;

        createDirectory(textDirectory);
        createTextPath(textPath);

        System.out.println("Hello, and welcome to your Contacts Manager!");

        do {
            mainMenu();

            int userChoice = scanner.nextInt();
            scanner.nextLine(); //needed because .nextInt() doesn't register when the enter key has been pressed and requires this to end the .nextInt()'s continuing search for an integer. This allows the next scanner method to work whatever that method happens to be.
            switch (userChoice) { //TODO: refactor all of the cases -> enhanced switch cases.
                case 1:
                    getPhoneNumberList(textPath);
                    break;
                case 2:
                    //TODO: create method for add contact.

                    System.out.println("What is the name of your contact?");
                    String contact = scanner.nextLine();

                    System.out.println("What's their number?");
                    long number = scanner.nextLong();
                    int numLength = Long.toString(number).length();
                    char[] oldNumArr = Long.toString(number).toCharArray();
                    String newNumStr = "";
                    int firstThree = 0;
                    int secondThree = 0;

                    //TODO: create method to check for num input length. Begin check method code.

                    switch (numLength) {
                        case (7) -> {
                            for (int i = 0; i < oldNumArr.length; i++) {
                                newNumStr += oldNumArr[i];
                                firstThree++;
                                if (firstThree == 3) {
                                    break;
                                }
                            }
                            newNumStr += "-";
                            for (int i = 3; i < oldNumArr.length; i++) {
                                newNumStr += oldNumArr[i];
                            }
                            contactsMap.put(contact, newNumStr);
                            List<String> test = List.of("Name: " + contact + " & Phone Number: " + newNumStr);
                            Files.write(textPath, test, StandardOpenOption.APPEND);
                        }
                        case (10) -> {
                            for (int i = 0; i < oldNumArr.length; i++) {
                                newNumStr += oldNumArr[i];
                                firstThree++;
                                if (firstThree == 3) {
                                    break;
                                }
                            }
                            newNumStr += "-";
                            for (int i = 3; i < oldNumArr.length; i++) {
                                newNumStr += oldNumArr[i];
                                secondThree++;
                                if (secondThree == 3) {
                                    break;
                                }
                            }
                            newNumStr += "-";
                            for (int i = 6; i < oldNumArr.length; i++) {
                                newNumStr += oldNumArr[i];
                            }

                            //End check method code.

                            contactsMap.put(contact, newNumStr);
                            List<String> test = List.of("Name: " + contact + " & Phone Number: " + newNumStr);
                            Files.write(textPath, test, StandardOpenOption.APPEND);
                        }
                        default -> System.out.println("That's not a valid format. Please try again.");
                    }

                    Files.readAllLines(textPath);
                    break;
                case 3:
                    System.out.println("Who would you like to search for?");

                    //TODO: create searchContact method.

                    String search = scanner.nextLine();
                    contactsList = Files.readAllLines(textPath);
                    for(String line : contactsList){
                        if(line.contains(search)){
                            System.out.println(line);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Who would you like to delete?");

                    //TODO: create deleteContact method.

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
                    } catch(ConcurrentModificationException e){
                        e.printStackTrace();
                    }

                    System.out.println("Deleted: " + delete + "! View your list to see the changes.");
                    break;
                case 5:
                    System.out.println("Ok, great. See you next time!");
                    return;
                default:
                    System.out.println("That number wasn't one of the options!");
                    break;
            }
            System.out.println("Do you want to return to the main menu?");
            useManager = scanner.next().equalsIgnoreCase("y");
        } while(useManager);

        System.out.println("Ok, great. See you next time!");
    }

    public static void createDirectory(Path directory) throws IOException {
        if (Files.notExists(directory)) {
                Files.createDirectory(directory);
        }
    }

    public static void createTextPath(Path textPath) throws IOException {
        if (Files.notExists(textPath)) {
                Files.createFile(textPath);
        }
    }

    private static void mainMenu() {
        System.out.println("Choose from the following options:");
        System.out.println("1. View All Contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
    }

    public static void getPhoneNumberList(Path textPath) throws IOException {
        List<String> printList = Files.readAllLines(textPath);
        for (String phoneNumber : printList) {
            System.out.println(phoneNumber);
        }
    }

    public static void main(String[] args) throws IOException {
        executeApp();
    }
}