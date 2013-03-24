package be.artesis.timelog.secure;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Voorbeeld_registry {

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 * @throws CertificateException 
	 * @throws KeyStoreException 
	 */
	public static void pain() throws NoSuchAlgorithmException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, CertificateException, IOException, KeyStoreException {
	
		
		WinRegistry.createKey(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\ChronoMatic");
		
		WinRegistry.writeStringValue(
				WinRegistry.HKEY_CURRENT_USER,
				"SOFTWARE\\ChronoMatic",
				"paswoord",
				"");
		
		String value = WinRegistry.readString (
			    WinRegistry.HKEY_CURRENT_USER,
			   "SOFTWARE\\ChronoMatic",
			   "paswoord");
			    System.out.println(value);
	}

}
