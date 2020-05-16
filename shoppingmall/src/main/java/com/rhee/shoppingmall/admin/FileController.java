package com.rhee.shoppingmall.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/file")
public class FileController {
	
	private String fileRoot="C:\\Shopping\\";
	/*
	 * @RequestMapping(value= "/{filePath}/{fileName}") public void
	 * fileDownload(@PathVariable String filePath, @PathVariable String fileName,
	 * HttpServletRequest request, HttpServletResponse response) throws Exception{
	 * 
	 * String fileFullLocalPath=fileRoot+filePath+"\\"+fileName;
	 * 
	 * InputStream in=null; OutputStream os=null;
	 * 
	 * File file=null; boolean skip=true; String client=""; try { file=new
	 * File(fileFullLocalPath); in=new FileInputStream(file); } catch (Exception e)
	 * { skip=true; }
	 * 
	 * client = request.getHeader("User-Agent");
	 * 
	 * response.reset(); response.setContentType("application/octet-stream");
	 * response.setHeader("Content-Description", "JSP Generated Data");
	 * 
	 * if(!skip) { if (client.indexOf("MSIE") != -1) {
	 * response.setHeader("Content-Disposition", "attachment; filename=\"" +
	 * java.net.URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "\\ ") +
	 * "\""); // IE 11 이상. } else if (client.indexOf("Trident") != -1) {
	 * response.setHeader("Content-Disposition", "attachment; filename=\"" +
	 * java.net.URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "\\ ") +
	 * "\""); } else { // 한글 파일명 처리 response.setHeader("Content-Disposition",
	 * "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"),
	 * "ISO8859_1") + "\""); response.setHeader("Content-Type",
	 * "application/octet-stream; charset=utf-8"); }
	 * response.setHeader("Content-Length", "" + file.length());
	 * 
	 * os=response.getOutputStream(); byte b[]=new byte[(int)file.length()]; int
	 * leng=0; while((leng=in.read(b))>0) { os.write(b, 0, leng); }
	 * 
	 * } else { response.setContentType("text/html;charset=UTF-8"); System.out.
	 * println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>"
	 * );
	 * 
	 * } in.close(); os.close();
	 * 
	 * }
	 */
	
	
}
