package lc.bookapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import lc.bookapp.SquareView;
import lc.bookapp.TagList;
import lc.bookapp.models.*;
import lc.bookapp.R;
import lc.bookapp.models.Character;

public class CharacterAdapter extends ArrayAdapter<Character> {
    public CharacterAdapter(Context context , ArrayList<Character> books){
        super(context, R.layout.book_tile, books);

    }


    @Bind(R.id.tagContainer)
    TagList tags;

    @Bind(R.id.name) TextView name;
    @Bind(R.id.height) TextView height;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            //LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = new SquareView(getContext() , R.layout.char_tile);//inflater.inflate( R.layout.char_tile , null);
        }

        ButterKnife.bind(this , convertView);

        name = (TextView) convertView.findViewById(R.id.name);
        name.setText(Core.characters.get(position).getName() +" " + Core.characters.get(position).getLastName());

        height.setText(Core.characters.get(position).getHeight()+" cm");

        tags.setList(getItem(position).getTags());
        return convertView;
    }
}
