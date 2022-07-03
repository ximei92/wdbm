package com.springbook.etc.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

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

@Mapper
public interface EtcMapper {

	int insertAsk(JoinAskVO vo);

	int inquiryListCount(String keyword);
	
	List<JoinAskVO> getInquiryList(Map<String, Object> map);

	List<JoinAskVO> getInquiryDetail(String idx);

	int updateInquiry(Map<String, Object> map);

	List<ProductVO> getProductList(Map<String, Object> map);

	int productListCount(Map<String, Object> map);

	int addProductType(ProductVO vo);

	int typeDelete(String list);

	List<ProductThicknessVO> getThicknessList(Map<String, Object> map);

	int thicknessListCount(Map<String, Object> map);

	List<ProductVO> getProductNameList();

	int addProductThickness(ProductThicknessVO vo);

	int thicknessDelete(String list);

	int sizeListCount(Map<String, Object> map);

	List<String> getSizeList(Map<String, Object> map);

	int addProductSize(ProductSizeVO vo);

	int sizeDelete(String string);

	int detailListCount(Map<String, Object> map);

	List<String> getDetailList(Map<String, Object> map);

	List<Map> selectDetailThickness(String name);

	List<Map> selectDetailSize(String name);

	int addProductDetail(ProductDetailVO vo);

	int detailDelete(String string);

	int checkNew(String num);

	int warehouseListCount();

	List<WarehouseVO> getWarehouseList(Map<String, Object> map);

	List<String> inventoryInfo();

	int updateInventoryStock(WarehouseInventoryVO vo);
	
	int insertInventoryStock(WarehouseInventoryVO vo);

	List<WarehouseStockVO> inventoryStockInfo(WarehouseInventoryVO vo);

	int insertInventoryInfo(WarehouseInventoryVO vo);

	List<ProductVO> getProductInfo(String idx);

	int updateProductType(ProductVO vo);

	List<ProductThicknessVO> getThicknessInfo(String idx);

	int updateProductThickness(ProductThicknessVO vo);

	List<ProductDetailVO> getDetailInfo(String idx);

	int updateProductDetail(ProductDetailVO vo);

	int safetyListCount(String key);

	List<ProductDetailVO> getSafetyList(Map<String, Object> map);

	int creditListCount(Map<String, Object> map);

	List<Map<String, Object>> getCreditList(Map<String, Object> map);

	List<MemberVO> memberList();

	List<WarehouseInventoryVO> getInventoryHistory(Map<String, Object> map);

	int inventoryHistoryCount();

	int selectCompanyCount(String keyword);

	List<MemberVO> getSelectCompany(Map<String, Object> map);

	Map<String, Object> searchCreditInfo(String keyword);

	int addDepositHistory(Map<String, Object> map);

	int updateTotalDeposit(Map<String, Object> map);

	int historyDelete(String string);

	Map<String, String> searchDepositInfo(String idx);

	int updateDepositHistory(Map<String, Object> map);

}
