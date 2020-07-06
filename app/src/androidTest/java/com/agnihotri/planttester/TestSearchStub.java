package com.agnihotri.planttester;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.agnihotri.planttester.UiUtils.UiSelectors.isToast;

//
//@RunWith(AndroidJUnit4.class)
//public class TestSearchStub {
//
//    String toastMessage = "Flower : Plant id : 0, genus : Tropoleum, species : spp, cultivar : , common name : Nasturtium";
//
//    @Rule
//    public ActivityTestRule<SearchPlantsActivity> searchPlantsActivityActivityTestRule = new ActivityTestRule(SearchPlantsActivity.class);
//
////    @Test
//    @Ignore
//    public void testRedbudSearchStub(){
//        // Type Redbud in the autocomplete search bar
//        onView(withId(R.id.actPlantName)).perform(typeText("Redbud"));
//        // Click the search button
//        onView(withId(R.id.btnSearchPlant)).perform(click());
//        // Verify a toast with toast message comes
//        onView(withText(toastMessage)).inRoot(isToast());
//    }
//
//
//
//}
