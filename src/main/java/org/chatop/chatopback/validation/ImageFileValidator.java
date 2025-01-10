package org.chatop.chatopback.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Validator class to check if a file is an image.
 */
public class ImageFileValidator implements ConstraintValidator<ImageFile, MultipartFile> {

    /**
     * Validates if the given file is an image.
     *
     * @param file the file to validate
     * @param context the context in which the constraint is evaluated
     * @return true if the file is an image, false otherwise
     */
    @Override
    public boolean isValid(@Nullable MultipartFile file, ConstraintValidatorContext context) {
        return Optional.ofNullable(file)
                .map(MultipartFile::getContentType)
                .map(contentType -> contentType.startsWith("image/"))
                .orElse(false);
    }
}
