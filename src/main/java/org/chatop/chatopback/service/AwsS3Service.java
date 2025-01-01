package org.chatop.chatopback.service;

import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final S3Template s3Template;


    public URL createSignedGetURL(String bucketName, String key, Duration duration) {

        return s3Template.createSignedGetURL(bucketName, key, duration);
    }
}