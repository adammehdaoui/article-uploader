package org.saveursdo.server.filestore;
import org.saveursdo.server.bucket.BucketName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {

    private final S3Client s3;

    @Autowired
    public FileStore(S3Client s3) {
        this.s3 = s3;
    }

    public void save(String path, String fileName, Optional<Map<String, String>> optionalMetadata, InputStream inputStream) {
        s3.putObject(request ->
                request.bucket(BucketName.PROFILE_IMAGE.getBucketName())
                        .key(path + "/" + fileName)
                        .metadata(optionalMetadata.orElse(null)), (Path) inputStream);
    }
}
