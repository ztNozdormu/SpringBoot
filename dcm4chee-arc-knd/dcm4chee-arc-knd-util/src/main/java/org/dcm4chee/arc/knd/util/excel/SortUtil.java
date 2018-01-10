package org.dcm4chee.arc.knd.util.excel;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.dcm4chee.arc.knd.util.enumInfo.EntityExcelRules;


public class SortUtil {   

    // 按任意属性进行排序   
    static class AnyProperComparator implements Comparator<Object> {   
  
        private String properName;// 根据此关键字属性排序   
  
        private boolean flag;// 为true的时候是正序，为false的时候是倒序   
  
        public AnyProperComparator(String properName, boolean flag) {   
            super();   
            this.properName = properName;   
            this.flag = flag;   
        }   
  
        public void setProperName(String properName) {   
            this.properName = properName;   
        }   
  
        public String getProperName() {   
            return properName;   
        }   
  
        public boolean isFlag() {   
            return flag;   
        }   
  
        public void setFlag(boolean flag) {   
            this.flag = flag;   
        }   
  
          
        @SuppressWarnings("unchecked")   
        public int compare(Object r1, Object r2) {   
            Class c = r1.getClass();   
            double result = 0;   
            try {   
                Field field = c.getDeclaredField(properName);   
                String classType = field.getType().getSimpleName();   
                Method method = null;   
                // 这里仅根据方法的返回值类型的名称来判定，比较方便   
                if ("String".equals(classType)) {   
                    method = c.getMethod("get" + properName.substring(0, 1).toUpperCase() + properName.substring(1), new Class[] {});   
                    if (flag) {   
                        result = ((String) method.invoke(r1)).compareTo((String) method.invoke(r2));   
                    } else {   
                        result = ((String) method.invoke(r2)).compareTo((String) method.invoke(r1));   
                    }   
  
                } else if ("Integer".equals(classType) || "int".equals(classType)) {   
                    method = c.getMethod("get" + properName.substring(0, 1).toUpperCase() + properName.substring(1), new Class[] {});   
                    if (flag) {   
                        result = ((Integer) method.invoke(r1)) - ((Integer) method.invoke(r2));   
                    } else {   
                        result = ((Integer) method.invoke(r2)) - ((Integer) method.invoke(r1));   
                    }   
                } else if ("Double".equals(classType) || "double".equals(classType)) {   
                    method = c.getMethod("get" + properName.substring(0, 1).toUpperCase() + properName.substring(1), new Class[] {});   
                    if (flag) {   
                        result = ((Double) method.invoke(r1)) - ((Double) method.invoke(r2));   
                    } else {   
                        result = ((Double) method.invoke(r2)) - ((Double) method.invoke(r1));   
                    }   
                } else if ("Float".equals(classType) || "float".equals(classType)) {   
                    method = c.getMethod("get" + properName.substring(0, 1).toUpperCase() + properName.substring(1), new Class[] {});   
                    if (flag) {   
                        result = ((Float) method.invoke(r1)) - ((Float) method.invoke(r2));   
                    } else {   
                        result = ((Float) method.invoke(r2)) - ((Float) method.invoke(r1));   
                    }   
                } else {   
                    System.out.println("属性排序只支持数据类型和String类型，其它类型暂不支持。");   
                    result = -100;   
                }   
            } catch (SecurityException e) {   
                e.printStackTrace();   
            } catch (NoSuchFieldException e) {   
                e.printStackTrace();   
            } catch (NoSuchMethodException e) {   
                e.printStackTrace();   
            } catch (IllegalArgumentException e) {   
                e.printStackTrace();   
            } catch (IllegalAccessException e) {   
                e.printStackTrace();   
            } catch (InvocationTargetException e) {   
                e.printStackTrace();   
            }   
  
            // 确定返回值   
            if (result > 0) {   
                return 1;   
            } else if (result < 0) {   
                return -1;   
            }   
            return 0;   
        }   
  
    }   
  
      
    @SuppressWarnings("unchecked")   
    public static <E> List<E> anyProperSort(List<E> list, String properName, boolean flag) {   
        AnyProperComparator comparator = new AnyProperComparator(properName, flag);   
        Collections.sort(list, comparator);
        System.out.println("字段排序后:"+list);
        return list;   
    }   
    

