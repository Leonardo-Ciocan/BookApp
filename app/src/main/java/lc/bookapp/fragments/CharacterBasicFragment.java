package lc.bookapp.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.R;
import lc.bookapp.models.Core;

public class CharacterBasicFragment extends android.support.v4.app.Fragment {
    public static CharacterBasicFragment newInstance(String param1, String param2) {
        CharacterBasicFragment fragment = new CharacterBasicFragment();
        return fragment;
    }

    public CharacterBasicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Bind(R.id.first_name)
    TextView firstNme;

    @Bind(R.id.last_name)
    TextView lastName;

    @Bind(R.id.height)
    TextView height;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_character_basic, container, false);
        ButterKnife.bind(this, v);


        firstNme.setText(Core.selectedCharacter.getName());
        lastName.setText(Core.selectedCharacter.getLastName());
        height.setText(Core.selectedCharacter.getHeight() == 0 ? "":Core.selectedCharacter.getHeight()+"");
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


    @OnClick(R.id.save)
    void save(){
        Core.selectedCharacter.setName(firstNme.getText().toString());
        Core.selectedCharacter.setLastName(lastName.getText().toString());
        Core.selectedCharacter.setHeight(Integer.parseInt(height.getText().toString()));
        Core.selectedCharacter.saveInBackground();
    }

}
