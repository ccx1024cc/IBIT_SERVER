package com.bit.ss.service;

import java.util.List;
import java.util.Random;

import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.bit.ss.exception.OuterException;
import com.bit.ss.mapper.UserMapper;
import com.bit.ss.model.Authority;
import com.bit.ss.model.User;
import com.bit.ss.push.MessageSender;

/**   
 * @Title: UserServiceImpl.java 
 * @Package com.bit.ss.service 
 * @Description:  用户管理服务实现类
 * @author CCX
 * @date 2015年12月18日 上午8:42:52 
 * @version V1.0   
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MessageSender messageSender;// 验证码发送器

	@Override
	public User findUserByAccount(String email, String password) {
		User user = userMapper.findUserByAccount(email, password);
		return user;
	}

	@Override
	public void addUser(User user) {
		try {
			userMapper.addUser(user);
		} catch (DuplicateKeyException e) {
			throw new OuterException(Status.CONFLICT, "EMAIL_OR_PHONE_CONFILICT");
		}
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public String sendCheckCode(String phone) {
		String checkCode = this.genarateCheckCode();
		messageSender.send(phone, "【数字北理】验证码：" + checkCode + ",有效期30分钟");
		return checkCode;
	}

	/**
	 * 
	 * @Title: genarateCheckCode 
	 * @Description: 随机产生6位验证码
	 * @return String    返回类型 
	 * @throws
	 */
	private String genarateCheckCode() {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			str += random.nextInt(10);
		}
		return str;
	}

	@Override
	public int findUserNumByEmail(String email) {
		return userMapper.findUserNumByEmail(email);
	}

	@Override
	public int findUserNumByPhone(String phone) {
		return userMapper.findUserNumByPhone(phone);
	}

	@Override
	public List<User> findUserListByCondition(User condition, int page) {
		int start = (page - 1) * PCpageSize;
		return userMapper.findUsersByCondition(condition, start, PCpageSize);
	}

	@Override
	public int findUserNumByCondition(User condition) {
		return userMapper.findUserNumByCondition(condition);
	}

	@Override
	public void addAuthority(Authority authority) {
		int exist = userMapper.getPersonalAuthNum(authority.getUserId(), authority.getName());
		if (exist == 0) {
			userMapper.addAuth(authority);
		}
	}

	@Override
	public void deleteAuthority(int authId) {
		userMapper.deleteAuth(authId);
	}

	@Override
	public List<Authority> getPersonalAuths(int userId) {
		return userMapper.getPersonalAuth(userId);
	}

	@Override
	public String getPersonalUsername(int userId) {
		return userMapper.getUserNameById(userId);
	}

	@Override
	public User getUserDetail(int userId) {
		return userMapper.getUserDetail(userId);
	}

	@Override
	public int updateUserDetail(User user) {
		return userMapper.updateUserDetail(user);
	}

	@Override
	public int findUserNumByEmail(String email, int userId) {
		return userMapper.findUserNumByEmailExceptOne(userId, email);
	}

	@Override
	public int findUserNumByPhone(String phone, int userId) {
		return userMapper.findUserNumByPhoneExceptOne(userId, phone);
	}

	@Override
	public int addUserByAdmin(User user) {
		return userMapper.addUserByAdmin(user);
	}
}
