package pl.pjatk.library.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int yearOfPublishment;
    private String referenceId;
    private EnBookType type;
    private EnBookStatus status;
    @OneToMany(mappedBy = "book")
    private List<Author> author;
}
