public class Panel extends RealEstate implements PanelInterface {
    private int floor;
    private boolean isInsulated;

    // Constructor
    public Panel(String city, double price, int sqm, double numberOfRooms, Genre genre, int floor, boolean isInsulated) {
        super(city, price, sqm, numberOfRooms, genre);
        this.floor = floor;
        this.isInsulated = isInsulated;
    }

    // Override getTotalPrice
    @Override
    public int getTotalPrice() {
        double totalPrice = super.getTotalPrice();
        if (floor >= 0 && floor <= 2) totalPrice *= 1.05;
        if (floor == 10) totalPrice *= 0.95;
        if (isInsulated) totalPrice *= 1.05;
        return (int) totalPrice;
    }

    // Override toString
    @Override
    public String toString() {
        return super.toString() + String.format(", Floor: %d, Insulated: %b", floor, isInsulated);
    }

    // Implements hasSameAmount
    @Override
    public boolean hasSameAmount(RealEstate other) {
        return this.getTotalPrice() == other.getTotalPrice();
    }

    // Implements roomPrice
    @Override
    public int roomPrice() {
        return (int) (price * sqm / numberOfRooms);
    }
}

