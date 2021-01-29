package pl.pjatk.library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Author() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonBackReference
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    private String name;
    private String surname;
    private String nickname;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    public Author(Long id, String name, String surname, String nickname, Book book) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.book = book;
    }
}
