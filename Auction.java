import java.util.ArrayList; // Imports ArrayList to store bid history

public class Auction {

    // Stores auction ID
    private int id;

    // Stores the name of the auction item
    private String itemName;

    // Stores the current highest bid price
    private double price;

    // Stores the seller's username
    private String sellerName;

    // Stores the username of the highest bidder
    private String highestBidder;

    // Checks whether the auction is active or closed
    private boolean active;

    // Stores all bids placed on this auction
    private ArrayList<Bid> bids;

    // Constructor initializes auction details
    public Auction(int id, String itemName, double price, String sellerName) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.sellerName = sellerName;
        this.highestBidder = "No bids"; // Initially no bidder
        this.active = true; // Auction starts as active
        this.bids = new ArrayList<Bid>(); // Creates empty bid history
    }

    // Returns the auction ID
    public int getId() {
        return id;
    }

    // Returns the item name
    public String getItemName() {
        return itemName;
    }

    // Returns the current price
    public double getPrice() {
        return price;
    }

    // Returns the seller's name
    public String getSellerName() {
        return sellerName;
    }

    // Returns the highest bidder
    public String getHighestBidder() {
        return highestBidder;
    }

    // Returns whether the auction is active
    public boolean isActive() {
        return active;
    }

    // Method to place a new bid
    public void placeBid(String bidderName, double amount) {

        // Checks whether the auction is closed
        if (active == false) {
            System.out.println("Auction is closed.");

        // Prevents the seller from bidding on their own item
        } else if (bidderName.equals(sellerName)) {
            System.out.println("Seller cannot bid on own item.");

        // Checks whether the bid is higher than the current price
        } else if (amount <= price) {
            System.out.println("Bid must be higher than " + price);

        } else {
            price = amount; // Updates the current highest price
            highestBidder = bidderName; // Updates the highest bidder

            // Adds the new bid to bid history
            bids.add(new Bid(bidderName, amount));

            System.out.println("Bid placed successfully.");
        }
    }

    // Closes the auction by changing its status
    public void closeAuction() {
        active = false;
    }
}
