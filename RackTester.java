import java.util.ArrayList;

public class RackTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test1 = "blob";
		String test2 = "bldgooo";
		String test3 = "duotdeg";
		String test4 = "dotwgoo";
		ArrayList<String> subsets1= Rack.allSubsets(test1);
		ArrayList<String> subsets2= Rack.allSubsets(test2);
		ArrayList<String> subsets3= Rack.allSubsets(test3);
		ArrayList<String> subsets4= Rack.allSubsets(test4);
		
		printResult(subsets1, "test1");
		printResult(subsets2, "test2");
		printResult(subsets3, "test3");
		printResult(subsets4, "test4");
	}
	
	private static void printResult(ArrayList<String> subsets, String word) {
		System.out.println("the result of " + word);
		for (String s: subsets) {
			System.out.println(s);
		}
		System.out.println();
	}

}
