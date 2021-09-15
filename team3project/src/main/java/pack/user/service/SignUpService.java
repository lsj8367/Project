package pack.user.service;

import java.util.Objects;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.domain.entity.CardInfo;
import pack.repository.CardInfoRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpService {
    private final CardInfoRepository cardInfoRepository;

    public void insertCard(CardInfo cardInfo) {
        if (Objects.isNull(cardInfo)) {
            throw new RuntimeException("카드 정보가 없습니다.");
        }
        cardInfoRepository.save(cardInfo);
    }

}
