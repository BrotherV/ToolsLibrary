package com.bvtech.toolslibrary.utility;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
		File documentStorageDir = null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			documentStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
					directoryName);
		}

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

	public static void copy(File src, File dst) throws IOException {
		InputStream in = new FileInputStream(src);
		try {
			OutputStream out = new FileOutputStream(dst);
			try {
				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}

	public static boolean copyFileFromAssetManagerToApplicationDirectory(Context context, String argAssetDir, String argDestinationDir) {
		boolean ret = true;
		try {
			AssetManager assetManager = context.getAssets();

			String sdPath = context.getApplicationInfo().dataDir;
			String destDirPath = sdPath + addLeadingSlash(argDestinationDir);
			File destDir = new File(destDirPath);

			createDir(destDir);

			String[] files = assetManager.list(argAssetDir);

			for (int i = 0; i < files.length; i++) {
				String absAssetFilePath = addTrailingSlash(argAssetDir) + files[i];
				String subFiles[] = assetManager.list(absAssetFilePath);

				if (subFiles.length == 0) {
					// It is a file
					String destFilePath = addTrailingSlash(destDirPath) + files[i];
					copyAssetFile(context, absAssetFilePath, destFilePath);
				} else {
					// It is a sub directory
					copyFileFromAssetManagerToApplicationDirectory(context, absAssetFilePath, addTrailingSlash(argDestinationDir) + files[i]);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}

	public static void copyAssetFile(Context context, String assetFilePath, String destinationFilePath) throws IOException {
		InputStream in = context.getAssets().open(assetFilePath);
		OutputStream out = new FileOutputStream(destinationFilePath);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0)
			out.write(buf, 0, len);
		in.close();
		out.close();
	}

	public static String addTrailingSlash(String path) {
		if (path.charAt(path.length() - 1) != '/') {
			path += "/";
		}
		return path;
	}

	public static String addLeadingSlash(String path) {
		if (path.charAt(0) != '/') {
			path = "/" + path;
		}
		return path;
	}

	public static void createDir(File dir) throws IOException {
		if (dir.exists()) {
			if (!dir.isDirectory()){
				throw new IOException("Can't create directory, a file is in the way");
			}
		} else {
			dir.mkdirs();
			if (!dir.isDirectory()){
				throw new IOException("Unable to create directory");
			}
		}
	}
}
