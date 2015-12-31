package tfidf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CalculateWeight {

	public CalculateWeight() {

	}

	/**
	 * This method will calculate weight and return the ranked list
	 * 
	 * @param index all IndexEntry
	 * @param keyword_list  keyword list
	 * @param num_docs num of document
	 * @return ranked list
	 */
	public List<RankItem> GetRankedList(Map<String, IndexEntry> index,
			String[] keyword_list, int num_docs) {
		// get all doc name
		String[] docName = getDocumentName(index);

		// get all token
		String[] tokenName = getIndexEntryTerm(index);

		// get doc number and the vector dimension
		int vectorDimension = tokenName.length;
		int docNumber = docName.length;

		// get the documentsVector and queryVector
		double[][] documentsVector = getDocumentsVector(vectorDimension,
				docNumber, docName, tokenName, index);
		double[] queryVector = getQueryVector(vectorDimension, tokenName,
				keyword_list);

		// calculate score
		double[] scores = calculateScore(vectorDimension, docNumber,
				documentsVector, queryVector);

		// get ranked list of the top num_docs documents
		List<RankItem> result = rankedListOfTopNDoc(num_docs, docNumber,
				scores, docName);
		return result;
	}

	private List<RankItem> rankedListOfTopNDoc(int n, int docNumber,
			double[] scores, String[] docName) {
		List<RankItem> result = new ArrayList<RankItem>();
		for (int i = 0; i < docNumber; i++) {
			result.add(new RankItem(docName[i], scores[i]));
		}
		Collections.sort(result);
		List<RankItem> rankedList = new ArrayList<RankItem>();
		for (int i = 0; i < n && i < result.size(); i++) {
			rankedList.add(result.get(i));
		}
		return rankedList;
	}

	private double[] calculateScore(int vectorDimension, int docNumber,
			double[][] documentsVector, double[] queryVector) {
		double[] result = new double[vectorDimension];
		for (int i = 0; i < docNumber; i++) {
			result[i] = calculateCosineSimilarityScore(queryVector,
					documentsVector[i]);
		}
		return result;
	}

	private double calculateCosineSimilarityScore(double[] a, double[] b) {
		int len = a.length;
		if (len > b.length)
			len = b.length;
		double docProduct = 0;
		for (int i = 0; i < len; i++) {
			docProduct += a[i] * b[i];
		}
		double ma = 0;
		double mb = 0;
		for (int i = 0; i < len; i++) {
			ma += a[i] * a[i];
			mb += b[i] * b[i];
		}
		if (0 == ma)
			ma = 1;
		if (0 == mb)
			mb = 1;
		ma = Math.sqrt(ma);
		mb = Math.sqrt(mb);
		double score = (docProduct) / (ma * mb);
		return score;
	}

	private double[][] getDocumentsVector(int vectorDimension, int docNumber,
			String[] docName, String[] tokenName, Map<String, IndexEntry> index) {
		double[][] documentsVector = new double[docNumber][vectorDimension];
		for (int i = 0; i < docNumber; i++) {
			String currentDocName = docName[i];
			for (int j = 0; j < vectorDimension; j++) {
				String currentToken = tokenName[j];
				if (index.get(currentToken).hasThisDoc(currentDocName)) {
					int tf = index.get(currentToken).doclist
							.get(currentDocName);
					double idf = index.get(currentToken).getIdf();
					documentsVector[i][j] = idf	* (double) tf;
				} else {
					documentsVector[i][j] = 0;
				}
			}
		}
		return documentsVector;
	}

	private double[] getQueryVector(int vectorDimension, String[] tokenName,
			String[] keyword_list) {
		double[] queryVector = new double[vectorDimension];
		Set<String> keywordSet = new HashSet<String>();
		for (int i = 0; i < keyword_list.length; i++) {
			if (!keywordSet.contains(keyword_list[i]))
				keywordSet.add(keyword_list[i]);
		}
		for (int i = 0; i < vectorDimension; i++) {
			if (keywordSet.contains(tokenName[i]))
				queryVector[i] = 1;
			else
				queryVector[i] = 0;
		}
		return queryVector;
	}

	private String[] getIndexEntryTerm(Map<String, IndexEntry> index) {
		List<String> result = new ArrayList<String>();
		for (String tmepstr : index.keySet()) {
			result.add(tmepstr);
		}

		String[] arrayresult = new String[result.size()];
		for (int i = 0; i < result.size(); i++) {
			arrayresult[i] = result.get(i);
		}
		return arrayresult;
	}

	private String[] getDocumentName(Map<String, IndexEntry> index) {
		Set<String> result = new HashSet<String>();
		List<String> resultlist = new ArrayList<String>();
		for (String tmepstr : index.keySet()) {
			for (String tempdocname : index.get(tmepstr).doclist.keySet()) {
				if (!result.contains(tempdocname)) {
					result.add(tempdocname);
					resultlist.add(tempdocname);
				}
			}
		}
		String[] arrayresult = new String[result.size()];
		for (int i = 0; i < result.size(); i++) {
			arrayresult[i] = resultlist.get(i);
		}
		return arrayresult;
	}
}
