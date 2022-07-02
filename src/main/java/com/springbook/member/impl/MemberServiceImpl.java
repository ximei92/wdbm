package com.springbook.member.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.member.mapper.MemberMapper;
import com.springbook.member.service.MemberService;
import com.springbook.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public int getId(String id) {		
		return memberMapper.getId(id.toString());
	}

	@Override
	public int insertMember(MemberVO vo) {
		return memberMapper.insertMember(vo);
	}

	@Override
	public MemberVO tryLogin(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberMapper.tryLogin(vo);
	}

	@Override
	public int memberListCount(String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();

		map.put("type", type);
		map.put("keyword", keyword);
		
		return memberMapper.memberListCount(map);
	}

	@Override
	public List<MemberVO> getMemberList(int i, int contentnum,String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("type", type);
		map.put("keyword", keyword);
		
		return memberMapper.getMemberList(map);
	}
	
	@Override
	public MemberVO getMemberInfo(String id) {
		// TODO Auto-generated method stub
		return memberMapper.getMemberInfo(id);
	}

	@Override
	public List<MemberVO> getTypeProductList(int i, int contentnum, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", keyword);
		
		return memberMapper.getTypeProductList(map);
	}

	@Override
	public int typeProductListCount(String keyword) {
		// TODO Auto-generated method stub
		return memberMapper.typeProductListCount(keyword);
	}

	@Override
	public List<String> getProductNameList() {
		// TODO Auto-generated method stub
		return memberMapper.getProductNameList();
	}

	@Override
	public int addPrice(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
		return memberMapper.addPrice(string);
	}

	@Override
	public int memberDelete(String string) {
		// TODO Auto-generated method stub
		return memberMapper.memberDelete(string);
	}

	@Override
	public int initMoneyInfo(MemberVO vo) {
		return memberMapper.initMoneyInfo(vo);
	}

}
