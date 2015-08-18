package lc.bookapp.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.R;
import lc.bookapp.models.*;
import lc.bookapp.models.Character;

public class NewEventDialog  extends Dialog {
    public Activity c;
    public Dialog d;
    public ArrayAdapter adapter;

    public NewEventDialog(Activity a,ArrayAdapter adapter) {
        super(a);
        this.c = a;
        this.adapter = adapter;
    }


    @Bind(R.id.name)
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_event_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.create)
    void createChar(){
        Event b = new Event();
        b.setName(name.getText().toString());
        b.setBook(Core.selectedBook);
        b.saveInBackground();
        Core.events.add(b);
        adapter.notifyDataSetChanged();
        this.dismiss();
    }
}