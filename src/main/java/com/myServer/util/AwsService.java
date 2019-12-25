package com.myServer.util;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;

public class AwsService {
	
	public static ProfileCredentialsProvider getCredentials() {
		ProfileCredentialsProvider provider = ProfileCredentialsProvider.create();
		return provider;
	}
	
	
}
