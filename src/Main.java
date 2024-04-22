import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Welcome to CarPlate App");
        System.out.println("1. Customize a Plate");
        System.out.println("2. Random Plate");
        System.out.println("3. govermmental plates");
        System.out.println("4. Exit");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        switch (choice) {
            case 1:
                customizePlate();

                break;
            case 2:
                generateRandomPlate();
                break;
            case 3:
                choosingGovern();
                break;
            case 4:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                break;
        }
        scanner.close();
        System.out.println("Program has ended.");
    }

    private static int calculateExtraCosts(String digits, String letters, String lastDigits) {
        int extraCost = 0;

        // Check for repeated adjacent digits in the first two digits
        if (digits.charAt(0) == digits.charAt(1)) {
            extraCost += 20000; // Add $20,000 if the first two digits are the same
        }

        // Check for matching letters
        if (letters.charAt(0) == letters.charAt(1)) {
            extraCost += 5000; // Add $5,000 if the two letters are the same
        }

        // Check if any digits in the last three digits are repeated
        int count = 0;
        if (lastDigits.charAt(0) == lastDigits.charAt(1)) count++;
        if (lastDigits.charAt(1) == lastDigits.charAt(2)) count++;
        if (lastDigits.charAt(0) == lastDigits.charAt(2)) count++;
        if (count == 1) extraCost += 20000; // Two digits are the same, not three
        else if (count == 3) extraCost += 30000; // All three digits are the same

        return extraCost;
    }



    private static void customizePlate() {
        try {
            System.out.println("Customize a Plate:");
            System.out.print("Enter first two digits: ");
            String digits = scanner.next("\\d{2}");
            scanner.nextLine();  // Clear buffer after numeric input

            System.out.print("Enter two uppercase letters: ");
            String letters = scanner.next("[A-Z]{2}");
            scanner.nextLine();  // Clear buffer after letter input

            System.out.print("Enter last three digits: ");
            String lastDigits = scanner.next("\\d{3}");
            scanner.nextLine();  // Clear buffer after final numeric input

            String customPlate = digits + " " + letters + " " + lastDigits;
            System.out.println("Your customized plate is: " + customPlate);
            int extraCost = calculateExtraCosts(digits, letters, lastDigits);
            System.out.println("Extra cost for customization: $" + extraCost);

        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter the correct format as indicated.");
            scanner.nextLine();  // Attempt to consume the incorrect input to reset
        }
    }

    private static void generateRandomPlate() {
        System.out.println("Random Plate:");
        String randomPlate = String.format("%02d %c%c %03d",
                random.nextInt(100),
                (char) ('A' + random.nextInt(26)),
                (char) ('A' + random.nextInt(26)),
                random.nextInt(1000));
        System.out.println("Your random plate is: " + randomPlate);
    }

    private static void choosingGovern() {
        System.out.println("Do you want to:");
        System.out.println("1. Customize a Governmental Plate");
        System.out.println("2. Get a Random Governmental Plate");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        if (choice == 1) {
            // User chooses to customize a governmental plate
            selectPlateTypeForCustomization();
        } else if (choice == 2) {
            // User chooses a random governmental plate
            generateGovernmentalPlate();
        } else {
            System.out.println("Invalid choice. Please select 1 or 2.");
        }
    }

    private static void selectPlateTypeForCustomization() {
        System.out.println("Select Type of Governmental Plate to Customize:");
        System.out.println("1. Police Plate (Code: II)");
        System.out.println("2. Ministry of Finance Plate (Code: FF)");
        System.out.println("3. State Security Plate (Code: SS)");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        if (choice == 1) {
            customizeGovernmentalPlate("II");
        }else if (choice == 2) {
            customizeGovernmentalPlate("FF");
        } else if (choice == 3) {
            customizeGovernmentalPlate("SS");
        } else {
            System.out.println("Invalid choice. Please select 1 or 2.");
        }
    }


    private static void generateGovernmentalPlate() {
        System.out.println("Select Type of Governmental Plate:");
        System.out.println("1. Police Plate (Code: II)");
        System.out.println("2. Ministry of Finance Plate (Code: FF)");
        System.out.println("3. State Security Plate (Code: SS)");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        switch (choice) {
            case 1:
                generateSpecificGovernmentalPlate("II");
                break;
            case 2:
                generateSpecificGovernmentalPlate("FF");
                break;
            case 3:
                generateSpecificGovernmentalPlate("SS");
                break;

            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                break;
        }
    }

    private static void generateSpecificGovernmentalPlate(String code) {
        System.out.println("Governmental Plate:");
        // Assume governmental plates have a special code in the middle
        String plateNumber = String.format("%02d %s %03d", random.nextInt(100), code, random.nextInt(1000));
        System.out.println("Your governmental plate is: " + plateNumber);
    }
    private static void customizeGovernmentalPlate(String code) {
        try {
            System.out.println("Customize Your Governmental Plate:");
            System.out.print("Enter the first two digits: ");
            String firstTwoDigits = scanner.next("\\d{2}");
            scanner.nextLine();  // Clear buffer after numeric input

            System.out.print("Enter the last three digits: ");
            String lastThreeDigits = scanner.next("\\d{3}");
            scanner.nextLine();  // Clear buffer after numeric input

            String customPlate = String.format("%s %s %s", firstTwoDigits, code, lastThreeDigits);
            System.out.println("Your customized governmental plate is: " + customPlate);
        } catch (Exception e) {
            System.out.println("Error: Please enter the correct format as indicated.");
            scanner.nextLine();  // Clear the buffer in case of incorrect input
        }
    }
}





