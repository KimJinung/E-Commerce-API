package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class JoinMemberResultDto {
    private String userId;

    public JoinMemberResultDto() {
    }

    public JoinMemberResultDto(String userId) {
        this.userId = userId;
    }
}
