package com.myServer.util;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;

public class AwsService {
	ProfileCredentialsProvider provider = ProfileCredentialsProvider.create();
}
