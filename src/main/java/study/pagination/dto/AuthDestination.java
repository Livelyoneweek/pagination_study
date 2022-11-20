package study.pagination.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AuthDestination {
    TOSS("TOSS"),
    KB("KB"),
    NAVER("NAVER"),
    KAKAO("KAKAO");

    private final String code;

    public static AuthDestination findBy(String val) {
        for (AuthDestination authDestination : AuthDestination.values()) {
            if (authDestination.name().equals(val)) {
                return authDestination;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
