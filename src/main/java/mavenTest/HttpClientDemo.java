package mavenTest;

public class HttpClientDemo {

	public String me() throws Exception{
		String result = null;
		String str = "12a";
		int intVal = 0;
//		intVal = Integer.parseInt(str);
		try {
			intVal = Integer.parseInt(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			result = "me  -----catch";
			throw new Exception(e.getMessage());
		} finally {
			
			System.out.println("--me--finally---");
			return result;
		}
		
//		System.out.println("------me----end");
//		result = "end";
//		return result;
	}
	
	public static void main(String[] args)  {
		HttpClientDemo demo = new HttpClientDemo();
		String str = null;
		try {
			str = demo.me();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("----main------catch---");
			
		}
		System.out.println("---------str:" + str);
	}

}
