package com.bzfar.util;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

public class Similarity {
	
	Map<String, int[]> vectorMap = new HashMap<String, int[]>();
	
	int[] tempArray = null;
	
	public Similarity(String content1, String content2) {
		
		List<String> words1 = segStr(content1);
		
		List<String> words2 = segStr(content2);
		
		int i;
		
		for (i = 0; i < words1.size(); i++) {
			if (this.vectorMap.containsKey(words1.get(i))) {
				((int[])this.vectorMap.get(words1.get(i)))[0] = ((int[])this.vectorMap.get(words1.get(i)))[0] + 1;
			} else {
				this.tempArray = new int[2];
				this.tempArray[0] = 1;
				this.tempArray[1] = 0;
				this.vectorMap.put(words1.get(i), this.tempArray);
			} 
		} 
		for (i = 0; i < words2.size(); i++) {
			if (this.vectorMap.containsKey(words2.get(i))) {
				((int[])this.vectorMap.get(words2.get(i)))[1] = ((int[])this.vectorMap.get(words2.get(i)))[1] + 1;
			} else {
				this.tempArray = new int[2];
				this.tempArray[0] = 0;
				this.tempArray[1] = 1;
				this.vectorMap.put(words2.get(i), this.tempArray);
			} 
		} 
	}


		public double sim() {
			double result = 0.0D;
			result = pointMulti(this.vectorMap) / sqrtMulti(this.vectorMap);
			return result;
		}


		private double sqrtMulti(Map<String, int[]> paramMap) {
			double result = 0.0D;
			result = squares(paramMap);
			result = Math.sqrt(result);
			return result;
		}

  
		private double squares(Map<String, int[]> paramMap) {
			double result1 = 0.0D;
			double result2 = 0.0D;
			Set<String> keySet = paramMap.keySet();
			for (String key : keySet) {
				int[] temp = paramMap.get(key);
				result1 += (temp[0] * temp[0]);
				result2 += (temp[1] * temp[1]);
			} 
			return result1 * result2;
		}


		
		public static List<String> segStr(String content) {
			Reader input = new StringReader(content);
			IKSegmenter iks = new IKSegmenter(input, true);
			Lexeme lexeme = null;
			List<String> list = new ArrayList<String>();
			try {
				while ((lexeme = iks.next()) != null)
					list.add(lexeme.getLexemeText()); 
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return list;
		}


		private double pointMulti(Map<String, int[]> paramMap) {
			double result = 0.0D;
			for (String key : paramMap.keySet()) {
				int[] temp = paramMap.get(key);
				result += (temp[0] * temp[1]);
			} 
			return result;
		}
		
}