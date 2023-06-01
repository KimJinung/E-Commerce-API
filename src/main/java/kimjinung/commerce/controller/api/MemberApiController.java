package kimjinung.commerce.controller.api;

import kimjinung.commerce.dto.error.ErrorResult;
import kimjinung.commerce.dto.member.*;
import kimjinung.commerce.exception.InvalidRequestException;
import kimjinung.commerce.exception.MemberJoinInvalidArgumentException;
import kimjinung.commerce.exception.MemberNotFoundException;
import kimjinung.commerce.usecase.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberApiController {

    private final MemberService memberService;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult exception(Exception e) {
        log.info("[Exception] ", e);
        return new ErrorResult("INTERNAL_SERVER_ERROR", e.getMessage());

    }

    @PostMapping("/join")
    public ResponseEntity<JoinMemberResultDto> join(
            @RequestBody @Validated JoinMemberDto joinMemberDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();

            for (ObjectError error : bindingResult.getAllErrors()) {
                errors.add(error.getDefaultMessage());
            }

            if (bindingResult.hasGlobalErrors()) {
                throw new InvalidRequestException(errors.toString());
            } else {
                throw new MemberJoinInvalidArgumentException(errors.toString());
            }
        }

        return ResponseEntity.ok(memberService.join(joinMemberDto));
    }

    @PostMapping("/search")
    public ResponseEntity<SearchMemberResultDto> search(
            @RequestBody @Validated SearchMemberDto searchMemberDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

        }
        return ResponseEntity.ok(memberService.search(searchMemberDto));

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
    public ResponseEntity<WithdrawalMemberResultDto> withdrawal(
            @RequestBody @Validated WithdrawalMemberDto withdrawalMemberDto) {
        try {
            boolean result = memberService.withdrawal(withdrawalMemberDto);
            return ResponseEntity.ok(new WithdrawalMemberResultDto(result));
        } catch (RuntimeException ex) {
            WithdrawalMemberResultDto errorResult = new WithdrawalMemberResultDto(false, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
        }
    }
}
