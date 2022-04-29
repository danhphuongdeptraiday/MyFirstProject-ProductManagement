import java.io.*;
import java.util.*;

public class App {

    static List<Product> productList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)  {
        int choose;
        App app = new App();
        productList = app.readFromFile();

        do {
            System.out.println("1. Add products\n"
                    + "2. Display products\n"
                    + "3. Edit a product\n"
                    + "4. Delete a product\n"
                    + "5. Search for products by name\n"
                    + "6. Sort products by price\n"
                    + "7. Sort products by name\n"
                    + "0. Quit");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1: {
                    Product pro = new Product();
                    pro.typeIn();
                    productList.add(pro);
                    break;
                }
                case 2: {
                    System.out.println("autoId: " + Product.autoId);
                    System.out.format("%5s %-45s %-10s\n", "Id", "Name", "Price");
                    for (Product product: productList){
                        product.print();
                    }
                    break;
                }
                case 3: {
                    System.out.println("Enter student's id: ");
                    int id = sc.nextInt();

                    for (Product product : productList) {
                        if (product.getId() == id) {
                            product.typeIn();
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    System.out.println("Enter student's id: ");
                    int id = sc.nextInt();

                    for (Product product : productList) {
                        if (product.getId() == id) {
                            productList.remove(product);
                            break;
                        }
                    }
                    break;
                }
                case 5: {
                    System.out.println("Product's name: ");
                    String name = sc.nextLine();
                    for (Product product: productList){
                        if (product.getName().contains(name)) {
                            System.out.println(product);
                        }
                    }
                    break;
                }
                case 6: {
                    productList.sort(new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            if (o1.getPrice() < o2.getPrice()) {
                                return -1;
                            } else if (o1.getPrice() > o2.getPrice()) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }
                    });
                    System.out.println("autoId: " + Product.autoId);
                    System.out.format("%5s %-45s %-10s\n", "Id", "Name", "Price");
                    for (Product product: productList){
                        product.print();
                    }
                    break;
                }
                case 7: {
                    Collections.sort(productList, new Comparator<Product>() {
                        @Override
                        public int compare(Product o1, Product o2) {
                            return o1.getName().compareToIgnoreCase(o2.getName());
                        }
                    });
                    System.out.println("autoId: " + Product.autoId);
                    System.out.format("%5s %-45s %-10s\n", "Id", "Name", "Price");
                    for (Product product: productList){
                        product.print();
                    }
                    break;
                }
                case 0: {
                    System.out.println("Exit successful");
                    break;
                }
                default: {
                    System.out.println("Choose fail!!");
                }
            }
        } while (choose != 0);
        writeToFile(productList);
    }

//  Write file
    public static void writeToFile(List<Product> productList){
        try {
            FileWriter fw = new FileWriter("save.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("autoId: " +Product.autoId);
            bw.write("\nproduct_no: " + productList.size());
            for (Product p: productList){
                bw.write("\n" + p.id + "\n"+ p.name + "\n" +p.price);
            }
            bw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
//  Read File
    public List<Product> readFromFile(){
         productList = new ArrayList<>();
        try {
            FileReader fr = new FileReader("save.txt");
            BufferedReader br = new BufferedReader(fr);
            String line ;
            List<String> allLine = new ArrayList<>()  ;
            while (true){
                line = br.readLine();
                allLine.add(line);
                if (line == null){
                    allLine.remove(null);
                    break;
                }
            }
            Product.autoId = Integer.parseInt(allLine.get(0).substring(8));
            int i =2;
            while(i< allLine.size()){ // allLine.size base on the lines of save.txt
                // i's steps are three because autoId and product_no contains position 0 and 1
                int id = Integer.parseInt(allLine.get(i));
                // so id will be position 2
                String name = allLine.get(i+1);
                // and name is position after id
                double price = Double.parseDouble( allLine.get(i+2));
                productList.add(new Product(id,name,price));
                i += 3;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}