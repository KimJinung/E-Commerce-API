package kimjinung.commerce.usecase.member;

import kimjinung.commerce.Infrastructure.repository.member.MemberRepository;
import kimjinung.commerce.domain.Address;
import kimjinung.commerce.domain.Member;
import kimjinung.commerce.dto.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    @Override
    public JoinMemberResultDto join(JoinMemberDto joinMemberDto) throws IllegalStateException{
        Optional<Member> optionalMember = repository.findByUserId(joinMemberDto.getUserId());
        if (optionalMember.isPresent()) {
            throw new IllegalStateException("Already exist userId");
        }

        String userId = joinMemberDto.getUserId();
        String password = joinMemberDto.getPassword();
        String email = joinMemberDto.getEmail();
        String phoneNumber = joinMemberDto.getPhoneNumber();
        String city = joinMemberDto.getCity();
        String street = joinMemberDto.getStreet();
        String zipcode = joinMemberDto.getZipcode();
        Address address = new Address(city, street, zipcode);
        Member member = new Member(userId, password, phoneNumber, email, address);

        UUID result = repository.save(member);
        if (result == null) {
            throw new IllegalStateException("Fail to join");
        }

        Optional<Member> byUserId = repository.findByUserId(userId);
        if (byUserId.isEmpty()) {
            throw new IllegalStateException("Fail to join");
        }
        Member savedMember = byUserId.get();

        String savedMemberUserId = savedMember.getUserId();
        return new JoinMemberResultDto(savedMemberUserId);
    }

    @Override
    public SearchMemberResultDto search(SearchMemberDto searchMemberDto) throws IllegalStateException{
        String userId = searchMemberDto.getUserId();
//        String email = searchMemberDto.getEmail(); // ADD 2Step validation

        Optional<Member> optionalMember = repository.findByUserId(userId);
        if (optionalMember.isEmpty()) {
            throw new IllegalStateException("Not found member");
        }
        Member foundMember = optionalMember.get();

        if (!userId.equals(foundMember.getUserId())) {
            throw new IllegalStateException("Not match user id and email");
        }

        return new SearchMemberResultDto(
                foundMember.getUserId(),
                foundMember.getPassword()
        );
    }

    @Override
    public UpdateMemberResultDto update(UpdateMemberDto updateMemberDto) throws IllegalStateException{
        Optional<Member> optionalMember = repository.findByUserId(updateMemberDto.getUserId());
        if (optionalMember.isEmpty()) {
            throw new IllegalStateException("Not found member");
        }
        Member member = optionalMember.get();

        String password = updateMemberDto.getPassword();
        String email = updateMemberDto.getEmail();
        String phoneNumber = updateMemberDto.getPhoneNumber();
        String city = updateMemberDto.getCity();
        String street = updateMemberDto.getStreet();
        String zipCode = updateMemberDto.getZipCode();
        Address address = new Address(city, street, zipCode);

        member.changePassword(password);
        member.changeEmail(email);
        member.changePhoneNumber(phoneNumber);
        member.changeAddress(address);

        Optional<Member> optionalUpdatedMember = repository.findByUserId(member.getUserId());
        if (optionalUpdatedMember.isEmpty()) {
            throw new IllegalStateException("Not found member");
        }
        Member updatedMember = optionalUpdatedMember.get();

        return new UpdateMemberResultDto(
                updatedMember.getEmail(),
                updatedMember.getPhoneNumber(),
                updatedMember.getAddress().getCity(),
                updatedMember.getAddress().getStreet(),
                updatedMember.getAddress().getZipCode()
        );

    }

    @Override
    public boolean withdrawal(WithdrawalMemberDto withdrawalMemberDto) {
        String userId = withdrawalMemberDto.getUserId();
        Optional<Member> optionalMember = repository.findByUserId(userId);
        if (optionalMember.isEmpty()) {
            throw new IllegalStateException("Not exist member");
        }
        Member member = optionalMember.get();
        return repository.remove(member);
    }
}
