package com.example.learnSecurity.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/** 検索Form */
@Data
public class SearchForm {
	/** 検索ワード */
	@NotBlank
	@Size(max=30)
	private String searchWord;
}
