package pl.pjatk.library.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate validFrom;
    private LocalDate validTo;
    private double balance;
    private EnCardStatus status;
    @OneToOne(mappedBy = "libraryCard")
    private Reader reader;

    public LibraryCard() {

    }

    public LibraryCard(Long id, LocalDate validFrom, LocalDate validTo, double balance, EnCardStatus status, Reader reader) {
        this.id = id;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.balance = balance;
        this.status = status;
        this.reader = reader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public EnCardStatus getStatus() {
        return status;
    }

    public void setStatus(EnCardStatus status) {
        this.status = status;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }
}
