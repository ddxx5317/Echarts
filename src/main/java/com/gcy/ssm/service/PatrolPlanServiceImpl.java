package com.gcy.ssm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcy.ssm.mapper.DeviceTroubleMapper;
import com.github.pagehelper.PageHelper;

@Service
public class PatrolPlanServiceImpl {
	
	@Autowired
	public DeviceTroubleMapper deviceTroubleMapper;
	
	public List<Map<String,Object>> select(){
		
		PageHelper.startPage(1, 20);
		
		List<Map<String,Object>> list=deviceTroubleMapper.select();
		
		return list;
	}
	
	

}
