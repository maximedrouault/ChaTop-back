package org.chatop.chatopback.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class ImageFileValidator implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public boolean isValid(@Nullable MultipartFile file, ConstraintValidatorContext context) {
        return Optional.ofNullable(file)
                .map(MultipartFile::getContentType)
                .map(contentType -> contentType.startsWith("image/"))
                .orElse(false);
    }
}
