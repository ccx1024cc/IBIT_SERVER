package com.bit.ss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.ss.mapper.VersionMapper;
import com.bit.ss.model.Version;

/**   
 * @Title: VersionServiceImpl.java 
 * @Package com.bit.ss.service 
 * @Description:  版本控制模块服务实现类
 * @author CCX
 * @date 2016年2月15日 下午5:57:07 
 * @version V1.0   
 */
@Service
public class VersionServiceImpl implements IVersionService {

	@Autowired
	private VersionMapper versionMapper;

	@Override
	public String getLatestNumber() {
		return versionMapper.getLatestNumber();
	}

	@Override
	public int getVersionNum() {
		return versionMapper.getVersionNum();
	}

	@Override
	public List<Version> getVersionList(int page) {
		int start = (page - 1) * PC_PAGE_SIZE;
		return versionMapper.getVersionList(start, PC_PAGE_SIZE);
	}

	@Override
	public int addVersion(Version version) {
		versionMapper.stopCurrentVersion();
		return versionMapper.addVersion(version);
	}
}
