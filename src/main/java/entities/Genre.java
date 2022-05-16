package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int genreId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Set <PrintedProduct> products;

    @Column(name = "genre_name")
    private String genreName;

    public Genre() { }

    public Genre(int genreId, Set<PrintedProduct> products, String genreName) {
        this.genreId = genreId;
        this.products = products;
        this.genreName = genreName;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Set<PrintedProduct> getProducts() {
        return products;
    }

    public void setProducts(Set<PrintedProduct> products) {
        this.products = products;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return String.format("%-15d%-15s", genreId, genreName);
    }
}
