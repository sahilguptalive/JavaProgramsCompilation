import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.Invocable;
import java.io.*;
import java.lang.NoSuchMethodException;

public class EmbeddScript{
	public static void main(String[] args){

		//initialise the script engine manager 
		//responsible for loading a script engine
		ScriptEngineManager enginerManager=new ScriptEngineManager();
		//initialize the nashorn engine using script manager
		ScriptEngine nashorn=enginerManager.getEngineByName("nashorn");
		try{
			//eval inline java script functions, which are stored in engine memory
			nashorn.eval("function greet(name){print('Hi! '+name);}");
			//call just created function using engine eval
			nashorn.eval("greet('Sahil Gupta');");
		}catch(ScriptException se){

			se.printStackTrace();
		}
		try{
			//load a java script file using file reader
			nashorn.eval(new FileReader("CalculatorScript.js"));
		}catch(FileNotFoundException|ScriptException e){
			e.printStackTrace();
		}
		//create invocable from engine to invoke a function from loaded script 
		Invocable invokable=(Invocable)nashorn;
		try{
			//invoke add function from script.
			System.out.println((invokable.invokeFunction("add",13,54)).toString());
		}catch(NoSuchMethodException|ScriptException e){
			e.printStackTrace();
		}
	}
}
