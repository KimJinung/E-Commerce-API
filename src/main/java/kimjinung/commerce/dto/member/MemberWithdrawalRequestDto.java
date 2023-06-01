package kimjinung.commerce.dto.member;


import lombok.Data;

@Data
public class MemberWithdrawalRequestDto {
    private String userId;
    private String password;

    protected MemberWithdrawalRequestDto() {
    }

    public MemberWithdrawalRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
