package logics;

import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class GenreLogic {
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
        System.out.print("Введите название жанра: ");
        String genreName = scanner.next();
        try {
            Genre genre = new Genre(genreName);
            session.save(genre);
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
                List<Genre> genresList = session.createQuery("SELECT g FROM Genre g",
                        Genre.class).getResultList();
                System.out.println("Жанр");
                System.out.printf("%-15s%s\n","ID","Название жанра");
                genresList.forEach(System.out::println);
            }
            default -> System.out.println("Неверный ввод");
        }
    }

    private void update(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID жанра: ");
        int genreId = scanner.nextInt();
        scanner = new Scanner(System.in);
        System.out.println("Введите название жанра: ");
        String genreName = scanner.nextLine();
        try {
            Genre genre = session.get(Genre.class, genreId);
            genre.setGenreName(genreName);
            session.save(genre);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует!");
        }
    }

    private void delete(Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID поставки: ");
        int genreId = scanner.nextInt();
        try {
            Genre genre = session.get(Genre.class, genreId);
            session.delete(genre);
        }
        catch (Exception ex){
            System.out.println("Этой записи не существует");
        }
    }

    private void filter(Session session){
        System.out.println("\tПоля");
        System.out.println("1.Id поставки");
        System.out.println("2.Дата поставки");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        List<Genre> genresList;
        switch (i) {
            case 1 -> {
                System.out.print("Введите ID жанра: ");
                int genreId = scanner.nextInt();
                try {
                    genresList = session.createQuery("SELECT g FROM Genre g WHERE genre_id =" + genreId,
                            Genre.class).getResultList();
                    System.out.println("Жанр");
                    System.out.printf("%-15s%s\n","ID","Название жанра");
                    genresList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            case 2 -> {
                System.out.print("Введите название жанра: ");
                String genreName = scanner.next();
                try {
                    genresList = session.createQuery("SELECT g FROM Genre g WHERE genre_name =" + genreName,
                            Genre.class).getResultList();
                    System.out.println("Жанр");
                    System.out.printf("%-15s%s\n","ID","Название жанра");
                    genresList.forEach(System.out::println);
                }
                catch (Exception ex){
                    System.out.println("Этой записи не существует!");
                }
            }
            default -> System.out.println("Неверный ввод!");
        }
    }
}
