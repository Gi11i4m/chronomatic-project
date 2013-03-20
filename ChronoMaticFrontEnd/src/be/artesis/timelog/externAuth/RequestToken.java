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

public class RequestToken {
    
    public static String request(String code) throws IOException, JSONException {

        String token = "";
        String clientId = "131195431047.apps.googleusercontent.com";
        String clientSecret = "N2CHWpyn35OdOE1KqU_lXNx6";
        String callback = "https://www.google.be/oauth2callback";

        String tokenUrl = "https://accounts.google.com/o/oauth2/token";
        tokenUrl = "https://login.live.com/oauth20_token.srf";

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
System.out.println(test);
        test = test.replace(" ","");
        test = test.replace("null", "");

        JSONObject requestedJson = new JSONObject(test);

        token = requestedJson.getString("access_token");

        return token;
    }

}