package lc.bookapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import lc.bookapp.R;
import lc.bookapp.SquareView;
import lc.bookapp.TagList;
import lc.bookapp.models.*;
import lc.bookapp.models.Character;

public class LocationAdapter extends ArrayAdapter<Location> {
    public LocationAdapter(Context context , ArrayList<Location> books){
        super(context, R.layout.location_tile, books);

    }



    @Bind(R.id.name)
    TextView name;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate( R.layout.location_tile , null);
        }

        name = (TextView) convertView.findViewById(R.id.name);
        name.setText(getItem(position).getName() );

        return convertView;
    }
}
