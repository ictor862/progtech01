// Enum for property type
enum Genre {
    FAMILYHOUSE, CONDOMINIUM, FARM
}

// RealEstate class implementing PropertyInterface
public class RealEstate implements PropertyInterface {
    protected String city;
    protected double price;
    protected int sqm;
    protected double numberOfRooms;
    protected Genre genre;

    // Constructor
    public RealEstate(String city, double price, int sqm, double numberOfRooms, Genre genre) {
        this.city = city;
        this.price = price;
        this.sqm = sqm;
        this.numberOfRooms = numberOfRooms;
        this.genre = genre;
    }

    // Implements makeDiscount
    @Override
    public void makeDiscount(int percentage) {
        price -= price * percentage / 100.0;
    }

    // Implements getTotalPrice
    @Override
    public int getTotalPrice() {
        double totalPrice = price * sqm;
        if (city.equalsIgnoreCase("Budapest")) totalPrice *= 1.3;
        else if (city.equalsIgnoreCase("Debrecen")) totalPrice *= 1.2;
        else if (city.equalsIgnoreCase("Nyíregyháza")) totalPrice *= 1.15;
        return (int) totalPrice;
    }

    // Implements averageSqmPerRoom
    @Override
    public double averageSqmPerRoom() {
        return sqm / numberOfRooms;
    }

    // toString
    @Override
    public String toString() {
        return String.format("City: %s, Price: %.2f, Sqm: %d, Rooms: %.1f, Genre: %s, TotalPrice: %d, AvgSqmPerRoom: %.2f",
                city, price, sqm, numberOfRooms, genre, getTotalPrice(), averageSqmPerRoom());
    }
}

