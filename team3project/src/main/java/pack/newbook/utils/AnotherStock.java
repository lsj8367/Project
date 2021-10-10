package pack.newbook.utils;

import pack.admin.utils.RankEnum;

public class AnotherStock implements Stock {

    @Override
    public int getRank() {
        return RankEnum.ANOTHER.getRank();
    }

    @Override
    public int getNewBookStock(int stock) {
        return stock;
    }

}
