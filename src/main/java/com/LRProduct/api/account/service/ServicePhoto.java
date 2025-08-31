package com.LRProduct.api.account.service;

import com.LRProduct.api.account.model.AccountResponsePhoto;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServicePhoto {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtUtil jwtUtil;

    public void validadePhotoAccount(HttpServletRequest request, AccountResponsePhoto photoFile){

        if(jwtUtil.getLoggedUser(request, accountRepository) != null){
            throw new ApiException("Você já esta logado.", "400", HttpStatus.BAD_REQUEST);
        }

         MultipartFile photo = photoFile.getPhotoFile();

        if (photo.isEmpty()) {
            throw new ApiException("Nenhum arquivo de foto foi enviado.", "400", HttpStatus.BAD_REQUEST);
        }

        String contentType = photo.getContentType();
        if (contentType == null || !config.getSupportedPhotoFormat().contains(contentType)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("400",
                    "Formato de foto não suportado. Os formatos suportados são: " + String.join(", ", config.getSupportedPhotoFormat())));
        }


    };

    public void photoAccount(AccountResponsePhoto accountResponsePhoto, HttpServletRequest request, AccountResponsePhoto photoFile){
        validadePhotoAccount(request, photoFile);
    };
}
