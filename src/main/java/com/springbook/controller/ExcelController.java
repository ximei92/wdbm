package com.springbook.controller;

import java.io.*;
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ExcelController {
//    public static void main(String[] args) {
//    	ExcelController csvReader = new ExcelController();
//        String filePath = null;
//		csvReader.readCsv(filePath);
//    }

	@PostMapping("/readCsv")
	@ResponseBody
    public List<List<String>> readCsv(String filePath) {
		System.out.println("오긴오냐;;");
		
		System.out.println(filePath);
        List<List<String>> csvList = new ArrayList<List<String>>();
        File csv = new File(filePath);
        BufferedReader br = null;
        String line = "";
System.out.println(filePath);
        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                List<String> aLine = new ArrayList<String>();
                String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                aLine = Arrays.asList(lineArr);
                csvList.add(aLine);
            }
            System.out.println(csvList.get(1).get(1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) { 
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }
}