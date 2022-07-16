package com.springbook.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.springbook.member.vo.MemberVO;

public interface MemberService {

	int getId(String id);

	int insertMember(MemberVO vo) throws Exception;

	ResponseEntity<MemberVO> tryLogin(MemberVO vo) throws Exception;

	int memberListCount(String type, String keyword);

	List<MemberVO> getMemberList(int i, int contentnum,String type, String keyword);

	MemberVO getMemberInfo(String id);

	List<MemberVO> getTypeProductList(int i, int contentnum, String keyword);

	int typeProductListCount(String keyword);

	List<String> getProductNameList();

	int addPrice(Map<String, Object> map);

	int memberDelete(String string);

	int initMoneyInfo(MemberVO vo);

	int selectPrice(Map<String, Object> map);

	int updatePrice(Map<String, Object> map);

	MemberVO checkEmailBy(String userId);

	int modifyPassword(MemberVO member) throws Exception;


}
