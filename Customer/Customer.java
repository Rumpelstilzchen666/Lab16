package Customer;

public class Customer {
    public static final Customer MATURE_UNKNOWN_CUSTOMER =
            new Customer(18);
    public static final Customer NOT_MATURE_UNKNOWN_CUSTOMER =
            new Customer(0);
    private final String firstName, secondName;
    private int age;
    private Address address;

    public Customer(String firstName, String secondName, int age,
            String address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.address = (address == null
                ? Address.EMPTY_ADDRESS : new Address(address));
    }

    public Customer(String firstName, String secondName, int age) {
        this(firstName, secondName, age, null);
    }

    public Customer(String firstName, int age) {
        this(firstName, null, age, null);
    }

    public Customer(int age) {
        this(null, null, age, null);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        String s = "";
        if(firstName != null) {
            s = "Имя = " + firstName + ", ";
        }
        if(secondName != null) {
            s += "Фамилия = " + secondName + ", ";
        }
        s += "Возраст = " + age;
        if(address != null) {
            s += ", Адрес = " + address;
        }
        return "Customer{" + s + "}";
    }
}
