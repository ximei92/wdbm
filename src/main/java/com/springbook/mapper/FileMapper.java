package com.springbook.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.springbook.vo.FileVO;

@Mapper
/* file dao */
public interface FileMapper {
	
	/** 파일 등록 */
	int insertFile(Map<String, Object> file);
	
	/** 파일 조회 */
	Map<String, Object> getFileInfo(String fileId);
	
	/** 해당 파일 삭제처리 */
	int deleteFile(String fileId);

	FileVO getFileData(String fileId);

//	FileVO getFileData(String fileId);
}