package com.springbook.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springbook.member.vo.MemberVO;

@Mapper
public interface MemberMapper {

	int getId(String id);

	int insertMember(MemberVO vo);
	
	MemberVO tryLogin(MemberVO vo);
	
	int memberListCount(Map<String, Object> map);

	List<MemberVO> getMemberList(Map<String, Object> map);

	MemberVO getMemberInfo(String id);

	List<String> getProductNameList();

	int typeProductListCount(String keyword);

	List<MemberVO> getTypeProductList(Map<String, Object> map);

	int addPrice(Map<String, Object> map);

	int memberDelete(String string);

	int initMoneyInfo(MemberVO vo);

	int selectPrice(Map<String, Object> map);

	int updatePrice(Map<String, Object> map);
	
	MemberVO findMemberById(@Param("userId")String userId);

	/*MemberVO findMemberByEmail(@Param("email")String email);*/

	int modfiyPassword(@Param("id")String id, @Param("password")String encodedPassword);


}
