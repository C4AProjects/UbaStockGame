package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubagroup.capital.app.base.Screen;

/**
 * Sign up screen
 * @author saladthieves
 *
 */
public class SignUpScreen extends Screen {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.sign_up_screen, container, false);
		getActionBar().setTitle("Sign Up");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		return root;
	}
}
