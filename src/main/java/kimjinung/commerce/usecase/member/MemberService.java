package kimjinung.commerce.usecase.member;

import kimjinung.commerce.dto.member.*;

public interface MemberService {
    JoinMemberResultDto join(JoinMemberDto joinMemberDto);
    SearchMemberResultDto search(SearchMemberDto memberSearchDto);
    UpdateMemberResultDto update(UpdateMemberDto updateMemberDto);
    boolean withdrawal(WithdrawalMemberDto withdrawalMemberDto);
}
