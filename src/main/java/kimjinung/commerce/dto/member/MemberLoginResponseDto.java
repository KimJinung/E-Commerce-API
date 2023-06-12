package kimjinung.commerce.dto.member;

import lombok.Data;

@Data
public class MemberLoginResponseDto {
    private boolean isOk;

    protected MemberLoginResponseDto() {
    }

    public MemberLoginResponseDto(boolean isOk) {
        this.isOk = isOk;
    }

}
