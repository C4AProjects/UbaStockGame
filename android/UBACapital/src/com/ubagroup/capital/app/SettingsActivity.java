package com.ubagroup.capital.app;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ubagroup.capital.app.base.Act;

public class SettingsActivity extends Act {
	
	private LinearLayout settingsRowsContainer;
	private Button saveSettingsButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_screen);
		
		settingsRowsContainer = (LinearLayout)getView(R.id.settingsRowsContainer);
		for (int i = 0; i < settingsRowsContainer.getChildCount(); i++) {
			if (i <= 1) continue;
			settingsRowsContainer.getChildAt(i).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
		}
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		saveSettingsButton = (Button)getView(R.id.saveSettingsButton);
		saveSettingsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
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
