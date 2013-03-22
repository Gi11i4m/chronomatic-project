package be.artesis.timelog.externAuth;

import java.net.MalformedURLException;
import java.net.URL;

import be.artesis.timelog.gui.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;

import org.w3c.dom.Document;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class AuthBrowser {
	
	private WebEngine webEngine;
	private Group group;
	private Scene scene;
	private WebView webView;
	private final int BROWSERWIDTH = 720;
	private final int BROWSERHEIGHT = 490;
	private LoginDialog loginDialog;
	private String Url;
	private SocialMedia social;
	
	public AuthBrowser(final LoginDialog loginDialog, SocialMedia social) {
    	this.social = social;
    	this.loginDialog = loginDialog;
	}
    
    public void buildUrl() {
    	StringBuilder params = new StringBuilder("");
    	params.append(social.getAuthorizeTokenUrl());
        params.append("client_id=").append(social.getClientID());
        params.append("&scope=").append(social.getScope());
        params.append("&response_type=").append(social.getResponseType());
        params.append("&state=").append("DCEEFWF45453sdffef424"); // enkel Linkedin => state is soort van code
        params.append("&redirect_uri=").append(social.getRedirectUrl());
        
        Url = params.toString();
        //System.out.println(Url);
    }
	
	public void initBrowser(final JFXPanel browserPanel) {
        //this.pack();
        Platform.runLater(new Runnable() { // this will run initFX as JavaFX-Thread
            @Override
            public void run() {
                startBrowser(browserPanel);
            }
        });
    }
	
	private void startBrowser(final JFXPanel BrowserPanel) {
        group = new Group();
        scene = new Scene(group);
        BrowserPanel.setScene(scene);
        webView = new WebView();
        group.getChildren().add(webView);
        //webView.setMinSize(720, 500);
        webView.setMaxSize(BROWSERWIDTH, BROWSERHEIGHT);
        webEngine = webView.getEngine();
        
        webEngine.load(Url);
        
        //webEngine.javaScriptEnabledProperty();
        //Document doc = webEngine.getDocument();
        //System.out.println(doc.getLocalName());
        
        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String oldValue, final String newValue) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override public void run() {
                        String url = webEngine.getLocation();
                        if(url != null) {
                        	System.out.println("piet");
                        }
                        
                        
                        //Google
                        if(url != null && url.startsWith("https://www.google.be/oauth2callback")) {
                        	System.out.println(url.substring(42));
                        	/*try {
								URL i = new URL(url);
								i.getQuery();
								System.out.println(i);
								int kk = url.indexOf("code=");
								System.out.println(kk);
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
                            loginDialog.loginExtern(url.substring(70), "Google");
                            //exit(); 
                        }
                        //Linkedin
                        if(url != null && url.startsWith("http://www.linkedin.com/chronomatic?code=")) {
                        	//System.out.println(url.substring(41,156));
                            loginDialog.loginExtern(url.substring(41,156), "Linkedin");
                            //exit(); 
                        }
                        //Facebook
                        if(url != null && url.startsWith("https://www.facebook.com/connect/login_success.html")) {
                        	//System.out.println(url.substring(65, 195));
                            loginDialog.loginExtern(url.substring(65), "Facebook");
                            //exit(); 
                        }
                        //Micosoft
                        if(url != null && url.startsWith("http://www.dvl-sanitair.be/")) {
                        	//System.out.println(url.substring(33,69));
                            loginDialog.loginExtern(url.substring(33, 69), "Microsoft");
                            //exit(); 
                        }
                    }
                });
            }
        });
    }
	
	private void exit() {
        Platform.runLater( new Runnable(){
            @Override 
            public void run(){
                //System.err.println( "exit/runLater/run" );
                webEngine.getLoadWorker().cancel();
                Platform.exit();
                SwingUtilities.invokeLater( new Runnable(){ 
                    @Override 
                    public void run() {
                        //System.err.println( "exit/invokeLater/run" );
                        loginDialog.dispose();
                    }
                });
            }
        });
    }

}
