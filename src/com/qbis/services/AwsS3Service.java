package com.qbis.services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressEventType;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.qbis.common.ConstantUtil;
import com.qbis.model.FileData;

@Component
public class AwsS3Service {

	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(AwsS3Service.class);

	private String bucketName = ConstantUtil.AWS_S3_BUCKET_NAME;

	/**
	 * This is used for getting client object.
	 * 
	 * @return s3Client
	 */
	public AmazonS3 getClient() {
		logger.log(Level.DEBUG, "Inside AwsS3Service getClient method ::::: ");
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(
				ConstantUtil.AWS_S3_ACCESS_KEY, ConstantUtil.AWS_S3_PRIVATE_KEY);
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withRegion(Regions.AP_SOUTHEAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.build();
		return s3Client;

	}

	/**
	 * This is used for uploading the multipart file content in S3 bucket.
	 * 
	 * @param file
	 * @param fileName
	 * @param orgName
	 * @return isUploaded
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Boolean uploadMultipartContent(MultipartFile file, String fileName,
			String orgName) throws IOException, InterruptedException {
		logger.log(Level.DEBUG,
				"Inside AwsS3Service uploadMultipartContent method ::::: ");
		Boolean isUploaded = false;
		try {
			AmazonS3 s3Client = getClient();
			byte[] contents = file.getBytes();
			InputStream stream = new ByteArrayInputStream(contents);
			System.out.println("=========="+fileName);
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(file.getContentType());
			metadata.setContentLength(file.getSize());
			TransferManager tm = TransferManagerBuilder.standard()
					.withS3Client(s3Client).build();
			// TransferManager processes all transfers asynchronously,
			// so this call will return immediately.
			PutObjectRequest putObjectRequest = new PutObjectRequest(
					bucketName, fileName, stream, metadata)
					.withCannedAcl(CannedAccessControlList.PublicRead);
			final Upload upload = tm.upload(putObjectRequest);
			upload.addProgressListener(new ProgressListener() {
				// This method is called periodically as your transfer
				// progresses
				public void progressChanged(ProgressEvent progressEvent) {
					logger.log(Level.DEBUG, upload.getProgress()
							.getPercentTransferred() + "%");
					if (progressEvent.getEventType() == ProgressEventType.TRANSFER_COMPLETED_EVENT) {
						logger.log(Level.DEBUG, "file uploaded in S3 bukcet");
					}
				}
			});
			// Or you can block and wait for the upload to finish
			upload.waitForCompletion();
			tm.shutdownNow();
			isUploaded = true;
		} catch (AmazonClientException amazonClientException) {
			logger.log(
					Level.ERROR,
					"Unable to upload the file in S3 Bucket using uploadContent method inside AwsS3Service, upload was aborted",
					amazonClientException);
			amazonClientException.printStackTrace();
		}
		return isUploaded;
	}

	/**
	 * This is used for uploading the google drive or dropbox content in S3
	 * Bucket.
	 * 
	 * @param contents
	 * @param fileData
	 * @param orgName
	 * @return isUploaded
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Boolean uploadGoogleDriveAndDropboxContent(byte[] contents,
			FileData fileData, String orgName) throws IOException,
			InterruptedException {
		logger.log(Level.DEBUG,
				"Inside AwsS3Service uploadMultipartContent method ::::: ");
		Boolean isUploaded = false;
		try {
			AmazonS3 s3Client = getClient();
			InputStream stream = new ByteArrayInputStream(contents);
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(fileData.getType());
			metadata.setContentLength(fileData.getSizeBytes());
			TransferManager tm = TransferManagerBuilder.standard()
					.withS3Client(s3Client).build();
			// TransferManager processes all transfers asynchronously,
			// so this call will return immediately.
			PutObjectRequest putObjectRequest = new PutObjectRequest(
					bucketName, fileData.getName(), stream,
					metadata).withCannedAcl(CannedAccessControlList.PublicRead);
			final Upload upload = tm.upload(putObjectRequest);
			upload.addProgressListener(new ProgressListener() {
				// This method is called periodically as your transfer
				// progresses
				public void progressChanged(ProgressEvent progressEvent) {
					logger.log(Level.DEBUG, upload.getProgress()
							.getPercentTransferred() + "%");
					if (progressEvent.getEventType() == ProgressEventType.TRANSFER_COMPLETED_EVENT) {
						logger.log(Level.DEBUG, "file uploaded in S3 bukcet");
					}
				}
			});
			// Or you can block and wait for the upload to finish
			upload.waitForCompletion();
			tm.shutdownNow();
			isUploaded = true;
		} catch (AmazonClientException amazonClientException) {
			logger.log(
					Level.ERROR,
					"Unable to upload the file in S3 Bucket using uploadContent method inside AwsS3Service, upload was aborted",
					amazonClientException);
		}
		return isUploaded;
	}

	/**
	 * This is used for uploading the file in S3 Bucket.
	 * 
	 * @param file
	 * @param fileName
	 * @param orgName
	 * @return isUploaded
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Boolean uploadContentFromServer(File file, String fileName,
			String orgName) {
		logger.log(Level.DEBUG,
				"Inside AwsS3Service uploadContent method ::::: ");
		Boolean isUploaded = false;
		try {
			AmazonS3 s3Client = getClient();

			TransferManager tm = TransferManagerBuilder.standard()
					.withS3Client(s3Client).build();
			// TransferManager processes all transfers asynchronously,
			// so this call will return immediately.
			PutObjectRequest putObjectRequest = new PutObjectRequest(
					bucketName, fileName, file)
					.withCannedAcl(CannedAccessControlList.PublicRead);
			final Upload upload = tm.upload(putObjectRequest);
			upload.addProgressListener(new ProgressListener() {
				// This method is called periodically as your transfer
				// progresses
				public void progressChanged(ProgressEvent progressEvent) {
					if (progressEvent.getEventType() == ProgressEventType.TRANSFER_COMPLETED_EVENT) {
						logger.log(Level.DEBUG, "file uploaded in S3 bukcet");
					}
				}
			});
			// Or you can block and wait for the upload to finish
			upload.waitForCompletion();
			tm.shutdownNow();
			isUploaded = true;
		} catch (AmazonClientException amazonClientException) {
			logger.log(
					Level.ERROR,
					"Unable to upload the file in S3 Bucket using uploadContent method inside AwsS3Service, upload was aborted",
					amazonClientException);
		} catch (Exception e) {

		}
		return isUploaded;
	}

	/**
	 * This is used deleting the S3 Bucket content.
	 * 
	 * @param keyName
	 * @return isDeleted
	 */
	public Boolean deleteS3Content(String keyName) {
		logger.log(Level.DEBUG,
				"Inside AwsS3Service deleteS3Content method ::::: ");
		AmazonS3 s3Client = getClient();
		Boolean isDeleted = false;
		try {
			s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
			isDeleted = true;
		} catch (AmazonServiceException ase) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonServiceException using deleteS3Content method inside AwsS3Service, upload was aborted");
			logger.log(Level.ERROR, "Error Message: " + ase.getMessage());
			logger.log(Level.ERROR, "HTTP Status Code: " + ase.getStatusCode());
			logger.log(Level.ERROR, "AWS Error Code:   " + ase.getErrorCode());
			logger.log(Level.ERROR, "Error Type:       " + ase.getErrorType());
			logger.log(Level.ERROR, "Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonClientException using deleteS3Content method inside AwsS3Service, upload was aborted");
			logger.log(Level.ERROR, "Error Message: " + ace.getMessage());
		}
		return isDeleted;
	}

