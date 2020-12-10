package MenuItems;

import java.util.Objects;

public abstract class MenuItem {
    protected final int cost;
    protected final String name;
    protected final String description;

    public MenuItem(int cost, String name, String description) {
        this.cost = cost;
        this.name = name;
        this.description = description;
    }

    public MenuItem(String name, String description) {
        this(0, name, description);
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' +
                "cost=" + cost +
                ", name=" + name +
                //", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Double.compare(menuItem.cost, cost) == 0 &&
                name.equals(menuItem.name) &&
                description.equals(menuItem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, name, description);
    }
}
