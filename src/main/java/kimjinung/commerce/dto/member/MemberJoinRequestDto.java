package kimjinung.commerce.dto.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class MemberJoinRequestDto {
    @NotBlank(message = "Input user id")
    private String userId;

    @NotBlank(message = "Input password")
    private String password;

    @Pattern(
            regexp="(^$|[0-9]{11})",
            message = "Please check phone number format"
    )
    private String phoneNumber;

    //TODO check email format
//    @Email(
//            message = "Please check email format"
//    )
    private String email;

    @NotBlank(message = "Input city address")
    private String city;

    @NotBlank(message = "Input street address")
    private String street;

    @NotBlank(message = "Input zip code address")
    private String zipcode;

    protected MemberJoinRequestDto() {
    }

    public MemberJoinRequestDto(
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
