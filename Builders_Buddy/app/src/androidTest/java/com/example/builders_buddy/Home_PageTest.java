package com.example.builders_buddy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
@RunWith(AndroidJUnit4.class)
public class Home_PageTest {
    @Rule
    public ActivityTestRule<Home_Page> Homepage =  new ActivityTestRule<>(Home_Page.class);

    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void onCreate() {
        onView(withId(R.id.tax));
        onView(withId(R.id.hEvents));
        onView(withId(R.id.invoice));
        onView(withId(R.id.tradeCards));
        onView(withId(R.id.logout));
    }

    @Test
    public void logout() {
        onView(withId(R.id.tax));
        onView(withId(R.id.invoice));
        onView(withId(R.id.tradeCards));
        onView(withId(R.id.hEvents));
        // checks pages loads
        onView(withId(R.id.logout)).perform(click());// clicks button
        // check new page loads
        onView(withId(R.layout.activity_main));
    }

    @Test
    public void calender() {
        onView(withId(R.id.tax));
        onView(withId(R.id.invoice));
        onView(withId(R.id.tradeCards));
        onView(withId(R.id.logout));
        // checks pages loads
        onView(withId(R.id.hEvents)).perform(click());// clicks button
        // check new page loads
        onView(withId(R.layout.activity_calender));
    }

    @Test
    public void tradeCards() {
        onView(withId(R.id.tax));
        onView(withId(R.id.invoice));
        onView(withId(R.id.hEvents));
        onView(withId(R.id.logout));
        // checks pages loads
        onView(withId(R.id.tradeCards)).perform(click());// clicks button
        // check new page loads
        onView(withId(R.layout.activity_trade_cards));
    }

    @Test
    public void taxPage() {
        onView(withId(R.id.hEvents));
        onView(withId(R.id.invoice));
        onView(withId(R.id.tradeCards));
        onView(withId(R.id.logout));
        // checks pages loads
        onView(withId(R.id.tax)).perform(click());// clicks button
        // check new page loads
        onView(withId(R.layout.activity_tax_page));
    }

    @Test
    public void jobsList() {
        onView(withId(R.id.tax));
        onView(withId(R.id.hEvents));
        onView(withId(R.id.tradeCards));
        onView(withId(R.id.logout));
        // checks pages loads
        onView(withId(R.id.invoice)).perform(click());// clicks button
        // check new page loads
        onView(withId(R.layout.activity_invoice_home));
    }
}