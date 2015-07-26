package lc.bookapp.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Tag")
public class Tag extends ParseObject {
    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put("name" , name);
    }

    public String getColor(){
        return getString("color");
    }

    public void setColor(String color){
        put("color" , color);
    }

    public Book getBook(){
        return (Book)get("book");
    }

    public void setBook(Book book){
        put("book" , book);
    }

}
