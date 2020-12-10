package MenuItems;

public enum DrinkType implements Alcoholable {
    BEER("Beer", "Beer description", 5),
    WINE("Wine", "Wine description", 11),
    VODKA("Vodka", "Vodka description", 40),
    BRANDY("Brandy", "Brandy description", 37.5),
    CHAMPAGNE("Champagne", "Champagne description", 12),
    WHISKEY("Whiskey", "Whiskey description", 42),
    TEQUILA("Tequila", "Tequila description", 50),
    RUM("Rum", "Rum description", 50),
    VERMOUTH("Vermouth", "Vermouth description", 18),
    LIQUOR("Liquor", "Liquor description", 97),
    JAGERMEISTER("Jägermeister", "Jägermeister description", 35),
    JUICE("Juice", "Juice description"),
    COFFEE("Coffee", "Coffee description"),
    GREEN_TEE("Green tee", "Green tee description"),
    BLACK_TEE("Black tee", "Black tee description"),
    MILK("Milk", "Milk description"),
    WATER("Water", "Water description"),
    SODA("Soda", "Soda description");
    private final String name;
    private final String description;
    private final double alcoholVol;

    DrinkType(String name, String description, double alcoholVol) {
        this.name = name;
        this.description = description;
        this.alcoholVol = alcoholVol;
    }

    DrinkType(String name, String description) {
        this(name, description, 0);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean isAlcoholicDrink() {
        return alcoholVol != 0;
    }

    @Override
    public double getAlcoholVol() {
        return alcoholVol;
    }

    @Override
    public String toString() {
        return "DrinkType{" +
                "name=" + name +
                ", alcoholVol=" + alcoholVol +
                '}';
    }
}
