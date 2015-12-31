package tfidf;



import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import document.DocumentEntity;

public class IndexHelper {
	
	public static Map<String, Integer> getTokenList(List<String> wordList, Set<String> stopWordSet) {
		Map<String, Integer> result = new HashMap<String, Integer>();
		for(int i = 0; i < wordList.size(); i++) {
			if(stopWordSet.contains(wordList.get(i)))
				continue;
			if(result.containsKey(wordList.get(i))) {
				int number = result.get(wordList.get(i));
				number++;
				result.replace(wordList.get(i), number);
			} else {
				result.put(wordList.get(i), 1);
			}
		}
		return result;
	}

	public static List<String> getWordList(DocumentEntity doc) {
		List<String> result = new ArrayList<String>();
		try {
			//
			Analyzer anal = new IKAnalyzer(true);
			StringReader reader = new StringReader(doc.txt);
			//
			TokenStream ts = anal.tokenStream("", reader);
			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
			//
			while (ts.incrementToken()) {
				result.add(term.toString());
			}
			anal.close();
		} catch (IOException e) {

		}
		return result;
	}
	
	public static List<String> getWordList(String doctxt) {
		List<String> result = new ArrayList<String>();
		try {
			//
			Analyzer anal = new IKAnalyzer(true);
			StringReader reader = new StringReader(doctxt);
			//
			TokenStream ts = anal.tokenStream("", reader);
			CharTermAttribute term = ts.getAttribute(CharTermAttribute.class);
			//
			while (ts.incrementToken()) {
				result.add(term.toString());
			}
			anal.close();
		} catch (IOException e) {

		}
		return result;
	}
}
