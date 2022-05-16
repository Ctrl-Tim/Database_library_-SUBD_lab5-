package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "printed_product")
public class PrintedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "source_expiration")
    private Date sourceExpiration;

    @Column(name = "amount")
    private int amount;

    public PrintedProduct() { }

    public PrintedProduct(int productId, Genre genre, String title, String author, String itemType, Date sourceExpiration, int amount) {
        this.productId = productId;
        this.genre = genre;
        this.title = title;
        this.author = author;
        this.itemType = itemType;
        this.sourceExpiration = sourceExpiration;
        this.amount = amount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Date getSourceExpiration() {
        return sourceExpiration;
    }

    public void setSourceExpiration(Date sourceExpiration) {
        this.sourceExpiration = sourceExpiration;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int Amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%-30d%-30d%-30s%-30s%-30s%-30d%tF-30d",
            productId, genre.getGenreId(), title, author, itemType, sourceExpiration, amount);
    }
}
