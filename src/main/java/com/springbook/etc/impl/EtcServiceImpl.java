package com.springbook.etc.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.etc.mapper.EtcMapper;
import com.springbook.etc.service.EtcService;
import com.springbook.etc.vo.CreditVO;
import com.springbook.etc.vo.JoinAskVO;
import com.springbook.etc.vo.ProductSizeVO;
import com.springbook.etc.vo.ProductThicknessVO;
import com.springbook.etc.vo.ProductVO;
import com.springbook.etc.vo.WarehouseInventoryVO;
import com.springbook.etc.vo.WarehouseStockVO;
import com.springbook.etc.vo.WarehouseVO;
import com.springbook.etc.vo.ProductDetailVO;
import com.springbook.member.vo.MemberVO;

@Service
public class EtcServiceImpl implements EtcService {
	@Autowired
	private EtcMapper etcMapper;

	@Override
	public int insertAsk(JoinAskVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.insertAsk(vo);
	}

	@Override
	public List<JoinAskVO> getInquiryList(int i, int contentnum, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", keyword);
		
		return etcMapper.getInquiryList(map);
	}

	@Override
	public int inquiryListCount(String keyword) {
		// TODO Auto-generated method stub
		return etcMapper.inquiryListCount(keyword);
	}

	@Override
	public List<JoinAskVO> getInquiryDetail(String idx) {
		// TODO Auto-generated method stub
		return etcMapper.getInquiryDetail(idx);
	}

	@Override
	public int updateInquiry(String info, String idx) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("info", info);
		map.put("idx", idx);
		return etcMapper.updateInquiry(map);
	}

	@Override
	public List<ProductVO> getProductList(int i, int contentnum, String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", keyword);
		map.put("type", type);
		
		return etcMapper.getProductList(map);
	}

	@Override
	public int productListCount(String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("type", type);
		map.put("keyword", keyword);
		return etcMapper.productListCount(map);
	}

	@Override
	public int addProductType(ProductVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.addProductType(vo);
	}

	@Override
	public int typeDelete(String list) {
		return etcMapper.typeDelete(list);
	}

	@Override
	public List<ProductThicknessVO> getThicknessList(int i, int contentnum, String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", keyword);
		map.put("type", type);
		
		return etcMapper.getThicknessList(map);
	}

	@Override
	public int thicknessListCount(String type, String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("type", type);
		map.put("keyword", keyword);
		return etcMapper.thicknessListCount(map);
	}

	@Override
	public List<ProductVO> getProductNameList() {
		// TODO Auto-generated method stub
		return etcMapper.getProductNameList();
	}

	@Override
	public int addProductThickness(ProductThicknessVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.addProductThickness(vo);
	}

	@Override
	public int thicknessDelete(String list) {
		// TODO Auto-generated method stub
		return etcMapper.thicknessDelete(list);
	}

	@Override
	public int sizeListCount(String type, String keyword) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("type", type);
		map.put("keyword", keyword);
		return etcMapper.sizeListCount(map);
	}

	@Override
	public List<String> getSizeList(int i, int contentnum, String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", keyword);
		map.put("type", type);
		
		return etcMapper.getSizeList(map);
	}

	@Override
	public int addProductSize(ProductSizeVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.addProductSize(vo);
	}

	@Override
	public int sizeDelete(String string) {
		// TODO Auto-generated method stub
		return etcMapper.sizeDelete(string);
	}

	@Override
	public int detailListCount(String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("type", type);
		map.put("keyword", keyword);
		return etcMapper.detailListCount(map);
	}

	@Override
	public List<String> getDetailList(int i, int contentnum, String type, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", keyword);
		map.put("type", type);
		
		return etcMapper.getDetailList(map);
	}

	@Override
	public List<Map> selectDetailThickness(String name) {
		// TODO Auto-generated method stub
		System.out.println("====="+name);
		return etcMapper.selectDetailThickness(name);
	}

	@Override
	public List<Map> selectDetailSize(String name) {
		// TODO Auto-generated method stub
		return etcMapper.selectDetailSize(name);
	}

	@Override
	public int addProductDetail(ProductDetailVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.addProductDetail(vo);
	}

	@Override
	public int detailDelete(String string) {
		// TODO Auto-generated method stub
		return etcMapper.detailDelete(string);
	}

	@Override
	public int checkNew(String num) {
		// TODO Auto-generated method stub
		return etcMapper.checkNew(num);
	}

	@Override
	public int warehouseListCount() {
		// TODO Auto-generated method stub
		return etcMapper.warehouseListCount();
	}

	@Override
	public List<WarehouseVO> getWarehouseList(int i, int contentnum) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		
		return etcMapper.getWarehouseList(map);
	}

	@Override
	public List<String> inventoryInfo() {
		// TODO Auto-generated method stub
		return etcMapper.inventoryInfo();
	}

	@Override
	public int updateInventoryStock(WarehouseInventoryVO vo) {
		// TODO Auto-generated method stub
		System.out.println("imple");
		System.out.println(vo);
		return etcMapper.updateInventoryStock(vo);
	}

	@Override
	public List<WarehouseStockVO> inventoryStockInfo(WarehouseInventoryVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.inventoryStockInfo(vo);
	}
	
	@Override
	public int insertInventoryStock(WarehouseInventoryVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.insertInventoryStock(vo);
	}
	
	@Override
	public int insertInventoryInfo(WarehouseInventoryVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.insertInventoryInfo(vo);
	}

	@Override
	public List<ProductVO> getProductInfo(String idx) {
		// TODO Auto-generated method stub
		return etcMapper.getProductInfo(idx);
	}

	@Override
	public int updateProductType(ProductVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.updateProductType(vo);
	}

	@Override
	public List<ProductThicknessVO> getThicknessInfo(String idx) {
		// TODO Auto-generated method stub
		return etcMapper.getThicknessInfo(idx);
	}

	@Override
	public int updateProductThickness(ProductThicknessVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.updateProductThickness(vo);
	}

	@Override
	public List<ProductDetailVO> getDetailInfo(String idx) {
		// TODO Auto-generated method stub
		return etcMapper.getDetailInfo(idx);
	}

	@Override
	public int updateProductDetail(ProductDetailVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.updateProductDetail(vo);
	}

	@Override
	public int safetyListCount(String key) {
		// TODO Auto-generated method stub
		return etcMapper.safetyListCount(key);
	}

	@Override
	public List<ProductDetailVO> getSafetyList(int i, int contentnum, String key) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", key);
		
		return etcMapper.getSafetyList(map);
	}

	@Override
	public int creditListCount(String keyword, Date startDt, Date endDt) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();

		map.put("keyword", keyword);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		
		return etcMapper.creditListCount(map);
	}

	@Override
	public List<MemberVO> memberList() {
		// TODO Auto-generated method stub
		return etcMapper.memberList();
	}

	@Override
	public List<WarehouseInventoryVO> getInventoryHistory(int i, int contentnum) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
