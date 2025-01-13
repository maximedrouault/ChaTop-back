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

/**
 * Service class for handling AWS S3 operations.
 */
@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final S3Template s3Template;


    @Value("${AWS_BUCKET_NAME}")
    private String bucketName;

    private final Duration signedUrlExpiration = Duration.ofMinutes(5);


    /**
     * Creates a signed URL for downloading a file from S3.
     *
     * @param key the key of the file in the S3 bucket
     * @return the signed URL for downloading the file
     */
    public URL createSignedGetURL(String key) {
        return s3Template.createSignedGetURL(bucketName, key, signedUrlExpiration);
    }

    /**
     * Uploads a file to the S3 bucket.
     *
     * @param key the key under which the file will be stored in the S3 bucket
     * @param file the file to be uploaded
     * @throws AwsS3FileUploadException if an error occurs during file upload
     */
    public void uploadFile(String key, MultipartFile file) {
        try {
            s3Template.upload(bucketName, key, file.getInputStream());
        } catch (Exception exception) {
            throw new AwsS3FileUploadException(exception);
        }
    }

    /**
     * Deletes a file from the S3 bucket.
     *
     * @param key the key of the file to be deleted
     * @throws AwsS3FileDeleteException if an error occurs during file deletion
     */
    public void deleteFile(String key) {
        try {
            s3Template.deleteObject(bucketName, key);
        } catch (Exception exception) {
            throw new AwsS3FileDeleteException(exception);
        }
    }
}