package com.example.builders_buddy.TradsCardTest;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.builders_buddy.R;
import com.example.builders_buddy.Sign_In;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
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
public class TardeCardListTest {

    @Rule
    public ActivityTestRule<Sign_In> mActivityTestRule = new ActivityTestRule<>(Sign_In.class);

    @Test
    public void viewCardTest() {

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.tradeCards), withText("Trade Cards"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        ViewInteraction textView = onView(
                allOf(withId(R.id.eventLocation), withText("B&Q"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("B&Q")));



        ViewInteraction textView2 = onView(
                allOf(withId(R.id.eventLocation), withText("wicks"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("wicks")));

        ViewInteraction cardView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.RecView),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1)),
                        0),
                        isDisplayed()));
        cardView.perform(click());

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
