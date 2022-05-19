package logics;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PrintedProductLogic {
    public void work(SessionFactory sessionFactory){
        System.out.println("1.Создать");
        System.out.println("2.Прочитать");
        System.out.println("3.Обновить");
        System.out.println("4.Удалить");
        System.out.println("5.Вернуться в меню");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Session session;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        switch (i) {
            case 1 -> create(session);
            case 2 -> read(session);
            case 3 -> update(session);
            case 4 -> delete(session);
            case 5 -> {
                session.close();
                return;
            }
            default -> System.out.println("Неверный ввод!");
        }
        session.getTransaction().commit();
    }

    private void create(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID жанра: ");
        int genreId = scanner.nextInt();
        scanner = new Scanner(System.in);
        System.out.print("Введите название: ");
        String title = scanner.nextLine();
        System.out.print("Введите автора: ");
        String author = scanner.nextLine();
        System.out.print("Введите тип изделия (книги или журнал): ");
        String itemType = scanner.nextLine();
        System.out.print("Введите дату просроченности источника: ");
        String sourceExpiration = scanner.nextLine();
        System.out.print("Введите количество: ");
        int amount = scanner.nextInt();
        try {
            PrintedProduct product = new PrintedProduct(session.get(Genre.class, genreId),
                                            title, author, itemType, java.sql.Date.valueOf(sourceExpiration), amount);
            session.save(product);
        }
        catch (Exception ex){
            System.out.println("Ошибка создания записи");
        }
    }

    private void read(Session session){
        System.out.println("1.С фильтром");
        System.out.println("2.Без фильтра");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        switch (i){
            case 1-> filter(session);
            case 2-> {
                List<PrintedProduct> productList = session.createQuery("SELECT p FROM PrintedProduct p",
                        PrintedProduct.class).getResultList();
                System.out.println("Продукт");
                System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID жанра","Название",
                        "Автор", "Тип", "Крайний срок источника", "Количество");
                productList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод! ");
        }

    }
    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID продукта:");
        int productId = scanner.nextInt();
        System.out.println("Что вы хотите обновить?");
        System.out.println("1.ID жанра");
        System.out.println("2.Название");
        System.out.println("3.Автор");
        System.out.println("4.Тип изделия");
        System.out.println("5.Дата просроченности источника");
        System.out.println("6.Количество");
        System.out.print(">");
        int i = scanner.nextInt();
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID жанра: ");
                scanner.nextLine();
                int genreId = scanner.nextInt();
                try {
                    PrintedProduct product = session.get(PrintedProduct.class, productId);
                    product.setGenre(session.get(Genre.class, genreId));
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 2 -> {
                System.out.print("Введите название: ");
                scanner.nextLine();
                String title = scanner.nextLine();
                try {
                    PrintedProduct product = session.get(PrintedProduct.class, productId);
                    product.setTitle(title);
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 3 -> {
                System.out.print("Введите автора: ");
                scanner.nextLine();
                String author = scanner.nextLine();
                try {
                    PrintedProduct product = session.get(PrintedProduct.class, author);
                    product.setAuthor(author);
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 4 -> {
                System.out.print("Введите тип изделия: ");
                scanner.nextLine();
                String itemType = scanner.nextLine();
                try {
                    PrintedProduct product = session.get(PrintedProduct.class, productId);
                    product.setItemType(itemType);
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 5 -> {
                System.out.print("Введите дату просроченности источника: ");
                scanner.nextLine();
                String sourceExpiration = scanner.nextLine();
                try {
                    PrintedProduct product = session.get(PrintedProduct.class, productId);
                    product.setSourceExpiration(java.sql.Date.valueOf(sourceExpiration));
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 6 -> {
                System.out.print("Введите количество: ");
                scanner.nextLine();
                int amount = scanner.nextInt();
                try {
                    PrintedProduct product = session.get(PrintedProduct.class, productId);
                    product.setAmount(amount);
                    session.save(product);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            default -> System.out.println("Неверный ввод!");
        }

    }

    private void delete(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID печатной продукции: ");
        int productId = scanner.nextInt();
        try {
            PrintedProduct product = session.get(PrintedProduct.class, productId);
            session.delete(product);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует!");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.ID печатной продукции");
        System.out.println("2.ID жанра");
        System.out.println("3.Название");
        System.out.println("4.Автор");
        System.out.println("5.Тип изделия");
        System.out.println("6.Дата просроченности источника");
        System.out.println("7.Количество");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<PrintedProduct> productList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID печатной продукции: ");
                int productId = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM PrintedProduct p WHERE product_id =" + productId,
                            PrintedProduct.class).getResultList();
                    System.out.println("Печатная продукция");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 2 -> {
                System.out.print("Введите ID жанра: ");
                int genreId = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM PrintedProduct p WHERE genre_id =" + genreId,
                            PrintedProduct.class).getResultList();
                    System.out.println("Печатная продукция");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID жанра","Название",
                            "Автор", "Тип", "Крайний срок источника", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 3 -> {
                System.out.print("Введите название: ");
                String title = scanner.nextLine();
                try {
                    productList = session.createQuery("SELECT p FROM PrintedProduct p WHERE title =" + title,
                            PrintedProduct.class).getResultList();
                    System.out.println("Печатная продукция");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID жанра","Название",
                            "Автор", "Тип", "Крайний срок источника", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 4 -> {
                System.out.print("Введите автора: ");
                String author = scanner.nextLine();
                try {
                    productList = session.createQuery("SELECT p FROM PrintedProduct p WHERE author =" + author,
                            PrintedProduct.class).getResultList();
                    System.out.println("Печатная продукция");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID жанра","Название",
                            "Автор", "Тип", "Крайний срок источника", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 5 -> {
                System.out.print("Введите тип изделия: ");
                String itemType = scanner.nextLine();
                try {
                    productList = session.createQuery("SELECT p FROM PrintedProduct p WHERE item_type =" + itemType,
                            PrintedProduct.class).getResultList();
                    System.out.println("Печатная продукция");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID жанра","Название",
                            "Автор", "Тип", "Крайний срок источника", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 6 -> {
                System.out.print("Введите дату просроченности источника: ");
                String sourceExpiration = scanner.nextLine();
                try {
                    productList = session.createQuery("SELECT p FROM PrintedProduct p WHERE source_expiration =" + sourceExpiration,
                            PrintedProduct.class).getResultList();
                    System.out.println("Печатная продукция");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID жанра","Название",
                            "Автор", "Тип", "Крайний срок источника", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 7 -> {
                System.out.print("Введите количество: ");
                int amount = scanner.nextInt();
                try {
                    productList = session.createQuery("SELECT p FROM PrintedProduct p WHERE amount =" + amount,
                            PrintedProduct.class).getResultList();
                    System.out.println("Печатная продукция");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID жанра","Название",
                            "Автор", "Тип", "Крайний срок источника", "Количество");
                    productList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            default -> System.out.println("Неверный ввод!");
        }
    }
}
