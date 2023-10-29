package com.example.learnSecurity.service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

/** HTML変換処理実装クラス(マークダウン形式)　*/
@Service
@Transactional
public class MarkdownToHTMLService implements ConversionToHTMLService{

	@Override
	public String fileToHTML(Path filePath) {
		MutableDataSet options = new MutableDataSet();
		options.set(Parser.EXTENSIONS, 
				Arrays.asList(TablesExtension.create()));

	    Parser parser = Parser.builder(options).build();
	    HtmlRenderer renderer = HtmlRenderer.builder(options).build();
	    
	    List<String> lines;
		try {
			lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			return "";
		}
	    String markdown = String.join("  \n", lines);

	    Node document = parser.parse(markdown);
	    String html = renderer.render(document);
	    
	    return html;
	}
}