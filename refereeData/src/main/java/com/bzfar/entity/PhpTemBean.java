package com.bzfar.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class PhpTemBean {
	
	private List<QuestionsBean> questions;
	
	private List<KeywordsBean> keywords;


	public static class QuestionsBean {
		private String question;

		public String getQuestion() {
			return this.question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}
	}

	public static class KeywordsBean {
		private String keyword;
		
		public String getKeyword() {
			return this.keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
	}
	
	public List<QuestionsBean> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<QuestionsBean> questions) {
		this.questions = questions;
	}

	public List<KeywordsBean> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(List<KeywordsBean> keywords) {
		this.keywords = keywords;
	}
}
