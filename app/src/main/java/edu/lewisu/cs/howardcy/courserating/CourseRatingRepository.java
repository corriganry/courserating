package edu.lewisu.cs.howardcy.courserating;

import android.app.Application;

import java.util.List;

public class CourseRatingRepository {
    private CourseRatingDao mCourseRatingDao;

    public CourseRatingRepository(Application application){
        CourseRatingDatabase database = CourseRatingDatabase.getInstance(application);
        mCourseRatingDao = database.courseRatingDao();
    }

    List<CourseRating> getAllRatings(){
        return mCourseRatingDao.getAllRatings();
    }

    CourseRating getRating(int id){
        return mCourseRatingDao.getRating(id);
    }

    void insertRating(CourseRating rating){
        mCourseRatingDao.insertRating(rating);
    }

    void updateRating(CourseRating rating){
        mCourseRatingDao.updateRating(rating);
    }

    void deleteRating(int id) {
        mCourseRatingDao.deleteRating(id);
    }
}
