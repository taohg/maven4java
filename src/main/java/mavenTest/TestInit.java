package mavenTest;

public class TestInit {

	public String commonProperty = null;
	public static String staticProperty = null;
	
	public TestInit(String common) {
		System.out.println("this is constructor section");
	}
	
	static {
		System.out.println("this is static section");
	}
	
	public static void staticMethod() {
		System.out.println("this is staticMethod section");
	}
	
	public static void main(String[] args) {
//		TestInit.staticMethod();
		System.out.println(TestInit.staticProperty);
	}

}
