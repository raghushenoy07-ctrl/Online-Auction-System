import java.util.ArrayList; // Imports ArrayList to store users and auctions
import java.util.Scanner; // Imports Scanner for user input

public class Main {

    // Creates Scanner object to read keyboard input
    static Scanner sc = new Scanner(System.in);

    // Stores all registered users
    static ArrayList<User> users = new ArrayList<User>();

    // Stores all created auctions
    static ArrayList<Auction> auctions = new ArrayList<Auction>();

    // Generates unique user IDs
    static int userId = 1;

    // Generates unique auction IDs
    static int auctionId = 1;

    // Main method - program execution starts here
    public static void main(String[] args) {
        int choice;

        // Repeats the main menu until the user chooses Exit
        do {
            System.out.println("\n===== ONLINE AUCTION SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt(); // Reads menu choice
            sc.nextLine(); // Clears the input buffer

            // Executes the selected menu option
            switch (choice) {
                case 1:
                    register(); // Calls registration method
                    break;

                case 2:
                    login(); // Calls login method
                    break;

                case 3:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 3); // Continues until choice is 3
    }

    // Registers a new user
    public static void register() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        // Creates a new User object
        User user = new User(name, username, password);

        // Adds the registered user to the users list
        users.add(user);

        userId++; // Increments user ID counter
        System.out.println("Registration successful.");
    }

    // Handles user login
    public static void login() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        // Initially assumes that no user is logged in
        User currentUser = null;

        // Searches through all registered users
        for (int i = 0; i < users.size(); i++) {

            // Checks whether entered credentials match
            if (users.get(i).checkLogin(username, password)) {
                currentUser = users.get(i); // Stores matching user
                break; // Stops searching after successful login
            }
        }

        // Checks whether login was unsuccessful
        if (currentUser == null) {
            System.out.println("Invalid username or password.");
        } else {
            System.out.println("Welcome " + currentUser.getName());

            // Opens the user menu after successful login
            userMenu(currentUser);
        }
    }

    // Displays menu for logged-in users
    public static void userMenu(User user) {
        int choice;

        // Repeats menu until user selects Logout
        do {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. Create Auction");
            System.out.println("2. View Auctions");
            System.out.println("3. Place Bid");
            System.out.println("4. View Auction Details");
            System.out.println("5. Close Auction");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt(); // Reads user choice
            sc.nextLine(); // Clears input buffer

            // Performs the selected operation
            switch (choice) {
                case 1:
                    createAuction(user);
                    break;

                case 2:
                    viewAuctions();
                    break;

                case 3:
                    placeBid(user);
                    break;

                case 4:
                    viewDetails();
                    break;

                case 5:
                    closeAuction(user);
                    break;

                case 6:
                    System.out.println("Logged out.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6); // Repeats until logout
    }

    // Creates a new auction for the logged-in user
    public static void createAuction(User user) {
        System.out.print("Enter item name: ");
        String itemName = sc.nextLine();

        System.out.print("Enter starting price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        // Validates item name and starting price
        if (price > 0 && itemName.length() > 0) {

            // Creates auction with seller's username
            Auction auction = new Auction(auctionId, itemName, price,
                    user.getUsername());

            // Adds auction to the auction list
            auctions.add(auction);

            auctionId++; // Increments auction ID

            System.out.println("Auction created successfully.");
        } else {
            System.out.println("Enter valid item name and price.");
        }
    }

    // Displays all available auctions
    public static void viewAuctions() {

        // Checks whether any auctions exist
        if (auctions.size() == 0) {
            System.out.println("No auctions available.");
        } else {
            System.out.println("\n===== AUCTION LIST =====");

            // Iterates through all auctions
            for (int i = 0; i < auctions.size(); i++) {

                // Gets the auction at the current index
                Auction a = auctions.get(i);

                // Displays auction information
                System.out.println("ID: " + a.getId());
                System.out.println("Item: " + a.getItemName());
                System.out.println("Price: " + a.getPrice());
                System.out.println("Seller: " + a.getSellerName());

                // Displays whether the auction is active or closed
                if (a.isActive()) {
                    System.out.println("Status: Active");
                } else {
                    System.out.println("Status: Closed");
                }

                System.out.println("--------------------");
            }
        }
    }

    // Allows a logged-in user to place a bid
    public static void placeBid(User user) {
        viewAuctions(); // Displays auctions before bidding

        System.out.print("Enter auction ID: ");
        int id = sc.nextInt();

        System.out.print("Enter your bid amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        // Searches for the selected auction
        Auction auction = findAuction(id);

        // Checks whether the auction exists
        if (auction == null) {
            System.out.println("Auction not found.");
        } else {

            // Calls placeBid method of the Auction class
            auction.placeBid(user.getUsername(), amount);
        }
    }

    // Displays details of a particular auction
    public static void viewDetails() {
        System.out.print("Enter auction ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        // Searches for auction using its ID
        Auction auction = findAuction(id);

        if (auction == null) {
            System.out.println("Auction not found.");
        } else {

            // Displays complete auction information and bid history
            auction.showDetails();
        }
    }

    // Allows the seller to close their own auction
    public static void closeAuction(User user) {
        System.out.print("Enter auction ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        // Finds the auction using the entered ID
        Auction auction = findAuction(id);

        // Checks whether the auction exists
        if (auction == null) {
            System.out.println("Auction not found.");

        // Checks whether the logged-in user owns the auction
        } else if (auction.getSellerName().equals(user.getUsername()) == false) {
            System.out.println("You can close only your own auction.");

        // Checks whether the auction is already closed
        } else if (auction.isActive() == false) {
            System.out.println("Auction is already closed.");

        } else {

            // Closes the selected auction
            auction.closeAuction();
            System.out.println("Auction closed.");

            // Checks whether any bids were received
            if (auction.getHighestBidder().equals("No bids")) {
                System.out.println("No bids received.");
            } else {

                // Displays the winner and final winning price
                System.out.println("Winner: " + auction.getHighestBidder());
                System.out.println("Winning price: " + auction.getPrice());
            }
        }
    }

    // Searches for an auction using its unique ID
    public static Auction findAuction(int id) {

        // Iterates through the auction list
        for (int i = 0; i < auctions.size(); i++) {

            // Checks whether the current auction ID matches
            if (auctions.get(i).getId() == id) {
                return auctions.get(i); // Returns matching auction
            }
        }

        return null; // Returns null if auction is not found
    }
}