package com.example.builders_buddy.InvoiceTest;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;

import com.example.builders_buddy.Invoice.AddInvoice;
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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InvalidAddInvoiceTest {

    AddInvoice addInvoice;
    @Rule
    public ActivityTestRule<AddInvoice> mActivityTestRule = new ActivityTestRule<>(AddInvoice.class);

    @Test
    public void invalidAddMaterialsInvoiceTest() {
        addInvoice = mActivityTestRule.getActivity();

        EditText material,qty,price;

        material = addInvoice.findViewById(R.id.Materials);
        qty = addInvoice.findViewById(R.id.QTY);
        price = addInvoice.findViewById(R.id.Price);


        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.addMaterials), withText("Add Materials"),
                        isDisplayed()));
        appCompatButton.perform(click());

        TestCase.assertEquals("Please enter your accounts password",
                "Must enter materials", material.getError());
        TestCase.assertEquals("Please enter your accounts password",
                "Must enter quantity", qty.getError());
        TestCase.assertEquals("Please enter your accounts password",
                "Must enter price", price.getError());
    }


    @Test
    public void InvalidNameInvoiceTest2() {
        addInvoice = mActivityTestRule.getActivity();

        EditText jobName;

        jobName = addInvoice.findViewById(R.id.jobName);


        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.jobName),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText(""), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.Hours),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText(""), closeSoftKeyboard());


        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.save), withText("Save Invoice"),
                        isDisplayed()));
        appCompatButton.perform(click());

        TestCase.assertEquals("correct error",
                "Please set a Job name", jobName.getError());
//        TestCase.assertEquals("correct error",
//                "Please Enter Man Hours", Hours.getError());
    }
    @Test
    public void InvalidJobNameInvoiceTest2() {
        addInvoice = mActivityTestRule.getActivity();

        EditText jobName,Hours;

        jobName = addInvoice.findViewById(R.id.jobName);
        Hours =  addInvoice.findViewById(R.id.Hours);

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.jobName),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("wqe"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.Hours),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText(" "), closeSoftKeyboard());


        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.save), withText("Save Invoice"),
                        isDisplayed()));
        appCompatButton.perform(click());

        TestCase.assertEquals("correct error",
                "Please Enter Man Hours", Hours.getError());
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
