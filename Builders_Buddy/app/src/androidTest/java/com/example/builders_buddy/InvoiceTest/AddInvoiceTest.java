package com.example.builders_buddy.InvoiceTest;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.builders_buddy.Invoice.AddInvoice;
import com.example.builders_buddy.R;

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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class AddInvoiceTest {
AddInvoice addInvoice;
    @Rule
    public ActivityTestRule<AddInvoice> mActivityTestRule = new ActivityTestRule<>(AddInvoice.class);

    @Test
    public void addInvoiceTest() {
        addInvoice = mActivityTestRule.getActivity();
        addInvoice.authCheck(true);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.jobName),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.jobName),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Tom  house "), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.Hours),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("12"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.Materials),
                        isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.Materials),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("white pa"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.QTY),
                        isDisplayed()));
        appCompatEditText6.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.QTY),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.Price),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("19"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.QTY), withText("2"),
                        isDisplayed()));
        appCompatEditText9.perform(replaceText("2\n"));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.QTY), withText("2\n"),
                        isDisplayed()));
        appCompatEditText10.perform(closeSoftKeyboard());

        onView(withId(R.id.addMaterials)).perform(click());// clicks button


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
