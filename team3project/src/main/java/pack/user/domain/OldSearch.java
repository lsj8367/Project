package pack.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OldSearch {
    private String type;
    private String search;

    @Builder
    public OldSearch(String type, String search) {
        this.type = type;
        this.search = search;
    }
}
