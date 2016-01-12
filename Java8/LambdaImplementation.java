package Java8;

public class LambdaImplementation {
    public static void main(String[] args) {

        NoArgLambda someLambda = () -> {
            System.out.println("Message from lambda with no args");
        };
        someLambda.doSomething();

        //specifying curly braces for definition is optional, if there is only statement and not returning something
        Display console = (String msg) -> System.out.println(msg);
        console.print("Hello World");

        //specifying type in lambda expression is optional
        //specifying brackets around parameters is optional if there is only parameter
        Display greeting = name -> System.out.println("Hi! " + name);
        greeting.print("Sahil Gupta");

        //lambdas support generic types
        MathOperation<Integer> addInt = (a, b) -> {
            return a + b;
        };
        MathOperation<Integer> subtractInt = (a, b) -> {
            return a - b;
        };
        //"return" keyword is optional if single statement is returning something
        //(can not use curly braces in definition in this case)
        MathOperation<Float> addFloat = (a, b) -> a + b;

        console.print("int add 3,4--> " + addInt.perform(3, 4));
        console.print("int subtract 45,67--> " + subtractInt.perform(45, 67));
        console.print("float add 19.0,17.0--> " + addFloat.perform(19.0f, 17.0f));

    }

    @FunctionalInterface
    private interface NoArgLambda {

        void doSomething();
    }

    @FunctionalInterface
    private interface Display {

        void print(String msg);
    }

    @FunctionalInterface
    private interface MathOperation<T> {

        T perform(T t1, T t2);
    }
}
