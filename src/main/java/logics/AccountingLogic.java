package logics;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AccountingLogic {
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
        System.out.print("Введите ID сотрудника: ");
        int employeeId = scanner.nextInt();
        System.out.print("Введите ID печатной продукции: ");
        int productId = scanner.nextInt();
        System.out.print("Введите номер читательского билета: ");
        int libraryCardNumber = scanner.nextInt();
        scanner = new Scanner(System.in);
        System.out.print("Введите дату выдачи: ");
        String dateOfIssue = scanner.nextLine();
        System.out.print("Введите срок хранения книги: ");
        int term = scanner.nextInt();
        scanner = new Scanner(System.in);
        System.out.print("Введите дату возвращения: ");
        String returnDate = scanner.nextLine();
        try {
            Accounting order = new Accounting(session.get(Employee.class, employeeId), session.get(PrintedProduct.class, productId),
                    session.get(Reader.class, libraryCardNumber), java.sql.Date.valueOf(dateOfIssue), term, java.sql.Date.valueOf(returnDate));
            session.save(order);
        }
        catch (Exception ex){
            System.out.println("Ошибка создания записи!");
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
                List<Accounting> orderList = session.createQuery("SELECT a FROM Accounting a",
                        Accounting.class).getResultList();
                System.out.println("Выдача");
                System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID сотрудника","ID печатной продукции",
                        "НЧБ", "Выдача", "Срок", "Возвращение");
                orderList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод!");
        }
    }

    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID продукта: ");
        int orderId = scanner.nextInt();
        System.out.println("Что вы хотите обновить?");
        System.out.println("1.ID сотрудника");
        System.out.println("2.ID печатной продукции");
        System.out.println("3.Номер читательского билета");
        System.out.println("4.Дата выдачи");
        System.out.println("5.Срок хранения");
        System.out.println("6.Дата возвращения");
        System.out.print(">");
        int i = scanner.nextInt();
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID сотрудника: ");
                scanner.nextLine();
                int employeeId = scanner.nextInt();
                try {
                    Accounting order = session.get(Accounting.class, orderId);
                    order.setEmployee(session.get(Employee.class, employeeId));
                    session.save(order);

                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 2 -> {
                System.out.print("Введите ID печатной продукции: ");
                scanner.nextLine();
                int productId = scanner.nextInt();
                try {
                    Accounting order = session.get(Accounting.class, orderId);
                    order.setProduct(session.get(PrintedProduct.class, productId));
                    session.save(order);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 3 -> {
                System.out.print("Введите номер читательского билета: ");
                scanner.nextLine();
                String libraryCardNumber = scanner.nextLine();
                try {
                    Accounting order = session.get(Accounting.class, orderId);
                    order.setReader(session.get(Reader.class, libraryCardNumber));
                    session.save(order);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 4 -> {
                System.out.print("Введите дату выдачи: ");
                scanner.nextLine();
                String dateOfIssue = scanner.nextLine();
                try {
                    Accounting order = session.get(Accounting.class, orderId);
                    order.setDateOfIssue(java.sql.Date.valueOf(dateOfIssue));
                    session.save(order);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 5 -> {
                System.out.print("Введите срок хранения: ");
                scanner.nextLine();
                int term = scanner.nextInt();
                try {
                    Accounting order = session.get(Accounting.class, orderId);
                    order.setTerm(term);
                    session.save(order);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 6 -> {
                System.out.print("Введите дату возвращения: ");
                scanner.nextLine();
                String returnDate = scanner.nextLine();
                try {
                    Accounting order = session.get(Accounting.class, orderId);
                    order.setReturnDate(java.sql.Date.valueOf(returnDate));
                    session.save(order);
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
        System.out.print("Введите ID выдачи: ");
        int orderId = scanner.nextInt();
        try {
            Accounting order = session.get(Accounting.class, orderId);
            session.delete(order);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует!");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.ID выдачи");
        System.out.println("2.ID сотрудника");
        System.out.println("3.ID печатной продукции");
        System.out.println("4.Номер читательского билета");
        System.out.println("5.Дата выдачи");
        System.out.println("6.Срок хранения");
        System.out.println("7.Дата возвращения");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Accounting> orderList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID выдачи: ");
                int orderId = scanner.nextInt();
                try {
                    orderList = session.createQuery("SELECT a FROM Accounting a WHERE order_number =" + orderId,
                            Accounting.class).getResultList();
                    System.out.println("Выдача");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID сотрудника","ID печатной продукции",
                            "НЧБ", "Выдача", "Срок", "Возвращение");
                    orderList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 2 -> {
                System.out.print("Введите ID сотрудника: ");
                int employeeId = scanner.nextInt();
                try {
                    orderList = session.createQuery("SELECT a FROM Accounting a WHERE employee_id =" + employeeId,
                            Accounting.class).getResultList();
                    System.out.println("Выдача");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    orderList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 3 -> {
                System.out.print("Введите номер читательского билета: ");
                int productId = scanner.nextInt();
                try {
                    orderList = session.createQuery("SELECT a FROM Accounting a WHERE product_id =" + productId,
                            Accounting.class).getResultList();
                    System.out.println("Выдача");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    orderList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 4 -> {
                System.out.print("Введите название: ");
                int libraryCardNumber = scanner.nextInt();
                try {
                    orderList = session.createQuery("SELECT a FROM Accounting a WHERE library_card_number =" + libraryCardNumber,
                            Accounting.class).getResultList();
                    System.out.println("Выдача");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    orderList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 5 -> {
                System.out.print("Введите дату выдачи: ");
                String dateOfIssue = scanner.nextLine();
                try {
                    orderList = session.createQuery("SELECT a FROM Accounting a WHERE date_of_issue =" + dateOfIssue,
                            Accounting.class).getResultList();
                    System.out.println("Выдача");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    orderList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 6 -> {
                System.out.print("Введите цену закупки: ");
                int term = scanner.nextInt();
                try {
                    orderList = session.createQuery("SELECT a FROM Accounting a WHERE term =" + term,
                            Accounting.class).getResultList();
                    System.out.println("Выдача");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    orderList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 7 -> {
                System.out.print("Введите дату возвращения:");
                String returnDate = scanner.nextLine();
                try {
                    orderList = session.createQuery("SELECT a FROM Accounting a WHERE return_date =" + returnDate,
                            Accounting.class).getResultList();
                    System.out.println("Выдача");
                    System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s%-30s%-30s\n","ID","ID типа","ID фабрики",
                            "Название", "Цена", "Цена закупки", "Размер", "Количество");
                    orderList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            default -> System.out.println("Неверный ввод!");
        }
    }
}
