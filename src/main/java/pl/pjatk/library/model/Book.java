package pl.pjatk.library.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int yearOfPublishment;
    private String referenceId;
    private EnBookType type;
    private EnBookStatus status;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Author> authors =new ArrayList<>();


    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }



    public Book(Long id, String title, int yearOfPublishment, String referenceId, EnBookType type, EnBookStatus status, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.yearOfPublishment = yearOfPublishment;
        this.referenceId = referenceId;
        this.type = type;
        this.status = status;
        this.authors = authors;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublishment() {
        return yearOfPublishment;
    }

    public void setYearOfPublishment(int yearOfPublishment) {
        this.yearOfPublishment = yearOfPublishment;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public EnBookType getType() {
        return type;
    }

    public void setType(EnBookType type) {
        this.type = type;
    }

    public EnBookStatus getStatus() {
        return status;
    }

    public void setStatus(EnBookStatus status) {
        this.status = status;
    }
}
