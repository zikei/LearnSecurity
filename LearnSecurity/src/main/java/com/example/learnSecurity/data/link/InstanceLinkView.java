package com.example.learnSecurity.data.link;

import com.example.learnSecurity.entity.Instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 実習インスタンスリンク表示用試験クラス */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstanceLinkView {
	/** 実習インスタンスID */
	private Integer InsId;
	
	/** 実習インスタンス名 */
	private String InsName;
	
	public InstanceLinkView(Instance ins) {
		this.InsId   = ins.getInstanceId();
		this.InsName = ins.getInstanceName();
	}
}