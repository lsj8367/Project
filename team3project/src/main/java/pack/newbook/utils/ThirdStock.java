package pack.newbook.utils;

import pack.admin.utils.RankEnum;

public class ThirdStock implements Stock {

    @Override
    public int getRank() {
        return RankEnum.THIRD.getRank();
    }

    @Override
    public int getNewBookStock(int stock) {
        return stock + 50;
    }

}
