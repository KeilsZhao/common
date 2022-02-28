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
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;

/**
 * @author Fimipeler
 * @Description GuidanceQuestionLuence,导诉咨询luence查询
 * @Date 2021/6/16 10:53
 */
@Component
@Slf4j
public class GuidanceQuestionLuence {

    private HashMap<String, String> mQuestion = new HashMap<>();

    @Autowired
    private PropertiesConfig config;

    private static Directory directory = null;

    private static DirectoryReader indexReader = null;

    @PostConstruct
    private void init() {
        try {
            directory = (Directory) FSDirectory.open(FileSystems.getDefault().getPath(config.getGuidanceUrl(), new String[0]));
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


    public void search(String keyWord) {
        DirectoryReader directoryReader = null;
        try {
            IndexSearcher indexSearcher = new IndexSearcher((IndexReader)getSearcher());
            IKAnalyzer iKAnalyzer = new IKAnalyzer(true);
            String[] fields = { "title" };
            BooleanClause.Occur[] clauses = { BooleanClause.Occur.SHOULD };
            Query multiFieldQuery = MultiFieldQueryParser.parse(keyWord, fields, clauses, (Analyzer)iKAnalyzer);
            TopDocs topDocs = indexSearcher.search(multiFieldQuery, 20);
            System.out.println("共找到匹配处：" + topDocs.totalHits);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            System.out.println("共找到匹配文档数：" + scoreDocs.length);
            byte b;
            int i;
            ScoreDoc[] arrayOfScoreDoc1;
            for (i = (arrayOfScoreDoc1 = scoreDocs).length, b = 0; b < i; ) {
                ScoreDoc scoreDoc = arrayOfScoreDoc1[b];
                Document document = indexSearcher.doc(scoreDoc.doc);
                this.mQuestion.put(document.get("title"), document.get("content"));
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

    public HashMap<String, String> getQuestion(String search) {
        long start = System.currentTimeMillis();
        search(search);
        System.out.println("查询时间" + (System.currentTimeMillis() - start));
        return this.mQuestion;
    }

}
