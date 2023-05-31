package kimjinung.commerce.dto.member;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class JoinMemberDto {

    @NotNull
    private String userId;
    @NotNull
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @NotNull(message = "전화 번호 11자리는 필수입니다.")
    @Pattern(regexp="(^$|[0-9]{11})", message = "전화 번호 형식은 -를 제외한 11자리입니다.")
    private String phoneNumber;
    @NotNull(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;
    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
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
