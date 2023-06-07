package kimjinung.commerce.dto.member;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Data
public class MemberSearchRequestDto {

    @NotBlank
    private String userId;

    protected MemberSearchRequestDto() {
    }

    public MemberSearchRequestDto(String userId) {
        this.userId = userId;
    }
}
