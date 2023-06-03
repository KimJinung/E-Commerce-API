package kimjinung.commerce.dto.member;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class MemberUpdateRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp="(^$|[0-9]{11})")
    private String phoneNumber;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String zipCode;

    protected MemberUpdateRequestDto() {
    }

    public MemberUpdateRequestDto(
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
