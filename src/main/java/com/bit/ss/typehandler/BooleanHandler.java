package com.bit.ss.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**   
 * @Title: BooleanHandler.java 
 * @Package com.bit.ss.typehandler 
 * @Description:  
 * @author CCX
 * @date 2016年2月14日 上午8:38:08 
 * @version V1.0   
 */
public class BooleanHandler extends BaseTypeHandler<Boolean> {

	@Override
	public Boolean getNullableResult(CallableStatement arg0, int arg1) throws SQLException {
		String str = arg0.getString(arg1);
		if (str == null)
			return false;
		int num = Integer.valueOf(str);
		if (num > 0)
			return true;
		else
			return false;
	}

	@Override
	public Boolean getNullableResult(ResultSet arg0, int arg1) throws SQLException {
		String str = arg0.getString(arg1);
		if (str == null)
			return false;
		int num = Integer.valueOf(str);
		if (num > 0)
			return true;
		else
			return false;
	}

	@Override
	public Boolean getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		String str = arg0.getString(arg1);
		if (str == null)
			return false;
		int num = Integer.valueOf(str);
		if (num > 0)
			return true;
		else
			return false;
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1, Boolean arg2, JdbcType arg3) throws SQLException {
		int intValue = arg2 == true ? 1 : 0;
		arg0.setInt(arg1, intValue);
	}
}
