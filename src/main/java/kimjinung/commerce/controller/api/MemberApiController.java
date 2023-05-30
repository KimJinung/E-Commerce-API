package kimjinung.commerce.controller.api;

import kimjinung.commerce.dto.member.*;
import kimjinung.commerce.usecase.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<JoinMemberResultDto> join(@RequestBody JoinMemberDto joinMemberDto) {
        try {
            return ResponseEntity.ok(memberService.join(joinMemberDto));
        } catch (RuntimeException ex) {
            JoinMemberResultDto errorResult = new JoinMemberResultDto(null, ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResult);
        }

    }

    @PostMapping("/search")
    public ResponseEntity<SearchMemberResultDto> search(@RequestBody SearchMemberDto searchMemberDto) {
        try {
            return ResponseEntity.ok(memberService.search(searchMemberDto));
        } catch (RuntimeException ex) {
            SearchMemberResultDto errorResult = new SearchMemberResultDto(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateMemberResultDto> update(@RequestBody UpdateMemberDto updateMemberDto) {
        try {
            return ResponseEntity.ok(memberService.update(updateMemberDto));
        } catch (RuntimeException ex){
            UpdateMemberResultDto result = new UpdateMemberResultDto(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<WithdrawalMemberResultDto> withdrawal(@RequestBody WithdrawalMemberDto withdrawalMemberDto) {
        try {
            boolean result = memberService.withdrawal(withdrawalMemberDto);
            return ResponseEntity.ok(new WithdrawalMemberResultDto(result));
        } catch (RuntimeException ex) {
            WithdrawalMemberResultDto errorResult = new WithdrawalMemberResultDto(false, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
        }
    }
}
