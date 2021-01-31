package pl.pjatk.library.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.library.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader,Long> {
}
