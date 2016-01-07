package com.example.radud.androidhardwarestore.ui.activities;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.radud.androidhardwarestore.R;
import com.example.radud.androidhardwarestore.model.NavigationDrawerItem;
import com.example.radud.androidhardwarestore.ui.adapters.NavigationDrawerAdapter;
import com.example.radud.androidhardwarestore.ui.fragments.BaseFragment;
import com.example.radud.androidhardwarestore.ui.fragments.CartFragment;
import com.example.radud.androidhardwarestore.ui.fragments.CategoriesFragment;
import com.example.radud.androidhardwarestore.ui.fragments.OrdersFragment;
import com.example.radud.androidhardwarestore.ui.fragments.SettingsFragment;
import com.example.radud.androidhardwarestore.ui.fragments.SubscriptionsFragment;
import com.example.radud.androidhardwarestore.utils.SessionUtils;
import com.example.radud.androidhardwarestore.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by radud on 02/01/2016.
 */
public class HomeActivity extends BaseActivity {

    private static final String[] HOME_LEFT_MENU_ITEMS = UIUtils.getResources().getStringArray(R.array.home_left_drawer_items);
    private static final TypedArray HOME_LEFT_MENU_ICONS = UIUtils.getResources().obtainTypedArray(R.array.home_left_drawer_icons);
    private final static int MENU_CLOSE_DELAY = 150;

    @Bind(R.id.ah_drawer_layout)
    DrawerLayout mDrawer;

    @Bind(R.id.ah_drawer_list_lv)
    ListView mDrawerLV;

    @Bind(R.id.ah_drawer_rl)
    RelativeLayout mDrawerLayoutRL;

    private NavigationDrawerAdapter mDrawerAdapter;
    private List<NavigationDrawerItem> mDrawerItems;
    private ActionBarDrawerToggle mDrawerToggle;
    private Runnable mMenuCloseRunnable;

    private int mSelectedPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setUpDrawer();

    }

    private void setUpDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
                mToolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawer.setScrimColor(UIUtils.getColor(R.color.white));
        mDrawerToggle.syncState();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    onBackPressed();
                } else {
                    if (mDrawer.isDrawerOpen(mDrawerLayoutRL)) {
                        mDrawer.closeDrawer(mDrawerLayoutRL);
                    } else {
                        mDrawer.openDrawer(mDrawerLayoutRL);
                    }
                }
            }
        });

        setDefaultContent();

        mDrawerItems = new ArrayList<>();
        mDrawerItems.add(new NavigationDrawerItem(HOME_LEFT_MENU_ITEMS[0], HOME_LEFT_MENU_ICONS.getResourceId(0, -1)));
        mDrawerItems.add(new NavigationDrawerItem(HOME_LEFT_MENU_ITEMS[1], HOME_LEFT_MENU_ICONS.getResourceId(1, -1)));
        mDrawerItems.add(new NavigationDrawerItem(HOME_LEFT_MENU_ITEMS[2], HOME_LEFT_MENU_ICONS.getResourceId(2, -1)));
        mDrawerItems.add(new NavigationDrawerItem(HOME_LEFT_MENU_ITEMS[3], HOME_LEFT_MENU_ICONS.getResourceId(3, -1)));
        mDrawerItems.add(new NavigationDrawerItem(HOME_LEFT_MENU_ITEMS[4], HOME_LEFT_MENU_ICONS.getResourceId(4, -1)));
        mDrawerItems.add(new NavigationDrawerItem(HOME_LEFT_MENU_ITEMS[5], HOME_LEFT_MENU_ICONS.getResourceId(5, -1)));

        mDrawerAdapter = new NavigationDrawerAdapter(this, mDrawerItems);

        mDrawerLV.setAdapter(mDrawerAdapter);

        mDrawerLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectedPos = position;
                mDrawerAdapter.setSelectedPosition(position);
                mDrawerAdapter.notifyDataSetChanged();

                BaseFragment newFragment = null;

                switch (position) {
                    case 0:
                        newFragment = new CategoriesFragment();
                        break;
                    case 1:
                        newFragment = new CartFragment();
                        break;
                    case 2:
                        newFragment = new OrdersFragment();
                        break;
                    case 3:
                        newFragment = new SubscriptionsFragment();
                        break;
                    case 4:
                        newFragment = new SettingsFragment();
                        break;
                    case 5:
                        logout();
                        break;

                    default:
                        throw new IllegalArgumentException("Apparently you use the wrong menuListener");
                }

                if (newFragment != null) {
                    switchContent(newFragment, false);
                    scheduleMenuClosing();
                }
            }
        });

        /* Setting default fragment */
        mSelectedPos = 0;

    }

    private void setDefaultContent() {
        switchContent(new CategoriesFragment(), false);
    }

    private void logout() {
        SessionUtils.clearUserSession();
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void scheduleMenuClosing() {
        if (mMenuCloseRunnable == null) {
            mMenuCloseRunnable = new Runnable() {
                @Override
                public void run() {
                    mDrawer.closeDrawers();
                }
            };
        }

        mDrawer.postDelayed(mMenuCloseRunnable, MENU_CLOSE_DELAY);
    }

    public void switchContent(Fragment newFragment, boolean addToBackStack) {
        FragmentManager manager = getSupportFragmentManager();

        // Clear previous backstack
        if (!addToBackStack) {
            int count = manager.getBackStackEntryCount();

            if (count > 0) {
                int id = manager.getBackStackEntryAt(count - 1).getId();
                manager.popBackStack(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ah_content_fl, newFragment)
                .setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);

        if (addToBackStack) {
            transaction.addToBackStack(newFragment.getClass().getSimpleName());
        }

        transaction.commitAllowingStateLoss();
        updateDrawerToggleState(addToBackStack);
    }


    private void updateDrawerToggleState(boolean showArrow) {
        int lockMode = showArrow ? DrawerLayout.LOCK_MODE_LOCKED_CLOSED
                : DrawerLayout.LOCK_MODE_UNLOCKED;

        mDrawer.setDrawerListener(showArrow ? null : mDrawerToggle);
        mDrawer.setDrawerLockMode(lockMode, mDrawerLayoutRL);

        animateToggle(showArrow);
    }

    private void animateToggle(boolean showArrow) {
        ValueAnimator anim = ValueAnimator.ofFloat(showArrow ? 0 : 1, showArrow ? 1 : 0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                mDrawerToggle.onDrawerSlide(mDrawerLayoutRL, slideOffset);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(400);
        anim.start();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}
