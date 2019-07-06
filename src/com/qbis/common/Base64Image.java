/**
 * Convert Base64 String To String.
 * Upload Image 
 * @author Kuldeep Singh.
 *
 */
package com.qbis.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import com.qbis.model.User;

public class Base64Image {

	public static User uploadUserProfileImage(User user) {
		try {
			/* Upload User Profile Image */
			byte[] imageByteArray = decodeImage(user.getImage());
			// Write a image byte array into file system
			String imageName = user.getUserId() + ".png";
			File file = new File(ConstantUtil.PROFILE_IMAGE_PATH
					+ ConstantUtil.IMAGE_DIRECTORY);
			if (!file.exists()) {
				file.mkdir();
			}
			String path = ConstantUtil.PROFILE_IMAGE_PATH
					+ ConstantUtil.IMAGE_DIRECTORY + imageName;
			FileOutputStream imageOutFile = new FileOutputStream(path);
			imageOutFile.write(imageByteArray);
			user.setImage(imageName);
			imageOutFile.close();
			System.out.println("Image Successfully Manipulated!");
		} catch (FileNotFoundException e) {
			try {
				System.out.println("Image not found" + e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		return user;

	}

	/**
	 * Encodes the byte array into base64 string
	 * 
	 * @param imageByteArray
	 *            - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}

	/**
	 * Decodes the base64 string into byte array
	 * 
	 * @param imageDataString
	 *            - a {@link java.lang.String}
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}

	/*
	 * public static void main(String args[]) { String image =
	 * "iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAnVJREFUeNrEl29kVXEYx889uyJijDEul7hclnGzKaWJ6NXSjGVEU5am3sRNKaNIqTebZin1Yi/2IqUUVy9mI6UpTbMYI10iYkTEZUT0ffhcbqd7br9zt3N6+DiXc+7ve87z5/c8v7Q3/cmLYHtEAVrENvFDlMWqmBc/XRZKOzyTEefFkOgQX8UaVxPtFvtFXlQQvykWmxW2rxkTZ8U3cVeUxIcGLzggjop34om4hDf+slSIq7vEU9Emrovbri7E7AWuiaw4KR4FH/Dr/GmXeI3bdoqJiKJmz0QP14d4raGr7Q2fiwVxRKx7zZv9d1h8FuNcS/WEtyK6tgmitXaF+M+IvWT/H64uiu3ikKOohWTQUfwMglPBGLeLi5TBlwgJdNnxWcuRC+KA6KsVLpJMk158tkCMx2qFj5HyFS9eu8/ul/ep2QwFH7e9IH/6fN7AvvRtAsLruLzgk8m2rf3ykrGP5uo02+L3kIdyNIiwcrIQ3Qu5P8vOFTTb91t97z9ZmtbWFnLfQjAacu8GNTkaUbPVcspn8RyNPQmzvl32adjWezsTEG2hipZMeIWADyQgvI+wzvqU0QMxIrbELHyaCWalmtWTlMZIjKLdzG1Xg6PPHW7soCe7tMWs41ZrsX3J796gsCXYe8qrt4lxp5HdEqdYdyk4CFQYAvJMC5sV7yIz14mqaL1hz2q6n41hjjl6I6Uzxbx1Ljhp1tsyXzEhZnD9YBOidtJ4I44zv024jLfVDtLDqeCxWMZl2X9shUMMjMuU6e6w5Es5nJ26ED1M8a+S9dUTQgfeKSA2z6RRarRoKuKh7WBNGbXzlWXa6iKiTuPTbwEGAJbkj6dMn5fWAAAAAElFTkSuQmCC"
	 * ; // Base64Image.base64(image); String x = "1445923840717"; long
	 * milliSeconds = Long.parseLong(x); Date date = new Date(milliSeconds); }
	 */

}