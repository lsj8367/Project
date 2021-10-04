package pack.user.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import pack.newbook.domain.NewBook;
import pack.newbook.repository.NewBookRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class NewBookService {

    private final NewBookRepository newBookRepository;

    public List<NewBook> selectGenre(String nbGenre) {
        return newBookRepository.selectGenre(nbGenre);
    }

    public List<NewBook> selectBest30() {
        return newBookRepository.selectBest30();
    }

    public List<NewBook> selectNew() {
        return newBookRepository.selectNew();
    }

    public List<NewBook> selectBookAll() {
        return newBookRepository.findAll(Sort.by(Direction.DESC, "id"));
    }

    @Transactional
    public void plusReadCnt(int nbNo) {
        Optional<NewBook> optionalNewBook = newBookRepository.findById((long) nbNo);
        optionalNewBook.ifPresent(newBook -> {
            newBook.setNbReadcnt(newBook.getNbReadcnt() + 1);
        });
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Transactional()
    public NewBook selectNewBook(Long nbNo) {
        return newBookRepository.findById(nbNo).orElseThrow(NullPointerException::new);
    }

    public List<NewBook> selectAuthorList(String nbAuthor) {
        return newBookRepository.selectAuthorList(nbAuthor);
    }

    public List<NewBook> getDataNewAllExist(String nbName) {
        return newBookRepository.getDataNewAllExist(nbName);
    }

}
