package com.LRProduct.api.account.service;

import com.LRProduct.api.account.config.Config;
import com.LRProduct.api.account.model.Account;
import com.LRProduct.api.account.model.AccountRequestPhoto;
import com.LRProduct.api.account.model.AccountResponsePhoto;
import com.LRProduct.api.account.repository.AccountRepository;
import com.LRProduct.api.utils.ApiException;
import com.LRProduct.api.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

/*
* !IDEIA!
* Fazer um service para edicao de upload, exemplo: foto, background, curriculo, pdf...
* e outro para campos, como o edit ja pronto faz.
* */

@Service
public class ServicePhoto {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    Config config;
    public void validadePhotoAccount(HttpServletRequest request, AccountRequestPhoto photoFile){

        if(jwtUtil.getLoggedUser(request, accountRepository) == null){
            throw new ApiException("Você não esta logado.", "400", HttpStatus.BAD_REQUEST);
        }

         MultipartFile photo = photoFile.getPhotoFile();

        if (photo.isEmpty()) {
            throw new ApiException("Nenhum arquivo de foto foi enviado.", "400", HttpStatus.BAD_REQUEST);
        }

        String contentType = photo.getContentType();
        if (contentType == null || !config.getSupportedPhotoFormat().contains(contentType)) {
            throw new ApiException("Formato de foto não suportado. Os formatos suportados são: " + String.join(", ", config.getSupportedPhotoFormat()), "400", HttpStatus.BAD_REQUEST);
        }

        if (photo.getSize() > config.getMaxFileSize()) {
            throw new ApiException("O tamanho da foto excede o limite de " + config.getMaxPhotoSizeMb() + "MB.", "413", HttpStatus.BAD_REQUEST);
        }
    };

    public AccountResponsePhoto photoAccount(AccountRequestPhoto photoFile, HttpServletRequest request) throws IOException {
        validadePhotoAccount(request, photoFile);

        Account account = jwtUtil.getLoggedUser(request, accountRepository);

        String photoUrl = saveAndGetPhotoUrl(photoFile.getPhotoFile(), account.getId());

        account.setPhoto(photoUrl);
        accountRepository.save(account);

        return new AccountResponsePhoto(
                account.getPhoto()
        );
    };

    public String saveAndGetPhotoUrl(MultipartFile photoFile, Long userId) throws IOException {
        String fileName = "user_" + userId + "_" + System.currentTimeMillis() + "_" + Objects.requireNonNull(photoFile.getOriginalFilename()).replaceAll("[^a-zA-Z0-9._-]", "");

        Path uploadPath = Paths.get(config.getUploadDir());

        Files.createDirectories(uploadPath);
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(photoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retorna a URL para acessar a foto (usando a URL base configurada)
        return config.getUploadUrl() + "/" + fileName;
    }
}
