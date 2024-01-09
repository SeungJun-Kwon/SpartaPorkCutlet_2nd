package Data;

import Menu.Product;

import java.util.HashMap;
import java.util.Map;

public class CartData {
    public Map<Product, Integer> cart_map = new HashMap<>();

    public void AddProductInCart(Product product) {
        if(cart_map.containsKey(product)) {
            cart_map.put(product, cart_map.get(product) + 1);
        }
        else {
            cart_map.put(product, 1);
        }
    }

    public Map<Product, Integer> GetCart() {
        return cart_map;
    }

    public void ClearCart() {
        cart_map.clear();
    }
}
