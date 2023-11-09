package fabiomarras.u5w2d2.repositories;

import fabiomarras.u5w2d2.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogsRepository extends JpaRepository<Blog, Integer> {
}
