package logics;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class EmployeeLogic {
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
        System.out.print("Введите имя сотрудника: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию сотрудника: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите отчество сотрудника: ");
        String middleName = scanner.nextLine();
        try {
            Employee employee = new Employee(firstName, lastName, middleName);
            session.save(employee);
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
                List<Employee> employeesList = session.createQuery("SELECT e FROM Employee e",
                        Employee.class).getResultList();
                System.out.println("Сотрудник");
                System.out.printf("%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество");
                employeesList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод!");
        }

    }
    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID сотрудника: ");
        int employeeId = scanner.nextInt();
        System.out.print("Введите имя сотрудника: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию сотрудника: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите отчество сотрудника: ");
        String middleName = scanner.nextLine();
        try {
            Employee employee = session.get(Employee.class, employeeId);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setLastName(middleName);
            session.save(employee);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует!");
        }
    }

    private void delete(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID сотрудника: ");
        int employeeId = scanner.nextInt();
        try {
            Employee employee = session.get(Employee.class, employeeId);
            session.delete(employee);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует!");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.Id типа");
        System.out.println("2.Имя");
        System.out.println("3.Фамилия");
        System.out.println("4.Отчество");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Employee> employeesList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID сотрудника: ");
                int employeeId = scanner.nextInt();
                try {
                    employeesList = session.createQuery("SELECT e FROM Employee e WHERE employee_id =" + employeeId,
                            Employee.class).getResultList();
                    System.out.println("Сотрудник");
                    System.out.printf("%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество");
                    employeesList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 2 -> {
                System.out.print("Введите имя: ");
                String firstName = scanner.nextLine();
                try {
                    employeesList = session.createQuery("SELECT e FROM Employee e WHERE first_name ='" + firstName + "'",
                            Employee.class).getResultList();
                    System.out.println("Сотрудник");
                    System.out.printf("%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество");
                    employeesList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 3 -> {
                System.out.print("Введите фамилию: ");
                String lastName = scanner.nextLine();
                try {
                    employeesList = session.createQuery("SELECT e FROM Employee e WHERE last_name ='" + lastName + "'",
                            Employee.class).getResultList();
                    System.out.println("Сотрудник");
                    System.out.printf("%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество");
                    employeesList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 4 -> {
                System.out.print("Введите отчество: ");
                String middleName = scanner.nextLine();
                try {
                    employeesList = session.createQuery("SELECT e FROM Employee e WHERE middle_name ='" + middleName + "'",
                            Employee.class).getResultList();
                    System.out.println("Сотрудник");
                    System.out.printf("%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество");
                    employeesList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            default -> System.out.println("Неверный ввод!");
        }
    }
}
