package kimjinung.commerce.dto.member;

import lombok.Data;

@Data
public class MemberUpdateResponseDto {
    private String userId;
    private String email;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;

    public MemberUpdateResponseDto() {
    }

    public MemberUpdateResponseDto(
            String userId,
            String email,
            String phoneNumber,
            String city,
            String street,
            String zipCode) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }


}
