package com.ql.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import sun.util.logging.resources.logging;

/**
 * 处理文件名称(ip+时间戳+三位随机数)工具类
 * @author Administrator
 *
 */
public class FileTools {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");;

	//根据ip生成 文件名称(ip+时间戳+三位随机数)
	public static String getIpTimeRand(String ip){
		StringBuffer str = new StringBuffer();
		if(ip != null){
			String s[] = ip.split("\\:");
			for(int i=0; i<s.length; i++){
				str.append(addZero(s[i],3));
			}
		}
		str.append(getTimeStamp());
		Random random = new Random();
		for(int i=0; i<3; i++){
			str.append(random.nextInt(10));
		}
		return str.toString();
	}
	
	private static String addZero(String str,int size){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(str);
		while(stringBuffer.length()<size){
			stringBuffer.insert(0,0);
		}
		return stringBuffer.toString();
	}
	
	//基于当前时间获取时间戳
	private static String getTimeStamp(){
		return sdf.format(new Date());
	}
	
	/**
	 * 基于当前servet实例化上传类SmartUpload
	 * @param servlet
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 */
	public static SmartUpload getSmartUpload(HttpServlet servlet,HttpServletRequest request,HttpServletResponse response) throws ServletException{
		SmartUpload su = new SmartUpload();
		JspFactory jspFactory = null;
		PageContext pageContext = null;
		jspFactory = JspFactory.getDefaultFactory();
		pageContext = jspFactory.getPageContext(servlet, request, response, "",
				true, 8192, true);
		su.initialize(pageContext);
		return su;
	}
	
	/**
	 * 上传当前请求中的全部文件
	 * @param su
	 * @param ip
	 * @throws ServletException
	 * @throws IOException
	 * @throws SmartUploadException
	 */
	public static void upload(SmartUpload su,String ip,String fileDic) throws ServletException, IOException, SmartUploadException{
		su.upload();
		
		String fileName = null;
		//多个文件上传
		for(int i=0; i<su.getFiles().getCount(); i++){
			/*fileName = FileTools.getIpTimeRand(ip)+su.getFiles().getFile(i).getFileName();*/
			fileName = su.getFiles().getFile(i).getFileName();
			System.out.println(fileName);
			System.out.println("C:/upload/"+fileDic+java.io.File.separator+fileName);
			su.getFiles().getFile(i).saveAs("/WEB-INF/upload/"+fileName,SmartUpload.SAVE_VIRTUAL);
		}
	}

	public static boolean upload(HttpServletRequest request,HttpServletResponse response,
			                                      Map dto, String desFileDic, String desFileTem) throws Exception{
		
		
		// 创建上传文件工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置缓冲区大小
		factory.setSizeThreshold(100 * 1024);
		// 设置上传文件临时目录
		factory.setRepository(new File(desFileTem));
		// 创建上传文件对象[核心]
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 设置上传文件的中文编码方式
		upload.setHeaderEncoding("UTF-8");
		// 客户端上传文件是否使用MIME协议，
		boolean flag = upload.isMultipartContent(request);
		if (!flag) {
			// 不是以MIME协议上传文件
			throw new ServletException();
		} else {
			/*
			 * 是以MIME协议上传的文件，解析request中的所有上传内容 每个内容封装成一个对象FileItem，
			 * FileItem代表普通字段和上传字段二类
			 */
			try {
				List<FileItem> fileItemList = upload.parseRequest(request);
				for (FileItem fileItem : fileItemList) {
					if (fileItem.isFormField()) {
						// 必定是普通字段
						String fieldName = fileItem.getFieldName();
						String fieldValue = fileItem.getString("UTF-8");
						dto.put(fieldName, fieldValue);
					} else if(fileItem.getSize() > 0) {
						System.out.println("FileTool"+fileItem.getName());
						// 必定是上传字段
						String realFileName = fileItem.getName();
						int index = realFileName.lastIndexOf("\\");
						if (index >= 0) {
							// IE6浏览器
							realFileName = realFileName.substring(index + 1);
						}
						realFileName = (new Date()).getTime()+realFileName;
						String fieldName = fileItem.getFieldName();
						dto.put(fieldName, realFileName);
//						// 通过真实文件名换算出唯一个文件名
//						String uuidFileName = makeUuidFileName(realFileName);
//						// 通过位运算换算出upload目录下的子目录
//						String uuidFilePath = makeUuidFilePath(desFileDic, uuidFileName);
						// 取得文件输入流
						InputStream is = fileItem.getInputStream();
						// 取得文件输出流
						OutputStream os = new FileOutputStream(desFileDic + "/" + realFileName);
						byte[] buf = new byte[1024];
						int len = 0;
						while ((len = is.read(buf)) > 0) {
							os.write(buf, 0, len);
						}
						is.close();
						os.close();
						// 将上传文件产生的临时文件删除
						fileItem.delete();
					}
				}
				return true ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false ;
	}

	public static String getPath(String pathKey){
		ResourceBundle rb = ResourceBundle.getBundle("property");
		String basePath = rb.getString(pathKey);
		File dir = new File(basePath);  
	    if (!dir.exists()) {  
	    	dir.mkdirs(); // 递归的创建不存在的文件夹  
	    }  
		return basePath;
	}
	
	private String makeUuidFilePath(String uploadPath, String uuidFileName) { 
		String uuidFilePath = null ; 
	    int code = uuidFileName.hashCode();
	    int dir1 = code & 0xF;
	    int dir2 = code >> 4 & 0xF; 
	    File file = new File(uploadPath+"/"+dir1+"/"+dir2); 
	    if (! file.exists()){ 
	    	 file.mkdirs(); 
	    } 
	     uuidFilePath = file.getPath();
	     return uuidFilePath ;
	   }
	public static void getImage(HttpServletRequest request, HttpServletResponse response,
						 String imageURL,String fileName) throws Exception{
		if(null == imageURL || null == fileName ||
		   "".equals(imageURL) || "".equals(fileName)){
			return ;
		}
		
		File image = new File(imageURL+"\\"+fileName); 
		if(image.exists() && !"".equals(fileName)){
				DataOutputStream dos=new DataOutputStream(response.getOutputStream());  
				DataInputStream dis=new DataInputStream(new FileInputStream(image.getAbsolutePath()));  
	                byte[] data=new byte[2048];  
	                while((dis.read(data)) != -1){  
	                    dos.write(data);  
	                    dos.flush();  
	                }  
	                dis.close();  
	                dos.close();  
		}
	}
	
	public static void main(String[] args) {
	
	}
}
