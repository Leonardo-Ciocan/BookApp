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
import lc.bookapp.models.*;
import lc.bookapp.R;
import lc.bookapp.models.Character;

public class CharacterAdapter extends ArrayAdapter<Character> {
    public CharacterAdapter(Context context , ArrayList<Character> books){
        super(context, R.layout.book_tile, books);

    }

    @Bind(R.id.name) TextView name;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate( R.layout.char_tile , null);
        }
        ButterKnife.bind(convertView);
        name = (TextView) convertView.findViewById(R.id.name);
        name.setText(Core.characters.get(position).getName() +" " + Core.characters.get(position).getLastName());

        return convertView;
    }
}
