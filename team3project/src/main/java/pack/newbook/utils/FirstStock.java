package pack.newbook.utils;

import pack.admin.utils.RankEnum;

public class FirstStock implements Stock {

    @Override
    public int getRank() {
        return RankEnum.FIRST.getRank();
    }

    @Override
    public int getNewBookStock(int stock) {
        return stock + 200;
    }

}
