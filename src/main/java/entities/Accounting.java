package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "accounting")
public class Accounting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_number")
    private int orderNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private PrintedProduct product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "library_card_number")
    private Reader reader;

    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @Column(name = "term")
    private int term;

    @Column(name = "return_date")
    private Date returnDate;

    public Accounting() { }

    public Accounting(int orderNumber, Employee employee, PrintedProduct product, Reader reader, Date dateOfIssue, int term, Date returnDate) {
        this.orderNumber = orderNumber;
        this.employee = employee;
        this.product = product;
        this.reader = reader;
        this.dateOfIssue = dateOfIssue;
        this.term = term;
        this.returnDate = returnDate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }


    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PrintedProduct getProduct() {
        return product;
    }

    public void setProduct(PrintedProduct product) {
        this.product = product;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return String.format("%-30d%-30d%-30d%-30d%-30d%tF-30d%-30d%tF",
                orderNumber, employee.getEmployeeId(), product.getProductId(), reader.getExpiryDateOfTheLibraryCard(),
                dateOfIssue, term, returnDate);
    }
}
