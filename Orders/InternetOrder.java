package Orders;

import Customer.Customer;
import MenuItems.MenuItem;
import MyUtils.Equality;

public class InternetOrder extends Order {
    private final ListNode head, tail;
    private int size;

    public InternetOrder(Customer customer, MenuItem... items) {
        this.customer = customer;
        size = 0;
        tail = new ListNode(null, null);
        head = new ListNode(null, tail);
        for(MenuItem item : items) {
            add(item);
        }
    }

    @Override
    public boolean add(MenuItem item) {
        head.next = new ListNode(item, head.next);
        size++;
        return true;
    }

    @Override
    public MenuItem[] getItems() {
        MenuItem[] items = new MenuItem[size];
        int i = 0;
        for(ListNode node = head.next; node != tail; node = node.next) {
            items[i] = node.value;
            i++;
        }
        return items;
    }

    @Override
    public int itemsQuantity() {
        return size;
    }

    @Override
    protected int itemQuantity(MenuItem item,
            Equality<? super MenuItem> equality) {
        int itemQuantity = 0;
        for(ListNode node = head.next; node != tail; node = node.next) {
            if(equality.equals(item, node.value)) {
                itemQuantity++;
            }
        }
        return itemQuantity;
    }

    @Override
    public String[] itemsNames() {
        String[] itemsNames = new String[size];
        int i = 0;
        for(ListNode node = head.next; node != tail; node = node.next) {
            itemsNames[i] = node.value.getName();
            i++;
        }
        return itemsNames;
    }

    @Override
    protected boolean remove(MenuItem item,
            Equality<? super MenuItem> equality) {
        ListNode prev = head;
        for(ListNode node = head.next; node != tail; node = node.next) {
            if(equality.equals(item, node.value)) {
                prev.next = node.next;
                size--;
                return true;
            }
            prev = node;
        }
        return false;
    }

    @Override
    protected int removeAll(MenuItem item,
            Equality<? super MenuItem> equality) {
        int n = 0;
        ListNode prev = head;
        for(ListNode node = head.next; node != tail; node = node.next) {
            if(equality.equals(item, node.value)) {
                prev.next = node.next;
                node = prev;
                n++;
            }
            prev = node;
        }
        size -= n;
        return n;
    }

    @Override
    public int costTotal() {
        int costTotal = 0;
        for(ListNode node = head.next; node != tail; node = node.next) {
            costTotal += node.value.getCost();
        }
        return costTotal;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("InternetOrder{")
                .append("items = [");
        for(ListNode node = head.next; node != tail; node = node.next) {
            stringBuilder.append(node.value).append(", ");
        }
        return stringBuilder.append(head.next != tail ? "\b\b]" : "]")
                .append(", customer=").append(customer)
                .append(", size=").append(size)
                .append('}').toString();
    }

    private static class ListNode {
        public final MenuItem value;
        public ListNode next;

        public ListNode(MenuItem value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }
}
