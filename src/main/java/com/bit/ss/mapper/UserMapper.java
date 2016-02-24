package com.bit.ss.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bit.ss.model.Authority;
import com.bit.ss.model.User;

/**   
 * @Title: UserMapper.java 
 * @Package com.bit.ss.mapper 
 * @Description:  
 * @author CCX
 * @date 2015年12月15日 下午12:54:55 
 * @version V1.0   
 */
@Repository
public interface UserMapper {

	/**
	 * 
	 * @Title: findUser 
	 * @Description: 根据账号、密码查询用户信息
	 * @return User    返回类型 
	 * @throws
	 */
	public User findUserByAccount(@Param("email") String email, @Param("pwd") String password);

	/**
	 * 
	 * @Title: addUser 
	 * @Description: 增加用户
	 * @return int    返回类型 
	 * @throws
	 */
	public void addUser(User user);

	/**
	 * 
	 * @Title: updateUser 
	 * @Description: 更新用户信息
	 * @return void    返回类型 
	 * @throws
	 */
	public void updateUser(User user);

	/**
	 * 
	 * @Title: findUserNumByPhone 
	 * @Description: 查询拥有该账号的用户数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByPhone(String phone);

	/**
	 * 
	 * @Title: findUserNumByEmail 
	 * @Description: 
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByEmail(String email);

	/**
	 * 
	 * @Title: findUsersByCondition 
	 * @Description: 搜索用户列表
	 * @return List<User>    返回类型 
	 * @throws
	 */
	public List<User> findUsersByCondition(@Param("user") User user, @Param("start") int start, @Param("num") int num);

	/**
	 * 
	 * @Title: findUserNumByCondition 
	 * @Description: 搜索符合条件的用户数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByCondition(User user);

	/**
	 * 
	 * @Title: getPersonalAuthNum 
	 * @Description: 判断某用户是否拥有某个权限
	 * @return int    返回类型 
	 * @throws
	 */
	public int getPersonalAuthNum(@Param("userId") int userId, @Param("authority") String authority);

	/**
	 * 
	 * @Title: addAuth 
	 * @Description: 添加权限
	 * @return int    返回类型 
	 * @throws
	 */
	public int addAuth(Authority authority);

	/**
	 * 
	 * @Title: deleteAuth 
	 * @Description: 撤回权限
	 * @return int    返回类型 
	 * @throws
	 */
	public int deleteAuth(@Param("authId") int authId);

	/**
	 * 
	 * @Title: getPersonalAuth 
	 * @Description: 获取某用户权限
	 * @return List<Authority>    返回类型 
	 * @throws
	 */
	public List<Authority> getPersonalAuth(@Param("userId")int userId);
	
	/**
	 * 
	 * @Title: getUserNameById 
	 * @Description: 获取用户名称
	 * @return String    返回类型 
	 * @throws
	 */
	public String getUserNameById(@Param("userId")int userId);
	
	/**
	 * 
	 * @Title: getUserDetail 
	 * @Description: 获取用户详细信息
	 * @return User    返回类型 
	 * @throws
	 */
	public User getUserDetail(@Param("userId")int userId);
	
	/**
	 * 
	 * @Title: updateUserDetail 
	 * @Description: 更新用户详细信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateUserDetail(User user);
	
	/**
	 * 
	 * @Title: findUserNumByPhoneExceptOne 
	 * @Description:  查询是否存在除某用户之外的其他拥有该手机号的用户
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByPhoneExceptOne(@Param("userId")int userId,@Param("phone")String phone);
	
	/**
	 * 
	 * @Title: findUserNumByEmailExceptOne 
	 * @Description: 查询是否存在除某用户之外的其他拥有邮箱的用户
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByEmailExceptOne(@Param("userId")int userId,@Param("email")String email);
	
	/**
	 * 
	 * @Title: addUserByAdmin 
	 * @Description: 管理员添加用户
	 * @return int    返回类型 
	 * @throws
	 */
	public int addUserByAdmin(User user);
}
