package lc.bookapp.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Location")
public class Location extends ParseObject {
    public String getName(){
        return getString("name");
    }
    public void setName(String name){
        put("name" , name);
    }
    public String getDescription(){
        return getString("description");
    }
    public void setDescription(String name){
        put("description" , name);
    }
    public Location getParent(){
        return (Location)get("parent");
    }

    public void setParent(Location location){
        put("parent" , location);
    }

    public Book getBook(){
        return (Book)get("book");
    }

    public void setBook(Book book){
        put("book" , book);
    }
}
