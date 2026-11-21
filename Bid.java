public class Bid {

    // Stores the name of the person placing the bid
    private String bidderName;

    // Stores the amount of the bid
    private double amount;

    // Constructor initializes bidder details
    public Bid(String bidderName, double amount) {
        this.bidderName = bidderName;
        this.amount = amount;
    }

    // Returns the bidder's name
    public String getBidderName() {
        return bidderName;
    }

    // Returns the bid amount
    public double getAmount() {
        return amount;
    }
}