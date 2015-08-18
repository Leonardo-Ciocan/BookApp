package lc.bookapp.models;

import java.util.ArrayList;

import lc.bookapp.models.Book;

public class Core {
    public static Character selectedCharacter;
    public static Book selectedBook;
    public static Location selectedLocation;
    public static Event selectedEvent;

    public static ArrayList<Book> Books = new ArrayList<>();
    public static ArrayList<Character> characters = new ArrayList<Character>();
    public static ArrayList<Location> locations = new ArrayList<Location>();
    public static ArrayList<Event> events = new ArrayList<Event>();
}
