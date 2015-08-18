package lc.bookapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import lc.bookapp.R;
import lc.bookapp.SquareView;
import lc.bookapp.models.Event;
import lc.bookapp.models.Location;

public class EventAdapter  extends ArrayAdapter<Event> {
    public EventAdapter(Context context , ArrayList<Event> books){
        super(context, R.layout.event_tile, books);

    }

    @Bind(R.id.name)
    TextView name;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            //LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //convertView = inflater.inflate( R.layout.location_tile , null);
            convertView = new SquareView(getContext() , R.layout.event_tile);
        }

        name = (TextView) convertView.findViewById(R.id.name);
        name.setText(getItem(position).getName() );

        return convertView;
    }
}

