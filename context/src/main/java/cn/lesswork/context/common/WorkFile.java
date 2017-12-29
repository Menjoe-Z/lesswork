package cn.lesswork.context.common;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class WorkFile {
	
	private String fileName;
	
	private OutputStream outputStream;
	
	private List<WorkFile> fileList = new ArrayList<>();

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public List<WorkFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<WorkFile> fileList) {
		this.fileList = fileList;
	}
	
	private HttpServletRequest request;
	
	public WorkFile(HttpServletRequest req) {
		this.request = req;
	}
	
	public void fileList() {
		DiskFileItemFactory dfi = new DiskFileItemFactory();
        //获得上传文件的存储路径
        String path  = "temp";
        //设置文件大小超过1024*1024就写到disk上
        dfi.setSizeThreshold(1024*1024);
        //设置存储的仓库
        dfi.setRepository(new File(path));
        //实例化一个servletFileUpload对象
        ServletFileUpload sfu = new ServletFileUpload(dfi);
        //解决上传文件乱码问题
        sfu.setHeaderEncoding("utf-8");
        try {
            List<FileItem> list = sfu.parseRequest(request);
            //遍历得到每个FileItem
            for(FileItem item : list){
                //取得表单文本框的名字
                String name = item.getFieldName();
                //如果上传的这个文件只是一个表单字段，而不是一个文件
                if(item.isFormField()){
                    //取得文本框输入的内容
                    String value = item.getString();
                }else{
                    //如果上传的是一个文件
                    //取得上传文件的名字，即上传框中的内容名字
                    String value = item.getName();
                    //因为在opera浏览器中文件上传item.geName()会得到具体路径而不止是名字,所以需要从路径中取出名字
                    //取得文件路径名字开始的位置
                    int start = value.lastIndexOf("\\");
                    //得到文件名
                    String fileName = value.substring(start+1);
                    //读取文件的内容
                    item.write(new File(path,fileName));
                }   
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
