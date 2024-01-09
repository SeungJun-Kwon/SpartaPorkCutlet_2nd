package Menu;

public class Product extends Menu{
    Double price;

    public Product(String name, String desc, Double price) {
        super(name, desc);
        this.price = price;
    }
}
