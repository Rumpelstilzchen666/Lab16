package MenuItems;

public class Dish extends MenuItem {
    public Dish(int cost, String name, String description) {
        super(cost, name, description);
    }

    public Dish(String name, String description) {
        super(name, description);
    }
}
