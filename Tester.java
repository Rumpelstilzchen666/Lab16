import Customer.*;
import MenuItems.*;
import Orders.*;
import OrdersManagers.*;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Tester {
    private static final MenuItem
            item1 = new Drink(13, "Item1", "Item1 description", 1),
            item2 = new Dish(14, "Item2", "Item2 description"),
            item3 = new Drink("Item3", "Item3 description"),
            item4 = new Dish(2, "Item4", "Item4 description"),
            item5 = new Drink(7, "Item5", "Item5 description");

    public static void main(String[] args) {
        print(DrinkType.BEER.toString());
        print(DrinkType.MILK.getName());
        print(DrinkType.RUM.toString() + "\n\n");

        TableOrder tableOrder = new TableOrder(4);
        testOrder(tableOrder);
        print("getTableNumber() = " + tableOrder.getTableNumber() + "\n\n");
        InternetOrder internetOrder = new InternetOrder(Customer.MATURE_UNKNOWN_CUSTOMER);
        testOrder(internetOrder);
        print("getCustomer() = " + internetOrder.getCustomer() + "\n\n");

        TableOrdersManager tableManager = new TableOrdersManager(5);
        testOrdersManager(tableManager);
        print("\n\n");
        InternetOrdersManager internetManager = new InternetOrdersManager();
        testOrdersManager(internetManager);

    }

    public static void testOrder(@NotNull Order order) {
        print("Test " + order.toString());
        print("costTotal() = " + order.costTotal());
        print("getItems() = " + Arrays.toString(order.getItems()));
        print("add(" + item1 + ") = " + order.add(item1));
        print("add(" + item2 + ") = " + order.add(item2));
        print("add(" + item3 + ") = " + order.add(item3));
        print("getItems() = " + order.costTotal());
        print("getItems() = " + Arrays.toString(order.getItems()));
        print("sortedItemsByCostDesc() = " + Arrays.toString(order.sortedItemsByCostDesc()));
        print("itemQuantity(" + item1 + ") = " + order.itemQuantity(item1));
        print("add(" + item4 + ") = " + order.add(item4));
        print("add(" + item1 + ") = " + order.add(item1));
        print("removeAll(" + "Item1" + ") = " + order.removeAll("Item1"));
        print("toString() = " + order);
        print("getItems() = " + Arrays.toString(order.getItems()));
        print("sortedItemsByCostDesc() = " + Arrays.toString(order.sortedItemsByCostDesc()));
    }

    public static void testOrdersManager(@NotNull TableOrdersManager manager) {
        TableOrder order1 = new TableOrder(1, item1, item3, item4),
                order2 = new TableOrder(4, item1, item5),
                order3 = new TableOrder(10, item1, item3, item2),
                order4 = new TableOrder(1, item5, item3, item5),
                order5 = new TableOrder(0, item5, item3, item5);
        testOrdersManager(manager, order1, order2, order3, order4, order5);
        print("freeTableNumbers() = " + Arrays.toString(manager.freeTableNumbers()));
        print("addItem(" + item5 + ", " + 1 + ") = " + manager.addItem(item5, 1));
        print("addItem(" + item3 + ", " + 3 + ") = " + manager.addItem(item3, 3));
        print("getOrder(" + 1 + ") = " + manager.getOrder(1));
        print("getOrder(" + 5 + ") = " + manager.getOrder(5));
        print("ordersQuantity() = " + manager.ordersQuantity());
        print("ordersCostSummary() = " + manager.ordersCostSummary());
        print("itemsQuantity(" + "Item1" + ") = " + manager.itemsQuantity("Item1"));
        print("itemsQuantity(" + item5 + ") = " + manager.itemsQuantity(item5));
        print("remove(" + 1 + ") = " + manager.remove(1));
        print("remove(" + 2 + ") = " + manager.remove(2));
        print("remove(" + "order1" + ") = " + manager.remove(order1));
        print("removeAll(" + "order2"  + ") = " + manager.removeAll(order2));
        print("freeTableNumbers() = " + Arrays.toString(manager.freeTableNumbers()));
    }

    public static void testOrdersManager(@NotNull InternetOrdersManager manager) {
        Customer customer1 = new Customer("FirstName", "SecondName", 29),
                customer2 = new Customer("Name", 17);
        InternetOrder order1 = new InternetOrder(customer1, item1, item3, item4),
                order2 = new InternetOrder(customer2, item1, item5),
                order3 = new InternetOrder(customer1, item1, item3, item2),
                order4 = new InternetOrder(null, item5, item3, item5);
        testOrdersManager(manager, order1, order2, order3, order4);
        print("remove() = " + manager.remove());
        print("order() = " + manager.order());
        while(manager.order() != null) {
            print("remove() = " + manager.remove());
        }
        print("order() = " + manager.order());
        print("remove() = " + manager.remove());
        print("ordersQuantity() = " + manager.ordersQuantity());
        print("add(" + order2 + ") = " + manager.add(order2));
        print("ordersQuantity() = " + manager.ordersQuantity());
        print("getOrders() = " + Arrays.toString(manager.getOrders()));
        print("ordersCostSummary() = " + manager.ordersCostSummary());
    }

    private static void testOrdersManager(@NotNull OrdersManager manager, Order... orders) {
        print("Test " + manager.toString());
        for(int i = 0; i < orders.length; i++) {
            print("order" + i + " = " + orders[i]);
        }
        print("ordersQuantity() = " + manager.ordersQuantity());
        print("ordersCostSummary() = " + manager.ordersCostSummary());
        print("getOrders() = " + Arrays.toString(manager.getOrders()));
        for(int i = 0; i < orders.length; i++) {
            print("add(order" + i + ") = " + manager.add(orders[i]));
        }
        print("ordersQuantity() = " + manager.ordersQuantity());
        print("getOrders() = " + Arrays.toString(manager.getOrders()));
        print("ordersCostSummary() = " + manager.ordersCostSummary());
        print("itemsQuantity(" + "Item1" + ") = " + manager.itemsQuantity("Item1"));
        print("itemsQuantity(" + item5 + ") = " + manager.itemsQuantity(item5));
    }

    private static void print(String string){
        System.out.println(string);
    }
}
