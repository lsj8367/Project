package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.domain.entity.NewBook;

public interface NewBookRepository extends JpaRepository<NewBook, Long>, NewBookRepositorySupport {
}
