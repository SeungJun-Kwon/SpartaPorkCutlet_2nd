package Data;

import Menu.Menu;
import Menu.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuData {
    public static String restaurantName = "스파르타 돈까스 2호점";

    int orderNumber;

    Map<String, List<Menu>> menus;
    Map<String, List<Product>> products;

    public MenuData() {
        menus = new LinkedHashMap<>();
        products = new LinkedHashMap<>();
        orderNumber = 0;

        Init();
    }

    public void Init() {
        List<Menu> mainMenus = new ArrayList<>();
        List<Menu> addMenus = new ArrayList<>();
        List<Menu> orderMenus = new ArrayList<>();

        mainMenus.add(new Menu("돈까스", "바삭바삭한 돈까스"));
        mainMenus.add(new Menu("소바", "소바"));
        mainMenus.add(new Menu("우동", "뜨끈뜨끈한 우동"));
        mainMenus.add(new Menu("카레", "맛있는 카레"));

        addMenus.add(new Menu("미니 카레라이스", "맛있는 미니 카레"));
        addMenus.add(new Menu("미니 돈까스", "바삭바삭한 미니 돈까스"));
        addMenus.add(new Menu("에비텐(새우 튀김)", "새우 튀김"));
        addMenus.add(new Menu("미니 우동", "뜨끈뜨끈한 미니 우동"));

        orderMenus.add(new Menu("주문하기", "장바구니를 확인 후 주문합니다."));
        orderMenus.add(new Menu("취소하기", "진행 중인 주문을 취소합니다."));

        menus.put("메인 메뉴", mainMenus);
        menus.put("추가 메뉴", addMenus);
        menus.put("주문 메뉴", orderMenus);

        List<Product> cutlets = new ArrayList<>();
        cutlets.add(new Product("등심까스", "부드러운 등심으로 만든 돈까스", 10500.0));
        cutlets.add(new Product("모짜렐라치즈까스", "쭉 늘어나는 치즈까스", 12900.0));
        cutlets.add(new Product("에비텐모듬까스", "새우튀김과 돈까스", 13900.0));

        List<Product> sobas = new ArrayList<>();
        sobas.add(new Product("마제소바", "풍미가 느껴지는 일본식 비빔면", 9900.0));
        sobas.add(new Product("카라이마제소바", "매운 마제소바", 10500.));

        List<Product> udons = new ArrayList<>();
        udons.add(new Product("우동", "깊고 진한 일본식 우동", 7900.));
        udons.add(new Product("에비텐우동", "새우튀김이 들어간 일본식 우동", 9900.));

        List<Product> currys = new ArrayList<>();
        currys.add(new Product("머쉬룸포크카레", "버섯향이 느껴지는 부드럽고 진한 카레", 9500.));
        currys.add(new Product("에비텐카레", "새우튀김이 들어간 부드럽고 진한 카레", 11500.));

        List<Product> miniCurrys = new ArrayList<>();
        miniCurrys.add(new Product("미니 카레라이스", "맛있는 미니 카레", 4500.));

        List<Product> miniCutlets = new ArrayList<>();
        miniCutlets.add(new Product("미니 돈까스", "바삭바삭한 미니 돈까스", 5500.));

        List<Product> miniEbitens = new ArrayList<>();
        miniEbitens.add(new Product("에비텐(새우 튀김)", "새우 튀김", 3000.));

        List<Product> miniUdons = new ArrayList<>();
        miniUdons.add(new Product("미니 우동", "뜨끈뜨끈한 미니 우동", 4500.));

        products.put("돈까스", cutlets);
        products.put("소바", sobas);
        products.put("우동", udons);
        products.put("카레", currys);
        products.put("미니 카레라이스", miniCurrys);
        products.put("미니 돈까스", miniCutlets);
        products.put("에비텐(새우 튀김", miniEbitens);
        products.put("미니 우동", miniUdons);
    }

    public Map<String, List<Menu>> getMenuMap() {
        return menus;
    }

    public List<Menu> getMenus(String key) {
        return menus.get(key);
    }

    public Map<String, List<Product>> getProductMap() {
        return products;
    }

    public List<Product> getProducts(String key) {
        return products.get(key);
    }

    public int getOrderNumber() {
        return ++orderNumber;
    }
}
