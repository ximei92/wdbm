package com.springbook.etc.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.springbook.etc.vo.*;
import com.springbook.member.vo.MemberVO;

public interface EtcService {

	int insertAsk(JoinAskVO vo);

	int inquiryListCount(String keyword);

	List<JoinAskVO> getInquiryList(int i, int contentnum, String keyword);

	List<JoinAskVO> getInquiryDetail(String idx);

	int updateInquiry(String info, String idx);

	List<ProductVO> getProductList(int i, int contentnum, String type, String keyword);

	int productListCount(String type, String keyword);

	int addProductType(ProductVO vo);

	int typeDelete(String list);

	List<Map<String, Object>> getThicknessList(int i, int contentnum, String type, String keyword);

	int thicknessListCount(String type, String keyword);

	List<ProductVO> getProductNameList();

	int addProductThickness(ProductThicknessVO vo);

	int thicknessDelete(String list);

	int sizeListCount(String type, String keyword);

	List<String> getSizeList(int i, int contentnum, String type, String keyword);

	int addProductSize(ProductSizeVO vo);

	int sizeDelete(String string);

	int detailListCount(String type, String keyword);

	List<String> getDetailList(int i, int contentnum, String type, String keyword);

	List<Map> selectDetailThickness(String name);

	List<Map> selectDetailSize(String name);

	int addProductDetail(ProductDetailVO vo);

	int detailDelete(String string);

	int checkNew(String num);

	int warehouseListCount();

	List<WarehouseVO> getWarehouseList(int i, int contentnum);

	List<String> inventoryInfo();

	int updateInventoryStock(WarehouseInventoryVO vo);

	int insertInventoryStock(WarehouseInventoryVO vo);
	
	List<WarehouseStockVO> inventoryStockInfo(WarehouseInventoryVO vo);

	int insertInventoryInfo(WarehouseInventoryVO vo);

	List<ProductVO> getProductInfo(String idx);

	int updateProductType(ProductVO vo);

	List<Map<String, Object>> getThicknessInfo(String idx);

	int updateProductThickness(ProductThicknessVO vo);

	List<Map<String, Object>> getSizeInfo(String idx);
	
	List<Map<String, Object>> getDetailInfo(String idx);

	int updateProductDetail(com.springbook.etc.vo.ProductDetailVO vo);

	int safetyListCount(String key);

	List<Map<String, Object>> getSafetyList(int i, int contentnum, String key);

	int creditListCount(String keyword, Date startDt, Date endDt);

	List<MemberVO> memberList();

	int inventoryHistoryCount(String productCd, String warehouseIdx, String thickness, String size);

	int selectCompanyCount(String keyword);

	List<MemberVO> getSelectCompany(int i, int contentnum, String keyword);

	Map<String, Object> searchCreditInfo(String keyword);

	int addDepositHistory(Map<String, Object> map);

	int updateTotalDeposit(Map<String, Object> map);

	int historyDelete(String string);

	Map<String, String> searchDepositInfo(String idx);

	int updateDepositHistory(Map<String, Object> map);

	List<Map<String, Object>> getCreditList(int i, int contentnum, String keyword, Date startDt, Date endDt);

	int updateProductSize(ProductSizeVO vo);

	List<Map<String, Object>> getInventoryHistory(int i, int contentnum, String productCd, String warehouseIdx,
			String thickness, String size);

	List<Map<String, Object>> getInventoryHistoryAll(int i, int contentnum, String productCd, String warehouseIdx,
			String thickness, String size);

	int inventoryHistoryAllCount();

	int inventoryStatusCount(String productCd, String warehouseIdx);

	List<Map<String, Object>> getInventoryStatus(int i, int contentnum, String productCd, String warehouseIdx);

	List<ProductVO> getProductNameListGroup();

	List<ProductDetailVO> findProductDetail(ProductDetailVO vo);

	int addWarehouse(String fileId, String warehouse);

	WarehouseVO selectWarehouse(String fileId, String warehouse);

	int addWarehouseFee(List<Map<String, Object>> insertFeeList);

	WarehouseVO getWarehoseInfo(String warehouseIdx);

	int updateWarehouse(String fileId, String warehouse, String warehouseIdx);

	int deleteWarehouseFee(String warehouseIdx);

	int warehouseDelete(String string);

	Map<String, Object> memberInfoDeposit(String keyword);

}
