package com.springbook.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbook.configuration.Page;
import com.springbook.member.service.MemberService;
import com.springbook.member.vo.MemberVO;
import com.springbook.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private FileService fileService;

	@Autowired(required = false)
	private PasswordEncoder encoder;

	@PostMapping("/checkId.do")
	@ResponseBody
	public int checkId(MemberVO vo) {
		int cnt = memberService.getId(vo.getId());
		return cnt;
	}

	@PostMapping("/addMember.do")
	@ResponseBody
	public int addMember(MemberVO vo) throws Exception {
		if(vo.getCreditAmount() != null){
			vo.setCreditAmount(vo.getCreditAmount().replace(",", ""));
		}
		int result = memberService.insertMember(vo);

//		if (vo.getRole().equals("1")) {
//			// 거래처일경우 초기 여신정보 넣어주기
//			memberService.initMoneyInfo(vo);
//		}
		return result;
	}

	@PostMapping("/tryLogin.do")
	@ResponseBody
	public ResponseEntity<MemberVO> tryLogin(HttpServletResponse response, MemberVO vo) throws Exception {
		log.info("vo = {}", vo.toString());
	return memberService.tryLogin(vo);
	}

	@PostMapping("/getMemberInfo.do")
	@ResponseBody
	public String getMemberInfo(Model model, String id) {

		MemberVO result = memberService.getMemberInfo(id);
		String type = result.getRole();

		return type;
	}

	@GetMapping("/memberList.do")
	public String memberList(Model model, String pagenum, String contentnum, String type, String keyword) {
		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;

		if (pagenum == null || pagenum.length() == 0) {
			cpagenum = 1;
		} else {
			cpagenum = Integer.parseInt(pagenum);
		}

		if (contentnum == null || contentnum.length() == 0) {
			ccontentnum = 10;
		} else {
			ccontentnum = Integer.parseInt(contentnum);
		}
		
		System.out.println(cpagenum+"//////////"+ ccontentnum);

		pagemaker.setTotalcount(memberService.memberListCount(type, keyword)); // mapper
																				
		pagemaker.setPagenum(cpagenum - 1); // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서
											// 사용할수 있다
		pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
		pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서
												// 지정한다.
		pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글
															// 수를 통해서 정한다.

		pagemaker.prevnext(cpagenum);// 현재 페이지 번호로 화살표를 나타낼지 정한다.
		pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지
																// 블록번호로 정한다.
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
		// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
		if (ccontentnum == 10) {// 선택 게시글 수
			List<MemberVO> list = memberService.getMemberList(pagemaker.getPagenum() * 10, pagemaker.getContentnum(),
					type, keyword);
			System.out.println(list);
			model.addAttribute("list", list);
		} else if (ccontentnum == 20) {
			List<MemberVO> list = memberService.getMemberList(pagemaker.getPagenum() * 20, pagemaker.getContentnum(),
					type, keyword);
			model.addAttribute("list", list);
		} else if (ccontentnum == 30) {
			List<MemberVO> list = memberService.getMemberList(pagemaker.getPagenum() * 30, pagemaker.getContentnum(),
					type, keyword);
			model.addAttribute("list", list);
		}

		System.out.println(pagemaker.getEndPage()+"getEnd");
		System.out.println(pagemaker.getLastblock()+"getLastBlock");
		System.out.println(pagemaker.getPagenum()+"getPagenum");
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", pagemaker);
		model.addAttribute("type", type);

		return "member_manage/member_list";
	}

	@GetMapping(value = "/priceSetting.do")
	public String priceSetting(Model model, String pagenum, String contentnum, String keyword, String id) {
		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;

		if (keyword == null || keyword.length() == 0) {
			List<String> productNameList = memberService.getProductNameList();
			model.addAttribute("productNameList", productNameList);
			String[] list = productNameList.toString().split(",");
			keyword = list[0].replace("[", "");
		}

		if (pagenum == null || pagenum.length() == 0) {
			cpagenum = 1;
		} else {
			cpagenum = Integer.parseInt(pagenum);
		}

		if (contentnum == null || contentnum.length() == 0) {
			ccontentnum = 10;
		} else {
			ccontentnum = Integer.parseInt(contentnum);
		}
		System.out.println("MEMBER CONTROLLLER");
		System.out.println(keyword);
		pagemaker.setTotalcount(memberService.typeProductListCount(keyword)); // mapper
																				// 전체
																				// 게시글
																				// 개수를
																				// 지정한다
		pagemaker.setPagenum(cpagenum - 1); // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서
											// 사용할수 있다
		pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
		pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서
												// 지정한다.
		pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글
															// 수를 통해서 정한다.

		pagemaker.prevnext(cpagenum);// 현재 페이지 번호로 화살표를 나타낼지 정한다.
		pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지
																// 블록번호로 정한다.
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
		// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
		if (ccontentnum == 10) {// 선택 게시글 수
			List<MemberVO> list = memberService.getTypeProductList(pagemaker.getPagenum() * 10,
			pagemaker.getContentnum(), keyword);
			model.addAttribute("list", list);
		} else if (ccontentnum == 20) {
			List<MemberVO> list = memberService.getTypeProductList(pagemaker.getPagenum() * 20,
			pagemaker.getContentnum(), keyword);
			model.addAttribute("list", list);
		} else if (ccontentnum == 30) {
			List<MemberVO> list = memberService.getTypeProductList(pagemaker.getPagenum() * 30,
			pagemaker.getContentnum(), keyword);
			model.addAttribute("list", list);
		}

		model.addAttribute("keyword", keyword);
		model.addAttribute("page", pagemaker);

		return "member_manage/price_setting";
	}

	@PostMapping(value = "/choicePriceSetting.do")
	@ResponseBody
	public Map<String, Object> choicePriceSetting(String pagenum, String contentnum, String keyword) {
		Page pagemaker = new Page();
		int cpagenum;
		int ccontentnum;
		Map<String, Object> map = new HashMap<String, Object>();
		// if(keyword == null || keyword.length() == 0){
		// List<String> productNameList = memberService.getProductNameList();
		//
		// String[] list = productNameList.toString().split(",");
		// keyword = list[0].replace("[", "");
		// }

		if (pagenum == null || pagenum.length() == 0) {
			cpagenum = 1;
		} else {
			cpagenum = Integer.parseInt(pagenum);
		}

		if (contentnum == null || contentnum.length() == 0) {
			ccontentnum = 10;
		} else {
			ccontentnum = Integer.parseInt(contentnum);
		}

		System.out.println(keyword + "popupkeyword");
		pagemaker.setTotalcount(memberService.typeProductListCount(keyword)); // mapper
																				// 전체
																				// 게시글
																				// 개수를
																				// 지정한다
		pagemaker.setPagenum(cpagenum - 1); // 현재 페이지를 페이지 객체에 지정한다 -1 을 해야 쿼리에서
											// 사용할수 있다
		pagemaker.setContentnum(ccontentnum); // 한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
		pagemaker.setCurrentblock(cpagenum); // 현재 페이지 블록이 몇번인지 현재 페이지 번호를 통해서
												// 지정한다.
		pagemaker.setLastblock(pagemaker.getTotalcount()); // 마지막 블록 번호를 전체 게시글
															// 수를 통해서 정한다.

		pagemaker.prevnext(cpagenum);// 현재 페이지 번호로 화살표를 나타낼지 정한다.
		pagemaker.setStartPage(pagemaker.getCurrentblock()); // 시작 페이지를 페이지
																// 블록번호로 정한다.
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
		// 마지막 페이지를 마지막 페이지 블록과 현재 페이지 블록 번호로 정한다.
		if (ccontentnum == 10) {// 선택 게시글 수
			List<MemberVO> list = memberService.getTypeProductList(pagemaker.getPagenum() * 10,
					pagemaker.getContentnum(), keyword);
			map.put("list", list);
			System.out.println(list);
		} else if (ccontentnum == 20) {
			List<MemberVO> list = memberService.getTypeProductList(pagemaker.getPagenum() * 20,
					pagemaker.getContentnum(), keyword);
			map.put("list", list);
		} else if (ccontentnum == 30) {
			List<MemberVO> list = memberService.getTypeProductList(pagemaker.getPagenum() * 30,
					pagemaker.getContentnum(), keyword);
			map.put("list", list);
		}
		map.put("page", pagemaker);

		return map;
	}

	/* 사용자별 제품 가격설정 */
	@PostMapping("/addPrice.do")
	@ResponseBody
	public int addPrice(@RequestBody List<Map<String, Object>> map) {

		System.out.println(map.get(0).get("price"));

		int result = 0;

		for (int i = 0; i < map.size(); i++) {
			int update = memberService.selectPrice(map.get(i));
			System.out.println(update + "update결과...!");
			if (update == 1) {
				System.out.println(map.get(i));
				result += memberService.updatePrice(map.get(i));
				System.out.println(result);
			} else {
				result += memberService.addPrice(map.get(i));
			}

		}

		int returnVal = 0;
		if (result != map.size()) {
			returnVal = 1;
		}
		return returnVal;
	}

	/* 멤버 삭제 */
	@PostMapping("/memberDelete.do")
	@ResponseBody
	public int memberDelete(HttpServletRequest request) {
		System.out.println("delete");
		String[] idxList = request.getParameterValues("idxList");

		int result = 0;
		for (int i = 0; i < idxList.length; i++) {
			result += memberService.memberDelete(idxList[i]);
		}

		int returnVal = 0;
		if (result != idxList.length) {
			returnVal = 1;
		}

		return returnVal;
	}

	@ResponseBody
	@GetMapping("/email/check/{userId}")
	public MemberVO checkUser(@PathVariable String userId, Model model) {
		log.info("userId ={}", userId);
		return memberService.checkEmailBy(userId);
	}

}
