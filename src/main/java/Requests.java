import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class Requests {
    public void work(SessionFactory sessionFactory){
        System.out.println("1.Первый запрос");
        System.out.println("2.Второй запрос");
        System.out.println("3.Третий запрос");
        System.out.println("4.Вернуться в меню");
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Session session;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        switch (i) {
            case 1 -> firstRequest(session);
            case 2 -> secondRequest(session);
            case 3 -> thirdRequest(session);
            case 4 -> {
                session.close();
                return;
            }
            default -> System.out.println("Неверный ввод");
        }
        session.getTransaction().commit();
    }

    private void firstRequest(Session session) {
        List<PrintedProduct> products = session.createQuery("SELECT pp FROM PrintedProduct pp",
                    PrintedProduct.class).getResultList();
        System.out.printf("%-25s%-25s%-15s\n", "Название жанра", "Название книги", "Автор");
        for (PrintedProduct p : products)
            System.out.printf("%-25s%-25s%-25s\n", p.getGenre().getGenreName(),
                    p.getTitle(), p.getAuthor());
    }


    private void secondRequest(Session session){
        List<Accounting> orders = session.createQuery("SELECT a FROM Accounting a", Accounting.class).getResultList();
        System.out.printf("%-25s%-25s\n", "Книга", "Количество");
        for (Accounting o : orders)
            if (o.getProduct().getAmount() >= 3)
                System.out.printf("%-25s%-25d \n", o.getProduct().getTitle(), o.getProduct().getAmount());
    }

    private void thirdRequest(Session session){
        List<Reader> readers = session.createQuery("SELECT r FROM Reader r", Reader.class).getResultList();
        System.out.printf("%-25s%-25s \n", "Имя", "Фамилия");
        for (Reader r : readers)
            if(r.getExpiryDateOfTheLibraryCard().after(java.sql.Date.valueOf("2022-09-05")))
                System.out.printf("%-25s%-25s \n", r.getFirstName(), r.getLastName());
    }
}
