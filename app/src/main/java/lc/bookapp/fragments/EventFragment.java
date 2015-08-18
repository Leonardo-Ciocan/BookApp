package lc.bookapp.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.LocationActivity;
import lc.bookapp.R;
import lc.bookapp.adapters.EventAdapter;
import lc.bookapp.adapters.LocationAdapter;
import lc.bookapp.dialogs.NewCharacterDialog;
import lc.bookapp.dialogs.NewEventDialog;
import lc.bookapp.models.Core;
import lc.bookapp.models.Event;
import lc.bookapp.models.Location;

public class EventFragment extends android.support.v4.app.Fragment {


    private EventAdapter a;

    public static EventFragment newInstance() {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this,v);

        final GridView g = (GridView) v.findViewById(R.id.gridview);
        a = new EventAdapter(getActivity() , Core.events);
        g.setAdapter(a);


        ParseQuery<Event> bookParseQuery = ParseQuery.getQuery(Event.class);
        bookParseQuery.whereEqualTo("book", Core.selectedBook);
        bookParseQuery.orderByAscending("name");
        bookParseQuery.findInBackground(new FindCallback<Event>() {
            public void done(List<Event> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                Core.events.clear();
                Core.events.addAll(scoreList);
                a.notifyDataSetChanged();
            }
        });

        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Core.selectedEvent = Core.events.get(position);
                Intent i = new Intent(getActivity(), LocationActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

    NewEventDialog dialog;

    @OnClick(R.id.add)
    public void showDialog(){
        if(dialog == null){
            dialog = new NewEventDialog(getActivity() , a);
        }
        dialog.show();
    }
}
