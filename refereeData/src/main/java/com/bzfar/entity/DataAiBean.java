package com.bzfar.entity;

import java.util.List;

public class DataAiBean {

	private int code;

	private String info;

	private String answer;

	private List<DataList> data;

	public static class DataList {
		private String answer;

		public DataList(String answer) {
			this.answer = answer;
		}

		public String getAnswer() {
			return this.answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<DataList> getData() {
		return data;
	}

	public void setData(List<DataList> data) {
		this.data = data;
	}

}
