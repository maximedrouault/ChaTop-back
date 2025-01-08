package org.chatop.chatopback.service;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.chatop.chatopback.exception.AwsS3FileDeleteException;
import org.chatop.chatopback.exception.AwsS3FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final S3Template s3Template;


    @Value("${aws.bucket.name}")
    private String bucketName;

    private final Duration signedUrlExpiration = Duration.ofMinutes(5);


    public URL createSignedGetURL(String key) {
        return s3Template.createSignedGetURL(bucketName, key, signedUrlExpiration);
    }

    public void uploadFile(String key, MultipartFile file) {
        try {
            s3Template.upload(bucketName, key, file.getInputStream());
        } catch (Exception exception) {
            throw new AwsS3FileUploadException(exception);
        }
    }

    public void deleteFile(String key) {
        try {
            s3Template.deleteObject(bucketName, key);
        } catch (Exception exception) {
            throw new AwsS3FileDeleteException(exception);
        }
    }
}