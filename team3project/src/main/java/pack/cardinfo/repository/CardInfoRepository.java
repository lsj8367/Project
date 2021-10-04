package pack.cardinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.cardinfo.domain.CardInfo;

public interface CardInfoRepository extends JpaRepository<CardInfo, String>, CardInfoRepositorySupport {
}
