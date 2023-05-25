package kimjinung.commerce.dto.member;

import lombok.Getter;

@Getter
public class MemberUpdateDTO {
    private String uuid;
    private String id;
    private String password;
    private String phoneNumber;
    private String email;
    private String city;
    private String street;
    private String zipcode;
}
