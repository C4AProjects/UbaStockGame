package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.MenuItem;

import com.ubagroup.capital.app.base.Act;

public class MainActivity extends Act {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main);
		
		replaceScreen(new ExperimentScreen(), false);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	onBackPressed();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
} 