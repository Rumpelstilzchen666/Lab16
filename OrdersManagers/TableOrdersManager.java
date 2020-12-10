package OrdersManagers;

import MenuItems.MenuItem;
import MyUtils.Equality;
import MyUtils.MyArrayList;
import Orders.Order;
import Orders.TableOrder;

public class TableOrdersManager implements OrdersManager {
    private static final Equality<TableOrder> EQUALITY = TableOrder::equals;
    private static final Equality<TableOrder> TABLE_NUMBER_EQUALITY =
            (o1, o2) -> o1.getTableNumber() == o2.getTableNumber();
    private final MyArrayList<TableOrder> orders;
    private final int nTables;

    public TableOrdersManager(int nTables, TableOrder... orders) {
        this.nTables = nTables;
        this.orders = new MyArrayList<>();
        this.orders.ensureCapacity(orders.length);
        for(TableOrder order : orders) {
            add(order);
        }
    }

    @Override
    public boolean add(Order order) {
        int tableNumber = ((TableOrder) order).getTableNumber();
        if(tableNumber <= nTables && tableNumber > 0) {
            Order sameTableOrder = getOrder(tableNumber);
            if(sameTableOrder == null) {
                orders.add((TableOrder) order);
            }
            else {
                for(MenuItem item : order.getItems()) {
                    sameTableOrder.add(item);
                }
            }
            return true;
        }
        return false;
    }

    public Order getOrder(int tableNumber) {
        if(tableNumber <= nTables && tableNumber > 0) {
            for(int i = 0; i < orders.size(); i++) {
                if(orders.get(i).getTableNumber() == tableNumber) {
                    return orders.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public Order[] getOrders() {
        return orders.toArray(new TableOrder[orders.size()]);
    }

    @Override
    public int ordersQuantity() {
        return orders.size();
    }

    public boolean remove(int tableNumber) {
        if(tableNumber <= nTables && tableNumber > 0) {
            return remove(new TableOrder(tableNumber), TABLE_NUMBER_EQUALITY);
        }
        return false;
    }

    public boolean remove(Order order) {
        if(((TableOrder) order).getTableNumber() <= nTables &&
                ((TableOrder) order).getTableNumber() > 0) {
            return remove((TableOrder) order, EQUALITY);
        }
        return false;
    }

    private boolean remove(TableOrder order,
            Equality<? super TableOrder> equality) {
        if(order.getTableNumber() <= nTables && order.getTableNumber() > 0) {
            for(int i = 0; i < orders.size(); i++) {
                if(equality.equals(orders.get(i), order)) {
                    orders.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public int removeAll(TableOrder order) {
        int n = 0;
        if(order.getTableNumber() <= nTables && order.getTableNumber() > 0) {
            for(int i = 0; i < orders.size(); i++) {
                if(EQUALITY.equals(orders.get(i), order)) {
                    orders.remove(i);
                    i--;
                    n++;
                }
            }
        }
        return n;
    }

    public boolean addItem(MenuItem item, int tableNumber) {
        for(int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getTableNumber() == tableNumber) {
                orders.get(i).add(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public int itemsQuantity(String itemName) {
        int n = 0;
        for(int i = 0; i < orders.size(); i++) {
            n += orders.get(i).itemQuantity(itemName);
        }
        return n;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        int n = 0;
        for(int i = 0; i < orders.size(); i++) {
            n += orders.get(i).itemQuantity(item);
        }
        return n;
    }

    @Override
    public int ordersCostSummary() {
        int n = 0;
        for(int i = 0; i < orders.size(); i++) {
            n += orders.get(i).costTotal();
        }
        return n;
    }

    public int freeTableNumber() {
        final int[] freeTablesNumbers = freeTableNumbers();
        return freeTablesNumbers.length > 0 ? freeTablesNumbers[0] : 0;
    }

    public int[] freeTableNumbers() {
        final int[] tablesNumbers = new int[nTables];
        for(int i = 0; i < nTables; i++) {
            tablesNumbers[i] = i + 1;
        }
        int freeTableNumber = nTables;
        for(int i = 0; i < orders.size(); i++) {
            tablesNumbers[orders.get(i).getTableNumber() - 1] = 0;
            freeTableNumber--;
        }
        final int[] freeTablesNumbers = new int[freeTableNumber];
        int freeTablesCounter = 0;
        for(int tableNumber : tablesNumbers) {
            if(tableNumber != 0) {
                freeTablesNumbers[freeTablesCounter] = tableNumber;
                freeTablesCounter++;
            }
        }
        return freeTablesNumbers;
    }

    @Override
    public String toString() {
        return "TableOrdersManager{" +
                "orders = " + orders +
                ", nTables=" + nTables +
                '}';
    }
}
