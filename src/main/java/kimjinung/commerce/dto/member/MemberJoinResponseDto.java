package kimjinung.commerce.dto.member;

import lombok.Data;

@Data
public class MemberJoinResponseDto {
    private String userId;
    private String email;
    private String phoneNumber;

    protected MemberJoinResponseDto() {
    }

    public MemberJoinResponseDto(String userId, String email, String phoneNumber) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
