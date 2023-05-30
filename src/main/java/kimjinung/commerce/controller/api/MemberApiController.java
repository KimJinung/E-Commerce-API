package kimjinung.commerce.controller.api;

import kimjinung.commerce.dto.member.*;
import kimjinung.commerce.usecase.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public JoinMemberResultDto join(@RequestBody JoinMemberDto joinMemberDto) {
        return memberService.join(joinMemberDto);
    }

    @PostMapping("/search")
    public SearchMemberResultDto search(@RequestBody SearchMemberDto searchMemberDto) {
        return memberService.search(searchMemberDto);
    }

    @PostMapping("/update")
    public UpdateMemberResultDto update(@RequestBody UpdateMemberDto updateMemberDto) {
        return memberService.update(updateMemberDto);
    }

    @PostMapping("/withdrawal")
    public boolean withdrawal(@RequestBody WithdrawalMemberDto withdrawalMemberDto) {
        System.out.println("withdrawalMemberDto = " + withdrawalMemberDto);
        return memberService.withdrawal(withdrawalMemberDto);
    }
}
