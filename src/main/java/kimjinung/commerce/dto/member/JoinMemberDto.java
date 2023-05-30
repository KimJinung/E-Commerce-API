package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class JoinMemberDto {
    private String userId;
    private String password;
    private String phoneNumber;
    private String email;
    private String city;
    private String street;
    private String zipcode;

    public JoinMemberDto() {
    }

    public JoinMemberDto(
            String userId,
            String password,
            String phoneNumber,
            String email,
            String city,
            String street,
            String zipcode) {
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
