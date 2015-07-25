package lc.bookapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import lc.bookapp.R;
import lc.bookapp.models.Book;

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context , ArrayList<Book> books){
        super(context , R.layout.book_tile , books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate( R.layout.book_tile , null);
        }
        CardView cardView = (CardView)convertView.findViewById(R.id.card_view);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        cardView.setCardBackgroundColor(getContext().getResources().getColor(R.color.brand));

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(getItem(position).getName());
        return convertView;
    }
}
