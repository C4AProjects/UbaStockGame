package com.ubagroup.capital.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.ubagroup.capital.app.base.Screen;

/**
 * Sign in screen
 * @author saladthieves
 *
 */
public class SignInScreen extends Screen {
	
	private Button signInButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.sign_in_screen, container, false);
		
		signInButton = (Button)getView(R.id.signInButton);
		signInButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getParent(), ProfileActivity.class));
			}
		});
		
		getActionBar().setTitle("Sign In");
		getActionBar().setDisplayHomeAsUpEnabled(true);

		return root;
	}
}
