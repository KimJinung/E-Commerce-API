package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class MemberWithdrawalResponseDto {
    private String userId;

    protected MemberWithdrawalResponseDto() {
    }

    public MemberWithdrawalResponseDto(String userId) {
        this.userId = userId;
    }
}
