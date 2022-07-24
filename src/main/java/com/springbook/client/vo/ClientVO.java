package com.springbook.client.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientVO {
	/**
	 *
	 */
	private String warehouseIdx;   //시도명
	private String warehouseNm;   //시도명
	private String warehouse;   	//창고명
	private String fileId;			//파일
	private String sidoNm;			//시도명
	private String sigunguNm;		//시군구명
	private String eubmyeondongNm;	//읍면동명
	private	String ton;				//톤
	private	String fee;				//운임료
	private	String productIdx;		//제품번호
	private	String productNm;		//제품번호
	private	String thicknessIdx;		//두께
	private	String thickness;		//두께
	private	String thicknessCuntomOrder;		//두께
	private	String sizeIdx;			//사이즈
	private	String size;			//사이즈
	private	String sizeCustomOrder;
	private	String width;			
	private	String height;			//사이즈
	private String hb;
	private	int orderQt;				//수량
	private	int stock;				//수량
	private	int pgt;				//수량
	private String productQuery;
	private String orderYN;	//발주가능여부
	private String cost;	//운반비용
	private String dueDt;
	private String orderYn1;
	private String orderYn2;
	private String orderYn3;
	private String today15;
	private String orderTx;
	private String ton2;
	private	int orderQt2;
	private String costYn;
	private String tprice;
	
    
}
