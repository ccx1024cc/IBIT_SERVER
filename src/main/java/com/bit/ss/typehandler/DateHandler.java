package com.bit.ss.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.bit.ss.util.DateUtil;

/**   
 * @Title: DateHandler.java 
 * @Package com.bit.ss.typehandler 
 * @Description:  日期格式化处理类
 * @author CCX
 * @date 2015年11月1日 上午10:54:39 
 * @version V1.0   
 */
public class DateHandler extends BaseTypeHandler<Date> {

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, Date arg2, JdbcType arg3) throws SQLException {
		String date = new DateUtil().formatDateTime(arg2, DateUtil.DATE_TIME_FORMAT);
		arg0.setString(arg1, date);
	}

	@Override
	public Date getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		String dateStr = arg0.getString(arg1);
		Date date = null;
		date = new DateUtil().parse(dateStr, DateUtil.DATE_TIME_FORMAT);
		return date;
	}

	@Override
	public Date getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		String dateStr = arg0.getString(arg1);
		Date date = null;
		date = new DateUtil().parse(dateStr, DateUtil.DATE_TIME_FORMAT);
		return date;
	}

	@Override
	public Date getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		String dateStr = arg0.getString(arg1);
		Date date = null;
		date = new DateUtil().parse(dateStr, DateUtil.DATE_TIME_FORMAT);
		return date;
	}

}
