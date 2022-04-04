package JavaBasic;

public class systemProperties {
	
	public static void main(String[] args ) {
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		
		System.out.println(projectPath + "\\\\browserDrivers\\\\geckodriver.exe");
	}

}
