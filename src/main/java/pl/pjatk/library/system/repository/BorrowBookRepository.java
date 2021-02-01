package pl.pjatk.library.system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.model.BorrowBook;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
}
