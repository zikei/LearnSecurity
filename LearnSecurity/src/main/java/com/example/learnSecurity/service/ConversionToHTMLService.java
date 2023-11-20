package com.example.learnSecurity.service;

import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** HTML変換処理 */
@Service
@Transactional
public interface ConversionToHTMLService {
	/** ファイルの内容をHTMLに変換 */
	String fileToHTML(Path filePath);
}
