package pack.repository;

import pack.domain.entity.NewBook;

public interface NewBookRepositorySupport {
    NewBook selectBestSeller();
}
