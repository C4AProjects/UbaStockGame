package com.ubagroup.capital.app.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ubagroup.capital.app.R;

/**
 * Easy implementation of the FragmentActivity class with added benfits, such as easily adding 
 * Screens/Fragments and quickly toasting messages :D
 * 
 * @author saladthieves
 */
public class Act extends FragmentActivity {
	
	/**
	 * Replace the currently active Screen fragment with another one provided. 
	 * If addToStack is true, the one being replaced will stay in memory.
	 * 
	 * @param fragment
	 * @param addToStack
	 */
	public void replaceScreen(Fragment fragment, boolean addToStack) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragmentContainer, fragment);
		if (addToStack)
			transaction.addToBackStack(null);
		transaction.commit();
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
	
	public View getView(int id) {
		return findViewById(id);
	}
	
	/**
	 * Because we're very lazy developers.
	 * <code>Toast.makeText(blablabla)</code> is waay too long!
	 * @param message
	 */
	public void toast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
