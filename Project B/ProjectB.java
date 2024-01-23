import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// main class
public class ProjectB {
    public static void main(String[] args) {
        BurgerMenu.displayMenu();
        HashMap<String, FoodItem> burgerOrder = OrderProcess.processBurgerOrder();

        SoftDrinkMenu.displaySoftDrinks();
        HashMap<String, FoodItem> softDrinkOrder = OrderProcess.processSoftDrinkOrder();

        OrderProcess.displayOrder(burgerOrder, softDrinkOrder);
    }
}

// parent class
class FoodItem {
    private String name;
    private double price;
    private int preparationTime;
    private int quantity;

    public FoodItem(String name, double price, int preparationTime) {
        this.name = name;
        this.price = price;
        this.preparationTime = preparationTime;
        this.quantity = 0;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void order(int quantity) {
        setQuantity(quantity);
    }

    public double calculateTotal() {
        return getQuantity() * getPrice();
    }

    public void displayOrder() {
        System.out.println("Order Details for " + getName() + ":");
        System.out.println("Quantity: " + getQuantity());
        System.out.println("Total Price: $" + calculateTotal());
    }
}

// child class 1
class BurgerMenu extends FoodItem {
    private static ArrayList<FoodItem> menu = new ArrayList<>(Arrays.asList(
            new BurgerMenu("Cheeseburger", 5.99, 2),
            new BurgerMenu("Veggie Burger", 4.99, 3),
            new BurgerMenu("Chicken Burger", 6.99, 4),
            new BurgerMenu("Crispy Chicken Burger", 7.99, 4)));

    public BurgerMenu(String name, double price, int preparationTime) {
        super(name, price, preparationTime);
    }

    public static void displayMenu() {
        System.out.println("Welcome to Waruna Shan's Burger Hut! Menu:");
        for (int i = 0; i < menu.size(); i++) {
            FoodItem item = menu.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
    }

    public static ArrayList<FoodItem> getMenu() {
        return menu;
    }

    public static int calculateTotalTime(HashMap<String, FoodItem> order) {
        return order.values().stream().mapToInt(item -> item.getQuantity() * item.getPreparationTime()).sum();
    }
}

// child class 2
class SoftDrinkMenu extends FoodItem {
    private static ArrayList<FoodItem> softDrinks = new ArrayList<>(Arrays.asList(
            new SoftDrinkMenu("Soft Drink - Coke", 1.99, 0),
            new SoftDrinkMenu("Soft Drink - Pepsi", 1.99, 0),
            new SoftDrinkMenu("Soft Drink - Sprite", 1.99, 0),
            new SoftDrinkMenu("Soft Drink - Fanta", 1.99, 0)));

    public SoftDrinkMenu(String name, double price, int preparationTime) {
        super(name, price, preparationTime);
    }

    public static void displaySoftDrinks() {
        System.out.println("\nOrder for Soft Drinks:");
        System.out.println("Please select your desired soft drink from the list below:");
        for (int i = 0; i < softDrinks.size(); i++) {
            FoodItem drink = softDrinks.get(i);
            System.out.println((i + 1) + ". " + drink.getName() + " - $" + drink.getPrice());
        }
    }

    public static ArrayList<FoodItem> getSoftDrinks() {
        return softDrinks;
    }

    public static int calculateTotalTime(HashMap<String, FoodItem> order) {
        return order.values().stream().mapToInt(item -> item.getQuantity() * item.getPreparationTime()).sum();
    }
}

// other class
class OrderProcess {
    public static HashMap<String, FoodItem> processBurgerOrder() {
        HashMap<String, FoodItem> burgerOrder = new HashMap<>();
        ArrayList<FoodItem> menu = BurgerMenu.getMenu();

        System.out.println("Place your burger order:");
        for (FoodItem item : menu) {
            System.out.print("How many " + item.getName() + "s would you like to order? (Enter 0 if none): ");
            int quantity = In.nextInt(); // Assuming In class is available for input
            item.order(quantity);
            if (item.getQuantity() > 0) {
                burgerOrder.put(item.getName(), item);
            }
        }

        return burgerOrder;
    }

    public static HashMap<String, FoodItem> processSoftDrinkOrder() {
        HashMap<String, FoodItem> softDrinkOrder = new HashMap<>();
        ArrayList<FoodItem> softDrinks = SoftDrinkMenu.getSoftDrinks();

        System.out.println("Place your soft drink order:");

        System.out.println("Select a soft drink by entering its number:");

        int selectedDrinkNumber = In.nextInt();

        while (selectedDrinkNumber >= 1 && selectedDrinkNumber <= softDrinks.size()) {
            FoodItem selectedDrink = softDrinks.get(selectedDrinkNumber - 1);

            System.out.print("How many " + selectedDrink.getName() + "s would you like to order? (Enter 0 if none): ");
            int quantity = In.nextInt();
            selectedDrink.order(quantity);

            if (selectedDrink.getQuantity() > 0) {
                softDrinkOrder.put(selectedDrink.getName(), selectedDrink);
            }

            System.out.print("Do you want any other soft drinks? (Enter 'yes' or 'no'): ");
            String continueOrder = In.nextLine().toLowerCase();

            if (continueOrder.equals("no")) {
                break;
            }

            // Show soft drink menu again
            System.out.println("Select a soft drink by entering its number:");
            SoftDrinkMenu.displaySoftDrinks();
            selectedDrinkNumber = In.nextInt();
        }

        return softDrinkOrder;
    }

    public static void displayOrder(HashMap<String, FoodItem> burgerOrder, HashMap<String, FoodItem> softDrinkOrder) {
        System.out.println("------------------------------------------");
        System.out.println("Order Summary:");
        System.out.println("------------------------------------------");

        for (FoodItem item : burgerOrder.values()) {
            item.displayOrder();
        }

        for (FoodItem drink : softDrinkOrder.values()) {
            drink.displayOrder();
        }

        int totalPreparationTime = BurgerMenu.calculateTotalTime(burgerOrder) +
                SoftDrinkMenu.calculateTotalTime(softDrinkOrder);

        System.out.println("------------------------------------------");
        System.out.println("\nTotal Preparation Time: " + totalPreparationTime + " minutes");
        System.out.println("------------------------------------------");
        System.out.println("\nThank you for choosing Waruna Shan's Burgers! Enjoy your food!");
        System.out.println("------------------------------------------");
    }
}

