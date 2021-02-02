package pl.pjatk.library.model;

import javax.persistence.*;

@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private Long pesel;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "library_card_id", referencedColumnName = "id")
    private LibraryCard libraryCard;


    public Reader() {

    }

    public Reader(Long id, String name, String surname, String address, String phoneNumber,Long pesel, LibraryCard libraryCard ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pesel=pesel;
        this.libraryCard = libraryCard;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {this.pesel = pesel; }

    public void setLibraryCard(LibraryCard libraryCard) {
        this.libraryCard = libraryCard;
    }

    public LibraryCard getLibraryCard() { return libraryCard; }
}
