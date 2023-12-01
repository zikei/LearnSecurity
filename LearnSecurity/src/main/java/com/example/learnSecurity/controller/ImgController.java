package com.example.learnSecurity.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** 画像コントローラ */
@Controller
@RequestMapping("/LearnSecurity/Img")
public class ImgController {
	/** 画像　*/
	@GetMapping
	@ResponseBody
	public HttpEntity<byte[]> img(@RequestParam("p") String filePath) {
		File fileImg = new File(filePath);
		
		byte[] byteImg = null;
		HttpHeaders headers = null;
		try {
			//バイト列に変換
			byteImg = Files.readAllBytes(fileImg.toPath());
			headers = new HttpHeaders();
			
			//Responseのヘッダーを作成
			headers.setContentType(MediaType.IMAGE_PNG);
			headers.setContentLength(byteImg.length);
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		return new HttpEntity<byte[]>(byteImg,headers);
	}
}