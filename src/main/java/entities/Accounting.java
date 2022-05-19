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
    private Employee employeeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private PrintedProduct productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "library_card_number")
    private Reader libraryCardNumber;

    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @Column(name = "term")
    private int term;

    @Column(name = "return_date")
    private Date returnDate;

    public Accounting() { }

    public Accounting(Employee employeeId, PrintedProduct productId, Reader libraryCardNumber, Date dateOfIssue, int term, Date returnDate) {
        this.employeeId = employeeId;
        this.productId = productId;
        this.libraryCardNumber = libraryCardNumber;
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
        return employeeId;
    }

    public void setEmployee(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public PrintedProduct getProduct() {
        return productId;
    }

    public void setProduct(PrintedProduct productId) {
        this.productId = productId;
    }

    public Reader getReader() {
        return libraryCardNumber;
    }

    public void setReader(Reader libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
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

    public PrintedProduct getProductId() { return productId; }

    public void setProductId(PrintedProduct productId) { this.productId = productId; }

    @Override
    public String toString() {
        return String.format("%-30d%-30d%-30d%-30d%-30d%tF-30d%-30d%tF",
                orderNumber, employeeId.getEmployeeId(), productId.getProductId(), libraryCardNumber.getExpiryDateOfTheLibraryCard(),
                dateOfIssue, term, returnDate);
    }
}
