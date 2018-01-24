package org.dcm4chee.arc.knd.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

public class ExcelPictureParse {

		 public ExcelPictureParse(){
		 }
		 public Map readPicture(String excelPath)throws InvalidFormatException, IOException {
		   FileInputStream fis = new FileInputStream(excelPath);
		   HSSFWorkbook workbook = (HSSFWorkbook) WorkbookFactory.create(fis);
		   List<HSSFPictureData> pictures = workbook.getAllPictures();
		   //假設讀取的Excel工作薄中的第一張表
		   HSSFSheet sheet = workbook.getSheet("0");
		   Map<Integer,HSSFPictureData> map = new HashMap<Integer,HSSFPictureData>();
		   for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
		    HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
		    int rowIndex = anchor.getRow1();
		    if (shape instanceof HSSFPicture) {
		     int rowmark = rowIndex;
		     HSSFPicture  picture = (HSSFPicture) shape;
		     int pictureIndex = picture.getPictureIndex() - 1;
		     HSSFPictureData  pictureData = pictures.get(pictureIndex);
		     map.put(rowmark, pictureData);
		    }
		  }
		  return map;
		 }
		 public Map<String, List<HSSFPictureData>> findAllPictureDate(HSSFSheet sheet){

		       Map<String, List<HSSFPictureData>> dataMap = null;

		       //处理sheet中的图形
		       HSSFPatriarch hssfPatriarch = sheet.getDrawingPatriarch();
		       //获取所有的形状图
		       List<HSSFShape> shapes = hssfPatriarch.getChildren();

		       if(shapes.size()>0){

		           dataMap = new HashMap<String, List<HSSFPictureData>>();

		           List<HSSFPictureData> pictureDataList = null;

		           for(HSSFShape sp : shapes){
		               if(sp instanceof HSSFPicture){
		                   //转换
		                   HSSFPicture picture = (HSSFPicture)sp;
		                   //获取图片数据
		                   HSSFPictureData pictureData = picture.getPictureData();
		                   //图形定位
		                   if(picture.getAnchor() instanceof HSSFClientAnchor){

		                       HSSFClientAnchor anchor = (HSSFClientAnchor)picture.getAnchor();
		                       //获取图片所在行作为key值,插入图片时，默认图片只占一行的单个格子，不能超出格子边界
		                       int row1 = anchor.getRow1();
		                       String rowNum = String.valueOf(row1);

		                       if(dataMap.get(rowNum)!=null){
		                               pictureDataList = dataMap.get(rowNum);
		                       }else{
		                               pictureDataList = new ArrayList<HSSFPictureData>();
		                       }
		                       pictureDataList.add(pictureData);
		                       dataMap.put(rowNum, pictureDataList);
		                       // 测试部分
		                         int row2 = anchor.getRow2();
		                         short col1 = anchor.getCol1();
		                         short col2 = anchor.getCol2();
		                         int dx1 = anchor.getDx1();
		                         int dx2 = anchor.getDx2();
		                         int dy1 = anchor.getDy1();
		                         int dy2 = anchor.getDy2();

		                         System.out.println("row1: "+row1+" , row2: "+row2+" , col1: "+col1+" , col2: "+col2);
		                         System.out.println("dx1: "+dx1+" , dx2: "+dx2+" , dy1: "+dy1+" , dy2: "+dy2);
		                   }
		               }
		           }
		       }

		       System.out.println("********图片数量明细 START********");
		       int t=0;
		       if(dataMap!=null){
		           t=dataMap.keySet().size();
		       }
		       if(t>0){
		               for(String key : dataMap.keySet()){
		                   System.out.println("第 "+key+" 行， 有图片： "+ dataMap.get(key).size() +" 张");
		               }
		       }else{
		               System.out.println("Excel表中没有图片!");
		       }
		       System.out.println("********图片数量明细 END ********");

		       return dataMap;
		   }
		 public static Map<String, List<XSSFPictureData>> process(String path) throws Exception {
			 Map<String, List<XSSFPictureData>> dataMap = null;
			 FileInputStream fis = new FileInputStream(path);
			   XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(fis);
			   XSSFSheet sheet = workbook.getSheetAt(0);
			 for (POIXMLDocumentPart dr : sheet.getRelations()) {
			 if (dr instanceof XSSFDrawing) {
			 XSSFDrawing drawing = (XSSFDrawing) dr;
			 List<XSSFShape> shapes = drawing.getShapes();
	         dataMap = new HashMap<String, List<XSSFPictureData>>();
			 
			 List<XSSFPictureData> pictureDataList = null;
			 for(XSSFShape sp : shapes){
				 if(sp instanceof XSSFPicture){
					 //转换
					 XSSFPicture picture = (XSSFPicture)sp;
					 //获取图片数据
					 XSSFPictureData pictureData = picture.getPictureData();
					 //图形定位
					 if(picture.getAnchor() instanceof XSSFClientAnchor){
						 
						 XSSFClientAnchor anchor = (XSSFClientAnchor)picture.getAnchor();
						 //获取图片所在行作为key值,插入图片时，默认图片只占一行的单个格子，不能超出格子边界
						 int row1 = anchor.getRow1();
						 String rowNum = String.valueOf(row1);
						 
						 if(dataMap.get(rowNum)!=null){
							 pictureDataList = dataMap.get(rowNum);
						 }else{
							 pictureDataList = new ArrayList<XSSFPictureData>();
						 }
						 pictureDataList.add(pictureData);
						 dataMap.put(rowNum, pictureDataList);
						 // 测试部分
						 int row2 = anchor.getRow2();
						 short col1 = anchor.getCol1();
						 short col2 = anchor.getCol2();
						 int dx1 = anchor.getDx1();
						 int dx2 = anchor.getDx2();
						 int dy1 = anchor.getDy1();
						 int dy2 = anchor.getDy2();
						 
						 System.out.println("row1: "+row1+" , row2: "+row2+" , col1: "+col1+" , col2: "+col2);
						 System.out.println("dx1: "+dx1+" , dx2: "+dx2+" , dy1: "+dy1+" , dy2: "+dy2);
					 }
				 }
			 }
			 
			 }
			 }
			 System.out.println("********图片数量明细 START********");
			 int t=0;
			 if(dataMap!=null){
				 t=dataMap.keySet().size();
			 }
			 if(t>0){
				  String savePicturePath = "D:\\images";
				  int i=0;
				 for(String key : dataMap.keySet()){
					 i++;
					 System.out.println("第 "+key+" 行， 有图片： "+ dataMap.get(key).size() +" 张");
					 //保存图片到本地
					 List<XSSFPictureData> xssfPictures = dataMap.get(key);
					 for(XSSFPictureData pictureData:xssfPictures) {
					 byte[] data = pictureData.getData();
					 String ext = pictureData.suggestFileExtension();
					 File file = new File(savePicturePath);
					 if(!file.exists()) {
						 file.mkdirs();
					 }
					  //根據圖片格式將圖片寫出到磁盤
					  if (ext.equals("jpeg")) {
						  
					   FileOutputStream out = new FileOutputStream(savePicturePath+file.separator+i+".jpeg");
					   out.write(data);
					   out.close();
					  }
					  if (ext.equals("png")) {
					   FileOutputStream out = new FileOutputStream(savePicturePath+file.separator+i+".jpeg");
					   out.write(data);
					   out.close();
					  }
					 }
				 }
			 }else{
				 System.out.println("Excel表中没有图片!");
			 }
			 System.out.println("********图片数量明细 END ********");
			 
			 return dataMap;
			 }
		 
		 
		 public static void main(String args[]) throws Exception{
			 
		  String excelPath = "C:\\\\Users\\Administrator\\Desktop\\EIMS\\胶片实施报告.xlsx";
		  String savePicturePath = "D:\\images\\";
		  ExcelPictureParse readExcelPicture = new ExcelPictureParse();
		  Map<String, List<XSSFPictureData>> maps = readExcelPicture.process(excelPath);
//		  Map<Integer,HSSFPictureData> map = readExcelPicture.readPicture(excelPath);
		  //傳入一個你需要的Excel圖片行索引，必須確保該Excel行索引中有圖片，
		  //而且是在已經讀取的Excel行索引范圍內，我傳入的是1
//		  XSSFPictureData pictureData = maps.get("").;
//		  //獲取包含圖片格式的文件字符串
//		  String ext = pictureData.suggestFileExtension();
//		  //代表圖片信息的字節數據
//		  byte[] data = pictureData.getData();
//		  //根據圖片格式將圖片寫出到磁盤
//		  if (ext.equals("jpeg")) {
//		   FileOutputStream out = new FileOutputStream(savePicturePath+"a.jpg");
//		   out.write(data);
//		   out.close();
//		  }
//		  if (ext.equals("png")) {
//		   FileOutputStream out = new FileOutputStream(savePicturePath+"a.png");
//		   out.write(data);
//		   out.close();
//		  }
		 }
	
}
