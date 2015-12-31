package ir_segment;


import java.util.List;
import java.util.Map;
import java.util.Set;

import document.DocumentEntity;
import document.DocumentHelper;
import tfidf.IndexHelper;
import tfidf.IndexManager;
import tfidf.RankItem;

public class IRSystem {
	Set<String> stopword;
	List<DocumentEntity> doclist;
	IndexManager myIndexManager;
	
	public IRSystem(String docfolder, String stopwordfile) {
		doclist = DocumentHelper.createDocumentEntityList(docfolder);
		stopword = DocumentHelper.getStopWord(stopwordfile);
		myIndexManager = new IndexManager(stopword);
	}
	
	public void createIndex() {
		for (int i = 0; i < this.doclist.size(); i++) {
			DocumentEntity doc = doclist.get(i);
			List<String> wordlist = IndexHelper.getWordList(doc);
			Map<String, Integer> tokenList = IndexHelper.getTokenList(wordlist, stopword);
			myIndexManager.addTokenList(tokenList, doc.docid);
		}
		myIndexManager.calculateIdf();
	}
	
	public void search(String word) {
		List<String> result = IndexHelper.getWordList(word);
		String[] arr = new String[result.size()];
		for (int i = 0; i < result.size(); i++)
			arr[i] = result.get(i);
		List<RankItem> rankedList = myIndexManager.searchCommand(5,arr);
		// output the result
		for (int i = 0; i < rankedList.size(); i++) {
			System.out.println(rankedList.get(i).toString());
		}
	}
}
