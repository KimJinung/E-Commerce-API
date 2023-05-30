package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class UpdateMemberResultDto {
    private String email;
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;

    public UpdateMemberResultDto() {
    }

    public UpdateMemberResultDto(
            String email,
            String phoneNumber,
            String city,
            String street,
            String zipCode) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }
}
