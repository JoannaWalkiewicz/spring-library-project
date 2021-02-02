package pl.pjatk.library.system.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pjatk.library.model.Book;
import pl.pjatk.library.model.EnBookStatus;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select case when count(b)> 0 then true else false end from Book b where lower(b.referenceId) like lower(:referenceId)")
    boolean existsBookByReferenceId(@Param("referenceId") String referenceId);

    @Modifying
    @Transactional
    @Query("update Book b set b.status = :status where b.id = :id")
    int setBookStatus(@Param("status") EnBookStatus status, @Param("id") Long id);
}
