package org.saveursdo.server.filestore;
import org.saveursdo.server.bucket.BucketName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final S3Client s3;

    @Autowired
    public FileStore(S3Client s3) {
        this.s3 = s3;
    }

    public void save(String path, String fileName, InputStream inputStream) throws IOException {
        String bucketName = BucketName.PROFILE_IMAGE.getBucketName();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(path + "-" + fileName)
                .build();

        s3.putObject(objectRequest, RequestBody.fromInputStream(inputStream, inputStream.available()));
    }
}
