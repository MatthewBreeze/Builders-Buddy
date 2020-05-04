package com.example.builders_buddy.InvoiceTest;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.builders_buddy.Invoice.ViewInvocieAll;
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
public class ListInvoices {

    ViewInvocieAll viewInvocieAll;
    @Rule
    public ActivityTestRule<ViewInvocieAll> mActivityTestRule = new ActivityTestRule<>(ViewInvocieAll.class);

    @Test
    public void listInvoices() {

        viewInvocieAll = mActivityTestRule.getActivity();



        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView = onView(
                allOf(ViewMatchers.withId(R.id.textViewTitle), withText("Toms House"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Toms House")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.eventLocation), withText("Total :  £159.5"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Total :  £159.5")));;


        ViewInteraction cardView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.RecView),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2)),
                        0),
                        isDisplayed()));
        cardView.perform(click());

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.name), withText(" Toms House"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        textView11.check(matches(withText(" Toms House")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.manHours), withText(" Man Hours :12"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText(" Man Hours :12")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.materialsheader), withText(" Materials"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        4),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText(" Materials")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.qtyheader), withText("QTY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        4),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("QTY")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.PriceHeader), withText("Price"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        4),
                                2),
                        isDisplayed()));
        textView5.check(matches(withText("Price")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.total), withText(" Total :  £159.5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        textView6.check(matches(withText(" Total :  £159.5")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.total), withText(" Total :  £159.5"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        textView7.check(matches(withText(" Total :  £159.5")));
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
