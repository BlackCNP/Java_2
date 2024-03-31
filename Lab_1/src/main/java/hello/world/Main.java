package hello.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;



public class Main {
    private static List<Product> products;

    private static List<Category> categories;
    private static Cart cart;
    private static List<Order> orders;

    public static void main(String[] args) {
        // Прод.
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном…", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        products = new ArrayList<>();
        cart = new Cart();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        orders = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Перегляд товарів.");
            System.out.println("2. Додати до кошика.");
            System.out.println("3. Переглад кошика");
            System.out.println("4. Видалити с кошика");
            System.out.println("5. Створити замовлення");
            System.out.println("6. Вихід");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Перегля
                    for (Product product : products) {
                        System.out.println("ID: " + product.getId() + ", Назва: " + product.getName() + ", Ціна: " + product.getPrice() + ", Category: " + product.getCategory().getName());
                    }
                    break;
                case 2:
                    // Додать до кошика
                    System.out.println("Виберіть ID продукта щоб додати його до кошика:");
                    int id = scanner.nextInt();
                    for (Product product : products) {
                        if (product.getId() == id) {
                            cart.addProduct(product);
                            System.out.println(product.getName() + " доданно до кошика. " + "з id: " +product.getId() );
                            break;
                        }
                    }
                    break;
                case 3:
                    // view cart
                    cart.viewCart();
                    break;
                case 4:
                    // Видалення
                    System.out.println("Введіть ID продукта для видалення його з кошика:");
                    int id_for_del = scanner.nextInt();
                    for (Product product : cart.getProducts()) {
                        if (product.getId() == id_for_del) {
                            cart.removeProduct(product);
                            System.out.println(product.getName() + " Видалено з кошика.");
                            break;
                        }
                    }
                    break;
                case 5:
                    // Створити замавлення
                    if (!cart.getProducts().isEmpty()) {
                        Order order = new Order(orders.size() + 1, new Date(), new ArrayList<>(cart.getProducts()));
                        orders.add(order);
                        System.out.println("Замовлення створенно успішно. Вітаю, ви придбали:");
                        for (Product product : order.getProducts()) {
                            System.out.println(product.getName()+" за "+ product.getPrice() );
                        }
                        cart.getProducts().clear();
                    } else {
                        System.out.println("Кошик пустий.");
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Нема такойї функції, будь ласка виберіть те що вказано на екрані ");
            }
        }

        scanner.close();
    }
}