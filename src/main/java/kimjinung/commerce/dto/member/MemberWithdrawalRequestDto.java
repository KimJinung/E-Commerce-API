package kimjinung.commerce.dto.member;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberWithdrawalRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    protected MemberWithdrawalRequestDto() {
    }

    public MemberWithdrawalRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
