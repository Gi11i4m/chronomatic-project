package be.artesis.timelog.externAuth;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.SwingUtilities;

import be.artesis.timelog.gui.LoginForm;

public class AuthBrowser {

	private WebEngine webEngine;
	private Group group;
	private Scene scene;
	private WebView webView;
	private final int BROWSERWIDTH = 715;
	private final int BROWSERHEIGHT = 450;
	private LoginForm loginDialog;
	private String Url;
	private SocialMedia social;

	public AuthBrowser(final LoginForm loginDialog, SocialMedia social) {
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

	private void startBrowser(final JFXPanel browserPanel) {
		group = new Group();
		scene = new Scene(group);
		browserPanel.setScene(scene);
		webView = new WebView();
		group.getChildren().add(webView);
		webView.setMinSize(BROWSERWIDTH, BROWSERHEIGHT);
		webView.setMaxSize(BROWSERWIDTH, BROWSERHEIGHT);
		webEngine = webView.getEngine();
		webEngine.load(Url);

		webEngine.locationProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> ov, String oldValue, final String newValue) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						String url = webEngine.getLocation();

						//Google
						if (url != null && url.startsWith("https://www.google.be/oauth2callback")) {
							exit();
							loginDialog.generateLoginExtern(url.substring(70));
						}
						//Linkedin
						if (url != null && url.startsWith("http://www.linkedin.com/chronomatic?code=")) {
							loginDialog.generateLoginExtern(url.substring(41, 156));
						}
						//Facebook
						if (url != null && url.startsWith("https://www.facebook.com/connect/login_success.html")) {
							loginDialog.generateLoginExtern(url.substring(65));
						}
						//Micosoft
						if (url != null && url.startsWith("http://www.dvl-sanitair.be/")) {
							loginDialog.generateLoginExtern(url.substring(33, 69));
						}
					}
				});
			}
		});
	}

	private void exit() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				webEngine.getLoadWorker().cancel();
				Platform.exit();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
					}
				});
			}
		});
	}

}
