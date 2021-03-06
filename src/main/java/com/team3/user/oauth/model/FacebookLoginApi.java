package com.team3.user.oauth.model;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class FacebookLoginApi extends DefaultApi20 {
	protected FacebookLoginApi(){
 	}
 
 	private static class InstanceHolder{
 		private static final FacebookLoginApi INSTANCE = new FacebookLoginApi();
 	}
 	
 	
 	public static FacebookLoginApi instance(){
 		return InstanceHolder.INSTANCE;
 	}
 	
 	@Override
 	public String getAccessTokenEndpoint() {
 		return "https://graph.facebook.com/v2.12/oauth/access_token";
 	}

	@Override
	protected String getAuthorizationBaseUrl() {
		return "https://www.facebook.com/v2.12/dialog/oauth";
	}					
 
}
