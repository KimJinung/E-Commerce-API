package kimjinung.commerce.controller.api;

import kimjinung.commerce.dto.common.ResponseDto;
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
public class MemberApiController extends BaseApiController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseDto<MemberJoinResponseDto> join(
            @RequestBody @Validated MemberJoinRequestDto memberJoinRequestDto,
            BindingResult bindingResult
    ) {
      validateRequest(bindingResult);
        MemberJoinResponseDto joinedMember = memberService.join(memberJoinRequestDto);
        return new ResponseDto<>(200, joinedMember);
    }

    @GetMapping("/search")
    public ResponseDto<MemberSearchResponseDto> search(
            @RequestBody @Validated MemberSearchRequestDto memberSearchRequestDto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        MemberSearchResponseDto searchedMember = memberService.search(memberSearchRequestDto);
        return new ResponseDto<>(200, searchedMember);
    }

    @PatchMapping("/update")
    public ResponseDto<MemberUpdateResponseDto> update(
            @RequestBody @Validated MemberUpdateRequestDto memberUpdateRequestDto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        MemberUpdateResponseDto updatedMember = memberService.update(memberUpdateRequestDto);
        return new ResponseDto<>(200, updatedMember);
    }

    @DeleteMapping("/withdrawal")
    public ResponseDto<MemberWithdrawalResponseDto> withdrawal(
            @RequestBody @Validated MemberWithdrawalRequestDto memberWithdrawalRequestDto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        MemberWithdrawalResponseDto withdrawnMember = memberService.withdrawal(memberWithdrawalRequestDto);
        return new ResponseDto<>(200, withdrawnMember);
    }

}

