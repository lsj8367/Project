package pack.orderinfo.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberEnum {
    MEMBER {
        @Override
        public void makeDataSet(Orderinfo orderinfo, String bookNo) {
            orderinfo.setOrderBooktype(bookNo);
        }
    },

    NON_MEMBER {
        @Override
        public void makeDataSet(Orderinfo orderinfo, String bookNo) {
            orderinfo.setOrderBooktype(bookNo);
        }
    };

    public abstract void makeDataSet(Orderinfo orderinfo, String bookNo);
}
