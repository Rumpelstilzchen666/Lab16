package OrdersManagers;

import MenuItems.MenuItem;
import Orders.Order;

public interface OrdersManager {
    boolean add(Order order);

    Order[] getOrders();

    int ordersQuantity();

    int itemsQuantity(String itemName);

    int itemsQuantity(MenuItem item);

    int ordersCostSummary();
}
