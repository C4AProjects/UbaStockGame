package com.ubagroup.capital.app.base;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Convenience class that acts as a Fragment. Think of it as a Fragments with benefits
 * 
 * @author saladthieves
 */
public abstract class Screen extends Fragment {
	
	/**
	 * Root view of this Screen object
	 */
	protected View root;
	
	
	/**
	 * This does the same thing as <code>root.findViewById(int id)</code> but we're too 
	 * lazy to type too much code!
	 * 
	 * @param id
	 * @return
	 */
	public View getView(int id) {
		return root.findViewById(id);
	}
	
	
	/**
	 * Get the ActionBar (from the <code>Screen Fragment</code>.
	 * @return
	 */
	public ActionBar getActionBar() {
		return getActivity().getActionBar();
	}

	/**
	 * Retrieve the <code>Activity</code> as an Act instance, for things such as replacing other fragments etc.
	 * @return
	 */
	public Act getParent() {
		return (Act)getActivity();
	}
	
	public void toast(String message) {
		getParent().toast(message);
	}
}
