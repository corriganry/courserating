package edu.lewisu.cs.howardcy.courserating;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private CourseRating mCourseRating;
    private EditText mCourseNameEditText;
    private EditText mInstructorNameEditText;
    private Spinner mCourseTypeSpinner;
    private RatingBar mRatingBar;
    private SharedPreferences mPreferences;
    private CourseRatingRepository mCourseRatingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCourseRatingRepository = new CourseRatingRepository(getApplication());

        mCourseRating = new CourseRating();

        mCourseNameEditText = findViewById(R.id.courseNameEditText);
        mInstructorNameEditText = findViewById(R.id.instructorNameEditText);
        mCourseTypeSpinner = findViewById(R.id.courseTypeSpinner);
        mRatingBar = findViewById(R.id.ratingBar);

        mCourseTypeSpinner.setOnItemSelectedListener(new CourseTypeSelectedListener());
        mRatingBar.setOnRatingBarChangeListener(new RatingChangedListener());
        mCourseNameEditText.addTextChangedListener(new NameTextListener(mCourseNameEditText));
        mInstructorNameEditText.addTextChangedListener(new NameTextListener(mInstructorNameEditText));
        mCourseNameEditText.addTextChangedListener(new NameTextListener(mCourseNameEditText));
        mInstructorNameEditText.addTextChangedListener(new NameTextListener(mInstructorNameEditText));

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String ratingString = mPreferences.getString(getString(R.string.pref_rating_key), "0");
        int default_rating = Integer.parseInt(ratingString);
        mRatingBar.setRating(default_rating);

    }

    public void submitClick(View v){
        mCourseRatingRepository.insertRating(mCourseRating);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }



    private class CourseTypeSelectedListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String courseType = (String)adapterView.getItemAtPosition(i);
            if (i!=0) { //not the prompt string
                mCourseRating.setCourseType(courseType);
                Log.d(TAG, "course type: " + courseType);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            mCourseRating.setRating((int)v);  //cast to integer
            Log.d(TAG, "rating " + v);
        }
    }

    private class NameTextListener implements TextWatcher {
        private final View editText; //editText that triggered the listener

        public NameTextListener(View v){
            editText = v;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(editText == mCourseNameEditText) {
                mCourseRating.setCourseName(charSequence.toString());
                Log.d(TAG, "updated course name to " + mCourseRating.getCourseName());
            }else if(editText == mInstructorNameEditText) {
                mCourseRating.setInstructorName(charSequence.toString());
                Log.d(TAG, "updated instructor name to " + mCourseRating.getInstructorName());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}