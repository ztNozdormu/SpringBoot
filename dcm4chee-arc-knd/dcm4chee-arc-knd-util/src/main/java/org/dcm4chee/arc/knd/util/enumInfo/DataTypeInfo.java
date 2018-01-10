package org.dcm4chee.arc.knd.util.enumInfo;

public enum DataTypeInfo {
	
	/**
	 * 基本类型的包装类: Integer 、Long、Short、Byte、Character、Double、Float、Boolean、BigInteger、BigDecmail
	 */
	
	   BYTE("class java.lang.Byte","java.lang.Byte"),
	   SHORT("class java.lang.Short","java.lang.Short"),
	   INTEGER("class java.lang.Integer","java.lang.Integer"),
	   LONG("class java.lang.Long","java.lang.Long"),
	   FLOAT("class java.lang.Float","java.lang.Float"),
	   DOUBLE("class java.lang.Double","java.lang.Double"),
	   BOOLEAN("class java.lang.Boolean","java.lang.Boolean"),
	   CHARACTER("class java.lang.Character","java.lang.Character"),
	   
	   STRING("class java.lang.String","java.lang.String"),
	   DATE("class java.util.Date","java.util.Date"),
	   TIMESTAMP("class java.sql.Timestamp","java.sql.Timestamp"),
	   BIGINTEGER("class java.lang.BigInteger","java.lang.BigInteger"),
	   BIGDECIMAL("class java.math.BigDecimal","java.math.BigDecimal");
	   
		private final String genericType;
		private final String type;
		
		private DataTypeInfo(String genericType,String type) {
			this.genericType = genericType;
			this.type = type;
		}
	   
		public String getGenericType() {
			return genericType;
		}
		
		public String getType() {
			return type;
		}
		
	    public static DataTypeInfo getInstance(String val, DataTypeInfo defaultValue) {    
		        try {
		            return DataTypeInfo.valueOf(val);
		        } catch (Exception e) {
		        }
		    return defaultValue;
	    }
	    
	    public static void main(String[] args) {
	    	for(DataTypeInfo dt:DataTypeInfo.values()){	
	    		System.out.println("类型名称"+dt.name());
	    		System.out.println("普通类型"+dt.type);
	    		System.out.println("常规类型"+dt.genericType);
	    		System.out.println("枚举序号"+dt.ordinal());
	    	}
		}
		
    
}
