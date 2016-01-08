import java.lang.annotation.Repeatable;
public class Java8Annotation{
	public static void main(String[] args){


	}
	@RoleAllowed(role="Admin")
	@RoleAllowed(role="Manager")
	private static class DBAccess{

	}
	//AllowedRoles is a containing annotation type, which enables to get all the repeated roles as an array
	//This has to contain the method "value" returning array of contained annotation
	private static @interface AllowedRoles{
		
		RoleAllowed[] value();
	}
	//create an annotation 
	//to make this annotation repeatable, we need to add annotation "Repeatable"
	//repeatable would be containing a class reference of another containing annotation.
	@Repeatable(AllowedRoles.class)
	private static @interface RoleAllowed{
		String role();
	}

}
