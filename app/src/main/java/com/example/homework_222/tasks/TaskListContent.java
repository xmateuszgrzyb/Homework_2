package com.example.homework_222.tasks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class TaskListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Task> ITEMS = new ArrayList<Task>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Task> ITEM_MAP = new HashMap<String, Task>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Task item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Task createDummyItem(int position) {
        return new Task(String.valueOf(position), "Item " + position, makeDetails(position), makeLocalization(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < 3; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
    private static String makeLocalization(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("\nLokalizacja: ").append(position);

        return builder.toString();
    }
    /**
     * A dummy item representing a piece of content.
     */
    public static class Task implements Parcelable {
        public final String id;
        public final String title;
        public final String details;
        public final String localization;
        public String picPath;

        public Task(String id, String title, String details,String localization) {
            this.id = id;
            this.title = title;
            this.details = details;
            this.localization = localization;
            this.picPath = "";
        }
        public Task(String id, String title, String details,String localization, String picPath) {
            this.id = id;
            this.title = title;
            this.details = details;
            this.localization = localization;
            this.picPath = picPath;
        }

        protected Task(Parcel in) {
            id = in.readString();
            title = in.readString();
            details = in.readString();
            localization = in.readString();
            picPath = in.readString();
        }

        public static final Creator<Task> CREATOR = new Creator<Task>() {
            @Override
            public Task createFromParcel(Parcel in) {
                return new Task(in);
            }

            @Override
            public Task[] newArray(int size) {
                return new Task[size];
            }
        };

        @Override
        public String toString() {
            return title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(title);
            dest.writeString(details);
            dest.writeString(localization);
            dest.writeString(picPath);
        }

        public void setPicPath(String path){
            this.picPath = path;
        }
    }

    public static void removeItem(int position){
        String itemId = ITEMS.get(position).id;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
    }

    public static void clearList(){
        ITEMS.clear();
        ITEM_MAP.clear();
    }
}
