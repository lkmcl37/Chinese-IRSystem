package tfidf;

import java.util.HashMap;
import java.util.Map;

public class IndexEntry {
	public String term;
	public Map<String, Integer> doclist;
	private double idf;

	public IndexEntry(String name) {
		doclist = new HashMap<String, Integer>();
		idf = 0;
		term = name;
	}

	public IndexEntry(String indexdata, boolean flag) {
		doclist = new HashMap<String, Integer>();
		String data[] = indexdata.split(",");
		term = data[0];
		for(int i = 1; i < data.length - 1; i += 2)
		{
			String doc = data[i];
			int doctf = Integer.valueOf(data[i + 1]);
			addDoc(doc, doctf);
		}
		idf = Double.valueOf(data[data.length - 1]);
	}

	/**
	 * This method will add a docid to this index entry
	 * 
	 * @param docid docid
	 * @param tf tf
	 */ 
	public void addDoc(String docid, int tf) {
		if (doclist.containsKey(docid)) {
			int newtf = doclist.get(docid) + tf;
			doclist.replace(docid, newtf);
		} else {
			doclist.put(docid, tf);
		}
	}
	
	/**
	 * This method will check the docid
	 * 
	 * @param docid docid
	 * @return True, if this index entry has the docid
	 */
	public boolean hasThisDoc(String docid) {
		if (doclist.containsKey(docid)) {
			return true;
		}
		return false;
	}

	/**
	 * This method will calculate the idf
	 * 
	 * @param totalDocNumber
	 */
	public void calculateIdf(int totalDocNumber) {
		idf = Math.log((double) totalDocNumber / (double) (doclist.size() + 1));
	}

	/**
	 * This method will return the idf
	 * @return idf
	 */
	public double getIdf() {
		return idf;
	}

	/**
	 * This method will return the save string
	 * @return save string
	 */
	public String toSaveString() {
		String result = term + ",";
		for (String temp : doclist.keySet()) {
			result += temp + "," + String.valueOf(doclist.get(temp)) + ",";
		}
		java.text.DecimalFormat doubleFormat = new java.text.DecimalFormat("0.000");
		result += doubleFormat.format(idf);
		return result;
	}

}
