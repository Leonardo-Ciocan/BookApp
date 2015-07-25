package lc.bookapp.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.MainActivity;
import lc.bookapp.R;
import lc.bookapp.models.*;
import lc.bookapp.models.Character;

public class NewCharacterDialog extends Dialog {
    public Activity c;
    public Dialog d;
    public ArrayAdapter adapter;

    public NewCharacterDialog(Activity a , ArrayAdapter adapter) {
        super(a);
        this.c = a;
        this.adapter = adapter;
    }

    @Bind(R.id.first_name)
    EditText firstName;

    @Bind(R.id.last_name)
    EditText lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_char_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.create)
    void createChar(){
        Character b = new Character();
        b.setName(firstName.getText().toString());
        b.setLastName(lastName.getText().toString());
        b.setBook(Core.selectedBook);
        b.saveInBackground();
        Core.characters.add(b);
        adapter.notifyDataSetChanged();
        this.dismiss();
    }
}
