package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class SearchMemberResultDto {
    private String userId;
    private String password;


    public SearchMemberResultDto() {
    }

    public SearchMemberResultDto(
            String userId,
            String password) {
        this.userId = userId;
        this.password = password;
    }
}
