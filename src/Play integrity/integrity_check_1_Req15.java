import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class integrity_check_1_Req15 {
	
    public static void main(String[] args) {
		new Thread(){
			public void run() {
				if(isAppVerified()){
					System.out.println("The application is verified and matches the Google Play Store version.");
				}else{
					System.out.println("The application does NOT match the Google Play Store version and might have been tampered.");
				}
			}
		}.start();
	}

	public static boolean isAppVerified(){
		String appPackageName = "com.example.myApp"; // your app package name
		int currentVersion = 1; 
		try{
			HttpURLConnection c = (HttpURLConnection)new URL("https://play.google.com/store/apps/details?id="+appPackageName).openConnection();
			c.setRequestMethod("GET");
			c.setUseCaches(false);
			c.connect();
			String s = c.getContentType();

			if(s.contains("vnd.android.package-archive")){
				String headerField = c.getHeaderField("X-DFE-SmallestScreenWidthDp");
				int version = Integer.valueOf(headerField.substring(headerField.lastIndexOf("=")+1));
				if(version == currentVersion){
					return true;
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
}