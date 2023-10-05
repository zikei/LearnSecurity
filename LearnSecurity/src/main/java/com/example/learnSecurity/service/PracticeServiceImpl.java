package com.example.learnSecurity.service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.learnSecurity.data.PracticeView;
import com.example.learnSecurity.data.link.LessonLinkView;
import com.example.learnSecurity.entity.Practice;
import com.example.learnSecurity.exception.NotFoundException;
import com.example.learnSecurity.repository.PracticeRepository;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

/** 実習関連処理実装クラス　*/
@Service
@Transactional
public class PracticeServiceImpl  implements PracticeService{
	@Autowired
	PracticeRepository practiceRepo;
	
	@Autowired
	PracticeLessonService plService;
	
	@Override
	public List<Practice> selectAllPractice() {
		List<Practice> practiceList = new ArrayList<>();
		practiceRepo.findAll().forEach(practiceList::add);
		
		return practiceList;
	}
	
	@Override
	public PracticeView selectPracticeView(Integer practiceId) throws NotFoundException{
		Practice practice = selectPractice(practiceId);
		String readmePath = getPracticeDirPath(practice) + "/README.md";
		String practiceInfo = markdownfileToHtml(readmePath);
		List<LessonLinkView> relationLessonList = plService.selectRelationLesson(practiceId);
		
		return new PracticeView(practice, practiceInfo, relationLessonList);
	}

	@Override
	public String selectPracticeDirPath(Integer practiceId) throws NotFoundException{
		Practice practice = selectPractice(practiceId);
		return getPracticeDirPath(practice);
	}
	
	
	/** マークダウンファイルをHTML形式に変換 */
	private String markdownfileToHtml(String filePath) {
		MutableDataSet options = new MutableDataSet();

	    Parser parser = Parser.builder(options).build();
	    HtmlRenderer renderer = HtmlRenderer.builder(options).build();
	    
	    List<String> lines;
		try {
			lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			return "";
		}
	    String markdown = String.join("\n", lines);

	    Node document = parser.parse(markdown);
	    String html = renderer.render(document);
	    
	    return html;
	}
	
	/** 実習情報からディレクトリの絶対パスを取得 */
	private String getPracticeDirPath(Practice practice) {
		return System.getenv("PRACTICE_DIR") + practice.getDirPath();
	}
	
	/** 実習情報をDBから取得 */
	private Practice selectPractice(Integer practiceId) throws NotFoundException {
		return practiceRepo.findById(practiceId)
				.orElseThrow(() -> new NotFoundException("404 PracticeID : " + practiceId));
	}
}