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

    private final MemberService memberService;

}

