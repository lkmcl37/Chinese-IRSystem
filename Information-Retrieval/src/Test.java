import java.io.IOException;

import ir_segment.IRSystem;

public class Test {

	public static void main(String[] args) throws IOException {

		IRSystem myIRSystem = new IRSystem("data", "stopword.txt");
		myIRSystem.createIndex();	// must create before search
		
		
		System.out.println("ËÑË÷ ½ÉÄÉ");
		myIRSystem.search("½ÉÄÉ");
		System.out.println();
		System.out.println("ËÑË÷ Ê¯ÓÍ");
		myIRSystem.search("Ê¯ÓÍ");
		System.out.println();
		System.out.println("ËÑË÷ Æû³µ");
		myIRSystem.search("Æû³µ");

	}
}
