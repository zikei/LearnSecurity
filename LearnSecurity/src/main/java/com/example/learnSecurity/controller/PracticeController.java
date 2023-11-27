package com.example.learnSecurity.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.learnSecurity.data.PageView;
import com.example.learnSecurity.data.PracticeView;
import com.example.learnSecurity.data.link.PracticeLinkView;
import com.example.learnSecurity.entity.Practice;
import com.example.learnSecurity.exception.NotFoundException;
import com.example.learnSecurity.service.AccountService;
import com.example.learnSecurity.service.PracticeService;

import jakarta.servlet.http.HttpServletResponse;

/** 実習コントローラ */
@Controller
@RequestMapping("/LearnSecurity/Practice")
public class PracticeController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	PracticeService practiceService;
	
	@Autowired
	PageView<PracticeLinkView> pracPage;
	
/* ====================================================================================================================== */
	
	/** pの値がない場合：実習一覧取得
	 * pの値がある場合はそのページを表示 */
	@GetMapping
	public String PracticeList(Model model,  @RequestParam(required = false) Integer p) {
		if(p != null) return PracticeView(model, p);
		List<Practice> pracList = practiceService.selectAllPractice();
		makeLessonPage(pracList);
		System.out.println(pracPage.getTotalPages());
		return "redirect:/LearnSecurity/Practice?p=1";
	}
	
	/** 実習検索　*/
	@PostMapping
	public String PracticeSearch(@RequestParam String sword, Model model) {
		List<Practice> pracList = practiceService.searchPractice(sword);
		makeLessonPage(pracList);
		
		return "redirect:/LearnSecurity/Practice?p=1";
	}
	
	/** 実習表示 */
	public String PracticeView(Model model, Integer page) {
		if(page == null) return "redirect:/LearnSecurity/Practice";
		if(pracPage.getFullList() == null) return "redirect:/LearnSecurity/Practice";
		try {
			pracPage.setPageNum(page);
		} catch (NotFoundException e) {
			 return "redirect:/LearnSecurity/Practice";
		}
		
		model.addAttribute("pracList", pracPage.getPageList());
		model.addAttribute("page", pracPage);
		return "PracticeList";
	}
	
	/** 実習ダウンロードページ */
	@GetMapping("/{pracId}")
	public String PracticeDLView(@PathVariable Integer pracId, Model model) {
		PracticeView prac;
		try {
			prac = practiceService.selectPracticeView(pracId);
		} catch (NotFoundException e) {
			model.addAttribute("errorMsg", "実習情報を取得できませんでした");
			return "error";
		}
		
		model.addAttribute("practice", prac);
		
		return "DLpractice";
	}
	
	/** 実習ダウンロード 
	 * @throws IOException */
	@GetMapping("/{pracId}/Download")
	public void PracticeDL(@PathVariable Integer pracId, HttpServletResponse res) throws IOException{
		try {
			String dirPath = practiceService.selectPracticeDirPath(pracId);
			res.setContentType("application/zip");
	        res.setHeader("Content-Disposition", "attachment; filename=" + zipName(dirPath));
			dirZipDL(dirPath, res);
		} catch (NotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (IOException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
/* ====================================================================================================================== */
	
	/** Zipファイル名を取得 
	 * @throws NotFoundException */
	private String zipName(String dirPath) throws NotFoundException {
		String zipName = dirPath.substring(dirPath.lastIndexOf("/") + 1) + ".zip";
		return zipName;
	}
	
	/** zipダウンロード 
	 * @throws IOException */
	private void dirZipDL(String dirPath, HttpServletResponse res) throws IOException {
        ZipOutputStream zos = new ZipOutputStream(res.getOutputStream());
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        addFilesForZip(files, zos, "");
        zos.close();
	}
	
	/** ファイルをzipファイルに追加　
	 * @throws IOException */
	private void addFilesForZip(File[] files, ZipOutputStream zos, String dirPath) throws IOException {
		for (File file : files) {
			String entry = dirPath + file.getName();
        	if(file.isDirectory()) {
        		entry += "/";
        		zos.putNextEntry(new ZipEntry(entry));
        		addFilesForZip(file.listFiles(), zos, entry);
        	}else {
        		zos.putNextEntry(new ZipEntry(entry));
                Files.copy(file.toPath(), zos);
        	}
        }
	}
	
	/** 実習ページ作成 */
	private void makeLessonPage(List<Practice> practiceList) {
		List<PracticeLinkView> practiceLinkList = makePracticeLinkViewList(practiceList);
		pracPage = new PageView<PracticeLinkView>(new ArrayList<>());
	}
	
	/** 実習リストをPracticeLinkViewListに変換 */
	private List<PracticeLinkView> makePracticeLinkViewList(List<Practice> pracList){
		List<PracticeLinkView> pracLinkList = new ArrayList<>();
		pracList.forEach(prac -> pracLinkList.add(new PracticeLinkView(prac)));
		
		return pracLinkList;
	}
}
