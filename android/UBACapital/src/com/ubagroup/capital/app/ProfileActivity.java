package com.ubagroup.capital.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ubagroup.capital.app.adapters.ScreenPagerAdapter;
import com.ubagroup.capital.app.base.Act;
import com.ubagroup.capital.app.base.Screen;

/**
 * Master activity that holds all the tabs used within the profile section of the application.<br>
 * 
 * All the tabs are hosted within a ViewPager, with some tabs, such as the News tab screen, containing
 * their own nested Fragments.
 * 
 * @author saladthieves
 *
 */
public class ProfileActivity extends Act {
	
	/**
	 * Represents the number of Screens (Fragments) that will be displayed by the ViewPager.<br>
	 * 
	 * This value is used by the ScreenPageAdapter.
	 */
	public static final int FRAGMENT_COUNT = 5;
	
	private ViewPager viewPager;
	private ScreenPagerAdapter screenPagerAdapter;
	
	private LinearLayout tabContainer;
	
	/**
	 * Special behavior for the action bar.<br>
	 * Use this behavior if you need to remove a nested screen, such as 
	 * removing a detailed news screen and going back to the list (if it was put on the backstack).
	 */
	public static final int BEHAVIOR_REMOVE_NESTED_FRAGMENT = 0x0;
	
	/**
	 * Default behavior of the action bar of the back button, which is to remove the current activity.
	 */
	public static final int BEHAVIOR_NORMAL = 0x1;
	
	private int actionBarBehavior;
	
	private Screen newsDetailScreen;
	private Screen stockDetailScreen;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		viewPager = (ViewPager)getView(R.id.viewPager);
		screenPagerAdapter = new ScreenPagerAdapter(getSupportFragmentManager(), this);
		viewPager.setAdapter(screenPagerAdapter);
		viewPager.setOnPageChangeListener(pageChangeListener);
		viewPager.setOffscreenPageLimit(FRAGMENT_COUNT);
		
