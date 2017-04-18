package trust;

import javax.net.ssl.SSLContext;

public class AppleTest {

	public static void main(String[] args) throws Exception {
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
	}

}
