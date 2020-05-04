package com.example.builders_buddy.EventsTest;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;

import com.example.builders_buddy.Events.Calender;
import com.example.builders_buddy.R;

import junit.framework.TestCase;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InvalidCalenderTest {
    public Calender calender;
    @Rule
    public ActivityTestRule<Calender> mActivityTestRule = new ActivityTestRule<>(Calender.class);

    @Test
    public void EmptyCalenderTest() {


        calender = mActivityTestRule.getActivity();

        EditText event,location;

        event = calender.findViewById(R.id.event);

        location = calender.findViewById(R.id.location);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.event),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText(""), closeSoftKeyboard());



        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.location),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText(""), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.save), withText("Save appointment"),
                        isDisplayed()));
        appCompatButton.perform(click());

        TestCase.assertEquals("Correct Error message",
                "you must enter a event", event.getError());

        TestCase.assertEquals("Correct Error message",
                "you must set a location", location.getError());
    }
    @Test
    public void EmptyLocationCalenderTest() {


        calender = mActivityTestRule.getActivity();

        EditText location;

        location = calender.findViewById(R.id.location);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.event),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("awd"), closeSoftKeyboard());



        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.location),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText(""), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.save), withText("Save appointment"),
                        isDisplayed()));
        appCompatButton.perform(click());


        TestCase.assertEquals("Correct Error message",
                "you must set a location", location.getError());
    }
    @Test
    public void EmptyEventCalenderTest() {


        calender = mActivityTestRule.getActivity();

        EditText event,location;

        event = calender.findViewById(R.id.event);

        calender.authCheck(true);

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.event),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText(""), closeSoftKeyboard());



        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.location),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("awd"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.save), withText("Save appointment"),
                        isDisplayed()));
        appCompatButton.perform(click());

        TestCase.assertEquals("Correct Error message",
                "you must enter a event", event.getError());


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
