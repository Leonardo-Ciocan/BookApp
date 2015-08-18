package lc.bookapp;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;

import butterknife.ButterKnife;
import lc.bookapp.dialogs.NewCharacterDialog;
import lc.bookapp.fragments.CharactersFragment;
import lc.bookapp.fragments.EventFragment;
import lc.bookapp.fragments.LocationsFragment;
import lc.bookapp.fragments.OverviewFragment;
import lc.bookapp.models.Core;


public class BookActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    int selected = 0;
    private ArrayList<Fragment> fragments;

    String[] names = new String[]{"Overview" , "Characters" , "Locations" , "Events"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Core.selectedBook.getName());

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .addProfiles(
                        new ProfileDrawerItem().withName("user").withEmail("email").withIcon(new ColorDrawable(getResources().getColor(R.color.brandDark)))
                )
                .withHeaderBackground(new ColorDrawable(getResources().getColor(R.color.brand)))
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        fragments = new ArrayList<>();
        fragments.add(new OverviewFragment());
        fragments.add(new CharactersFragment());
        fragments.add(new LocationsFragment());
        fragments.add(new EventFragment());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragments.get(0)).commit();
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)

                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Overview"),
                        new PrimaryDrawerItem().withName("Characters"),
                        new PrimaryDrawerItem().withName("Locations"),
                        new PrimaryDrawerItem().withName("Events"),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Settings")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        selected = position;
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragments.get(position))
                                .commit();
                        getSupportActionBar().setTitle(names[position]);
                        return false;
                    }
                })
                .build();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.action_new){
            if(selected == 1){
                ((CharactersFragment)fragments.get(1)).showDialog();
            }
            else if(selected == 2){
                ((LocationsFragment)fragments.get(2)).showDialog();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
