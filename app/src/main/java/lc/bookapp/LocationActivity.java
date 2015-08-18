package lc.bookapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import lc.bookapp.adapters.LocationAdapter;
import lc.bookapp.dialogs.NewLocationDialog;
import lc.bookapp.models.Core;
import lc.bookapp.models.Location;


public class LocationActivity extends ActionBarActivity {

    @Bind(R.id.locationName)
    TextView locationName;

    @Bind(R.id.description)
    TextView description;

    @Bind(R.id.childrenLocations)
    GridView childrenLocations;

    @Bind(R.id.locationHeader)
    TextView locationHeader;

    Location current;
    private LocationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ButterKnife.bind(this);

        current = Core.selectedLocation;
        locationName.setText(current.getName());
        description.setText(current.getDescription());

        ParseQuery<Location> bookParseQuery = ParseQuery.getQuery(Location.class);
        bookParseQuery.whereEqualTo("parent", current);
        bookParseQuery.orderByAscending("name");
        bookParseQuery.findInBackground(new FindCallback<Location>() {
            public void done(List<Location> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                adapter = new LocationAdapter(LocationActivity.this, (ArrayList) scoreList);
                childrenLocations.setAdapter(adapter);
            }
        });

        childrenLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Core.selectedLocation = adapter.getItem(position);
                Intent i = new Intent(LocationActivity.this, LocationActivity.class);
                startActivity(i);
            }
        });

        locationHeader.setText("LOCATIONS IN " + current.getName().toUpperCase());
    }

    @OnTextChanged(R.id.description)
    void descriptionChanged(CharSequence text) {
        Core.selectedLocation.setDescription(text.toString());
    }

    @OnTextChanged(R.id.locationName)
    void nameChanged(CharSequence text) {
        Core.selectedLocation.setName(text.toString());
    }

    NewLocationDialog dialog;
    @OnClick(R.id.add)
    void add(){
        if(dialog == null){
            dialog = new NewLocationDialog(this , adapter , current);
        }
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        current.saveInBackground();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        current.saveInBackground();
        super.onPause();
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

        return super.onOptionsItemSelected(item);
    }
}
