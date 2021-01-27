package pl.pjatk.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    //@OneToMany(mappedBy = "author")
    //private List<Author> author;
}
