package com.bit.ss.service;

import java.util.List;

import com.bit.ss.model.Authority;
import com.bit.ss.model.User;

/**   
 * @Title: IUserService.java 
 * @Package com.bit.ss.service 
 * @Description:  用户管理服务接口
 * @author CCX
 * @date 2015年12月18日 上午8:42:34 
 * @version V1.0   
 */
public interface IUserService {

	public static final int PCpageSize = 30;// 网页中每一页显示的数量

	/**
	 * 
	 * @Title: sendCheckCode 
	 * @Description: 发送验证码
	 * @return string    返回验证码
	 * @throws
	 */
	public String sendCheckCode(String phone);

	/**
	 * 
	 * @Title: findUserByAccount 
	 * @Description: 登陆服务，支持邮箱登陆
	 * @return User    该用户的详细信息
	 * @throws
	 */
	public User findUserByAccount(String email, String password);

	/**
	 * 
	 * @Title: addUser 
	 * @Description: 添加用户
	 * @return void    返回类型 
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
	 * @Title: findUserNumByEmail 
	 * @Description: 查找该邮箱用户数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByEmail(String email);

	/**
	 * 
	 * @Title: findUserNumByPhone 
	 * @Description: 查找该手机号用户
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByPhone(String phone);

	/**
	 * 
	 * @Title: findUserListByCondition 
	 * @Description: 后台服务，搜索用户列表
	 * @return List<User>    返回类型 
	 * @throws
	 */
	public List<User> findUserListByCondition(User condition, int page);

	/**
	 * 
	 * @Title: findUserNumByCondition 
	 * @Description: 后台服务,搜索符合条件的用户数量
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByCondition(User condition);

	/**
	 * 
	 * @Title: addAuthority 
	 * @Description: 后台服务，添加权限
	 * @return void    返回类型 
	 * @throws
	 */
	public void addAuthority(Authority authority);

	/**
	 * 
	 * @Title: deleteAuthority 
	 * @Description: 后台服务，删除权限
	 * @return void    返回类型 
	 * @throws
	 */
	public void deleteAuthority(int authId);

	/**
	 * 
	 * @Title: getPersonalAuths 
	 * @Description: 后台服务，获取用户权限列表
	 * @return List<Authority>    返回类型 
	 * @throws
	 */
	public List<Authority> getPersonalAuths(int userId);
	
	/**
	 * 
	 * @Title: getPersonalUsername 
	 * @Description: 后台服务，获取用户名称
	 * @return String    返回类型 
	 * @throws
	 */
	public String getPersonalUsername(int userId);
	
	/**
	 * 
	 * @Title: getUserDetail 
	 * @Description: 后台服务，获取用户详细信息
	 * @return User    返回类型 
	 * @throws
	 */
	public User getUserDetail(int userId);
	
	/**
	 * 
	 * @Title: updateUserDetail 
	 * @Description: 后台服务，更新用户详细信息
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateUserDetail(User user);
	
	/**
	 * 
	 * @Title: findUserNumByPhone 
	 * @Description: 后台服务，查询是否存在除某用户之外的其他拥有该手机号的用户
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByPhone(String phone,int userId);
	
	/**
	 * 
	 * @Title: findUserNumByEmail 
	 * @Description: 后台服务，查询是否存在除某用户之外的其他拥有邮箱的用户
	 * @return int    返回类型 
	 * @throws
	 */
	public int findUserNumByEmail(String email,int userId);
	
	/**
	 * 
	 * @Title: addUserByAdmin 
	 * @Description: 后台服务，添加用户
	 * @return int    返回类型 
	 * @throws
	 */
	public int addUserByAdmin(User user);
}
