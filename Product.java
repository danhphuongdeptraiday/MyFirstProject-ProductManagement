import java.util.Scanner;

public class Product{
    static int autoId;
    int id ;
    String name;
    double price;

    public Product() {
        id = autoId++;
    }

    public Product(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void print(){
        System.out.println(this);
    }

    public void typeIn(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter product name: ");
        name = sc.nextLine();
        System.out.println("Enter price: ");
        price = sc.nextDouble();
    }

    @Override
    public String toString() {
        System.out.format("%5d %-45s %10.2f", id, name, price);
        return "";
    }
}
