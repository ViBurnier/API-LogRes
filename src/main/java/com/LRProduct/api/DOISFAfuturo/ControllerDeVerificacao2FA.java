//package com.LRProduct.api.DOISFAfuturo;
//
//import com.LRProduct.api.utils.ApiException;
//import com.LRProduct.api.utils.ApiResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RequestMapping("/account")
//@RestController
//@RequiredArgsConstructor
//@CrossOrigin
//public class ControllerDeVerificacao2FA {
//            private final ServiceVerification2FA serviceVerificationAccount;
//    @PostMapping("/sendCode")
//    public ResponseEntity<Map<String, Object>> sendCodeEmailTwoFA(HttpServletRequest request){
//        try{
//            serviceVerificationAccount.sendCodeEmail(request);
//
//            return ResponseEntity.ok(ApiResponse.success("Código enviado para seu email com sucesso.", "200"));
//        }
//        catch (ApiException e){
//            return ResponseEntity.status(e.getHttpStatus())
//                    .body(ApiResponse.error(e.getMessage(), e.getCode()));
//        }
//    };
//
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> verifyCodeTwoFA(@Valid @RequestBody AccountRequestVerify2FA accountRequestVerifyAccount, HttpServletRequest request){
//        try {
//            serviceVerificationAccount.verificationValidateAccount(request, accountRequestVerifyAccount);
//            return ResponseEntity.ok(ApiResponse.success("Conta verificada com sucesso.", "200"));
//        }
//        catch (ApiException e){
//            return ResponseEntity.status(e.getHttpStatus())
//                    .body(ApiResponse.error(e.getMessage(), e.getCode()));
//        }
//    }
//
//
//}
