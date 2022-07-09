package com.springbook.member.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.springbook.member.mapper.MemberMapper;
import com.springbook.member.service.MemberService;
import com.springbook.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

	private final MemberMapper memberMapper;	
	private final PasswordEncoder passwordEncoder;

	@Override
	public int getId(String id) {		
		return memberMapper.getId(id.toString());
	}

	@Override
	public int insertMember(MemberVO vo) {		
		vo.setPassword(passwordEncoder.encode("0000"));
		return memberMapper.insertMember(vo);
	}

	@Override
	public boolean tryLogin(MemberVO vo) {
		MemberVO member = memberMapper.tryLogin(vo.getId());
		
		//id가 틀린경우 
		if(member == null){
			return false;
		}
		// 원본패스워드, 암호화패스워드 비교
		return passwordEncoder.matches(vo.getPassword(),member.getPassword() );  
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
	public int addPrice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return memberMapper.addPrice(map);
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

	@Override
	public int selectPrice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return memberMapper.selectPrice(map);
	}

	@Override
	public int updatePrice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return memberMapper.updatePrice(map);
	}

	@Override
	public MemberVO checkEmailBy(String userId) {
		if(isEmail(userId))
			return memberMapper.findMemberByEmail(userId);
		else
			return memberMapper.findMemberById(userId);
	}

	private boolean isEmail(String userId) {
		if(userId.contains("@"))
			return true;
		else{
			return false; 
		}
	}

	@Override
	public int modifyPassword(MemberVO member) {
		return memberMapper.modfiyPassword(
				member.getId(), 
				passwordEncoder.encode(member.getPassword()));
	}
	

}
