package lc.bookapp.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.CharacterActivity;
import lc.bookapp.R;
import lc.bookapp.adapters.BookAdapter;
import lc.bookapp.adapters.CharacterAdapter;
import lc.bookapp.dialogs.NewCharacterDialog;
import lc.bookapp.models.*;
import lc.bookapp.models.Character;


public class CharactersFragment extends android.support.v4.app.Fragment {

    public static CharactersFragment newInstance() {
        CharactersFragment fragment = new CharactersFragment();
        return fragment;
    }

    public CharactersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Bind(R.id.gridview)
    GridView gridview;


    public static CharacterAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_characters, container, false);

        ButterKnife.bind(this,v);
        gridview = (GridView) v.findViewById(R.id.gridview);

        ParseQuery<Character> bookParseQuery = ParseQuery.getQuery(Character.class);
        bookParseQuery.whereEqualTo("book", Core.selectedBook);
        bookParseQuery.orderByAscending("name");
        bookParseQuery.findInBackground(new FindCallback<Character>() {
            public void done(List<Character> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
                Core.characters.clear();
                Core.characters.addAll(scoreList);
                adapter = new CharacterAdapter(getActivity(), Core.characters);
                gridview.setAdapter(adapter);
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Core.selectedCharacter = Core.characters.get(position);
                Intent i = new Intent(getActivity(), CharacterActivity.class);
                startActivity(i);
            }
        });

        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                /*Core.selectedCharacter = Core.characters.get(position);
                Intent i = new Intent(getActivity(), CharacterActivity.class);
                startActivity(i);*/
                return false;
            }
        });

        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    NewCharacterDialog dialog;

    @OnClick(R.id.add)
    public void showDialog(){
        if(dialog == null){
            dialog = new NewCharacterDialog(getActivity() , adapter);
        }
        dialog.show();
    }
}
