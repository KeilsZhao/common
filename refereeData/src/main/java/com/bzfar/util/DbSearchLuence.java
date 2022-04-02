package com.bzfar.util;

import com.bzfar.config.PropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class DbSearchLuence {

	@Autowired
	private PropertiesConfig config;

	private List<HashMap<String, String>> mQuestionList = new ArrayList<>();

	private HashMap<String, String> mQuestionId = new HashMap<>();
	
	private static Directory directory = null;
 
	private static DirectoryReader indexReader = null;
	
	@PostConstruct
	private void init() {
		try {
			directory = (Directory)FSDirectory.open(FileSystems.getDefault().getPath(config.getPathUrl(), new String[0]));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public DirectoryReader getSearcher() throws IOException {
		if (indexReader == null) {
			indexReader = DirectoryReader.open(directory);
		} else {
			DirectoryReader changeReader = DirectoryReader.openIfChanged(indexReader);
			if (changeReader != null) {
				indexReader.close();
				indexReader = changeReader;
			}
		}
		return indexReader;
	}
	
	public void search(String keyWord,String[] fields,int max) {
		DirectoryReader directoryReader = null;
		this.mQuestionList.clear();
		try {
			IndexSearcher indexSearcher = new IndexSearcher((IndexReader)getSearcher());
			IKAnalyzer iKAnalyzer = new IKAnalyzer(true);
//			String[] fields = { "ay" ,"countName"};
			BooleanClause.Occur[] clauses = { BooleanClause.Occur.SHOULD };
			Query multiFieldQuery = MultiFieldQueryParser.parse(keyWord, fields, clauses, (Analyzer)iKAnalyzer);
			TopDocs topDocs = indexSearcher.search(multiFieldQuery, max);
			System.out.println("共找到匹配处：" + topDocs.totalHits);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			System.out.println("共找到匹配文档数：" + scoreDocs.length);
			int b;
			int i;
			ScoreDoc[] arrayOfScoreDoc1;
			for (i = (arrayOfScoreDoc1 = scoreDocs).length, b = 0; b < i; ) {
				ScoreDoc scoreDoc = arrayOfScoreDoc1[b];
				Document document = indexSearcher.doc(scoreDoc.doc);
				HashMap<String, String> mQuestion = new HashMap<>();
				mQuestion.put("id", document.get("id"));
				mQuestion.put("title", document.get("title"));
				mQuestion.put("countName", document.get("countName"));
				mQuestion.put("ah", document.get("ah"));
				mQuestion.put("ay", document.get("ay"));
				mQuestion.put("caseType", document.get("caseType"));
				mQuestion.put("refereeTime", document.get("refereeTime"));
				mQuestion.put("spr", document.get("spr"));
				mQuestion.put("content", document.get("content"));
				mQuestion.put("refereeType", document.get("refereeType"));
				this.mQuestionList.add(mQuestion);
				b++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (directoryReader != null)
					directoryReader.close(); 
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} 
	}

	public void searchById(String id) {
		DirectoryReader directoryReader = null;
		try {
			IndexSearcher indexSearcher = new IndexSearcher((IndexReader)getSearcher());
			IKAnalyzer iKAnalyzer = new IKAnalyzer(true);
			String[] fields = { "id" };
			BooleanClause.Occur[] clauses = { BooleanClause.Occur.SHOULD };
			Query multiFieldQuery = MultiFieldQueryParser.parse(id, fields, clauses, (Analyzer)iKAnalyzer);
			TopDocs topDocs = indexSearcher.search(multiFieldQuery, 1000);
			System.out.println("共找到匹配处：" + topDocs.totalHits);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			System.out.println("共找到匹配文档数：" + scoreDocs.length);
			int b;
			int i;
			ScoreDoc[] arrayOfScoreDoc1;
			for (i = (arrayOfScoreDoc1 = scoreDocs).length, b = 0; b < i; ) {
				ScoreDoc scoreDoc = arrayOfScoreDoc1[b];
				Document document = indexSearcher.doc(scoreDoc.doc);
				mQuestionId.put("id", document.get("id"));
				mQuestionId.put("title", document.get("title"));
				mQuestionId.put("countName", document.get("countName"));
				mQuestionId.put("ah", document.get("ah"));
				mQuestionId.put("ay", document.get("ay"));
				mQuestionId.put("caseType", document.get("caseType"));
				mQuestionId.put("refereeTime", document.get("refereeTime"));
				mQuestionId.put("spr", document.get("spr"));
				mQuestionId.put("content", document.get("content"));
				b++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (directoryReader != null)
					directoryReader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<HashMap<String, String>> getQuestion(String search,String[] fields,int max) {
		long start = System.currentTimeMillis();
		search(search,fields,max);
		System.out.println("查询时间" + (System.currentTimeMillis() - start));
		return this.mQuestionList;
	}

	public HashMap<String, String> getQuestionById(String search) {
		long start = System.currentTimeMillis();
		searchById(search);
		System.out.println("查询时间" + (System.currentTimeMillis() - start));
		return this.mQuestionId;
	}
	
	public static void main(String[] args) throws IOException {
//		DbSearchLuence dbSearchLuence = new DbSearchLuence();
//		HashMap<String, String> map = dbSearchLuence.getQuestion("案外人");
//		System.out.println(map.size());
//		System.out.println(map.toString());
	}
	

}
