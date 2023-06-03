package kimjinung.commerce.dto.member;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class MemberSearchRequestDto {

    @NotBlank
    private String userId;

    protected MemberSearchRequestDto() {
    }

    public MemberSearchRequestDto(String userId) {
        this.userId = userId;
    }
}
