package kimjinung.commerce.domain;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "uuid", columnDefinition = "BINARY(16)")
    private UUID uuid;
    private String userId;
    private String password;
    private String phoneNumber;
    private String email;
    @Embedded
    private Address address;

    protected Member() {
    }

    public Member(
            String userId,
            String password,
            String phoneNumber,
            String email,
            Address address) {
        this.userId = userId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void changePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }

    public void changeAddress(Address newAddress) {
        this.address = newAddress;
    }
}
