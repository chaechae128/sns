package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileManagerService {
	//이미지 업로드, 삭제
	
	//실제 업로드 된 이미지가 저장될 경로(서버의 주소)
	
	//학원용
	//public static final String FILE_UPLOAD_PATH = "D:\\kimchaeyeon\\6_spring_project\\SNS\\sns_workspace\\images/";
	//집용
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\mouse\\OneDrive\\바탕 화면\\mega_백엔드\\spring_project_noetebook\\sns\\workspace\\images/";
	
	public String saveFile(String loginId, MultipartFile file) {
		String directoryName = loginId + "_" + System.currentTimeMillis();//파일명
		String filePath = FILE_UPLOAD_PATH + directoryName;// 
		
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			return null;
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + "/" + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return "/images/" + directoryName + "/" + file.getOriginalFilename();
		
	}
	
	public void deleteFile(String imagePath) {
		Path path = Paths.get(FILE_UPLOAD_PATH + imagePath.replace("/images", ""));
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.info("[파일매니저 삭제] 이미지 삭제 실패. path:{}", path.toString());
				return;
			}
			
			//폴더 삭제
			path = path.getParent();
			if(Files.exists(path)) {
				try {
					Files.delete(path);
				} catch (IOException e) {
					log.info("[파일매니저 삭제] 이미지 삭제 실패. path:{}", path.toString());
				}
			}
		}
	}
}
