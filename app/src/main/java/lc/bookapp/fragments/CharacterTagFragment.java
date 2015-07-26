package lc.bookapp.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.R;
import lc.bookapp.adapters.BookAdapter;
import lc.bookapp.adapters.TagAdapter;
import lc.bookapp.dialogs.TagDialog;
import lc.bookapp.models.Book;
import lc.bookapp.models.Core;
import lc.bookapp.models.Tag;

public class CharacterTagFragment extends android.support.v4.app.Fragment {

    private TagAdapter adapter;
    private TagDialog dialog;
    private AlertDialog firstDialog;
    private ArrayList<Tag> tags;
    private AlertDialog existingDialog;

    public static CharacterTagFragment newInstance(String param1, String param2) {
        CharacterTagFragment fragment = new CharacterTagFragment();
        return fragment;
    }

    public CharacterTagFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Bind(R.id.listview)
    ListView listView;
    CharSequence choices[] = new CharSequence[] {"Add existing tags" , "Create new tag"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_character_tag, container, false);
        ButterKnife.bind(this , v);
        adapter = new TagAdapter(getActivity() , Core.selectedCharacter.getTags());

        dialog = new TagDialog(getActivity() , adapter);
        listView.setAdapter(adapter);

        final AlertDialog.Builder tagBuilder = new AlertDialog.Builder(getActivity());
        tagBuilder.setTitle("Existing tags");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Tags");
        firstDialog = builder.setItems(choices, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface fx, int which) {
                if (which == 1)
                    dialog.show();
                else
                    existingDialog.show();
            }
        }).create();

        ParseQuery<Tag> quqery = ParseQuery.getQuery(Tag.class);
        quqery.whereEqualTo("book", Core.selectedBook);
        quqery.findInBackground(new FindCallback<Tag>() {
            public void done(List<Tag> scoreList, ParseException e) {
                tags = (ArrayList) scoreList;

                String[] strings = new String[tags.size()];
                for(int x= 0; x< tags.size();x++) strings[x] = tags.get(x).getName();
                tagBuilder.setItems(strings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface fx, int which) {
                        Core.selectedCharacter.getTags().add(tags.get(which));
                        Core.selectedCharacter.saveInBackground();
                        adapter.notifyDataSetChanged();
                    }
                });
                existingDialog = tagBuilder.create();

            }
        });






        return v;
    }


    @OnClick(R.id.add)
    void add(){
        firstDialog.show();
    }


}