//		map.put("keyword", keyword);
		
		return etcMapper.getInventoryHistory(map);
	}

	@Override
	public int inventoryHistoryCount() {
		// TODO Auto-generated method stub
		return etcMapper.inventoryHistoryCount();
	}

	@Override
	public int selectCompanyCount(String keyword) {
		// TODO Auto-generated method stub
		return etcMapper.selectCompanyCount(keyword);
	}

	@Override
	public List<MemberVO> getSelectCompany(int i, int contentnum, String keyword) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", keyword);
		
		return etcMapper.getSelectCompany(map);
	}

	@Override
	public Map<String, Object> searchCreditInfo(String keyword) {
		// TODO Auto-generated method stub
		return etcMapper.searchCreditInfo(keyword);
	}

	@Override
	public int addDepositHistory(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return etcMapper.addDepositHistory(map);
	}

	@Override
	public int updateTotalDeposit(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("updateTotalDDDD===");
		return etcMapper.updateTotalDeposit(map);
	}

	@Override
	public int historyDelete(String string) {
		// TODO Auto-generated method stub
		return etcMapper.historyDelete(string);
	}

	@Override
	public Map<String, String> searchDepositInfo(String idx) {
		// TODO Auto-generated method stub
		return etcMapper.searchDepositInfo(idx);
	}

	@Override
	public int updateDepositHistory(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("updateTotalDDDD===++++++++++++++++++++++++++++");
		return etcMapper.updateDepositHistory(map);
	}

	@Override
	public List<Map<String, Object>> getCreditList(int i, int contentnum, String keyword, Date startDt, Date endDt) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("keyword", keyword);
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		
		return etcMapper.getCreditList(map);
	}


}
