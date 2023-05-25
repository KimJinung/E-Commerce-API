package kimjinung.commerce.usecase.member;

import kimjinung.commerce.dto.member.*;

import java.util.List;

public interface MemberService {
    MemberDTO join(MemberJoinDTO memberJoinDTO);
    List<MemberDTO> search(MemberSearchDTO memberSearchDTO);
    MemberDTO update(MemberUpdateDTO memberUpdateDTO);
    boolean withdrawal(MemberWithdrawalDTO memberWithdrawalDTO);
}
