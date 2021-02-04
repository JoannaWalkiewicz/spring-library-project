package pl.pjatk.library.system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.model.BorrowBook;
import pl.pjatk.library.model.EnBookStatus;

import java.time.LocalDate;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
   @Query("select b from BorrowBook b where b.book.id = :bookId")
   BorrowBook getBorrowBookByBookId(@Param("bookId") Long bookId);

   @Modifying
   @Transactional
   @Query("update BorrowBook b set b.dateOfReturn = :dateOfReturn where b.id = :id")
   int setBorrowBookReturnDate(@Param("dateOfReturn") LocalDate dateOfReturn, @Param("id") Long id);
}
