package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class MemberSearchRequestDto {
    private String userId;

    protected MemberSearchRequestDto() {
    }

    public MemberSearchRequestDto(String userId) {
        this.userId = userId;
    }
}
