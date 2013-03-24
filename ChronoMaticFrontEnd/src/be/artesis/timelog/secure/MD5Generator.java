package be.artesis.timelog.secure;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Generator {

	public static String gen(String pass) throws NoSuchAlgorithmException {
		final String secret = "|@@#[{{^^ии45646 44.4654f 4r-CHRONOMATIC-e4f8r7щ$^,;:=:;,&й''' '''и--@#^!846^{и {!{!и";
		MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
		pass += secret;
		digest.update(pass.getBytes());
		byte[] hash = digest.digest();
		
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hash.length; ++i) {
          sb.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1,3));
        }
        
        return sb.toString();
	}

}
