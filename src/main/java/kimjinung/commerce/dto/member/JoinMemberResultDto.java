package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class JoinMemberResultDto {
    private String userId;
    private String errorMsg;


    public JoinMemberResultDto() {
    }

    public JoinMemberResultDto(String userId) {
        this.userId = userId;
    }

    public JoinMemberResultDto(String userId, String errorMsg) {
        this.userId = userId;
        this.errorMsg = errorMsg;
    }
}
