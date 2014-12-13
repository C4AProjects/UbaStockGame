package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ubagroup.capital.app.base.Screen;

/**
 * Welcome Screen!!
 * @author saladthieves
 *
 */
public class WelcomeScreen extends Screen {
	
	private Button signInButton;
	private Button signUpButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.welcome_screen, container, false);
		
		getActionBar().setTitle("Welcome");
		getActionBar().setDisplayHomeAsUpEnabled(false);
		
		signInButton = (Button)getView(R.id.welcomeSignInButton);
		signInButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getParent().replaceScreen(new SignInScreen(), true);
			}
		});
		
		signUpButton = (Button)getView(R.id.welcomeSignUpButton);
		signUpButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getParent().replaceScreen(new SignUpScreen(), true);
			}
		});
		return root;
	}
}
