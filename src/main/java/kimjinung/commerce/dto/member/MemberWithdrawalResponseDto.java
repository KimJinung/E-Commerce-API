package kimjinung.commerce.dto.member;

import lombok.Data;
import lombok.Getter;

@Data
public class MemberWithdrawalResponseDto {
    private String userId;

    protected MemberWithdrawalResponseDto() {
    }

    public MemberWithdrawalResponseDto(String userId) {
        this.userId = userId;
    }
}
