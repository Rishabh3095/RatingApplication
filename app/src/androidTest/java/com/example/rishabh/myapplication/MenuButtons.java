package com.example.rishabh.myapplication;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Basic tests showcasing simple view matchers and actions like {@link ViewMatchers#withId},
 * {@link ViewActions#click} and {@link ViewActions#typeText}.
 * <p>
 * Note that there is no need to tell Espresso that a view is in a different {@link Activity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MenuButtons {

    public static final String LOGIN = "0";
    public static final String PASS = "0";

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<Menu> nActivityRule = new ActivityTestRule<>(
            Menu.class);

    @Test
    public void viewAllRating() {
        // Type text and then press the button.
        onView(withId(R.id.button_view_edit_ratings)).perform(click());

    }
    @Test
    public void viewPolls() {
        // Type text and then press the button.
        onView(withId(R.id.button_view_all_polls)).perform(click());

    }
    @Test
    public void createPoll() {
        // Type text and then press the button.
        onView(withId(R.id.button_create_poll)).perform(click());

    }
    @Test
    public void createRating() {
        // Type text and then press the button.
        onView(withId(R.id.button_create_rating)).perform(click());

    }
    @Test
    public void Logout() {
        // Type text and then press the button.
        onView(withId(R.id.button_logout)).perform(click());

    }
    @Test
    public void userPolls() {
        // Type text and then press the button.
        onView(withId(R.id.button_view_edit_polls)).perform(click());

    }
    @Test
    public void userRatings() {
        // Type text and then press the button.
        onView(withId(R.id.button_create_rating)).perform(click());

    }



}