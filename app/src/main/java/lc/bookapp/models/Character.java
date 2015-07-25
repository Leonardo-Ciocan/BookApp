package lc.bookapp.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Character")
public class Character extends ParseObject {
    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put("name" , name);
    }

    public String getLastName(){
        return getString("LastName");
    }

    public void setLastName(String lastName){
        put("LastName" , lastName);
    }

    public Book getBook(){
        return (Book)get("book");
    }

    public void setBook(Book book){
        put("book" , book);
    }

    public int getHeight() { return getInt("height");}
    public void setHeight(int height) { put("height" , height);}
}
