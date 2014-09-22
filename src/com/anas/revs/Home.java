/**
 * Commiting
 */

package com.anas.revs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;

	public DrawerLayout drawer;
	DisplayMetrics metrics;
	int width;
	ActionBarDrawerToggle mDrawerToggle ;
	// Button btn;

	HashMap<String, List<String>> listDataChild;
	List<String> listDataHeader;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		//getActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		 
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerToggle = new ActionBarDrawerToggle(
	            this,                  /* host Activity */
	            drawer, /* DrawerLayout object */
	           R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
	            R.string.drawer_open,  /* "open drawer" description */
	            R.string.drawer_close  /* "close drawer" description */
	            ) { 
	 
	        /** Called when a drawer has settled in a completely closed state. */ 
	        public void onDrawerClosed(View view) {
//	            getActionBar().setTitle(R.string.title_activity_add);
	        } 
	 
	        /** Called when a drawer has settled in a completely open state. */ 
	        public void onDrawerOpened(View drawerView) {
//	            getActionBar().setTitle(R.string.drawer_title);
	        } 
	    }; 
	    drawer.setDrawerListener(mDrawerToggle);
	    getActionBar().setDisplayHomeAsUpEnabled(true); // Pressing the app icon in the action bar will navigate to the parent activity.
	    getActionBar().setHomeButtonEnabled(true);
		prepareListData();
		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
		expListView.setAdapter(listAdapter);
		expListView.setGroupIndicator(null);
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();
			}
		});
		expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
								+ " : "
								+ listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
						.show();
				return false;
			}
		});
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	@Override 
	public void onConfigurationChanged(Configuration newConfig) { 
	    super.onConfigurationChanged(newConfig); 
	    mDrawerToggle.onConfigurationChanged(newConfig);
	} 
	 
	@Override 
	public boolean onOptionsItemSelected(MenuItem item) {
	     // Pass the event to ActionBarDrawerToggle, if it returns 
	     // true, then it has handled the app icon touch event 
	     if (mDrawerToggle.onOptionsItemSelected(item)) {
	         return true; 
	     } 
	     // Handle your other action bar items... 
	 
	     return super.onOptionsItemSelected(item);
	} 
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();
		listDataHeader.add("Home");
		listDataHeader.add("About MPM Oils");
		listDataHeader.add("Products");
		listDataHeader.add("News & Updates");
		listDataHeader.add("Contact Us");
		
		
		List<String> home = new ArrayList<String>();

		List<String> aboutOils = new ArrayList<String>();
		

		List<String> products = new ArrayList<String>();
		products.add("Motor Oils");
		products.add("Motor Oils (High Performance)");
		products.add("Automatic Transmission Fluids (ATF)");
		products.add("Gear Oils");
		products.add("Coolants");
		products.add("Hydrolics and Power Steering Fluids");
		products.add("Break Fluids");
		products.add("Aerosols");
		products.add("Motorcycles");
		
		
		List<String> news = new ArrayList<String>();
		news.add("Why choose OEM Oils");
		news.add("Missed Opportunities for Wrokshops");
		
		List<String> contactUs = new ArrayList<String>();
		

		listDataChild.put(listDataHeader.get(0), home); // Header, Child data
		listDataChild.put(listDataHeader.get(1), aboutOils);
		listDataChild.put(listDataHeader.get(2), products);
		listDataChild.put(listDataHeader.get(3), news);
		listDataChild.put(listDataHeader.get(4), contactUs);
	}
	class ExpandableListAdapter extends BaseExpandableListAdapter {
		 
		private Context _context;
		private List<String> _listDataHeader;
		private HashMap<String, List<String>> _listDataChild;
		 
		public ExpandableListAdapter(Context context, List<String> listDataHeader,
		        HashMap<String, List<String>> listChildData) {
		    this._context = context;
		    this._listDataHeader = listDataHeader;
		    this._listDataChild = listChildData;
		} 
		 
		@Override 
		public Object getChild(int groupPosition, int childPosititon) {
		    return this._listDataChild.get(this._listDataHeader.get(groupPosition))
		            .get(childPosititon);
		} 
		 
		@Override 
		public long getChildId(int groupPosition, int childPosition) {
		    return childPosition;
		} 
		 
		@Override 
		public View getChildView(int groupPosition, final int childPosition,
		        boolean isLastChild, View convertView, ViewGroup parent) {
		 
		    final String childText = (String) getChild(groupPosition, childPosition);
		 
		    if (convertView == null) {
		        LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        convertView = infalInflater.inflate(R.layout.list_item, null);
		    } 
		 
		    TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
		 
		    txtListChild.setText(childText);
		    return convertView;
		} 
		 
		@Override 
		public int getChildrenCount(int groupPosition) {
		    return this._listDataChild.get(this._listDataHeader.get(groupPosition))
		            .size();
		} 
		 
		@Override 
		public Object getGroup(int groupPosition) {
		    return this._listDataHeader.get(groupPosition);
		} 
		 
		@Override 
		public int getGroupCount() { 
		    return this._listDataHeader.size();
		} 
		 
		@Override 
		public long getGroupId(int groupPosition) {
		    return groupPosition;
		} 
		 
		@Override 
		public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		    String headerTitle = (String) getGroup(groupPosition);
		    int iconId = 0;  // assume we have an icon we want to have on the left of each group
		    int expandableGroupResId = 0;
		 
		    if (convertView == null) {
		        LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		        convertView = infalInflater.inflate(R.layout.list_group, null);
		    } 
		 
		    TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
		    lblListHeader.setTypeface(null, Typeface.BOLD);
		    lblListHeader.setText(headerTitle);
		    
		    if (getChildrenCount(groupPosition) > 0) {
		        if (isExpanded) expandableGroupResId = android.R.drawable.arrow_up_float;
		        else expandableGroupResId = android.R.drawable.arrow_down_float;
		    } 
		    lblListHeader.setCompoundDrawablesWithIntrinsicBounds(iconId, 0, expandableGroupResId, 0);
		 
		    return convertView;
		} 
		 
		@Override 
		public boolean hasStableIds() { 
		    return false; 
		} 
		 
		@Override 
		public boolean isChildSelectable(int groupPosition, int childPosition) {
		    return true; 
		} 
	}
}