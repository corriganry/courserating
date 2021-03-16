package edu.lewisu.cs.howardcy.courserating;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CourseRating.class}, version=1, exportSchema = false)
abstract public class CourseRatingDatabase extends RoomDatabase {

    public abstract CourseRatingDao courseRatingDao();

    private static CourseRatingDatabase sCourseRatingDatabase;

    static CourseRatingDatabase getInstance(final Context context){
        if(sCourseRatingDatabase == null){
            synchronized (CourseRatingDatabase.class){
                if(sCourseRatingDatabase == null){
                    sCourseRatingDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            CourseRatingDatabase.class, "rating_db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return sCourseRatingDatabase;
    }

}
