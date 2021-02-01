package pl.pjatk.library.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.library.model.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Long> {
}
