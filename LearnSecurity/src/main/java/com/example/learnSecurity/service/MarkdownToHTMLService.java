package com.example.learnSecurity.service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

/** HTML変換処理実装クラス(マークダウン形式)　*/
@Service
@Transactional
public class MarkdownToHTMLService implements ConversionToHTMLService{
	/* 画像マークダウン正規表現:![*](*) **/
	final private String IMG_REGEX = "!\\[.*\\]\\((.*)\\)";
	final private Pattern IMG_PTN = Pattern.compile(IMG_REGEX);
	
	@Override
	public String fileToHTML(Path filePath) {
		MutableDataSet options = new MutableDataSet();
		options.set(Parser.EXTENSIONS, 
				Arrays.asList(TablesExtension.create(),
							  TocExtension.create()));
		options.set(TocExtension.TITLE, "目次");
		options.set(TocExtension.DIV_CLASS, "toc-box");
		options.set(TocExtension.LIST_CLASS, "toc-list");
		
	    Parser parser = Parser.builder(options).build();
	    HtmlRenderer renderer = HtmlRenderer.builder(options).build();
	    
	    List<String> lines;
		try {
			lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			return "";
		}
		
	    String markdown = String.join("  \n", lines);
	    markdown = convImgPath(filePath, markdown);

	    Node document = parser.parse(markdown);
	    String html = renderer.render(document);
	    
	    return html;
	}
	
	/** マークダウンの画像のパスを絶対パスに変換する */
	private String convImgPath(Path filePath, String line) {
		Matcher imgM = IMG_PTN.matcher(line);
		
		while(imgM.find()) {
			// path部分を取得
			String path = imgM.group(1);
			String fPath = filePath.getParent() + "/" + path;
			// 画像のフルパスを送信しているため要修正
			String imgUrl = "http://" + System.getenv("HOST_NAME") + "/LearnSecurity/Img?p=" + fPath;
			
			String target = imgM.group();
			String replacement = target.replaceAll(Pattern.quote(path), imgUrl);
			
			line = line.replaceAll(Pattern.quote(target), replacement);
		}
		return line;
	}
}