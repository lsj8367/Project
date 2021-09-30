package pack.mypage.utils;

public class DelayState {
    private final int delayDays;

    private DelayState(int delayDays) {
        this.delayDays = delayDays;
    }

    public static DelayState of(int delayDays) {
        if (delayDays < 0) {
            throw new IllegalArgumentException("연체기간이 0보다 작을 수 없습니다.");
        }

        return new DelayState(delayDays);
    }

    public int delPointCalculate(int price) {
        if (delayDays < 3) {
            return Integer.parseInt(String.valueOf(Math.round(price * 0.05)));
        }

        if (delayDays < 6) {
            return Integer.parseInt(String.valueOf(Math.round(price * 0.1)));
        }

        if (delayDays < 10) {
            return Integer.parseInt(String.valueOf(Math.round(price * 0.15)));
        }

        if (delayDays < 14) {
            return Integer.parseInt(String.valueOf(Math.round(price * 0.2)));
        }

        return 0;
    }

}
