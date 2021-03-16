package edu.lewisu.cs.howardcy.courserating;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseRatingDao {

    @Insert
    void insertRating(CourseRating...ratings);

    @Update
    void updateRating(CourseRating...ratings);

    @Query("SELECT * from CourseRating WHERE id = :id")
    CourseRating getRating(int id);

    @Query("SELECT * FROM CourseRating ORDER BY instructorName, courseName")
    List<CourseRating> getAllRatings();

    @Query("DELETE FROM CourseRating WHERE id = :id")
    void deleteRating(int id);
}
