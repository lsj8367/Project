package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.domain.entity.CardInfo;

public interface CardInfoRepository extends JpaRepository<CardInfo, String>, CardInfoRepositorySupport {
}
