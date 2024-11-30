public interface PropertyInterface {
    void makeDiscount(int percentage); // Reduces price per sqm by given percentage
    int getTotalPrice();               // Returns total price considering city modifiers
    double averageSqmPerRoom();        // Returns average square meters per room
}

