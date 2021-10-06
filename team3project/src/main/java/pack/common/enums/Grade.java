package pack.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Grade {
    READY("0"),
    FIRST_GRADE("1"),
    SECOND_GRADE("2"),
    THIRD_GRADE("3"),
    FORTH_GRADE("4"),
    FIFTH_GRADE("5");

    private String grade;
}
