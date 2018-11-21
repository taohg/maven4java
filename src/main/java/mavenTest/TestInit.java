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
		//先静态块，后构造方法
		TestInit t = new TestInit("");
		TestInit.staticMethod();
		System.out.println("----"+TestInit.staticProperty);
//		boolean b = Boolean.valueOf("TrU");
//		if(b) {
//			System.out.println("wo s 1");
//		}
	}

}
