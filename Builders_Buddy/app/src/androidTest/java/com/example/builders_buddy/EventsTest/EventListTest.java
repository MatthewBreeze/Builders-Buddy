package com.example.builders_buddy.EventsTest;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.builders_buddy.Events.EventsList;
import com.example.builders_buddy.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EventListTest {

    @Rule
    public ActivityTestRule<EventsList> mActivityTestRule = new ActivityTestRule<>(EventsList.class);
    public EventsList testing;
    @Test
    public void EventListTest() {

        testing = mActivityTestRule.getActivity();
        testing.testing(true);
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ViewInteraction textView = onView(
                allOf(ViewMatchers.withId(R.id.textViewTitle), withText("toms house"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("toms house")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.eventLocation), withText("Ts17 0fe"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Ts17 0fe")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.phoneNumber), withText("07112341234"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("07112341234")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.phoneNumber), withText("07112341234"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                2),
                        isDisplayed()));
        textView4.check(matches(withText("07112341234")));

        ViewInteraction cardView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.RecView),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0)),
                        0),
                        isDisplayed()));
        cardView.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.event), withText("Event toms house"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("Event toms house")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.date), withText("Date 3/4/2020"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Date 3/4/2020")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.phoneNumber), withText("Contact Number : 07112341234"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("Contact Number : 07112341234")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.location), withText("Location Ts17 0fe"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        4),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("Location Ts17 0fe")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.location), withText("Location Ts17 0fe"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        4),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("Location Ts17 0fe")));
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
