package Java8;

import java.lang.annotation.*;


public class AnnotationImplementation {


    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface A {
    }

    @Target(ElementType.TYPE_USE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface B {
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @interface C {

    }

    @Target(ElementType.TYPE_PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @interface D {

    }

    private int[][] array1;

    // @C applies to field array2
    // @A applies to int[][]
    // @B applies to int[]
    @C
    private int @A [] @B [] array2;

    // @C applies to array3
    @C
    private int array3;

    // @C applies to array4
    @C
    private int[] array4;

    //@C applies to array5
    @C
    private int[][] array5;

    // D is an annotation of type - TYPE_PARAMETER
    // can be applied to generic type.
    class GenericClass<@D T> {

    }

    //B is a TYPE_USE annotation, can be used while declaring throws clause.
    //D is a TYPE_PARAMETER annotation, can be used while declaring generic type variable.
    public <@D T> void someMethod(T arg) throws @B NullPointerException {

    }

    public static void main(String[] args) {

        //we can use TYPE_USE type annotations while using a type.
        new @A DBAccess(); //new instance
        String var1 = (@B String) new Object(); //type casting


    }

    //================ Repeating Annotations =================

    @RoleAllowed(role = "Admin")
    @RoleAllowed(role = "Manager")
    private static class DBAccess {

    }

    //AllowedRoles is a containing annotation type, which enables to get all the repeated roles as an array
    //This has to contain the method "value" returning array of contained annotation
    private static @interface AllowedRoles {

        RoleAllowed[] value();
    }

    //create an annotation
    //to make this annotation repeatable, we need to add annotation "Repeatable"
    //repeatable would be containing a class reference of another containing annotation.
    @Repeatable(AllowedRoles.class)
    private static @interface RoleAllowed {
        String role();
    }

}
