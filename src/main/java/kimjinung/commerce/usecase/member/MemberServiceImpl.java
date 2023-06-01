package kimjinung.commerce.usecase.member;

import kimjinung.commerce.Infrastructure.repository.member.MemberRepository;
import kimjinung.commerce.domain.Address;
import kimjinung.commerce.domain.Member;
import kimjinung.commerce.dto.member.*;
import kimjinung.commerce.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberJoinResponseDto join(MemberJoinRequestDto memberJoinRequestDto) throws RuntimeException {

        String requestUserId = memberJoinRequestDto.getUserId();
        Optional<Member> optionalDuplicateMember = memberRepository.findByUserId(requestUserId);
        if (optionalDuplicateMember.isPresent()) {
            throw new UserIdAlreadyExistException(requestUserId);
        }

        Member newMember = createMember(memberJoinRequestDto);

        Optional<Member> optionalMember = memberRepository.save(newMember);
        if (optionalMember.isEmpty()) {
            throw new MemberJoinFailException();
        }
        Member member = optionalMember.get();

        return new MemberJoinResponseDto(
                member.getUserId(),
                member.getEmail(),
                member.getPhoneNumber()
        );
    }

    @Override
    public MemberSearchResponseDto search(MemberSearchRequestDto memberSearchRequestDto) throws RuntimeException {

        String userId = memberSearchRequestDto.getUserId();

        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if (optionalMember.isEmpty()) {
            throw new MemberNotFoundException();
        }
        Member foundMember = optionalMember.get();

        return new MemberSearchResponseDto(
                foundMember.getUserId(),
                foundMember.getEmail()
        );
    }

    @Override
    public MemberUpdateResponseDto update(MemberUpdateRequestDto memberUpdateRequestDto) throws RuntimeException {

        String requestUserId = memberUpdateRequestDto.getUserId();
        Optional<Member> optionalMember = memberRepository.findByUserId(requestUserId);

        if (optionalMember.isEmpty()) {
            throw new MemberNotFoundException();
        }
        Member member = optionalMember.get();

        updateMember(member, memberUpdateRequestDto);

        Optional<Member> optionalUpdatedMember = memberRepository.findByUserId(requestUserId);
        if (optionalUpdatedMember.isEmpty()) {
            throw new MemberUpdateFailException();
        }
        Member updatedMember = optionalMember.get();

        return new MemberUpdateResponseDto(
                updatedMember.getUserId(),
                updatedMember.getEmail(),
                updatedMember.getPhoneNumber(),
                updatedMember.getAddress().getCity(),
                updatedMember.getAddress().getStreet(),
                updatedMember.getAddress().getZipCode()
        );
    }

    @Override
    public MemberWithdrawalResponseDto withdrawal(MemberWithdrawalRequestDto memberWithdrawalRequestDto)
            throws RuntimeException{

        String requestUserId = memberWithdrawalRequestDto.getUserId();
        Optional<Member> optionalMember = memberRepository.findByUserId(requestUserId);

        if (optionalMember.isEmpty()) {
            throw new MemberNotFoundException();
        }
        Member member = optionalMember.get();
        Optional<Member> optionalRemovedMember = memberRepository.remove(member);

        if (optionalRemovedMember.isPresent()) {
            throw new MemberWithdrawalFailException();
        }

        return new MemberWithdrawalResponseDto(requestUserId);
    }

    private Member createMember(MemberJoinRequestDto memberJoinRequestDto) {
        String userId = memberJoinRequestDto.getUserId();
        String password = memberJoinRequestDto.getPassword();
        String email = memberJoinRequestDto.getEmail();
        String phoneNumber = memberJoinRequestDto.getPhoneNumber();
        String city = memberJoinRequestDto.getCity();
        String street = memberJoinRequestDto.getStreet();
        String zipcode = memberJoinRequestDto.getZipcode();
        Address address = new Address(city, street, zipcode);

        return new Member(userId, password, phoneNumber, email, address);
    }

    private void updateMember(Member member, MemberUpdateRequestDto memberUpdateRequestDto) {
        String city = memberUpdateRequestDto.getCity();
        String street = memberUpdateRequestDto.getStreet();
        String zipCode = memberUpdateRequestDto.getZipCode();
        Address address = new Address(city, street, zipCode);

        member.changePassword(memberUpdateRequestDto.getPassword());
        member.changeEmail(memberUpdateRequestDto.getEmail());
        member.changePhoneNumber(memberUpdateRequestDto.getPhoneNumber());
        member.changeAddress(address);
    }
}
