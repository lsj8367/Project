package pack.newbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.newbook.domain.NewBook;

public interface NewBookRepository extends JpaRepository<NewBook, Long>, NewBookRepositorySupport {
}
