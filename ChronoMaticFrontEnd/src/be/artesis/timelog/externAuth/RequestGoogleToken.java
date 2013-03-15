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

public class RequestGoogleToken {
    
    public static String request(String code) throws IOException, JSONException {

        String token = "";
        String clientSecret = "jEMJUo-qwpxCspCgnpxwn_s2";
        String clientId = "536253651406.apps.googleusercontent.com";
        String callback = "urn:ietf:wg:oauth:2.0:oob";

        String tokenUrl = new String("https://accounts.google.com/o/oauth2/token");

        StringBuilder params = new StringBuilder("");
        params.append("code=").append(URLEncoder.encode(code, "UTF-8")); 
        params.append("&client_id=").append(clientId);
        params.append("&client_secret=").append(clientSecret);
        params.append("&redirect_uri=").append(callback);
        params.append("&grant_type=authorization_code");


        // Verzend data
        URL url = new URL(tokenUrl.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", Integer.toString(params.toString().length()));
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

        String test = null;
        while ((line = rd.readLine()) != null)
        {
            test+=(line);
        }

        rd.close();

        test = test.replace(" ","");
        test = test.replace("null", "");

        JSONObject requestedJson = new JSONObject(test);

        token = requestedJson.getString("access_token");

        return token;
    }

}