package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class SearchMemberResultDto {
    private String userId;
    private String password;
    private String errorMsg;


    public SearchMemberResultDto() {
    }

    public SearchMemberResultDto(
            String userId,
            String password) {
        this.userId = userId;
        this.password = password;
    }

    public SearchMemberResultDto(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
