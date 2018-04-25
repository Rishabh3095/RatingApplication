package com.example.rishabh.myapplication;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

public class TestSjsuId {

    @Rule
    public ActivityTestRule<SignUp> mActivityRule = new ActivityTestRule<>(
            SignUp.class);

    @Test
    public void userID() throws Exception
    {
        onView(withId(R.id.sjsuId_signUp))
                .perform(typeText("01234567"), closeSoftKeyboard());
        int count = withId(R.id.sjsuId_signUp).toString().length();

        assertEquals(false, count == 9);

    }
    @Test
    public void userInfo() throws Exception
    {
        assertEquals(true, withId(R.id.first_name_signUp).toString().length() == 0);
        assertEquals(true, withId(R.id.last_name_signUp).toString().length() == 0);
    }
}
