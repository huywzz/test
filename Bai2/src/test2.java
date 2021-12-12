
public class test2 {


public class function_chuoi {
	static int count ( String str, String word) {
		String array[] = str.split(" ");
		int dem = 0;
		for (int i=0; i<=str.length(); i++) {
			if(word.equals(array[i])) { 
				dem++;}
		}
		return dem;
	}

	public static void main(String[] args) {
		String str="Java is exciting. I love java";
		String word="java";
		System.out.println(count(str, word));
	}

}

	}


