package OrdersManagers;

import MenuItems.MenuItem;
import Orders.InternetOrder;
import Orders.Order;

public class InternetOrdersManager implements OrdersManager {
    private final QueueNode head = new QueueNode(), tail = new QueueNode();
    private int size;

    public InternetOrdersManager(InternetOrder... orders) {
        size = 0;
        head.next = tail;
        tail.prev = head;
        for(InternetOrder order : orders) {
            add(order);
        }
    }

    @Override
    public boolean add(Order order) {
        QueueNode newNode = new QueueNode(head, order, head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size++;
        return true;
    }

    public Order order() {
        return tail.prev.value;
    }

    public Order remove() {
        if(size > 0) {
            Order order = tail.prev.value;
            tail.prev = tail.prev.prev;
            tail.prev.next = tail;
            size--;
            return order;
        }
        return null;
    }

    @Override
    public Order[] getOrders() {
        Order[] orders = new Order[size];
        int i = 0;
        for(QueueNode node = head.next; node != tail; node = node.next) {
            orders[i] = node.value;
            i++;
        }
        return orders;
    }

    @Override
    public int ordersQuantity() {
        return size;
    }

    @Override
    public int itemsQuantity(String itemName) {
        int n = 0;
        for(QueueNode node = head.next; node != tail; node = node.next) {
            n += node.value.itemQuantity(itemName);
        }
        return n;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        int n = 0;
        for(QueueNode node = head.next; node != tail; node = node.next) {
            n += node.value.itemQuantity(item);
        }
        return n;
    }

    @Override
    public int ordersCostSummary() {
        int n = 0;
        for(QueueNode node = head.next; node != tail; node = node.next) {
            n += node.value.costTotal();
        }
        return n;
    }

    private static class QueueNode {
        public final Order value;
        public QueueNode prev, next;

        public QueueNode(QueueNode prev, Order value, QueueNode next) {
            this.prev = prev;
            this.value = value;
            this.next = next;
        }

        public QueueNode() {
            this(null, null, null);
        }
    }
}
