package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import android.app.Activity;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testAddCity() {
        // click on Add City button
        onView(withId(R.id.button_add)).perform(click());

        // type "Edmonton" in the editText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));

        // click on Confirm
        onView(withId(R.id.button_confirm)).perform(click());

        // check if text "Edmonton" is matched with any of the text displayed on the screen
        onView(withText("Edmonton")).check(matches(isDisplayed()));
    }

    @Test
    public void testClearCity() {
        // add first city to the list
        onView(withId(R.id.button_add)).perform((click()));
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // add another city to the list
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Vancouver"));
        onView(withId(R.id.button_confirm)).perform((click()));

        // clear the list
        onView(withId(R.id.button_clear)).perform(click());
        onView(withText("Edmonton")).check(doesNotExist());
        onView(withText("Vancouver")).check(doesNotExist());
    }

    @Test
    public void testListView() {
        // add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // check if in the Adapter view (given id of that adapter view), there is data
        // (which is an instance of String) located at position zero
        // if this data matches the text we provided then our test should pass
        // you can also use anything() in place of is(instanceOf(String.class))
        onData(is(instanceOf(String.class))).inAdapterView(withId(R.id.city_list)).atPosition(0).check(matches(withText("Edmonton")));
    }


    /*
    The following code (@Before and @After tags) are adapted from...
    Source: Google AI Overview
    Query "what replaces intents test rule in espresso java"
    Retrieved: 2026-02-25
    */
    @Before
    public void setup() {
        Intents.init();
    }
    @After
    public void cleanup() {
        Intents.release();
    }

    @Test
    public void testSwitchActivity() {
        // add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // click first item in ListView
        /*
        The following code is adapted from...
        Author: elpatricko https://stackoverflow.com/users/5110938/elpatricko
        Title: "Espresso: click first item in ListView inside a ViewPager"
        Answer: i just used the stuff in the question
        Date: 2016-08-18
        Retrieved: 2026-02-25
        */
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // check if activity launched
        /*
        The following code is adapted from...
        Author: DeeV https://stackoverflow.com/users/786718/deev
        Title: "Whats the difference between intending vs intended in Espresso?"
        Answer: https://stackoverflow.com/a/39727317
        Date: 2016-09-27
        Retrieved: 2026-02-25
        License: CC BY-SA 3.0
        */
        intended(hasComponent(ShowActivity.class.getName()));
    }

    @Test
    public void testClickCity() {
        // add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // click first item in ListView
        /*
        The following code is adapted from...
        Author: elpatricko https://stackoverflow.com/users/5110938/elpatricko
        Title: "Espresso: click first item in ListView inside a ViewPager"
        Answer: i just used the stuff in the question
        Date: 2016-08-18
        Retrieved: 2026-02-25
        */
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // check if activity launched
        /*
        The following code is adapted from...
        Source: Google AI Overview
        Query "espresso android studio java check if data is sent correctly to activity"
        Retrieved: 2026-02-25
        */
        intended(allOf(hasComponent(ShowActivity.class.getName()),
                hasExtra("cityName", "Edmonton")
        ));
    }

    @Test
    public void testBackButton() {
        // add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // click first item in ListView
        /*
        The following code is adapted from...
        Author: elpatricko https://stackoverflow.com/users/5110938/elpatricko
        Title: "Espresso: click first item in ListView inside a ViewPager"
        Answer: i just used the stuff in the question
        Date: 2016-08-18
        Retrieved: 2026-02-25
        */
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // click back button
        onView(withId(R.id.button_back)).perform(click());

        // check if activity is MainActivity (by looking for Add button)
        // idea from: Ben Bui (bbui)
        onView(withId(R.id.button_add)).check(matches(isDisplayed()));
    }

}

