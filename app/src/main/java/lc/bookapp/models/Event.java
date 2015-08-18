package lc.bookapp.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("Event")
public class Event extends ParseObject {
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
    public Book getBook(){
        return (Book)get("book");
    }

    public void setBook(Book book){
        put("book" , book);
    }

    public Location getLocation(){
        return (Location)get("location");
    }

    public void setLocation(Location location){
        put("location" , location);
    }

    public List<Character> getRelatedCharacters(){
        if(getList("characters") == null) put("characters", new ArrayList<Character>());
        return getList("characters");
    }
}
