package pack.obfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.obfile.domain.ObFile;

public interface ObFileRepository extends JpaRepository<ObFile, Long> {

}
