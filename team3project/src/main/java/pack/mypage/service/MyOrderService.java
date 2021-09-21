package pack.mypage.service;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.domain.entity.NewBook;
import pack.repository.NewBookRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MyOrderService {

    private final NewBookRepository newBookRepository;

    public void updateScount(int nb_no, int order_scount) {
        Optional<NewBook> optionalNewBook = newBookRepository.findById((long) nb_no);

        optionalNewBook.ifPresent(newBook -> {
            newBook.setNbScount(newBook.getNbScount() - order_scount);
            newBook.setNbStock(newBook.getNbStock() + order_scount);
        });
    }

    public NewBook recommandNewBook() {
        return newBookRepository.recommandNewBook();
    }

}
