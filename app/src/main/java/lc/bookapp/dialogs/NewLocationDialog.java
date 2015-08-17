package lc.bookapp.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.R;
import lc.bookapp.models.*;
import lc.bookapp.models.Character;

/**
 * Created by leo on 09/08/15.
 */
public class NewLocationDialog  extends Dialog {
    public Activity c;
    public Dialog d;
    public ArrayAdapter adapter;

    Location parent;

    public NewLocationDialog(Activity a , ArrayAdapter adapter , Location parent) {
        super(a);
        this.c = a;
        this.adapter = adapter;
        this.parent = parent;
    }

    @Bind(R.id.name)
    EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_location_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.create)
    void createLocation(){
        Location l = new Location();
        l.setName(name.getText().toString());
        l.saveInBackground();
        l.setBook(Core.selectedBook);
        if(parent != null) l.setParent(parent);
        l.saveInBackground();

        adapter.add(l);
        this.dismiss();
    }
}