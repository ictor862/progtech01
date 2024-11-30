import java.io.*;
import java.util.*;

public class RealEstateAgent {
    private static TreeSet<RealEstate> properties = new TreeSet<>(Comparator.comparing(RealEstate::getTotalPrice));

    // Load data from file
    public static void loadProperties(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split("#");
            String type = data[0];
            String city = data[1];
            double price = Double.parseDouble(data[2]);
            int sqm = Integer.parseInt(data[3]);
            double numberOfRooms = Double.parseDouble(data[4]);
            Genre genre = Genre.valueOf(data[5]);
            if (type.equalsIgnoreCase("PANEL")) {
                int floor = Integer.parseInt(data[6]);
                boolean isInsulated = data[7].equalsIgnoreCase("yes");
                properties.add(new Panel(city, price, sqm, numberOfRooms, genre, floor, isInsulated));
            } else {
                properties.add(new RealEstate(city, price, sqm, numberOfRooms, genre));
            }
        }
        reader.close();
    }

    // Write output to file
    public static void writeOutput(String filename) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(filename));

        // Average sqm price
        double avgSqmPrice = properties.stream().mapToDouble(p -> p.price).average().orElse(0);
        writer.printf("Average sqm price: %.2f\n", avgSqmPrice);

        // Cheapest property
        RealEstate cheapest = properties.first();
        writer.printf("Cheapest property: %s\n", cheapest);

        // Most expensive property in Budapest
        RealEstate mostExpensiveInBudapest = properties.stream()
                .filter(p -> p.city.equalsIgnoreCase("Budapest"))
                .max(Comparator.comparing(RealEstate::getTotalPrice)).orElse(null);
        if (mostExpensiveInBudapest != null) {
            writer.printf("Avg sqm value per room (Budapest): %.2f\n", mostExpensiveInBudapest.averageSqmPerRoom());
        }

        // Total price
        int totalPrice = properties.stream().mapToInt(RealEstate::getTotalPrice).sum();
        writer.printf("Total price of properties: %d\n", totalPrice);

        // List of affordable condominiums
        double avgPrice = properties.stream().mapToInt(RealEstate::getTotalPrice).average().orElse(0);
        writer.println("Affordable condominiums:");
        properties.stream()
                .filter(p -> p.genre == Genre.CONDOMINIUM && p.getTotalPrice() <= avgPrice)
                .forEach(writer::println);

        writer.close();
    }

    // Main method
    public static void main(String[] args) throws IOException {
        loadProperties("realestates.txt");
        writeOutput("outputRealEstate.txt");
    }
}

