package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class UpdateMemberDto {
    private String userId;
    private String password;
    private String email;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;

    public UpdateMemberDto() {
    }

    public UpdateMemberDto(
            String userId,
            String password,
            String email,
            String phoneNumber,
            String city,
            String street,
            String zipCode) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
