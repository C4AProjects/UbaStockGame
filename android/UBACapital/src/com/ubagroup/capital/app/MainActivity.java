package com.ubagroup.capital.app;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.ubagroup.capital.app.base.Act;
import com.ubagroup.capital.app.base.Screen;

public class MainActivity extends Act {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		
		replaceScreen(new WelcomeScreen(), false);
	}
	
	/**
	 * Convenience method to replace the current screen with another one.<br>
	 * If addToStack is <code>true</code> the screen being replaced will be kept on the backstack.<br>
	 * Pressing the back button will retrieve back this screen from the backstack.
	 * 
	 */
	public void replaceScreen(Screen screen, boolean addToStack) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragmentContainer, screen);
		if (addToStack)
			transaction.addToBackStack(null);
		transaction.commit();
	}
} 