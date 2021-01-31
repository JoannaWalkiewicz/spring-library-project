package pl.pjatk.library.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.library.model.LibraryCard;

public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {
}
