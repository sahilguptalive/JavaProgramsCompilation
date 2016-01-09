package Java8;
import java.util.*;
public class StringJoinImplementation {

	public static void main(String[] args){

		//print join of some string separated by delimiter
		print(String.join(" - ","011","234","567","911"));
		//print join of collection of strings
		List<String> names=new ArrayList<>();
		names.add("Sahil");
		names.add("Sam");
		names.add("Sakshi");
		print(String.join(", ",names));
		//print collection of custom classes(non string)
		List<Custom> values=new ArrayList<>();
		values.add(new Custom(9));
		values.add(new Custom(10));
		values.add(new Custom(11));
		values.add(new Custom(12));
		//would not be able to do join since custom class does not implements CharSequence
		//would result in a compile time error
		//print(String.join(":",values));
	}

	private static void print(String msg){
		System.out.println(msg);
	}

	private static class Custom{

		private int value;
		Custom(int val){

			value=val;
		}
		@Override
			public String toString(){
				return "value is:"+value;
			}
	}
}

