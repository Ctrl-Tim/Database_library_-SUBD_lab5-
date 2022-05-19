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

    public Reader() { }

    public Reader(String firstName, String lastName, String middleName, int yearOfBirth, Date expiryDateOfTheLibraryCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.yearOfBirth = yearOfBirth;
        this.expiryDateOfTheLibraryCard = expiryDateOfTheLibraryCard;
    }

    public int getLibraryCardNumber() {
        return libraryCardNumber;
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
        return String.format("%-25d%-25s%-25s%-25s%-25d%tF",
                libraryCardNumber, firstName, lastName, middleName, yearOfBirth, expiryDateOfTheLibraryCard);
    }
}
