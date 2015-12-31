package document;


public class DocumentEntity {
	public String docid;
	public String txt;
	
	public DocumentEntity() {
		this.docid = "";
		this.txt = "";
	}

	public void addTxt(String str) {
		this.txt += str;
	}
	
}
