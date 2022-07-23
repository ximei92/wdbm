package com.springbook.vo;

import java.io.Serializable;

public class ProductVO {

	private int PRODUCT_IDX;		//
	private String PRODUCT_NM;   	//제품명
	private String PRODUCT_CD;   	//제품명코드
	private String TYPE;			//사업자구분
	private String FILE_ID;			//시험성적서파일
	private String FILE_NM;			//파일명
	private String REG_DATE;		//등록일
	private String DEL_YN;			//삭제여부
	
	private String THICKNESS;		//두께
	private String SIZE;			//사이즈
	private String HEIGHT;			//길이
	private String WIDTH;			//폭
	private String SAFETY;			//안전재고수량
	private String PALLET;			//파렛당장수
	private int STOCK;				//재고
	private String SLICE;			//컨테이너당장수
	
	private String CUSTOM_ORDER;	//주문제작 여부
	private String CUSTOM_ORDER_NM;	//주문제작 여부
    
	private String TEST_INSTITUTE;	//발급기관
	private String TEST_NUM;		//발급번호
	
	private int SIZE_IDX;
	private int THICKNESS_IDX;
	private int DETAIL_IDX;
	
	public int getPRODUCT_IDX() {
		return PRODUCT_IDX;
	}
	public void setPRODUCT_IDX(int pRODUCT_IDX) {
		PRODUCT_IDX = pRODUCT_IDX;
	}
	public String getPRODUCT_NM() {
		return PRODUCT_NM;
	}
	public void setPRODUCT_NM(String pRODUCT_NM) {
		PRODUCT_NM = pRODUCT_NM;
	}
	public String getPRODUCT_CD() {
		return PRODUCT_CD;
	}
	public void setPRODUCT_CD(String pRODUCT_CD) {
		PRODUCT_CD = pRODUCT_CD;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getFILE_ID() {
		return FILE_ID;
	}
	public void setFILE_ID(String fILE_ID) {
		FILE_ID = fILE_ID;
	}
	public String getFILE_NM() {
		return FILE_NM;
	}
	public void setFILE_NM(String fILE_NM) {
		FILE_NM = fILE_NM;
	}
	public String getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(String rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	public String getDEL_YN() {
		return DEL_YN;
	}
	public void setDEL_YN(String dEL_YN) {
		DEL_YN = dEL_YN;
	}
	public String getTHICKNESS() {
		return THICKNESS;
	}
	public void setTHICKNESS(String tHICKNESS) {
		THICKNESS = tHICKNESS;
	}
	public String getSIZE() {
		return SIZE;
	}
	public void setSIZE(String sIZE) {
		SIZE = sIZE;
	}
	public String getHEIGHT() {
		return HEIGHT;
	}
	public void setHEIGHT(String hEIGHT) {
		HEIGHT = hEIGHT;
	}
	public String getWIDTH() {
		return WIDTH;
	}
	public void setWIDTH(String wIDTH) {
		WIDTH = wIDTH;
	}
	public String getSAFETY() {
		return SAFETY;
	}
	public void setSAFETY(String sAFETY) {
		SAFETY = sAFETY;
	}
	public String getPALLET() {
		return PALLET;
	}
	public void setPALLET(String pALLET) {
		PALLET = pALLET;
	}
	public int getSTOCK() {
		return STOCK;
	}
	public void setSTOCK(int sTOCK) {
		STOCK = sTOCK;
	}
	public String getSLICE() {
		return SLICE;
	}
	public void setSLICE(String sLICE) {
		SLICE = sLICE;
	}
	public String getCUSTOM_ORDER() {
		return CUSTOM_ORDER;
	}
	public void setCUSTOM_ORDER(String cUSTOM_ORDER) {
		CUSTOM_ORDER = cUSTOM_ORDER;
	}
	public String getTEST_INSTITUTE() {
		return TEST_INSTITUTE;
	}
	public void setTEST_INSTITUTE(String tEST_INSTITUTE) {
		TEST_INSTITUTE = tEST_INSTITUTE;
	}
	public String getTEST_NUM() {
		return TEST_NUM;
	}
	public void setTEST_NUM(String tEST_NUM) {
		TEST_NUM = tEST_NUM;
	}
	public String getCUSTOM_ORDER_NM() {
		return CUSTOM_ORDER_NM;
	}
	public void setCUSTOM_ORDER_NM(String cUSTOM_ORDER_NM) {
		CUSTOM_ORDER_NM = cUSTOM_ORDER_NM;
	}
	public int getSIZE_IDX() {
		return SIZE_IDX;
	}
	public void setSIZE_IDX(int sIZE_IDX) {
		SIZE_IDX = sIZE_IDX;
	}
	public int getTHICKNESS_IDX() {
		return THICKNESS_IDX;
	}
	public void setTHICKNESS_IDX(int tHICKNESS_IDX) {
		THICKNESS_IDX = tHICKNESS_IDX;
	}
	public int getDETAIL_IDX() {
		return DETAIL_IDX;
	}
	public void setDETAIL_IDX(int dETAIL_IDX) {
		DETAIL_IDX = dETAIL_IDX;
	}
	
}
