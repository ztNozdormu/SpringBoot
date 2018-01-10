package org.dcm4chee.arc.knd.util.enumInfo;
/**
 * 枚举定义业务规则
 * @author Administrator
 *
 */
public enum ProjFilmPresaleReprotRule implements EntityExcelRules{
	   
		isMakeFilmreprot(0,YES_NO,EXCEL_CELL_KEY,0),		// 是否使用胶片报告
		electronics(1,IS_HAS,EXCEL_CELL_KEY,0),		// 放射科有无电子
		ambient(2,YES_NO,EXCEL_CELL_KEY,0),		// 环境标准
	    
		hospitalName(0,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,1),		// 医院名称
		inspectionAmount(1,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,1),		// 检查量
		filmAmount(2,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,1),		// 胶片量
		otherSelfhelpMachine(3,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,1),		// 其它自助机
		selfhelpMachineType(4,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,1),		// 自助机类型
		selfhelpSerivceType(5,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,1),		// 自助服务类型
		planSelfhelpAmount(6,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,1),		// 计划自助机数量
		mainPosion(7,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,1),		// 主要安装位置
		
		pacsSystem(0,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,2),		// PACS系统
		pacsIscharge(1,YES_NO,EXCEL_CELL_KEY,2),		// PACS是否收费
		risSystem(2,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,2),		// RIS系统
		risIscharge(3,YES_NO,EXCEL_CELL_KEY,2),		// RIS是否收费
		centralizedPrinting(4,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,2),// 集中打印
		printingIscharge(5,YES_NO,EXCEL_CELL_KEY,2),	// 打印是否收费
		filmsavedate(6,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,2),//胶片保存时间
		filmprintType(7,HOS_FILM_PRINT_TYPE,EXCEL_CELL_KEY,2),		// 医院胶片打印方式
		filmprintPatientType(8,HOS_PATIENT_TYPE,EXCEL_CELL_KEY,2),		// 自助胶片患者类型
		
		networkIsUnobstructed(0,YES_NO,EXCEL_CELL_KEY,3),		// 网络是否通畅
		positionSpace(1,IS_MEET,EXCEL_CELL_KEY,3),		// 位置空间
		supplyIsUnblocked(2,YES_NO,EXCEL_CELL_KEY,3),		// 供电是否畅通
		powerSupplyRequirements(3,YES_NO,EXCEL_CELL_KEY,3),		// 供电要求
		registerReportRelationship(4,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,3),// 登记和报告关系
		
		triggerMode(0,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,4),		// 触发方式
		ischeckNumber(1,YES_NO,EXCEL_CELL_KEY,4),		// 是否为检查号
		ocrRecognition(2,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,4),		// OCR识别号
		
		risEntityInterface(0,YES_NO,EXCEL_CELL_KEY,5),		// RIS时候实体接口
		risDatabaseType(1,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,5),		// RIS数据库类型
		rissystemBarcode(2,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,5),		// RIS系统是否条码
		isneedPrint(3,YES_NO,EXCEL_CELL_KEY,5),		// 是否需要打印机
		isneedSupplyInterface(4,YES_NO,EXCEL_CELL_KEY,5),		// 是否需要我司提供接口
		barcodeMachineModel(5,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,5),		// 科室已使用条码机型号
		
		operatingSystem(0,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,6),		// 操作系统
		antivirusSoftware(1,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,6),		// 杀毒软件
		
		selfhelpReprotType(0,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,7),		// 自助报告打印方式
		isElectronicSignature(1,YES_NO,EXCEL_CELL_KEY,7),		// 有无电子签名
		
		investigators(0,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,8),		// 调研姓名
		telephone(1,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,8),		// 电话
		emali(2,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,8),		// 邮箱
		remarks(3,DEFAULT_FIELDTYPE,EXCEL_CELL_KEY,8);		// 附件
		 
		private final int index;
	    private final String fieldType;
	    private final String mapKey;
	    private final int sheetIndex;
	    
	    private ProjFilmPresaleReprotRule(int index,String fieldType,String mapKey,int sheetIndex) {
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
	
	
	    public static ProjFilmPresaleReprotRule getInstance(String val, ProjFilmPresaleReprotRule defaultValue) {    
	            try {
	                return ProjFilmPresaleReprotRule.valueOf(val);
	            } catch (Exception e) {
	
	            }
	        return defaultValue;
	    }

	public static void main(String[] args) {
//		String[] fields = EntityExcelRules.ProjFilmEquipmentRule.values()；
		System.out.println(ProjFilmPresaleReprotRule.values());
		for(ProjFilmPresaleReprotRule p:ProjFilmPresaleReprotRule.values()) {
			System.out.println("字段名称: "+p.name());
			System.out.println("字段类型: "+p.fieldType);
			System.out.println("字段取值KEY "+p.mapKey);
			System.out.println("字段对应的EXCEL中cell下标: "+p.index);	
		}
	}

}
