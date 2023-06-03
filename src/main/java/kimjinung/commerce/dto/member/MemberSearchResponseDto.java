package kimjinung.commerce.dto.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberSearchResponseDto {

    private String userId;
    private String email;

    protected MemberSearchResponseDto() {
    }

    public MemberSearchResponseDto(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
