package lc.bookapp.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.R;
import lc.bookapp.models.*;
import lc.bookapp.models.Character;

public class TagAdapter extends ArrayAdapter<Tag> {
    public TagAdapter(Context context , List<Tag> books){
        super(context, R.layout.book_tile, books);
    }

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.tagColor)
    FrameLayout tagColor;

    @Bind(R.id.delete)
    ImageView delete;

    @OnClick(R.id.delete)
    void delete(View v){
        int tag = (int)v.getTag();
        //Core.selectedCharacter.getTags().remove(getItem(tag));
        remove(getItem(tag));
        Core.selectedCharacter.saveInBackground();


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate( R.layout.tag_tile , null);
        }

        ButterKnife.bind(this, convertView);

        delete.setTag( position);

        try {
            getItem(position).fetchIfNeeded();
            name.setText(getItem(position).getName());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GradientDrawable d =(GradientDrawable) tagColor.getBackground();
        try {
            getItem(position).fetchIfNeeded();
            d.setColor(Integer.parseInt(getItem(position).getColor()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }


}
