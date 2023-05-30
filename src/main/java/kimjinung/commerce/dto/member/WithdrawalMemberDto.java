package kimjinung.commerce.dto.member;


import lombok.Getter;

@Getter
public class WithdrawalMemberDto {
    private String userId;

    public WithdrawalMemberDto(String userId) {
        this.userId = userId;
    }
}
