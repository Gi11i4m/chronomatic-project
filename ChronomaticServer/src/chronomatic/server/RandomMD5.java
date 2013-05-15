package chronomatic.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RandomMD5 {

	public static String generate(String random) throws NoSuchAlgorithmException {
		
		MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
		digest.update(random.getBytes());
		byte[] hash = digest.digest();
		
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; ++i) {
          sb.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1,3));
        }
        
        String outputHash = sb.toString();
        return outputHash;
	}
}
