import java.io.IOException;

import ir_segment.IRSystem;

public class Test {

	public static void main(String[] args) throws IOException {

		IRSystem myIRSystem = new IRSystem("data", "stopword.txt");
		myIRSystem.createIndex();	// must create before search
		
		
		System.out.println("search 汽车");
		myIRSystem.search("汽车");
		System.out.println();
	}
}
