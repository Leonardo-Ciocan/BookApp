package lc.bookapp.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Book")
public class Book extends ParseObject{
    public String getName(){
        return getString("name");
    }

    public void setName(String name){
        put("name" , name);
    }

    public String getAuthor(){
        return getString("author");
    }

    public void setAuthor(String author){
        put("author" , author);
    }

    public ParseUser getUser(){
        return getParseUser("user");
    }

    public void setUser(ParseUser user){
        put("user" , user);
    }
}
