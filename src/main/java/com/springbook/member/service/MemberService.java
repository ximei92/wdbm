package com.springbook.member.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springbook.member.vo.MemberVO;

public interface MemberService {

	int getId(String id);

	int insertMember(MemberVO vo);

	MemberVO tryLogin(MemberVO vo);

	int memberListCount(String type, String keyword);

	List<MemberVO> getMemberList(int i, int contentnum,String type, String keyword);

	MemberVO getMemberInfo(String id);

	List<MemberVO> getTypeProductList(int i, int contentnum, String keyword);

	int typeProductListCount(String keyword);

	List<String> getProductNameList();

	int addPrice(String string);

	int memberDelete(String string);

	int initMoneyInfo(MemberVO vo);

	String findEmailBy(String memberId);

	String checkEmail(String email);

}
