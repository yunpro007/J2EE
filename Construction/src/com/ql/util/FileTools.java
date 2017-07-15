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
 * �����ļ�����(ip+ʱ���+��λ�����)������
 * @author Administrator
 *
 */
public class FileTools {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");;

	//����ip���� �ļ�����(ip+ʱ���+��λ�����)
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
	
	//���ڵ�ǰʱ���ȡʱ���
	private static String getTimeStamp(){
		return sdf.format(new Date());
	}
	
	/**
	 * ���ڵ�ǰservetʵ�����ϴ���SmartUpload
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
	 * �ϴ���ǰ�����е�ȫ���ļ�
	 * @param su
	 * @param ip
	 * @throws ServletException
	 * @throws IOException
	 * @throws SmartUploadException
	 */
	public static void upload(SmartUpload su,String ip,String fileDic) throws ServletException, IOException, SmartUploadException{
		su.upload();
		
		String fileName = null;
		//����ļ��ϴ�
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
		
		
		// �����ϴ��ļ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ���û�������С
		factory.setSizeThreshold(100 * 1024);
		// �����ϴ��ļ���ʱĿ¼
		factory.setRepository(new File(desFileTem));
		// �����ϴ��ļ�����[����]
		ServletFileUpload upload = new ServletFileUpload(factory);
		// �����ϴ��ļ������ı��뷽ʽ
		upload.setHeaderEncoding("UTF-8");
		// �ͻ����ϴ��ļ��Ƿ�ʹ��MIMEЭ�飬
		boolean flag = upload.isMultipartContent(request);
		if (!flag) {
			// ������MIMEЭ���ϴ��ļ�
			throw new ServletException();
		} else {
			/*
			 * ����MIMEЭ���ϴ����ļ�������request�е������ϴ����� ÿ�����ݷ�װ��һ������FileItem��
			 * FileItem������ͨ�ֶκ��ϴ��ֶζ���
			 */
			try {
				List<FileItem> fileItemList = upload.parseRequest(request);
				for (FileItem fileItem : fileItemList) {
					if (fileItem.isFormField()) {
						// �ض�����ͨ�ֶ�
						String fieldName = fileItem.getFieldName();
						String fieldValue = fileItem.getString("UTF-8");
						dto.put(fieldName, fieldValue);
					} else if(fileItem.getSize() > 0) {
						System.out.println("FileTool"+fileItem.getName());
						// �ض����ϴ��ֶ�
						String realFileName = fileItem.getName();
						int index = realFileName.lastIndexOf("\\");
						if (index >= 0) {
							// IE6�����
							realFileName = realFileName.substring(index + 1);
						}
						realFileName = (new Date()).getTime()+realFileName;
						String fieldName = fileItem.getFieldName();
						dto.put(fieldName, realFileName);
//						// ͨ����ʵ�ļ��������Ψһ���ļ���
//						String uuidFileName = makeUuidFileName(realFileName);
//						// ͨ��λ���㻻���uploadĿ¼�µ���Ŀ¼
//						String uuidFilePath = makeUuidFilePath(desFileDic, uuidFileName);
						// ȡ���ļ�������
						InputStream is = fileItem.getInputStream();
						// ȡ���ļ������
						OutputStream os = new FileOutputStream(desFileDic + "/" + realFileName);
						byte[] buf = new byte[1024];
						int len = 0;
						while ((len = is.read(buf)) > 0) {
							os.write(buf, 0, len);
						}
						is.close();
						os.close();
						// ���ϴ��ļ���������ʱ�ļ�ɾ��
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
	    	dir.mkdirs(); // �ݹ�Ĵ��������ڵ��ļ���  
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
