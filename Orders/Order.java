package Orders;

import Customer.Customer;
import MenuItems.Dish;
import MenuItems.MenuItem;
import MyUtils.Equality;
import MyUtils.QuickSort;

public abstract class Order {
    protected static final Equality<MenuItem> EQUALITY = MenuItem::equals;
    protected static final Equality<MenuItem> NAME_EQUALITY =
            (o1, o2) -> o1.getName().equals(o2.getName());
    protected Customer customer;

    public abstract boolean add(MenuItem item);

    public abstract MenuItem[] getItems();

    public abstract String[] itemsNames();

    public abstract int itemsQuantity();

    public int itemQuantity(String itemName) {
        return itemQuantity(new Dish(itemName, ""), NAME_EQUALITY);
    }

    public int itemQuantity(MenuItem item) {
        return itemQuantity(item, EQUALITY);
    }

    protected abstract int itemQuantity(MenuItem item,
            Equality<? super MenuItem> equality);

    public boolean remove(String itemName) {
        return remove(new Dish(itemName, ""), NAME_EQUALITY);
    }

    public boolean remove(MenuItem item) {
        return remove(item, EQUALITY);
    }

    protected abstract boolean remove(MenuItem item,
            Equality<? super MenuItem> equality);

    public int removeAll(String itemName) {
        return removeAll(new Dish(itemName, ""), NAME_EQUALITY);
    }

    public int removeAll(MenuItem item) {
        return removeAll(item, EQUALITY);
    }

    protected abstract int removeAll(MenuItem item,
            Equality<? super MenuItem> equality);

    public MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] items = getItems();
        QuickSort.quickSort(items,
                (o1, o2) -> Double.compare(o1.getCost(), o2.getCost()));
        return items;
    }

    public abstract int costTotal();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
