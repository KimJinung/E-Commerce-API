package kimjinung.commerce.controller.api;

import kimjinung.commerce.dto.error.ErrorResult;
import kimjinung.commerce.dto.member.*;
import kimjinung.commerce.exception.InvalidRequestException;
import kimjinung.commerce.exception.MemberJoinInvalidArgumentException;
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

//    private final MemberService memberService;

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    public ErrorResult exception(Exception e) {
//        log.info("[Exception] ", e);
//        return new ErrorResult("INTERNAL_SERVER_ERROR", e.getMessage());
//
//    }
//
//    @PostMapping("/join")
//    public ResponseEntity<MemberJoinResponseDto> join(
//            @RequestBody @Validated MemberJoinRequestDto memberJoinRequestDto,
//            BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            List<String> errors = new ArrayList<>();
//
//            for (ObjectError error : bindingResult.getAllErrors()) {
//                errors.add(error.getDefaultMessage());
//            }
//
//            if (bindingResult.hasGlobalErrors()) {
//                throw new InvalidRequestException(errors.toString());
//            } else {
//                throw new MemberJoinInvalidArgumentException(errors.toString());
//            }
//        }
//
//        return ResponseEntity.ok(memberService.join(memberJoinRequestDto));
//    }
//
//    @PostMapping("/search")
//    public ResponseEntity<MemberSearchResponseDto> search(
//            @RequestBody @Validated MemberSearchRequestDto memberSearchRequestDto,
//            BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//
//        }
//        return ResponseEntity.ok(memberService.search(memberSearchRequestDto));
//
//    }
//
//    @PostMapping("/update")
//    public ResponseEntity<MemberUpdateResponseDto> update(@RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {
//        try {
//            return ResponseEntity.ok(memberService.update(memberUpdateRequestDto));
//        } catch (RuntimeException ex){
//            MemberUpdateResponseDto result = new MemberUpdateResponseDto(());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
//        }
//    }
//
//    @PostMapping("/withdrawal")
//    public ResponseEntity<MemberWithdrawalResponseDto> withdrawal(
//            @RequestBody @Validated MemberWithdrawalRequestDto memberWithdrawalRequestDto) {
//        try {
//            boolean result = memberService.withdrawal(memberWithdrawalRequestDto);
//            return ResponseEntity.ok(new MemberWithdrawalResponseDto());
//        } catch (RuntimeException ex) {
//            MemberWithdrawalResponseDto errorResult = new MemberWithdrawalResponseDto(false, ex.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
//        }
//    }
}

