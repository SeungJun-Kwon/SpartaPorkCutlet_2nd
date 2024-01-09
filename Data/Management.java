package Data;

import Menu.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Management {
    List<Order> orderList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Double sales = 0.0;

    public void ShowManagement() {
        System.out.println("[ 가게 관리 ]");
        System.out.println("1. 주문 내역");
        System.out.println("2. 가게 매출");
        System.out.println("3. 메뉴 추가");
        System.out.println("4. 메뉴 조회");
        System.out.println("5. 메뉴 삭제\n");
    }

    public void showHistory() {
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
            String name = sc.nextLine();

            System.out.println("\n상품 가격 : ");
            Double price = sc.nextDouble();

            System.out.println("\n상품 설명 : ");
            String desc = sc.nextLine();

            System.out.println("\n상품이 추가되었습니다.");

            return new Product(name, desc, price);
        } catch (Exception e) {
            System.out.println("올바르지 않은 입력입니다.\n상품 추가에 실패했습니다.");
            sc.next();

            return null;
        }
    }

    public void addOrder(int orderNumber, List<Product> productList) {
        orderList.add(new Order(orderNumber, productList));

        for(Product p : productList)
            sales += p.getPrice();
    }
}
