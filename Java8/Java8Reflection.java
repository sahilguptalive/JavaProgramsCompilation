import java.lang.reflect.*;
import java.util.function.*;
import static java.lang.System.out;

public class Java8Reflection{

	private static final String  fmt = "%24s: %s%n";
	public static void main(String[] args){

		Method method=MyClass.class.getDeclaredMethods()[0];
		printMethodDetails(method);
		Parameter[] parameters=method.getParameters();
		for(Parameter p:parameters){
			printParameterDetails(p);
		}

	}


	private static void printMethodDetails(Method m){

		print("Method name:"+m.getName());
	}
	private static void printParameterDetails(Parameter p){

		print("================================");
		out.format(fmt, "Parameter class", p.getType());
		out.format(fmt, "Parameter name", p.getName());
		out.format(fmt, "Modifiers", p.getModifiers());
		out.format(fmt, "Is implicit?", p.isImplicit());
		out.format(fmt, "Is name present?", p.isNamePresent());
		out.format(fmt, "Is synthetic?", p.isSynthetic());
	}


	private class MyClass{

		public void add(int a,int b,String msg){

			System.out.println(msg!=null?msg:""+(a+b));
		}
	}
	public static void print(String msg){

		System.out.println(msg);
	}
}
