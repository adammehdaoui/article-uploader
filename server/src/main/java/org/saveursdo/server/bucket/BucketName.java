package org.saveursdo.server.bucket;

public enum BucketName {

    PROFILE_IMAGE("saveursdo-image-upload-123");

    private final String bucketName;

    BucketName(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
