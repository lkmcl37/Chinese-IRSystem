package tfidf;

public class RankItem implements Comparable<RankItem>{
	public String docID;
	public double cosineScore;

	public RankItem(String doc, double score) {
		docID = doc;
		cosineScore = score;
	}

	@Override
	public String toString() {
		String result = docID + ",";
		result += String.valueOf(cosineScore);
		return result;
	}

	@Override
	public int compareTo(RankItem arg0) {
		if(cosineScore < arg0.cosineScore)
			return 1;
		if(cosineScore > arg0.cosineScore)
			return -1;
		return 0;
	}
}
