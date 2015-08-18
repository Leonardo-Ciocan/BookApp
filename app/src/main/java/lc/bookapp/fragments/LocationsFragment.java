package lc.bookapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.CharacterActivity;
import lc.bookapp.LocationActivity;
import lc.bookapp.R;
import lc.bookapp.adapters.CharacterAdapter;
import lc.bookapp.adapters.LocationAdapter;
import lc.bookapp.dialogs.NewCharacterDialog;
import lc.bookapp.dialogs.NewLocationDialog;
import lc.bookapp.models.*;
import lc.bookapp.models.Character;

public class LocationsFragment extends android.support.v4.app.Fragment {
    public static LocationsFragment newInstance(String param1, String param2) {
        LocationsFragment fragment = new LocationsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public LocationsFragment() {
        // Required empty public constructor
    }

    public static LocationAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_locations, container, false);
        ButterKnife.bind(this, v);

        final GridView g = (GridView) v.findViewById(R.id.gridview);
        LocationAdapter a = new LocationAdapter(getActivity() , Core.locations);
        g.setAdapter(a);


        ParseQuery<Location> bookParseQuery = ParseQuery.getQuery(Location.class);
        bookParseQuery.whereEqualTo("book", Core.selectedBook);
        bookParseQuery.whereEqualTo("parent", null);
        bookParseQuery.orderByAscending("name");
        bookParseQuery.findInBackground(new FindCallback<Location>() {
            public void done(List<Location> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                Core.locations.clear();
                Core.locations.addAll(scoreList);
                adapter = new LocationAdapter(getActivity(), Core.locations);
                g.setAdapter(adapter);
            }
        });

        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Core.selectedLocation = Core.locations.get(position);
                Intent i = new Intent(getActivity() , LocationActivity.class);
                startActivity(i);
            }
        });


        return v;
    }

    NewLocationDialog dialog;
    @OnClick(R.id.add)
    public void showDialog(){
        if(dialog == null){
            dialog = new NewLocationDialog(getActivity() , adapter , null);
        }
        dialog.show();
    }


}
