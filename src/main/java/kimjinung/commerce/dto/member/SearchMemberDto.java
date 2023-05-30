package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class SearchMemberDto {
    private String userId;
    private String email;

    public SearchMemberDto() {
    }

    public SearchMemberDto(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
