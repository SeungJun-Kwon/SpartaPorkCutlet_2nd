package Kiosk;

import Data.CartData;
import Data.MenuData;
import Menu.Menu;
import Menu.Product;

import java.util.*;

import static Data.MenuData.restaurantName;

public class KioskApp {
    static MenuData md;
    static CartData cd;

    Scanner sc = new Scanner(System.in);
    Map<Integer, Menu> controlKiosk = new HashMap<>();

    public KioskApp() {
        Init();
    }

    public void Init() {
        md = new MenuData();
        cd = new CartData();
        cd.ClearCart();

        ShowMain();
    }

    public void ShowMain() {
        System.out.println("\"" + restaurantName + "에 오신걸 환영합니다.\"");
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");

        int index = 1;
        controlKiosk.clear();

        for (String menuName : md.getMenuMap().keySet()) {
            System.out.println("\n[ " + menuName + " ]");
            for (Menu menu : md.getMenus(menuName)) {
                System.out.printf("%d. %-30s | %-50s\n", index, menu.getName(), menu.getDesc());
                controlKiosk.put(index, menu);
                index++;
            }
        }

        try {
            System.out.print("\n입력 : ");
            int answer = sc.nextInt();

            switch (controlKiosk.get(answer).getName()) {
                case "주문하기":
                    ShowOrder();
                    break;
                case "취소하기":
                    ShowCancel();
                    break;
                default:
                    ShowDetail(answer);
                    break;
            }
        } catch (Exception e) {
            WrongInput();
        }
    }

    void ShowDetail(int input) {
        System.out.println("\n\"" + restaurantName + "에 오신걸 환영합니다.\"");
        System.out.println("아래 메뉴판을 보시고 상품를 골라 입력해주세요.");

        String menuName = controlKiosk.get(input).getName();
        List<Product> products = md.getProducts(menuName);

        System.out.println("[ " + menuName + " ]");

        int index = 1;
        controlKiosk.clear();

        for (Product p : products) {
            System.out.printf("%d. %-30s | %-10s | %-50s\n", index, p.getName(), p.getPrice().toString(), p.getDesc());
            controlKiosk.put(index, p);
            index++;
        }

        try {
            System.out.print("\n입력 : ");
            int answer = sc.nextInt();
            ShowAddCart(answer);
        } catch (Exception e) {
            WrongInput();
        }
    }

    void ShowAddCart(int input) {
        Product product = (Product) controlKiosk.get(input);
        controlKiosk.clear();

        System.out.printf("\n[ %-30s | %-10s | %-50s ]\n", product.getName(), product.getPrice().toString(), product.getDesc());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인\t2. 취소");

        try {
            System.out.print("\n입력 : ");
            int answer = sc.nextInt();

            if (answer == 1) {
                System.out.println(product.getName() + "(이)가 장바구니에 추가되었습니다.");
                cd.AddProductInCart(product);
            }

            ShowMain();
        } catch (Exception e) {
            WrongInput();
        }
    }

    void ShowOrder() {
        System.out.println("아래와 같이 주문하시겠습니까?");
        controlKiosk.clear();
        double totalPrice = 0;

        System.out.println("\n[ 장바구니 ]");
        for (Product p : cd.GetCart().keySet()) {
            System.out.printf("%-30s | %-10s | %-10s개 | %-50s\n", p.getName(), p.getPrice(), cd.GetCart().get(p), p.getDesc());
            totalPrice += p.getPrice();
        }

        System.out.println("\n[ 가격 ]");
        System.out.println(totalPrice + " 원");

        System.out.println("\n1. 주문 \t 2. 메뉴판");

        try {
            System.out.print("\n입력 : ");
            int answer = sc.nextInt();

            if (answer == 1) {
                ShowClearOrder();
            } else {
                ShowMain();
            }
        } catch (Exception e) {
            WrongInput();
        }
    }

    void ShowClearOrder() {
        cd.ClearCart();

        System.out.println("주문이 완료되었습니다!");
        System.out.println("(3초 후 메인 화면으로 돌아갑니다.)");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ShowMain();
            }
        };

        timer.schedule(task, 3000);
    }

    void ShowCancel() {
        System.out.println("진행하던 주문을 취소하시겠습니까?");
        System.out.println("1. 확인 \t 2. 취소");

        try {
            System.out.print("\n입력 : ");
            int answer = sc.nextInt();

            if (answer == 1) {
                System.out.println("\n진행하던 주문이 취소되었습니다.");
                cd.ClearCart();
            }
        } catch (Exception e) {
            WrongInput();
        }
    }

    void WrongInput() {
        System.out.println("\n잘못된 입력입니다.\n1초 후 메인 화면으로 돌아갑니다.\n");
        sc.next();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ShowMain();
            }
        };

        timer.schedule(task, 1000);
    }
}
