package pack.orderinfo;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.orderinfo.domain.Orderinfo;
import pack.orderinfo.repository.OrderinfoRepository;

@Service
@RequiredArgsConstructor
public class OrderInfoService {
    private final OrderinfoRepository orderinfoRepository;

    public List<Orderinfo> selectNewBookOrderAll() {
        return orderinfoRepository.selectNewBookOrderAll();
    }

    public List<Orderinfo> selectOldBookOrderAll() {
        return orderinfoRepository.selectOldBookOrderAll();
    }
}
