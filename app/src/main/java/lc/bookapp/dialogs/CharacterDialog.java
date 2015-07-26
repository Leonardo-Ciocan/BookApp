package lc.bookapp.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.MainActivity;
import lc.bookapp.R;
import lc.bookapp.models.Book;
import lc.bookapp.models.Core;

public class CharacterDialog extends Dialog {
    public Activity c;
    public Dialog d;
    public Button yes, no;

    public CharacterDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Bind(R.id.book_name)
    TextView book_name;

    @Bind(R.id.book_author)
    TextView book_author;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_book_dialog);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.create)
    void createBook(){
        Book b = new Book();
        b.setName(book_name.getText().toString());
        b.setAuthor(book_author.getText().toString());
        b.setUser(ParseUser.getCurrentUser());
        b.saveInBackground();
        Core.Books.add(b);
        MainActivity.adapter.notifyDataSetChanged();
        this.dismiss();
    }
}
