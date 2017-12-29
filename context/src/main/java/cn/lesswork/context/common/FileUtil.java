package cn.lesswork.context.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public final class FileUtil {
	/**
	 * 读取文件.
	 * @param path
	 * @return
	 */
	public static String readFile(final String path) {
		return path;
	}
	public static String relativePath(final String path) {
		String contextPath = System.getProperty("user.dir");
		return contextPath + "/" + path;
	}
	/** 
     * 以行为单位读取文件.
     * @param fileName 文件名 
	 * @throws IOException 
     */  
    public static String readFileByLine(String fileName) throws IOException {
    	StringBuilder result = new StringBuilder();
        BufferedReader reader = null;
        try {
        	File file = new File(fileName);
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	result.append(tempString).append("\n");
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return result.toString();
    }
}
