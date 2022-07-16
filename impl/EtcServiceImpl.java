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
	public List<Map<String, Object>> getThicknessList(int i, int contentnum, String type, String keyword) {
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
	public List<ProductDetailVO> findProductDetail(ProductDetailVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.findProductDetail(vo);
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
	public List<Map<String, Object>> getThicknessInfo(String idx) {
		// TODO Auto-generated method stub
		return etcMapper.getThicknessInfo(idx);
	}

	@Override
	public int updateProductThickness(ProductThicknessVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.updateProductThickness(vo);
	}

	@Override
	public List<Map<String, Object>> getSizeInfo(String idx) {
		// TODO Auto-generated method stub
		return etcMapper.getSizeInfo(idx);
	}
	
	@Override
	public List<Map<String, Object>> getDetailInfo(String idx) {
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
	public List<Map<String, Object>> getSafetyList(int i, int contentnum, String productIdx) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("productIdx", productIdx);
		
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
	public List<Map<String,Object>> getInventoryHistory(int i, int contentnum, String productIdx, String warehouseIdx,String thickness, String size) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		//검색조건 null값은 공백처리
		if(productIdx == null || productIdx.equals("")){
			productIdx ="null0";
		}
		if(warehouseIdx == null || warehouseIdx.equals("")){
			warehouseIdx ="null0";
		}
		if(thickness == null || thickness.equals("")){
			thickness ="null0";
		}
		if(size == null || size.equals("")){
			size ="null0";
		}
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("productIdx", productIdx);
		map.put("warehouseIdx", warehouseIdx);
		map.put("thickness", thickness);
		map.put("size", size);

		return etcMapper.getInventoryHistory(map);
	}

	//검색조건이 있을 떄 창고 재고 갯수조회
	@Override
	public int inventoryHistoryCount( String productIdx, String warehouseIdx,String thickness, String size) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		if(productIdx == null || productIdx.equals("")){
			productIdx ="null0";
		}
		if(warehouseIdx == null || warehouseIdx.equals("")){
			warehouseIdx ="null0";
		}
		if(thickness == null || thickness.equals("")){
			thickness ="null0";
		}
		if(size == null || size.equals("")){
			size ="null0";
		}
		
		map.put("productIdx", productIdx);
		map.put("warehouseIdx", warehouseIdx);
		map.put("thickness", thickness);
		map.put("size", size);
		return etcMapper.inventoryHistoryCount(map);
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
		System.out.println("ddddd");
		System.out.println(map);
		return etcMapper.getCreditList(map);
	}

	@Override
	public int updateProductSize(ProductSizeVO vo) {
		// TODO Auto-generated method stub
		return etcMapper.updateProductSize(vo);
	}

	@Override
	public List<Map<String, Object>> getInventoryHistoryAll(int i, int contentnum, String productIdx,
			String warehouseIdx, String thickness, String size) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("i", i);
		map.put("contentnum", contentnum);
		
		return etcMapper.getInventoryHistoryAll(map);
	}

	@Override
	public int inventoryHistoryAllCount() {
		// TODO Auto-generated method stub
		return etcMapper.inventoryHistoryAllCount();
	}

	@Override
	public int inventoryStatusCount(String productNm, String warehouseIdx) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("productNm", productNm);
		map.put("warehouseIdx", warehouseIdx);
		return etcMapper.inventoryStatusCount(map);
	}

	@Override
	public List<Map<String, Object>> getInventoryStatus(int i, int contentnum, String productNm, String warehouseIdx) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("i", i);
		map.put("contentnum", contentnum);
		map.put("productNm", productNm);
		map.put("warehouseIdx", warehouseIdx);
		return etcMapper.getInventoryStatus(map);
	}

	@Override
	public List<ProductVO> getProductNameListGroup() {
		// TODO Auto-generated method stub
		return etcMapper.getProductNameListGroup();
	}

	@Override
	public int addWarehouse(String fileId, String warehouse) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fileId", fileId);
		map.put("warehouse", warehouse);
		// TODO Auto-generated method stub
		return etcMapper.addWarehouse(map);
	}

	@Override
	public WarehouseVO selectWarehouse(String fileId, String warehouse) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fileId", fileId);
		map.put("warehouse", warehouse);
		// TODO Auto-generated method stub
		return etcMapper.selectWarehouse(map);
	}

	@Override
	public int addWarehouseFee(List<Map<String, Object>> insertFeeList) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("insertFeeList", insertFeeList);
		return etcMapper.addWarehouseFee(map);
	}

	@Override
	public WarehouseVO getWarehoseInfo(String warehouseIdx) {
		// TODO Auto-generated method stub
		return etcMapper.getWarehoseInfo(warehouseIdx);
	}

	@Override
	public int updateWarehouse(String fileId, String warehouse, String warehouseIdx) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fileId", fileId);
		map.put("warehouse", warehouse);
		map.put("warehouseIdx", warehouseIdx);
		return etcMapper.updateWarehouse(map);
	}

	@Override
	public int deleteWarehouseFee(String warehouseIdx) {
		// TODO Auto-generated method stub
		return etcMapper.deleteWarehouseFee(warehouseIdx);
	}

	@Override
	public int warehouseDelete(String string) {
		// TODO Auto-generated method stub
		return etcMapper.warehouseDelete(string);
	}

	@Override
	public Map<String, Object> memberInfoDeposit(String keyword) {
		// TODO Auto-generated method stub
		return etcMapper.memberInfoDeposit(keyword);
	}
}
