package com.solutionladder.ethearts.service.utility;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * AWS client for S3
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * @date Sep 12 18
 *
 */
@Service
public class AwsClientService {

    private AmazonS3 s3Client;

    @Value("${aws.endpointUrl}")
    private String endpointUrl;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretKey;
    
    @Value("${aws.bucketRegion}")
    private String bucketRegion;

    @PostConstruct
    private void initializeAmazon() {
        BasicAWSCredentials creds = new BasicAWSCredentials(this.accessKey, this.secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(creds))
                .withRegion(Regions.US_EAST_1)
                .build();
    }
    
    public String getBucketName() {
        return this.bucketName;
    }
    
    public String getEndPointUrl() {
        return this.endpointUrl;
    }

    public AmazonS3 getAwsS3() {
        return this.s3Client;
    }

    /**
     * Upload file to AWS S3
     * 
     * @param fileName
     * @param file
     */
    public void uploadFileToS3(String fileName, File file) {
        this.s3Client.putObject(
                new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }
}