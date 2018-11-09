package com.bvtech.toolslibrary.Utility;

import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {

	public static final int FILE_TYPE_IMAGE = 1;
	public static final int FILE_TYPE_VIDEO = 2;

	// directory name to store captured images and videos

	public static File createOutputMediaFile(int type, String directoryName) {
		// External sdcard location
		File mediaStorageDir = new File(
				Environment.getExternalStoragePublicDirectory(type == FILE_TYPE_IMAGE ? Environment.DIRECTORY_PICTURES : Environment.DIRECTORY_MOVIES),
				directoryName);

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d(directoryName, "Oops! Failed create "
						+ directoryName + " directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		File mediaFile;
		if (type == FILE_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else if (type == FILE_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "VID_" + timeStamp + ".mp4");
		} else {
			return null;
		}
		return mediaFile;
	}

	public static File createOutputImageFile(String directoryName) {
		// External sdcard location
		File mediaStorageDir = new File(
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				directoryName);

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d(directoryName, "Oops! Failed create "
						+ directoryName + " directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		File mediaFile;
		mediaFile = new File(mediaStorageDir.getPath() + File.separator
				+ "IMG_" + timeStamp + ".jpg");

		return mediaFile;
	}

	public static File createOutputVideoFile(String directoryName) {
		// External sdcard location
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),
				directoryName);

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d(directoryName, "Oops! Failed create "
						+ directoryName + " directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		File mediaFile;
		mediaFile = new File(mediaStorageDir.getPath() + File.separator
				+ "VID_" + timeStamp + ".jpg");

		return mediaFile;
	}

	public static File createOutputDocumentFile(String directoryName) {
		// External sdcard location
		File documentStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
				directoryName);

		// Create the storage directory if it does not exist
		if (!documentStorageDir.exists()) {
			if (!documentStorageDir.mkdirs()) {
				Log.d(directoryName, "Oops! Failed create "
						+ directoryName + " directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		File mediaFile;
		mediaFile = new File(documentStorageDir.getPath() + File.separator
				+ "VID_" + timeStamp + ".jpg");

		return mediaFile;
	}

	public static File createFile(String directoryName, String fileName) {

		// External sdcard location
		File fileStorageDir = new File(directoryName);

		// Create the storage directory if it does not exist
		if (!fileStorageDir.exists()) {
			if (!fileStorageDir.mkdirs()) {
				Log.d(directoryName, "Oops! Failed create "
						+ directoryName + " directory");
				return null;
			}
		}

		File mediaFile = new File(fileStorageDir.getPath() + File.separator, fileName);
		if (!mediaFile.exists()) {
			Log.d(directoryName, "Oops! Failed create "
					+ directoryName + " directory");
			return null;
		}
		return mediaFile;
	}

	public static File getFile(String directoryName, String fileName) {
		// External sdcard location
		File fileStorageDir = new File(directoryName);

		// Create the storage directory if it does not exist
		if (!fileStorageDir.exists()) {
			Log.d(directoryName, "Oops! Failed to find "
					+ directoryName + " directory");
			return null;
		}

		File mediaFile = new File(fileStorageDir.getPath() + File.separator, fileName);
		if (!mediaFile.exists()) {
			Log.d(directoryName, "Oops! Failed to find " + directoryName + " directory");
			return null;
		}
		return mediaFile;
	}

	public static File createExternalStorageDirectory(String directoryName) {
		// External sdcard location
		File storageDir = new File(Environment.getExternalStorageDirectory() +  File.separator +  directoryName + File.separator);

		// Create the storage directory if it does not exist
		if (!storageDir.exists()) {
			if (!storageDir.mkdirs()) {
				Log.d(directoryName, "Oops! Failed create " + directoryName + " directory");
				return null;
			}
		}
		return storageDir;
	}

	public static void deleteFileOrDirectory(File fileOrDirectory) {
		if(fileOrDirectory.exists()){
			if (fileOrDirectory.isDirectory())
				for (File child : fileOrDirectory.listFiles())
					deleteFileOrDirectory(child);

			fileOrDirectory.delete();
		}
	}

	public static void deleteFileOrDirectory(String path) {
		File fileOrDirectory = new File(path);
		if(fileOrDirectory.exists()){
			if (fileOrDirectory.isDirectory())
				for (File child : fileOrDirectory.listFiles())
					deleteFileOrDirectory(child);

			fileOrDirectory.delete();
		}
	}

	public static void deleteFile(String path) {
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
	}

	public static void deleteFile(File file) {
		if(file.exists()){
			file.delete();
		}
	}

	public static String getFileName(File file) {
		if(file.exists()){
			String path = file.getPath();
			return path.substring(path.lastIndexOf("/") + 1);
		}
		return null;
	}

	public static String getFileName(String p) {
		File file = new File(p);
		if(file.exists()){
			String path = file.getPath();
			return path.substring(path.lastIndexOf("/") + 1);
		}
		return null;
	}
}
