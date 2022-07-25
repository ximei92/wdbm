package com.springbook.member.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springbook.member.mapper.MemberMapper;
import com.springbook.member.service.MemberService;
import com.springbook.member.vo.MemberVO;
import com.springbook.security.base64.DataScrty;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {


	private final SessionManager sessionManager; 
	private final MemberMapper memberMapper;
	private final String DEFUALT_PW = "0000";
	// private final PasswordEncoder passwordEncoder;

	@Override
	public int getId(String id) {
		return memberMapper.getId(id.toString());
	}

	@Override
	public int insertMember(MemberVO vo) throws Exception {
		// vo.setPassword(passwordEncoder.encode("0000"));
		vo.setPassword(DataScrty.encryptPassword(DEFUALT_PW, vo.getId()));
		return memberMapper.insertMember(vo);
	}

	@Override
	public ResponseEntity<MemberVO> tryLogin(MemberVO vo) throws Exception {
		String enpassword = DataScrty.encryptPassword(vo.getPassword(),vo.getId());
		vo.setPassword(enpassword);
		MemberVO member = memberMapper.tryLogin(vo);
		
		log.info("member={}",member);
		if(hasMember(member)){
			log.debug("성공");
			return new ResponseEntity<MemberVO>(member,HttpStatus.OK); 
		} else {
			log.debug("실패");
			return new ResponseEntity<MemberVO>(HttpStatus.BAD_REQUEST);
		}
		
		

	}

	private boolean hasMember(MemberVO member) {
		if (member != null &&!member.getId().equals("")) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public int memberListCount(String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("type", type);
		map.put("keyword", keyword);

		return memberMapper.memberListCount(map);
	}

	@Override
	public List<MemberVO> getMemberList(int i, int contentnum, String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();

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
		Map<String, Object> map = new HashMap<String, Object>();

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
		return memberMapper.findMemberById(userId);
		/*if (isEmail(userId))
			return memberMapper.findMemberByEmail(userId);
		else
			return memberMapper.findMemberById(userId);*/
	}

	/**
	 * userId인지 이메일인지 체크
	 * @param userId
	 * @return
	 */
	/*private boolean isEmail(String userId) {
		if (userId.contains("@"))
			return true;
		else {
			return false;
		}
	}*/

	@Override
	public void modifyPassword(MemberVO member) throws Exception {
		String enpassword = DataScrty.encryptPassword(member.getPassword(),member.getId());
		int result = memberMapper.modfiyPassword(member.getId(), enpassword);
		log.debug("modify passwrod result = {}", result);
	}

}
