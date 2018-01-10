package org.dcm4chee.arc.knd.util.enumInfo;
/**
 * 通过枚举定义业务规则
 * @author Administrator
 *
 */
public interface EntityExcelRules {
   
    //非下拉列表字段默认为string
    public static final String DEFAULT_FIELDTYPE = "string";
    //下拉框字段字典类型
    public static final String YES_NO = "yes_no";
    public static final String IS_HAS = "is_has";
    public static final String HOS_FILM_PRINT_TYPE = "hosFilmPrintType";
    public static final String HOS_PATIENT_TYPE = "hosPatientType";
    public static final String IS_MEET = "is_meet";
    //解析EXCEL后的MAP数据对应的key 格式为: cell+(当前字段在excel中的cell下标)
    public static final String EXCEL_CELL_KEY = "cell";
    //对象对应的EXCEL中sheet的下标集合
    public static final int[] PFE_SHEET_INDEX= {10};
    //对象对应的EXCEL中sheet的下标集合
    public static final int[] PFE_REPORT_SHEET_INDEX= {0,1,2,3,4,5,6,7};
    //自定义规则枚举类型
	public enum ProjFilmEquipmentRule implements EntityExcelRules{
                //字段名称(字段在excel中对应的cell下标,字段字典类型)  如果不需要从字典表查询那么默认设定为string
				equipmentType(0,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,10), 
				venderModel(1,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,10), 
				filmNum(2,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,10), 
				isGuarantee(3,YES_NO,EXCEL_CELL_KEY,10),
				checknoExmpl(4,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,10),
				imgenoExmpl(5,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,10), 	
				isAdjustment(6,YES_NO,EXCEL_CELL_KEY,10),
				isAsk(7,IS_HAS,EXCEL_CELL_KEY,10);
    
	        private final int index;
	        private final String fieldType;
	        private final String mapKey;
	        private final int sheetIndex;
	        private ProjFilmEquipmentRule(int index,String fieldType,String mapKey,int sheetIndex) {
	            this.index = index;
	            this.fieldType = fieldType;   
	            this.mapKey = EXCEL_CELL_KEY+this.index;
	            this.sheetIndex = sheetIndex;
	        }

	        public int getIndex() {
	            return index;
	        }
	        public String getFieldType() {
	        	return fieldType;
	        }
	        public String getMapKey() {
	        	return mapKey;
	        }
	        public int getSheetIndex() {
	        	return sheetIndex;
	        }
            
	        @Override
	        public String toString() {
	        	return String.valueOf(index);
	        }
	 

	        public static ProjFilmEquipmentRule getInstance(String val, ProjFilmEquipmentRule defaultValue) {    
	                try {
	                    return ProjFilmEquipmentRule.valueOf(val);
	                } catch (Exception e) {

	                }
	            return defaultValue;
	        }
	    
	}
	public static void main(String[] args) {
//		String[] fields = EntityExcelRules.ProjFilmEquipmentRule.values()；
		System.out.println(EntityExcelRules.ProjFilmEquipmentRule.values());
		for(ProjFilmEquipmentRule p:EntityExcelRules.ProjFilmEquipmentRule.values()) {
			System.out.println("字段名称: "+p.name());
			System.out.println("字段类型: "+p.fieldType);
			System.out.println("字段取值KEY "+p.mapKey);
			System.out.println("字段对应的EXCEL中cell下标: "+p.index);	
		}
	}
	
	    public int getIndex();
	    public  String name();
	    public String getFieldType();
	    public String getMapKey();
        public int getSheetIndex();
	    public String toString();
}
