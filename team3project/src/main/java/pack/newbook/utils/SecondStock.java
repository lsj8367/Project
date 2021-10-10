package pack.newbook.utils;

import pack.admin.utils.RankEnum;

public class SecondStock implements Stock {

    @Override
    public int getRank() {
        return RankEnum.SECOND.getRank();
    }

    @Override
    public int getNewBookStock(int stock) {
        return stock + 100;
    }

}
