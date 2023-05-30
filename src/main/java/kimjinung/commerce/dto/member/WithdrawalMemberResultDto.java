package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class WithdrawalMemberResultDto {
    private boolean result;
    private String errorMsg;

    public WithdrawalMemberResultDto() {
    }

    public WithdrawalMemberResultDto(boolean result) {
        this.result = result;
    }

    public WithdrawalMemberResultDto(boolean result, String errorMsg) {
        this.result = result;
        this.errorMsg = errorMsg;
    }
}
