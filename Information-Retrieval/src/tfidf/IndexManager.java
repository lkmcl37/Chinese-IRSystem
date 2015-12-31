package tfidf;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IndexManager {
	
	Map<String, IndexEntry> index;
	Set<String> docIDSet;
	Set<String> stopWordSet;
	
	public IndexManager(Set<String> stopWordSet) {
		this.index = new HashMap<String, IndexEntry>();
		this.docIDSet = new HashSet<String>();
		this.stopWordSet = stopWordSet;
	}
	
	public void addTokenList(Map<String, Integer> tokenList, String docID) {
		for (String temp : tokenList.keySet()) {
			int doctf = tokenList.get(temp);
			if (index.containsKey(temp)) {
				index.get(temp).addDoc(docID, doctf);
			} else {
				IndexEntry newIndexEntry = new IndexEntry(temp);
				newIndexEntry.addDoc(docID, doctf);
				index.put(temp, newIndexEntry);
			}
		}
		if (!docIDSet.contains(docID))
			docIDSet.add(docID);
		
	}
	
	public void calculateIdf() {
		for (String temp : index.keySet()) {
			IndexEntry ie = index.get(temp);
			ie.calculateIdf(docIDSet.size());
		}
	}
	
	public List<RankItem> searchCommand(int num_docs,
			String[] keyword_list) {
		// calculate the weight
		CalculateWeight myCalculateWeight = new CalculateWeight();
		List<RankItem> rankedList = myCalculateWeight.GetRankedList(index,
				keyword_list, num_docs);
		return rankedList;

	}
	
}
