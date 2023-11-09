package fabiomarras.u5w2d2.repositories;

import fabiomarras.u5w2d2.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
