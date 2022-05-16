package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "library_card_number")
    private int libraryCardNumber;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Set <Accounting> orders;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "expiry_date_of_the_library_card")
    private Date expiryDateOfTheLibraryCard;

    public int getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(int libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }

    public Set<Accounting> getOrders() {
        return orders;
    }

    public void setOrders(Set<Accounting> orders) {
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Date getExpiryDateOfTheLibraryCard() {
        return expiryDateOfTheLibraryCard;
    }

    public void setExpiryDateOfTheLibraryCard(Date expiryDateOfTheLibraryCard) {
        this.expiryDateOfTheLibraryCard = expiryDateOfTheLibraryCard;
    }

    @Override
    public String toString() {
        return String.format("%-30d%-30s%-30s%-30s%-30d%-30d%tF",
                libraryCardNumber, firstName, lastName, middleName, yearOfBirth, expiryDateOfTheLibraryCard);
    }
}
