import entities.*;
import logics.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Reader.class)
                .addAnnotatedClass(Genre.class)
                .addAnnotatedClass(PrintedProduct.class)
                .addAnnotatedClass(Accounting.class)
                .buildSessionFactory();

        boolean working = true;
        while (working) {
            System.out.println("\n\t\tМенюшка");
            System.out.println("1.Работать с 'employee'");
            System.out.println("2.Работать с 'reader'");
            System.out.println("3.Работать с 'genre'");
            System.out.println("4.Работать с 'printed_product'");
            System.out.println("5.Работать с 'accounting'");
            System.out.println("6.Работать с запросами");
            System.out.println("7.Выход");
            System.out.print(">");
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            switch (i) {
                case 1 -> {
                    EmployeeLogic employeeLogic = new EmployeeLogic();
                    employeeLogic.work(sessionFactory);
                }
                case 2 -> {
                    ReaderLogic readerLogic = new ReaderLogic();
                    readerLogic.work(sessionFactory);
                }
                case 3 -> {
                    GenreLogic genreLogic = new GenreLogic();
                    genreLogic.work(sessionFactory);
                }
                case 4 -> {
                    PrintedProductLogic printedProductLogic = new PrintedProductLogic();
                    printedProductLogic.work(sessionFactory);
                }
                case 5 -> {
                    AccountingLogic accountingLogic = new AccountingLogic();
                    accountingLogic.work(sessionFactory);
                }
                case 6 -> {
                    Requests requests = new Requests();
                    requests.work(sessionFactory);
                }
                case 7 -> {
                    System.out.println("Exit...");
                    working = false;
                }
                default -> System.out.println("Invalid input");
            }
        }
        sessionFactory.close();
    }
}
