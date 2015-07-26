package lc.bookapp;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import lc.bookapp.adapters.BookAdapter;
import lc.bookapp.dialogs.NewBookDialog;
import lc.bookapp.models.*;


public class MainActivity extends ActionBarActivity {

    public static BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        newBookDialog= new NewBookDialog(this);
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.brand));
        }
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(Book.class);
        ParseObject.registerSubclass(Tag.class);
        ParseObject.registerSubclass(lc.bookapp.models.Character.class);
        Parse.initialize(this, "ipVyo9ZoDfz4klE7P6GWR3izeVeCYR9nr3nvqlTM",
                               "TYKaiNtXGmDSWy4sZ0dgDolABFgNjMaiVEuEHuZu");


        if(ParseUser.getCurrentUser() == null){
            try {
                ParseUser.logIn("leonardo","cake");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final GridView gridview = (GridView) findViewById(R.id.gridview);

        ParseQuery<Book> bookParseQuery = ParseQuery.getQuery(Book.class);
        bookParseQuery.whereEqualTo("user",ParseUser.getCurrentUser());
        bookParseQuery.orderByAscending("name");
        bookParseQuery.findInBackground(new FindCallback<Book>() {
            public void done(List<Book> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                Core.Books.clear();
                Core.Books.addAll(scoreList);
                adapter = new BookAdapter(MainActivity.this, Core.Books);
                gridview.setAdapter(adapter);
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Core.selectedBook = Core.Books.get(position);
                Intent i = new Intent(MainActivity.this , BookActivity.class);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    NewBookDialog newBookDialog ;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == R.id.action_new_book){
            newBookDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
