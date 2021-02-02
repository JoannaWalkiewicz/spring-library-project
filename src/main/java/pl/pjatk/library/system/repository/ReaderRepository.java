package pl.pjatk.library.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pjatk.library.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader,Long> {
    @Query("select case when count(r)> 0 then true else false end from Reader r where r.pesel =:pesel")
    boolean existsByPesel(@Param("pesel") Long pesel);

}
