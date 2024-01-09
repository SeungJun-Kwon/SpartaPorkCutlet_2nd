package Data;

import Menu.Product;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Order {
    int orderNumber;
    List<Product> orderList;
    LocalDate date;
    LocalTime time;

    public Order(int orderNumber, List<Product> orderList) {
        this.orderNumber = orderNumber;
        this.orderList = orderList;
        date = LocalDate.now();
        time = LocalTime.now();
    }
}
