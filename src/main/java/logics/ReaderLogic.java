package logics;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReaderLogic {
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
            default -> System.out.println("Неверный ввод");
        }
        session.getTransaction().commit();
    }

    private void create(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine();
        System.out.print("Введите отчество: ");
        String middleName = scanner.nextLine();
        System.out.print("Крайний срок действия читательского билета: ");
        String expiryDateOfTheLibraryCard = scanner.nextLine();
        System.out.print("Введите год рождения: ");
        int yearOfBirth = scanner.nextInt();
        try {
            Reader reader = new Reader(firstName, lastName, middleName, yearOfBirth, java.sql.Date.valueOf(expiryDateOfTheLibraryCard));
            session.save(reader);
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
            case 1 -> filter(session);
            case 2 -> {
                List<Reader> readersList = session.createQuery("SELECT r FROM Reader r",
                        Reader.class).getResultList();
                System.out.println("Читатель");
                System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество", "Год рождения", "КСДЧБ");
                readersList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод!");
        }

    }
    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID читателя:");
        int readerId = scanner.nextInt();
        System.out.print("Введите имя:");
        String firstName = scanner.nextLine();
        System.out.print("Введите фамилию:");
        String lastName = scanner.nextLine();
        System.out.print("Введите отчество:");
        String middleName = scanner.nextLine();
        System.out.print("Введите крайний срок действия читательского билета:");
        String newDate = scanner.nextLine();
        System.out.print("Введите год рождения:");
        int yearOfBirth = scanner.nextInt();
        try {
            Reader reader = session.get(Reader.class, readerId);
            reader.setFirstName(firstName);
            reader.setLastName(lastName);
            reader.setMiddleName(middleName);
            reader.setYearOfBirth(yearOfBirth);
            reader.setExpiryDateOfTheLibraryCard(java.sql.Date.valueOf(newDate));
            session.save(reader);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void delete(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID читателя:");
        int readerId = scanner.nextInt();
        try {
            Reader reader = session.get(Reader.class, readerId);
            session.delete(reader);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.Id типа");
        System.out.println("2.Имя");
        System.out.println("3.Фамилия");
        System.out.println("4.Отчество");
        System.out.println("5.Год рождения");
        System.out.println("6.КСДЧБ");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Reader> readersList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите номер читательского билета: ");
                int libraryCardNumber = scanner.nextInt();
                try {
                    readersList = session.createQuery("SELECT r FROM Reader r WHERE library_card_number =" + libraryCardNumber,
                            Reader.class).getResultList();
                    System.out.println("Читатель");
                    System.out.printf("%-25s%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество", "Год рождения", "КСДЧБ");
                    readersList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 2 -> {
                System.out.print("Введите имя: ");
                String firstName = scanner.nextLine();
                try {
                    readersList = session.createQuery("SELECT r FROM Reader r WHERE first_name ='" + firstName + "'",
                            Reader.class).getResultList();
                    System.out.println("Читатель");
                    System.out.printf("%-25s%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество", "Год рождения", "КСДЧБ");
                    readersList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 3 -> {
                System.out.print("Введите фамилию: ");
                String lastName = scanner.nextLine();
                try {
                    readersList = session.createQuery("SELECT r FROM Reader r WHERE last_name ='" + lastName + "'",
                            Reader.class).getResultList();
                    System.out.println("Читатель");
                    System.out.printf("%-25s%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество", "Год рождения", "КСДЧБ");
                    readersList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 4 -> {
                System.out.print("Введите отчество: ");
                String middleName = scanner.nextLine();
                try {
                    readersList = session.createQuery("SELECT r FROM Reader r WHERE middle_name ='" + middleName + "'",
                            Reader.class).getResultList();
                    System.out.println("Читатель");
                    System.out.printf("%-25s%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество", "Год рождения", "КСДЧБ");
                    readersList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 5 -> {
                System.out.print("Введите год рождения: ");
                int yearOfBirth = scanner.nextInt();
                try {
                    readersList = session.createQuery("SELECT r FROM Reader r WHERE year_of_birth ='" + yearOfBirth + "'",
                            Reader.class).getResultList();
                    System.out.println("Читатель");
                    System.out.printf("%-25s%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество", "Год рождения", "КСДЧБ");
                    readersList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            case 6 -> {
                System.out.print("Введите крайний срок действия читательского билета: ");
                String expiryDateOfTheLibraryCard = scanner.nextLine();
                try {
                    readersList = session.createQuery("SELECT r FROM Reader r WHERE expiry_date_of_the_library_card ='" + java.sql.Date.valueOf(expiryDateOfTheLibraryCard) + "'",
                            Reader.class).getResultList();
                    System.out.println("Читатель");
                    System.out.printf("%-25s%-25s%-25s%-25s%-25s\n","ID", "Имя", "Фамилия", "Отчество", "Год рождения", "КСДЧБ");
                    readersList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует");
                }
            }
            default -> System.out.println("Неверный ввод");
        }
    }
}
