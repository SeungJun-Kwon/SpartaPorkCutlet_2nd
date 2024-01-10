package Kiosk;

import Data.CartData;
import Data.Management;
import Data.MenuData;
import Menu.Menu;
import Menu.Product;

import java.util.*;

import static Data.MenuData.restaurantName;

public class KioskApp {
    MenuData md;
    CartData cd;
    Management management;


    Scanner sc = new Scanner(System.in);
    Map<Integer, Menu> controlKiosk = new HashMap<>();

    public KioskApp() {
        Init();
    }

    public void Init() {
        md = new MenuData();
        cd = new CartData();
        cd.ClearCart();
        management = new Management();

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

            if (answer == 0) {
                ShowManagement();
            } else {
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
        management.addOrder(md.getOrderNumber(), new ArrayList<>(cd.GetCart().keySet().stream().toList()));

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

    void ShowManagement() {
        System.out.println("[ 가게 관리 ]");
        System.out.println("1. 주문 내역");
        System.out.println("2. 가게 매출");
        System.out.println("3. 메뉴 추가");
        System.out.println("4. 메뉴 삭제\n");

        System.out.print("입력 : ");
        try {
            int answer = sc.nextInt();

            switch (answer) {
                case 1:
                    management.showHistory();
                    break;
                case 2:
                    management.showSales();
                    break;
                case 3:
                    ShowAddProduct();
                    break;
                case 4:
                    ShowRemoveProduct();
                    break;
            }
        } catch (Exception e) {
            WrongInput();
        }

        try {
            System.out.println("\n관리 메뉴로 돌아가려면 1을, 메인 화면으로 돌아가려면 2를 입력하세요.");
            int answer = sc.nextInt();

            if (answer == 1) {
                ShowManagement();
            } else {
                ShowMainTimer(3000);
            }
        } catch (Exception e) {
            WrongInput();
        }
    }

    void ShowAddProduct() {
        int index = 1;

        System.out.println("상품을 추가할 메뉴를 골라주세요.");
        List<String> menuNames = md.getProductMap().keySet().stream().toList();

        for (String name : menuNames) {
            System.out.println(index++ + ". " + name);
        }

        try {
            System.out.print("입력 : ");
            int answer = sc.nextInt();

            String menuName = menuNames.get(answer - 1);

            Product product = management.addNewProduct();

            if (product != null) {
                if (md.addProduct(menuName, product)) {
                    System.out.println("\n[ " + product.getName() + " ] 상품이 추가되었습니다.");
                } else {
                    System.out.println("\n[ " + product.getName() + " ] 상품 추가에 실패했습니다.");
                }
            }
        } catch (Exception e) {
            WrongInput();
        }
    }

    void ShowRemoveProduct() {
        int index = 1;

        System.out.println("상품을 제거할 메뉴를 골라주세요.");
        List<String> menuNames = md.getProductMap().keySet().stream().toList();

        for (String name : menuNames) {
            System.out.println(index++ + ". " + name);
        }

        try {
            System.out.print("입력 : ");
            int answer = sc.nextInt();
            sc.reset();

            String menuName = menuNames.get(answer - 1);

            System.out.println("\n제거할 상품을 골라주세요.");
            index = 1;
            for(Product p : md.getProducts(menuName)) {
                System.out.println(index++ + ". [ " + p.getName() + " ] " + p.getPrice() + "원");
            }

            System.out.print("입력 : ");
            answer = sc.nextInt();

            Product product = md.getProducts(menuName).get(answer - 1);

            if (product != null) {
                if (md.removeProduct(menuName, product)) {
                    System.out.println("\n[ " + product.getName() + " ] 상품이 제거되었습니다.");
                } else {
                    System.out.println("\n[ " + product.getName() + " ] 상품 제거에 실패했습니다.");
                }
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

    void ShowMainTimer(long delay) {
        System.out.println("\n" + delay / 1000 + "초 후 메인 화면으로 돌아갑니다.\n");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ShowMain();
            }
        };

        timer.schedule(task, delay);
    }
}
