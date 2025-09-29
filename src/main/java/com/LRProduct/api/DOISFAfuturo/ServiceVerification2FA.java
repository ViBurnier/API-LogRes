//package com.LRProduct.api.DOISFAfuturo;
//
//import com.LRProduct.api.account.model.Account;
//import com.LRProduct.api.account.repository.AccountRepository;
//import com.LRProduct.api.serviceglobal.ServiceEmail;
//import com.LRProduct.api.utils.ApiException;
//import com.LRProduct.api.utils.JwtUtil;
//import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.Mac;
//import javax.crypto.SecretKey;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.time.Instant;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class ServiceVerification2FA {
//
//    private final TimeBasedOneTimePasswordGenerator totp;
//    private final Map<String, SecretKey> userKeys = new HashMap<>();
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Autowired
//    JwtUtil jwtUtil;
//
//    @Autowired
//    ServiceEmail serviceMail;
//
//    public ServiceVerification2FA() {
//        try {
//            this.totp = new TimeBasedOneTimePasswordGenerator();
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao iniciar TOTP: ", e);
//       }
//    }
//
//    private SecretKey getUserKey(String email) {
//        return userKeys.computeIfAbsent(email, k -> {
//            try {
//                KeyGenerator keyGen = KeyGenerator.getInstance(totp.getAlgorithm());
//
//                final int macLengthInBytes = Mac.getInstance(totp.getAlgorithm()).getMacLength();
//
//                keyGen.init(macLengthInBytes * 8);
//
//                return keyGen.generateKey();
//            } catch (NoSuchAlgorithmException e) {
//                throw new RuntimeException("Erro ao gerar chave do usuário: ", e);
//            }
//        });
//    }
//
//    public void sendCodeEmail(HttpServletRequest request) {
//        Account account = jwtUtil.getLoggedUser(request, accountRepository);
//
//        SecretKey key = getUserKey(account.getEmail());
//        Instant now = Instant.now();
//
//        int code;
//        try {
//            code = totp.generateOneTimePassword(key, now);
//        } catch (InvalidKeyException e) {
//            throw new RuntimeException("Erro ao gerar OTP", e);
//        }
//
//        serviceMail.sendEmail(account.getEmail(), "Verification code", String.valueOf(code));
//    }
//
////    public void loopTryWriteCode(HttpServletRequest request, AccountRequestVerifyAccount dto){
////        int tryCode = 0;
////        while(verificationValidateAccount(request, dto) && tryCode > 5 ){
////
////            tryCode++;
////        }
////    }
//
//    public void verificationValidateAccount(HttpServletRequest request, AccountRequestVerify2FA dto) {
//        Account account = jwtUtil.getLoggedUser(request, accountRepository);
//
//        if(account.getVerificationAccount() == Account.VerificationAccount.ON){
//            throw new ApiException("Sua conta está verificada.", "401", HttpStatus.UNAUTHORIZED);
//        }
//
//        if(account.getStatus() != Account.Status.ON){
//            throw new ApiException("Conta desativada, contate o suporte para mais informações.", "401", HttpStatus.UNAUTHORIZED);
//        }
//
//        SecretKey key = getUserKey(account.getEmail());
//
//        Instant now = Instant.now();
//
//        int expectedCode;
//        try {
//            expectedCode = totp.generateOneTimePassword(key, now);
//        } catch (InvalidKeyException e) {
//            throw new RuntimeException("Erro ao validar OTP", e);
//        }
//
//
//        if (dto.getCode() != expectedCode) {
//            throw new ApiException("Codigo incompativel", "401", HttpStatus.UNAUTHORIZED);
//        }
//
//        account.setVerificationAccount(Account.VerificationAccount.ON);
//        accountRepository.save(account);
//    }
//}