    /**obj:目标对象的class对象
     * fieldMap:对象字段(key:fieldObject);字段以及字段对应的字段对象
     */
    public static <E> Map<String,Object>  reflectFields(Class<E> obj) {  
    	Map<String,Object> fieldMap = new LinkedHashMap<String,Object>();
        if (obj == null) return fieldMap;  
        Field[] fields = obj.getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {  
            fields[j].setAccessible(true);  
            fieldMap.put(fields[j].getName(),fields[j]);
        }  
        System.out.println("对象的字段集合"+fieldMap); 
        return fieldMap;
    }  
    
    // 把一个字符串的第一个字母大写、效率是最高的、  
    private static String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    }  
    /**
     * 
     * @param class1
     * @param startIndex
     * @param sheetIndex
     * @param mapKey
     * @param eer
     * @param MultipartFile file(文件，文件流，或者是前端表单传输的file流)
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    
    public static <E>  List<E> sheetDataToObj(Class<E> class1,int startIndex, int sheetIndex,String mapKey,EntityExcelRules[] eer,File file) throws InstantiationException, IllegalAccessException{	
    	List<E> objList = new ArrayList<E>();
    	E e = (E)class1.newInstance();
    	Map<String,Object> objFields = reflectFields(class1);
    	List<Map<String,Object>> sheetDatas = getExcelSheetData(file,startIndex,sheetIndex,mapKey);
    	for(Map<String,Object> sheetData:sheetDatas) {	
    		for(EntityExcelRules p:eer) {
    			Field field  = (Field) objFields.get(p.name());
    			if(!p.getFieldType().equals("string")) {
//    				field.set(e, DictUtils.getDictValue(sheetData.get(p.getMapKey()).toString(),p.getFieldType(),""));
    			}else {
    				field.set(e, sheetData.get(p.getMapKey()));
    			}
    		}
    		objList.add(e);
    	}
    	return objList;
    }
    
    /**
     * 
     * @param class1
     * @param startIndex
     * @param sheetIndexs
     * @param eer
     * @param file
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <E>  List<E> sheetDataToObj(Class<E> class1,int startIndex,int[] sheetIndexs,EntityExcelRules[] eer,File file) throws InstantiationException, IllegalAccessException{	
    	List<E> objList = new ArrayList<E>();
    	E e = (E)class1.newInstance();
    	Map<String,Object> objFields = reflectFields(class1);
//    	sheetIndexs = EntityExcelRules.PFE_SHEET_INDEX;
    	//考虑一个表对应一个sheet的情况，以及多个sheet对应一个表的情况
    	if(sheetIndexs.length>0) {
    		for(int i = 0;i<sheetIndexs.length;i++) {
    		 	List<Map<String,Object>> sheetDatas = getExcelSheetData(file,startIndex,i,EntityExcelRules.EXCEL_CELL_KEY);
    		 	//每一个sheetDatas对应一个对象
    	    	for(Map<String,Object> sheetData:sheetDatas) {	
    	    		for(EntityExcelRules p:eer) {
    	    			//将当前sheet的数据赋值给对应对象的对应字段
    	    			if(sheetIndexs[i]==p.getSheetIndex()) {   				
	    	    			Field field  = (Field) objFields.get(p.name());
	    	    			//注意字段类型
	    	    			if(!p.getFieldType().equals(EntityExcelRules.DEFAULT_FIELDTYPE)) {
//	    	    				field.set(e, DictUtils.getDictValue(sheetData.get(p.getMapKey()).toString(),p.getFieldType(),""));
	    	    			}else {
	    	    				if(field.getGenericType().equals("class java.lang.String")&&sheetData.get(p.getMapKey())!=null) {
	    	    					field.set(e, String.valueOf(sheetData.get(p.getMapKey())));
	    	    				}
//	    	    				if(field.getGenericType().equals("class java.util.Date")&&sheetData.get(p.getMapKey())!=null) {
//	    	    					field.set(e, sheetData.get(p.getMapKey()));
//	    	    				}
	    	    			}
    	    			}
    	    		}
    	    		//一个sheet对应一个表
    	    		if(sheetIndexs.length==1) {
    	    		  objList.add(e);
    	    		}
    	    	}
    		}
    		//多个sheet对应一个表
    		if(sheetIndexs.length>1) {
    			 objList.add(e);
    		}
    	}
    

    	return objList;
    }

    /**
     * 解析EXCEL具体sheet的数据
     * @param startIndex 从上往下 默认启始0
     * @param sheetIndex EXCEL中sheet下标
     * @param mapKey
     * MultipartFile 表单提交的文件流 此处暂时不用
     * @return
     */

    public static List<Map<String, Object>> getExcelSheetData(File file,int startIndex, int sheetIndex,String mapKey) {
//    	File file1 = new File("C://Users/Administrator/Desktop/EIMS/售前调研报告.xlsx");
//    	ImportExcel importExcel = null;
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
//    	try {
//			importExcel =	new ImportExcel(file,startIndex,sheetIndex);
//		} catch (InvalidFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/* 通过流或者是文件创建一个Workbook对象 */
		Sheet sheet = null;// importExcel.getWorkbook().getSheetAt(sheetIndex);
		/* 创建一个行对象 */
		Row row = null;
		/* 获取第一行下标 */
		int firstRowNum = sheet.getFirstRowNum();
		/* 获取最后一行下标 */
		int lastRowNum = sheet.getLastRowNum();
		/* 遍历每行遍历row(行 1开始) */
		for (int rowIndex = firstRowNum + 1; rowIndex <= lastRowNum; rowIndex++) {
			map = new HashMap<String, Object>();
			/* 获取当前行对象 */
			row = sheet.getRow(rowIndex);
			/* 判断如果行存在 */
			if (null != row) {
				/* 通过行取到第一格下标 */
				int firstCellNum = row.getFirstCellNum();

				/* 通过行对象取得最后一格下标 */
				int lastCellNum = row.getLastCellNum();

				/* 遍历行对象的每格，遍历cell（列 1开始）除开序列号 firstCellNum+1 */
				for (int cellIndex = firstCellNum; cellIndex < lastCellNum; cellIndex++) {

					/* 通过格子index和行返回设置返回格子对象 */
					Cell cell = row.getCell(cellIndex, Row.RETURN_BLANK_AS_NULL);
					/* 如果存在cell对象 */
					if (cell != null) {
						/* 创建一个对象保存cell数据 */
						Object cellValue = null;// cellValue的值
						/* 分支循环(条件为cell类型) */
						switch (cell.getCellType()) {
						/* 字符串类型 */
						case Cell.CELL_TYPE_STRING:
							cellValue = String.valueOf(cell.getStringCellValue());
							break;
						/* 数字类型 */
						case Cell.CELL_TYPE_NUMERIC:
							/* 数字类型中时间类型的判断 */
							if (DateUtil.isCellDateFormatted(cell)) {
								System.out.println(cell.getDateCellValue());
								cellValue = String.valueOf(cell.getDateCellValue());
								// TODO 可以按日期格式转换
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								cellValue = formatter.format(cellValue);
		
							} else {
								cellValue = String.valueOf(cell.getNumericCellValue());
							}
							break;

						/* 布尔类型 */
						case Cell.CELL_TYPE_BOOLEAN:
							System.out.println("布尔数据" + cell.getBooleanCellValue());
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;

						/* EXCEL表格内公式类型 */
						case Cell.CELL_TYPE_FORMULA:
							System.out.println(cell.getCellFormula());
							cellValue = String.valueOf(cell.getCellFormula());
							break;

						/* 如果没有以上类型匹配，则默认处理的方式 */
						default:
							System.out.println("没有匹配的数据类型..");
						}
						/* switch循环完毕后得到一行每格的数据;由于表格中有特殊字符那么将特殊截取掉 */
						// 保存每行数据
						map.put(mapKey + cellIndex, cellValue);
					} else {
						System.out.println("EXCEL表中数据为空");
					}
				} // end cell
			} else {

				System.out.println("EXCEL表中数据为空");
			}
			if (map.size() > 0) {
				maps.add(map);
			}
		} // 行遍历结束

		return maps;
	}
    
 //	E e= (E)obj.newInstance();   
//    switch(fields[j].getGenericType().toString()) {
//	case "class java.lang.String":
//		// 拿到该属性的get set方法  
//        /** 
//         * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的 
//         * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX） 
//         * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范 
//         */ 
//		fields[j].set(e, "赋值"+j);
//		break;
//	case "class java.lang.Integer":
//		fields[j].set(e, j);
//        break;
//	case "class java.lang.Double":
//		fields[j].set(e, (double)j);
//		break;
//	case "class java.lang.Boolean":
//		   // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的  
//        // 反射找不到getter的具体名  
//		fields[j].set(e,0);
//		break;
//	case "class java.util.Date":
//		fields[j].set(e,new Date());
//		break;
//	case "class java.lang.Short":
//		break;
//	default:	
//		break;
//	}
}  
