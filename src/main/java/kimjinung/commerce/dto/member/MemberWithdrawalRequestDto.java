package kimjinung.commerce.dto.member;


import lombok.Data;

@Data
public class MemberWithdrawalRequestDto {
    private String userId;
    private String password;

    protected MemberWithdrawalRequestDto() {
    }


}
