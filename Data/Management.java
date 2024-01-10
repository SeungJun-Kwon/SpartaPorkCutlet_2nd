package Data;

import Menu.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
    List<Order> orderList = new ArrayList<>();
    Double sales = 0.0;

    public void showHistory() {
        System.out.println("\n[ 주문 내역 ]");
        for(Order o : orderList) {
            System.out.println(o.orderNumber + "번 주문 (" + o.date + " " + o.time.getHour() + ":" + o.time.getMinute() + ":" + o.time.getSecond());
            for(Product p : o.orderList) {
                System.out.printf("- %-30s | %-10s\n", p.getName(), p.getPrice());
            }
            System.out.println("\n");
        }
    }

    public void showSales() {
        System.out.println("가게 총 매출 : " + sales.intValue());
    }

    public Product addNewProduct() {
        try {
            System.out.print("\n상품 이름 : ");
            String name = new Scanner(System.in).nextLine();

            System.out.print("\n상품 가격 : ");
            Double price = new Scanner(System.in).nextDouble();

            System.out.print("\n상품 설명 : ");
            String desc = new Scanner(System.in).nextLine();

            return new Product(name, desc, price);
        } catch (Exception e) {
            System.out.println("올바르지 않은 입력입니다.\n상품 추가에 실패했습니다.");

            return null;
        }
    }

    public void addOrder(int orderNumber, List<Product> productList) {
        orderList.add(new Order(orderNumber, productList));

        for(Product p : productList)
            sales += p.getPrice();
    }
}
