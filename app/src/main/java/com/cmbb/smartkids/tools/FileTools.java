package com.cmbb.smartkids.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.webkit.URLUtil;

public class FileTools {

	/**
	 * 
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内�?
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除指定文件夹下�?��文件
	 * 
	 * @param path
	 *            文件夹完整绝对路�?
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文�?
				delFolder(path + "/" + tempList[i]);// 再删除空文件�?
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 计算文件的大�?
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	// 递归
	public static long getFileSize(File f) {
		long size = 0;
		try {
			if (!f.exists())
				return 0;

			File flist[] = f.listFiles();
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getFileSize(flist[i]);
				} else {
					size = size + flist[i].length();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	public static boolean existsFile(String file) {
		FileInputStream fis = null;
		try {
			File f = new File(file);
			fis = new FileInputStream(f);
			int fileLen = fis.available();
			return f.exists() && fileLen > 0;
		} catch (IOException e) {
			return false;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断是否存在文件�?不存在则会偿试进行创�?
	 * 
	 * @param strFolder
	 *            文件夹绝对路�?
	 * @return 文件夹存在或者文件夹创建成功返回true，不存在文件夹且文件夹创建失败返回false
	 */
	public static boolean BExistFolder(String strFolder) {
		if (strFolder == null)
			return true;
		boolean bReturn = false;

		File f = new File(strFolder);
		if (!f.exists()) {
			/* 创建文件�?*/
			if (f.mkdirs()) {
				bReturn = true;
			} else {
				bReturn = false;
			}
		} else {
			bReturn = true;
		}
		return bReturn;
	}

	/**
	 * 
	 * @param dir
	 */
	public static void createDirs(String dir) {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File dirroot = Environment.getExternalStorageDirectory()
					.getAbsoluteFile();
			File file = new File(dirroot, File.separator + dir);
			file.mkdirs();
		}
	}

	public static String readAssertFile(Context c, String fileName) {
		String result = "";
		InputStream in = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			in = c.getAssets().open(fileName);
			isr = new InputStreamReader(in, Charset.forName("UTF-8"));
			reader = new BufferedReader(isr);
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				result += temp;
			}
		} catch (IOException e) {
			result = "";
		} finally {
			try {
				if (reader != null) {
					reader.close();
					reader = null;
				}
				if (isr != null) {
					isr.close();
					isr = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 保存文件内容
	 * 
	 * @param context
	 * @param fileName
	 * @param data
	 *            文件内容
	 *            <p>
	 *            如果文件路径不存在，将创建文件路径�?
	 *            </p>
	 */
	public static String writeFile(Context context, String fileName, String data) {
		String LastError = "";
		File file = new File(fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(file);
			byte[] buf = data.getBytes();
			fOut.write(buf);
			fOut.flush();
		} catch (Exception e) {
			LastError = "文件存储失败，失败原因：" + e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				// osw.close();
				if (fOut != null)
					fOut.close();
			} catch (IOException e) {
				LastError = "文件存储失败，失败原因：" + e.getMessage();
			}
		}
		return LastError;
	}
	
	/**
	 * 保存文件内容
	 * 
	 * @param fileName
	 *            文件内容
	 *            <p>
	 *            如果文件路径不存在，将创建文件路径�?
	 *            </p>
	 */
	public static String writeFile(String fileName, Bitmap bmp) {
		String LastError = "";
		File file = new File(fileName);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(file);
			bmp.compress(Bitmap.CompressFormat.PNG, 90, fOut);
			fOut.flush();
		} catch (Exception e) {
			LastError = "文件存储失败，失败原因：" + e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				// osw.close();
				if (fOut != null)
					fOut.close();
			} catch (IOException e) {
				LastError = "文件存储失败，失败原因：" + e.getMessage();
			}
		}
		return LastError;
	}

	// 读取文件
	public static String readTextFile(File file) {
		String text = "";
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			text = readTextInputStream(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return text;
	}

	// 从流中读取文�?
	public static String readTextInputStream(InputStream is) throws IOException {
		StringBuffer strbuffer = new StringBuffer();
		String line;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			while ((line = reader.readLine()) != null) {
				strbuffer.append(line).append("\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return strbuffer.toString();
	}

	public static String getFileName(String fileUrl) {
		String filename = fileUrl;
		int po = filename.lastIndexOf("/");
		if (po > -1)
			filename = filename.substring(po + 1);
		return filename;
	}

	/**
	 * 下载文件
	 * 
	 * @param savepath
	 *            本地文件保存地址
	 * @param fileurl
	 *            远程文件访问地址
	 * @param overwrite
	 *            如果本地存在文件是否覆盖
	 * 
	 * @return 下载成功或不覆盖时本地存在文件则返回 true,失败返回false,失败可用LastError来获取错误信�?
	 */
	public static String LastError;

	public static Boolean downLoadFile(String savepath, String fileurl,
			Boolean overwrite) {
		LastError = "";
		int po = fileurl.lastIndexOf("/");
		if (po > -1) {
			fileurl = fileurl.substring(0, po + 1)
					+ URLEncoder.encode(fileurl.substring(po + 1));
		}
		File myTempFile = new File(savepath);
		if (myTempFile.exists()) {
			if (!overwrite)
				return true;
			myTempFile.delete();
		}
		if (!BExistFolder(myTempFile.getParent())) {
			LastError = "无法将文件写入到指定的文�?";
			return false;
		}

		if (!URLUtil.isNetworkUrl(fileurl)) {
			LastError = "网络连接失败或无效的下载地址1!";
			return false;
		}
		/* 取得URL */
		URL myURL;
		try {
			myURL = new URL(fileurl);
		} catch (MalformedURLException e) {
			LastError = "网络连接失败或无效的下载地址!";
			e.printStackTrace();
			return false;
		}
		/* 创建连接 */
		URLConnection conn;
		/* InputStream 下载文件 */
		InputStream is;
		try {
			conn = myURL.openConnection();
			conn.connect();
			is = conn.getInputStream();
		} catch (IOException e) {
			LastError = "网络连接失败或无效的下载地址!";
			e.printStackTrace();
			return false;
		}
		if (is == null) {
			LastError = "服务器端文件不存�?";
			return false;
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(myTempFile);

			byte buf[] = new byte[512];
			do {
				int numread = is.read(buf);
				if (numread <= 0) {
					break;
				}
				fos.write(buf, 0, numread);
			} while (true);

			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			LastError = "文件存储失败!";
			return false;
		} catch (IOException e) {
			LastError = "文件存储失败!";
			return false;
		} finally {
			try {
				if (fos != null) {
					fos.close();
					fos = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
