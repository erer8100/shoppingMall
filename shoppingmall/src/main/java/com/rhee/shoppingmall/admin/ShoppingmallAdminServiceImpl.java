package com.rhee.shoppingmall.admin;



import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("shoppingmallAdminService")
public class ShoppingmallAdminServiceImpl implements ShoppingmallAdminService {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Transactional(rollbackFor = {RuntimeException.class})
	public int insertProductInfo(ProductInfoVO vo, String status) throws Exception{
		if("init".equals(status))
		{
			return insertInfo(vo);
		} else {
			return updateInfo(vo);
		}
	}
	
	private int insertInfo(ProductInfoVO vo)
	{
		int result=0;
		
		LocalDate today = LocalDate.now();
		String todayString=today.format(DateTimeFormatter.BASIC_ISO_DATE);
		
		int max=adminMapper.maxProductReg(todayString);
		
		vo.setProductId(todayString+codifyInt(max+1, 4));
		result=adminMapper.insertProductInfo(vo);
		
		return result;
	}
	private int updateInfo(ProductInfoVO vo)
	{
		return adminMapper.updateProductInfo(vo);
	}
	public ProductInfoVO getProductInfoById(String productId) throws Exception{
		
		
		return adminMapper.getProductInfoById(productId);
	}
	
	private String codifyInt(int number, int digits) {
		
		String StrNumber=String.valueOf(number);
		int leng=StrNumber.length();
		
		String code="";
		if(leng>=0 && leng<=digits)
		{
			for(int i=0; i<digits-leng; i++) {
				code+="0";
			}
			code+=StrNumber;
		}
		
		System.out.println("-----------------------------------code "+code+"--------------------------------");
		return code;
	}
	
	public String saveUploadFiles(MultipartFile file, String uploadPath) throws Exception{
		
		File destFile=new File(getFilePath(uploadPath+file.getOriginalFilename()));
		
		System.out.println("-----------------------------------check "+uploadPath+file.getOriginalFilename()+"--------------------------------");
		try {
			file.transferTo(destFile);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return destFile.getAbsolutePath();
	}
	
	private String getFilePath(String originalFilePath)
	{
		String originalFileName=originalFilePath.substring(originalFilePath.lastIndexOf("\\")+1);
		String originalFileBeginPath=originalFilePath.substring(0, originalFilePath.lastIndexOf("\\")+1);
		LocalDate today = LocalDate.now();
		String todayString=today.format(DateTimeFormatter.BASIC_ISO_DATE);
		String fileName="";
		String filePath="";
		String extension=originalFileName.substring(originalFileName.lastIndexOf("."));
		
		int i=0;
		
		while(true) {
			fileName="image"+todayString+codifyInt(i, 4)+extension;
			filePath=originalFileBeginPath+fileName;
			File file=new File(filePath);
			if(file.exists())
			{
				i++;
			} else {
				break;
			}
			
		}
		System.out.println("-----------------------------------check fileName="+fileName+"--------------------------------");
		return filePath;
	}
	
	@Transactional(rollbackFor = {RuntimeException.class})
	public int deleteProduct(String productId) throws Exception{
		return adminMapper.deleteProduct(productId);
	}
}
