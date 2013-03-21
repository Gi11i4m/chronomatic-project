package be.artesis.timelog.externAuth;

import java.io.IOException;

import org.json.JSONException;

public class Test {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, JSONException {
		// TODO Auto-generated method stub

		String piet = AccessToken.request("AQRwYPcvYhNoPkgKnYveRWwoqyOw_u-vSNgUaKHf0ixXFce6IWXZQ8sB0D38z0Q8UdXNNsoy2ZtyIaFV4mEKFQzxPZFsmlg_J-u5nl5t08vy6tyDLFs", new Linkedin());
	
		System.out.println(piet);
	}

}
