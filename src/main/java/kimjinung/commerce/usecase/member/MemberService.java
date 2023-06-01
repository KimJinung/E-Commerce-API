package kimjinung.commerce.usecase.member;

import kimjinung.commerce.dto.member.*;

public interface MemberService {
    MemberJoinResponseDto join(MemberJoinRequestDto memberJoinRequestDto);
    MemberSearchResponseDto search(MemberSearchRequestDto memberSearchDto);
    MemberUpdateResponseDto update(MemberUpdateRequestDto memberUpdateRequestDto);
    MemberWithdrawalResponseDto withdrawal(MemberWithdrawalRequestDto memberWithdrawalRequestDto);
}
