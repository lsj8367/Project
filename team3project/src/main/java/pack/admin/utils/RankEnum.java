package pack.admin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RankEnum {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    ANOTHER(-1);

    private int rank;
}
