package be.artesis.timelog.externAuth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public class AccessToken {
    
    public static String request(String code, SocialMedia social) throws IOException, JSONException {

    	// maak de POST query
        StringBuilder params = new StringBuilder();
        params.append("code=").append(URLEncoder.encode(code, "UTF-8"));
        params.append("&client_id=").append(social.getClientID());
        params.append("&redirect_uri=").append(social.getRedirectUrl());
        params.append("&client_secret=").append(social.getClientSecret());
        params.append("&grant_type=authorization_code");


        // Verzend data
        URL url = new URL(social.getAccessTokenUrl());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        //conn.setRequestProperty("Content-Length", Integer.toString(params.toString().length())); // blijkbaar ni nodig
        conn.connect();


        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(params.toString());
        wr.flush();
        wr.close();
        // Get the response
        InputStream is;
        if (conn.getResponseCode() == 200) {
            is = conn.getInputStream();
        } else {
            is = conn.getErrorStream();
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;

        String test = "";
        while ((line = rd.readLine()) != null) {
            test+=(line);
        }

        rd.close();
        
        test = test.replace(" ","");
        test = test.replace("null", "");
        //System.out.println(test);
        JSONObject requestedJson = new JSONObject(test);

        return requestedJson.getString("access_token");
    }

}