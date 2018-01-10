package org.dcm4chee.arc.knd.common.MapperCommonUtil;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

//
/**
 * 项目中定义的 MyMapper接口 继承该接口，而该接口继承了通用mapper提供的Mybatis通用方法。extends Mapper<T>, MySqlMapper<T> 
 * 该接口不能被MapperScan扫描
 * @author Administrator
 *
 * @param <T>
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>{

}