	/**
	 * This is used deleting the S3 Bucket directory and its content.
	 * 
	 * @param keyName
	 * @return isDeleted
	 */
	public Boolean deleteS3DirWithitsContent(String keyName) {
		logger.log(Level.DEBUG,
				"Inside AwsS3Service deleteS3DirWithitsContent method ::::: ");
		AmazonS3 s3Client = getClient();
		Boolean isDirDeleted = false;
		try {
			ObjectListing objects = s3Client.listObjects(bucketName, keyName);
			for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
				s3Client.deleteObject(bucketName, objectSummary.getKey());
			}
			isDirDeleted = true;
		} catch (AmazonServiceException ase) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonServiceException using deleteS3DirWithitsContent method inside AwsS3Service, upload was aborted");
			logger.log(Level.ERROR, "Error Message: " + ase.getMessage());
			logger.log(Level.ERROR, "HTTP Status Code: " + ase.getStatusCode());
			logger.log(Level.ERROR, "AWS Error Code:   " + ase.getErrorCode());
			logger.log(Level.ERROR, "Error Type:       " + ase.getErrorType());
			logger.log(Level.ERROR, "Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonClientException using deleteS3DirWithitsContent method inside AwsS3Service, upload was aborted");
			logger.log(Level.ERROR, "Error Message: " + ace.getMessage());
		}
		return isDirDeleted;
	}

	/**
	 * This is used for copy the S3 Bucket content.
	 * 
	 * @param keyName
	 * @param destinationKey
	 */
	public void copyContent(String keyName, String destinationKey) {
		logger.log(Level.DEBUG, "Inside AwsS3Service copyContent method ::::: ");
		AmazonS3 s3client = getClient();
		try {
			// Copying object
			CopyObjectRequest copyObjRequest = new CopyObjectRequest(
					bucketName, keyName, bucketName, destinationKey)
					.withCannedAccessControlList(CannedAccessControlList.PublicRead);
			s3client.copyObject(copyObjRequest);

		} catch (AmazonServiceException ase) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonServiceException, which means your request made it to Amazon S3, but was rejected with an error response for some reason. using copyContent method inside AwsS3Service");
			logger.log(Level.ERROR, "Error Message: " + ase.getMessage());
			logger.log(Level.ERROR, "HTTP Status Code: " + ase.getStatusCode());
			logger.log(Level.ERROR, "AWS Error Code:   " + ase.getErrorCode());
			logger.log(Level.ERROR, "Error Type:       " + ase.getErrorType());
			logger.log(Level.ERROR, "Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonClientException, which means your request made it to Amazon S3, but was rejected with an error response for some reason. using copyContent method inside AwsS3Service");
			logger.log(Level.ERROR, "Error Message: " + ace.getMessage());
		}
	}

	/**
	 * This is used for copy the S3 Bucket directory and its content.
	 * 
	 * @param keyName
	 * @param destinationKey
	 */
	public void copyS3Directory(String keyName, String destinationKey) {
		logger.log(Level.DEBUG, "Inside AwsS3Service copyContent method ::::: ");
		AmazonS3 s3client = getClient();
		try {
			ObjectListing objects = s3client.listObjects(bucketName, keyName);
			for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
				String destKeyName = destinationKey
						+ "/"
						+ objectSummary.getKey().substring(
								objectSummary.getKey().lastIndexOf("/") + 1);
				CopyObjectRequest copyObjRequest = new CopyObjectRequest(
						bucketName, objectSummary.getKey(), bucketName,
						destKeyName)
						.withCannedAccessControlList(CannedAccessControlList.PublicRead);
				s3client.copyObject(copyObjRequest);
			}

		} catch (AmazonServiceException ase) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonServiceException, which means your request made it to Amazon S3, but was rejected with an error response for some reason. using copyContent method inside AwsS3Service");
			logger.log(Level.ERROR, "Error Message: " + ase.getMessage());
			logger.log(Level.ERROR, "HTTP Status Code: " + ase.getStatusCode());
			logger.log(Level.ERROR, "AWS Error Code:   " + ase.getErrorCode());
			logger.log(Level.ERROR, "Error Type:       " + ase.getErrorType());
			logger.log(Level.ERROR, "Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonClientException, which means your request made it to Amazon S3, but was rejected with an error response for some reason. using copyContent method inside AwsS3Service");
			logger.log(Level.ERROR, "Error Message: " + ace.getMessage());
		}
	}

	/**
	 * This is used for rename or moving the S3 Bucket content.
	 * 
	 * @param keyName
	 * @param destinationKey
	 */
	public void renameContent(String keyName, String destinationKey) {
		logger.log(Level.DEBUG,
				"Inside AwsS3Service renameContent method ::::: ");
		AmazonS3 s3client = getClient();
		try {
			CopyObjectRequest copyObjRequest = new CopyObjectRequest(
					bucketName, keyName, bucketName, destinationKey)
					.withCannedAccessControlList(CannedAccessControlList.PublicRead);
			// Copying object
			s3client.copyObject(copyObjRequest);
			s3client.deleteObject(new DeleteObjectRequest(bucketName, keyName));
		} catch (AmazonServiceException ase) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonServiceException, which means your request made it to Amazon S3, but was rejected with an error response for some reason. using renameContent method inside AwsS3Service");
			logger.log(Level.ERROR, "Error Message: " + ase.getMessage());
			logger.log(Level.ERROR, "HTTP Status Code: " + ase.getStatusCode());
			logger.log(Level.ERROR, "AWS Error Code:   " + ase.getErrorCode());
			logger.log(Level.ERROR, "Error Type:       " + ase.getErrorType());
			logger.log(Level.ERROR, "Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonClientException, which means your request made it to Amazon S3, but was rejected with an error response for some reason. using renameContent method inside AwsS3Service");
			logger.log(Level.ERROR, "Error Message: " + ace.getMessage());
		}
	}

	/**
	 * This method is used for checking that object exits or not in bucket using
	 * key name.
	 * 
	 * @param keyName
	 * @return isExist
	 */
	public Boolean isObjectExist(String keyName) {
		Boolean isExist = false;
		logger.log(Level.DEBUG,
				"Inside AwsS3Service isObjectExist method ::::: ");
		try {
			AmazonS3 s3client = getClient();
			isExist = s3client.doesObjectExist(bucketName, keyName);
		} catch (AmazonServiceException ase) {
			logger.log(
					Level.ERROR,
					"Caught an AmazonServiceException, which means your request made it to Amazon S3, but was rejected with an error response for some reason. using isObjectExist method inside AwsS3Service");
			logger.log(Level.ERROR, "Error Message: " + ase.getMessage());
			logger.log(Level.ERROR, "HTTP Status Code: " + ase.getStatusCode());
			logger.log(Level.ERROR, "AWS Error Code:   " + ase.getErrorCode());
			logger.log(Level.ERROR, "Error Type:       " + ase.getErrorType());
			logger.log(Level.ERROR, "Request ID:       " + ase.getRequestId());
		} catch (SdkClientException sce) {
			logger.log(
					Level.ERROR,
					"Caught an SdkClientException, which means your request made it to Amazon S3, but was rejected with an error response for some reason. using isObjectExist method inside AwsS3Service");
			logger.log(Level.ERROR, "Error Message: " + sce.getMessage());
		}
		return isExist;
	}
}
