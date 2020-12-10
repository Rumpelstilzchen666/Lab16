package MenuItems;

import java.util.Objects;

public class Drink extends MenuItem implements Alcoholable {
    private final double alcoholVol;

    public Drink(int cost, String name, String description, double alcoholVol) {
        super(cost, name, description);
        this.alcoholVol = alcoholVol;
    }

    public Drink(int cost, String name, String description) {
        this(cost, name, description, 0);
    }

    public Drink(String name, String description, double alcoholVol) {
        super(name, description);
        this.alcoholVol = alcoholVol;
    }

    public Drink(String name, String description) {
        this(name, description, 0);
    }

    @Override
    public boolean isAlcoholicDrink() {
        return !(alcoholVol == 0);
    }

    @Override
    public double getAlcoholVol() {
        return alcoholVol;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o) && (alcoholVol == ((Drink) o).alcoholVol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, name, description, alcoholVol);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' +
                "cost=" + cost +
                ", name=" + name +
                //", description=" + description +
                ", alcoholVol=" + alcoholVol +
                '}';
    }
}
