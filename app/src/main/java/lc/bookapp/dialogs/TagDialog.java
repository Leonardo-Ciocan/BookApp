package lc.bookapp.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lc.bookapp.MainActivity;
import lc.bookapp.R;
import lc.bookapp.models.Book;
import lc.bookapp.models.Core;
import lc.bookapp.models.Tag;

public class TagDialog  extends Dialog {
    public Activity c;
    public Dialog d;
    public Button yes, no;

    ArrayAdapter adapter;
    public TagDialog(Activity a, ArrayAdapter adapter) {
        super(a);
        this.c = a;
        this.adapter = adapter;
    }

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.tagColor)
    FrameLayout tagColor;

    AlertDialog dialog;
    String color="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_tag_dialog);
        ButterKnife.bind(this);

        dialog = ColorPickerDialogBuilder
                .with(getContext())
                .setTitle("Choose color")
                .initialColor(getContext().getResources().getColor(R.color.brand))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        GradientDrawable d =(GradientDrawable) tagColor.getBackground();
                        d.setColor(selectedColor);
                        color = selectedColor +"";
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .build();
    }

    @OnClick(R.id.create)
    void createTag(){
        Tag t = new Tag();
        t.setName(name.getText().toString());
        t.saveInBackground();
        t.setBook(Core.selectedBook);
        t.setColor(color);
        Core.selectedCharacter.getTags().add(t);
        Core.selectedCharacter.saveInBackground();
        adapter.notifyDataSetChanged();
        this.dismiss();
    }

    @OnClick(R.id.tagColor)
    void showD(){
        dialog.show();
    }
}