		tabContainer = (LinearLayout)getView(R.id.tabContainer);
		initializeTabContainer();
		activateTab(0);
	}
	
	/**
	 * Set the news detail Screen that will be removed
	 */
	public void setNewsDetailScreen(Screen newsDetailScreen) {
		this.newsDetailScreen = newsDetailScreen;
	}
	
	/**
	 * Set the stock detail Screen that will be removed
	 */
	public void setStockDetailScreen(Screen stockDetailScreen) {
		this.stockDetailScreen = stockDetailScreen;
	}
	
	
	/**
	 * Retrieve the news screen that will be removed
	 */
	public Screen getNewsDetailScreen() {
		return newsDetailScreen;
	}
	
	/**
	 * Retrieve the stock screen that will be removed
	 */
	public Screen getStockDetailScreen() {
		return newsDetailScreen;
	}

	
	/**
	 * Set the action bar behavior for the back button to either remove a detailed screen or behave normally.
	 * @param actionBarBehavior could be {@link ProfileActivity#BEHAVIOR_NORMAL} to make the action bar 
	 * act normally or {@link ProfileActivity#BEHAVIOR_REMOVE_NESTED_FRAGMENT} to remove a nested fragment.
	 */
	public void setActionBarBackButtonBehavior(int actionBarBehavior) {
		this.actionBarBehavior = actionBarBehavior;
	}
	
	/**
	 * Return the action bar behavior for the back button
	 * @return the action bar back button behavior, either {@link ProfileActivity#BEHAVIOR_NORMAL} or 
	 * {@link ProfileActivity#BEHAVIOR_REMOVE_NESTED_FRAGMENT}.
	 */
	public int getActionBarBackButtonBehavior() {
		return actionBarBehavior;
	}
	
	/**
	 * Removes the news detailed nested screen and returns back to the list version.<br>
	 * This method is called after setting the action bar back button behavior to the {@link ProfileActivity#BEHAVIOR_REMOVE_NESTED_FRAGMENT} flag. 
	 */
	public void removeNewsDetailedNestedScreen() {
		if (newsDetailScreen == null) return;
		FragmentTransaction transaction = newsDetailScreen.getChildFragmentManager().beginTransaction();
		transaction.remove(newsDetailScreen);
		transaction.commit();
		setActionBarBackButtonBehavior(BEHAVIOR_NORMAL);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		newsDetailScreen = null;
	}
	
	/**
	 * Removes the stock detailed nested screen and returns back to the list version.<br>
	 * This method is called after setting the action bar back button behavior to the {@link ProfileActivity#BEHAVIOR_REMOVE_NESTED_FRAGMENT} flag. 
	 */
	public void removeStockDetailedNestedScreen() {
		if (stockDetailScreen == null) return;
		FragmentTransaction transaction = stockDetailScreen.getChildFragmentManager().beginTransaction();
		transaction.remove(stockDetailScreen);
		transaction.commit();
		setActionBarBackButtonBehavior(BEHAVIOR_NORMAL);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		stockDetailScreen = null;
	}
	
	/**
	 * Handle changes of what happens when a page is changed on the ViewPager
	 */
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		@Override public void onPageScrollStateChanged(int arg0) {}
		@Override public void onPageScrolled(int arg0, float arg1, int arg2) {}
		@Override
		public void onPageSelected(int position) {
    		setActionBarTitle(position);
    		activateTab(position);
			if (position == 2) {
				setActionBarBackButtonBehavior(stockDetailScreen != null ? BEHAVIOR_REMOVE_NESTED_FRAGMENT : BEHAVIOR_NORMAL);
				getActionBar().setDisplayHomeAsUpEnabled(stockDetailScreen != null);
			} else if (position == 3) {
				setActionBarBackButtonBehavior(newsDetailScreen != null ? BEHAVIOR_REMOVE_NESTED_FRAGMENT : BEHAVIOR_NORMAL);
				getActionBar().setDisplayHomeAsUpEnabled(newsDetailScreen != null);
			} else {
				setActionBarBackButtonBehavior(BEHAVIOR_NORMAL);
				getActionBar().setDisplayHomeAsUpEnabled(false);
			}
		}
    };
    
    /**
     * Set up the tab container and register click listeners for them to automatically
     * change the screen when clicked.
     */
    private void initializeTabContainer() {
    	for (int i = 0; i < tabContainer.getChildCount(); i++) {
    		LinearLayout tabItemContainer = (LinearLayout)tabContainer.getChildAt(i);
    		tabItemContainer.setOnClickListener(tabSelectionListener);
    	}
    }
    
    /**
     * Handle what happens when a tab is selected;
     */
    private View.OnClickListener tabSelectionListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			int position = getTabPosition((LinearLayout)v);
			viewPager.setCurrentItem(position);
			activateTab(position);
		}
	};
	
	/**
	 * Return the tab <code>int</code> position based on the label that the tabItemContainer has 
	 * @return the position of the tab
	 */
	private int getTabPosition(LinearLayout tabItemContainer) {
		String title = ((TextView)tabItemContainer.getChildAt(1)).getText().toString().trim();
		if (title.equals("Portfolio")) return 0;
		else if (title.equals("Trivia")) return 1;
		else if (title.equals("Trade")) return 2;
		else if (title.equals("News")) return 3;
		else return 4;
	}
	
	/**
	 * Activated the tab at the given position.<br>
	 * This does nothing to change the gab content, but it swaps out the appropriate tab image.
	 * 
	 * @param position
	 */
	private void activateTab(int position) {
    	for (int i = 0; i < tabContainer.getChildCount(); i++) {
    		LinearLayout tabItemContainer = (LinearLayout)tabContainer.getChildAt(i);
    		ImageView image = (ImageView)tabItemContainer.getChildAt(0);
    		image.setImageDrawable(getResources().getDrawable(getDrawableFromPosition(i, i == position)));
    		TextView title = (TextView)tabItemContainer.getChildAt(1);
    		title.setTextColor(getResources().getColor(i == position ? R.color.white : R.color.red));
    	}
	}
	
	/**
	 * Returns an appropriate image based on the tab position as well as its selected/unselected version
	 * @param position to use as the tab position.
	 * @param activated status of the tab (activated or not)
	 * @return the <code>int</code> resource for the drawable
	 */
	private int getDrawableFromPosition(int position, boolean activated) {
		if (position == 0) 
			return activated ? R.drawable.tab_icon_portfolio_active : R.drawable.tab_icon_portfolio_inactive;
		if (position == 1) 
			return activated ? R.drawable.tab_icon_trivia_acitve : R.drawable.tab_icon_trivia_inacitve;
		if (position == 2) 
			return activated ? R.drawable.tab_icon_trade_acitve : R.drawable.tab_icon_trade_inacitve;
		if (position == 3) 
			return activated ? R.drawable.tab_icon_news_acitve : R.drawable.tab_icon_news_inacitve;
		return activated ? R.drawable.tab_icon_history_acitve : R.drawable.tab_icon_history_inacitve;
	}
    
    @Override
    public void onBackPressed() {
    	int currentItem = viewPager.getCurrentItem();
    	if (currentItem == 0) {
    		super.onBackPressed();
    	} else {
    		viewPager.setCurrentItem(currentItem - 1);
    	}
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
        	if (actionBarBehavior == BEHAVIOR_NORMAL)
            	onBackPressed();
        	else {
        		int position = viewPager.getCurrentItem();
        		if (position == 2) {
            		removeStockDetailedNestedScreen();
        		} else if (position == 3) {
        			removeNewsDetailedNestedScreen();
        		}
        	}
        	return true;
        case R.id.action_settings:
        	startActivity(new Intent(this, SettingsActivity.class));
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.profile, menu);
    	return true;
	}

    /**
     * Change the action bar title. <br>
     * Convenience of when the tabs are changed, so that the action bar title reflects the changes as well
     * @param position
     */
	private void setActionBarTitle(int position) {
    	String title;
    	switch (position) {
    	case 0 : title = "Profile"; break;
    	case 1 : title = "Trivia"; break;
    	case 2 : title = "Trade"; break;
    	case 3 : title = "News"; break;
    	default : title = "History";
    	}
    	getActionBar().setTitle(title);
    }
}
