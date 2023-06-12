package kimjinung.commerce.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    public MemberLoginRequestDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
