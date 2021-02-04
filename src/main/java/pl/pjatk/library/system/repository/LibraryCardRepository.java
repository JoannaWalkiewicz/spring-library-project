package pl.pjatk.library.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.pjatk.library.model.LibraryCard;

import java.time.LocalDate;

public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {
    @Modifying
    @Transactional
    @Query("update LibraryCard lc set lc.balance = :balance where lc.id = :id")
    int setBalanace(@Param("balance") double balance, @Param("id") Long id);
}
