package document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DocumentHelper {

	static String encoding = "UTF-8";
	
	public static DocumentEntity createDocumentEntity(String filename) {
		try {
			DocumentEntity result = new DocumentEntity();
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					filename), encoding);
			BufferedReader bufferedReader = new BufferedReader(read);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.length() == 0)
					continue;
				if (line.toLowerCase().startsWith("<docid>"))
					result.docid = getDocID(line);
				else if (!line.toLowerCase().equals("<doc>"))
					result.addTxt(line);
			}
			bufferedReader.close();
			if (result.docid != "")
				return result;
		} catch (IOException e) {
			return null;
		}
		return null;
	}

	public static List<DocumentEntity> createDocumentEntityList(
			String foldername) {
		List<DocumentEntity> result = new ArrayList<DocumentEntity>();
		File file = new File(foldername);
		File[] filelist = file.listFiles();
		for (int i = 0; i < filelist.length; i++) {
			DocumentEntity doc = createDocumentEntity(foldername + "/" + filelist[i].getName());
			if (doc != null)
				result.add(doc);
		}
		return result;
	}
	
	public static Set<String> getStopWord(String filename) {
		Set<String> result = new HashSet<String>();
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					filename), encoding);
			BufferedReader bufferedReader = new BufferedReader(read);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.length() == 0)
					continue;
				if (!result.contains(line))
					result.add(line);
			}
			bufferedReader.close();
		} catch (IOException e) {
			
		}
		return result;
	}
	
	private static String getDocID(String line) {
		if (!line.toLowerCase().startsWith("<docid>"))
			return null;
		return line.replaceAll("<docid>", "");
	}
}
