package Orders;

import MenuItems.MenuItem;
import MyUtils.Equality;
import MyUtils.MyArrayList;

public class TableOrder extends Order {
    private final MyArrayList<MenuItem> items;
    private final int tableNumber;

    public TableOrder(int tableNumber, MenuItem... items) {
        this.tableNumber = Math.max(tableNumber, 0);
        this.items = new MyArrayList<>(items);
    }

    @Override
    public boolean add(MenuItem item) {
        return items.add(item);
    }

    @Override
    public MenuItem[] getItems() {
        return items.toArray(new MenuItem[items.size()]);
    }

    @Override
    public String[] itemsNames() {
        String[] itemsNames = new String[items.size()];
        for(int i = 0; i < items.size(); i++) {
            itemsNames[i] = items.get(i).getName();
        }
        return itemsNames;
    }

    @Override
    public int itemsQuantity() {
        return items.size();
    }

    @Override
    protected int itemQuantity(MenuItem item,
            Equality<? super MenuItem> equality) {
        int itemQuantity = 0;
        for(int i = 0; i < items.size(); i++) {
            if(equality.equals(item, items.get(i))) {
                itemQuantity++;
            }
        }
        return itemQuantity;
    }

    @Override
    protected boolean remove(MenuItem item,
            Equality<? super MenuItem> equality) {
        for(int i = 0; i < items.size(); i++) {
            if(equality.equals(items.get(i), item)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    protected int removeAll(MenuItem item,
            Equality<? super MenuItem> equality) {
        int n = 0;
        for(int i = 0; i < items.size(); i++) {
            if(equality.equals(items.get(i), item)) {
                items.remove(i);
                i--;
                n++;
            }
        }
        return n;
    }

    @Override
    public int costTotal() {
        int costTotal = 0;
        for(int i = 0; i < items.size(); i++) {
            costTotal += items.get(i).getCost();
        }
        return costTotal;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public String toString() {
        return "TableOrder{" +
                "items = " + items +
                ", tableNumber=" + tableNumber +
                '}';
    }
}
