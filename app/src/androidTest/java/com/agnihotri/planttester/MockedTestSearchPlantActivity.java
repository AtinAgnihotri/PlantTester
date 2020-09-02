package com.agnihotri.planttester;

import android.app.Activity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.agnihotri.planttester.MockUtils.MockWebServerUtils;
import com.agnihotri.planttester.UiUtils.LogUITest;
import com.agnihotri.planttester.dao.BaseUrl;
import com.agnihotri.planttester.dao.IPlantDAO;
import com.agnihotri.planttester.dao.PlantDAO;
import com.agnihotri.planttester.dto.PlantDTO;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockWebServer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MockedTestSearchPlantActivity {
    // region Variables
    MockWebServer server;
    HttpUrl baseUrl;
    IPlantDAO plantDAO;
    List<PlantDTO> plants;
    // endregion

    @Rule
    public ActivityTestRule<SearchPlantsActivity> searchPlantsActivityActivityTestRule = new ActivityTestRule(SearchPlantsActivity.class){

    };


    // region Setup
    @Before
    public void setupMockedTests() throws IOException, GeneralSecurityException {
        server = MockWebServerUtils.startNewServer();
        baseUrl = server.url("api/v1/plants/search");
//        BaseUrl.setBaseUrl(baseUrl.toString());
        System.out.println("# URL Base : " +  BaseUrl.getBaseUrl() + " #");

    }

    @Test
    public void testSearchPlantActivity_searchingRedbudReturnsAtLeastTwoResults(){
//        givenPlantDAOBaseURLPointingToMockWebServer();
//        LogUITest.startLogging("testSearchPlantActivity_searchingRedbudReturnsAtLeastTwoResults");
        whenUserSearchesForRedbud();
        thenVerifyAtLeastTwoRedbudResultsInList();
//        LogUITest.stopLogging();
//        onView(withId(R.id.actPlantName)).perform(typeText("Redbud"));
        // Click the search button
//        onView(withId(R.id.btnSearchPlant)).perform(click());
        //
    }

    public void givenPlantDAOBaseURLPointingToMockWebServer(){
//        SearchPlantsActivity searchPlantClass = (SearchPlantsActivity) searchPlantsActivityActivityTestRule.getActivity().getClass();
//        plantDAO = new PlantDAO(baseUrl.toString());
    }

    public void whenUserSearchesForRedbud(){
        onView(withId(R.id.actPlantName)).perform(typeText("Redbud"));
        // Click the search button
        LogUITest.info("Redbud Typed in Search Line Edit");
        onView(withId(R.id.btnSearchPlant)).perform(click());
        LogUITest.info("Search Button clicked");
        // On View

    }

    public void thenVerifyAtLeastTwoRedbudResultsInList(){
//        assertTrue();
        onView(withText("Common Name : middle eastern redbud, Scientific Name : Cercis canadensis"));
        LogUITest.info("Middle Eastern Redbud Found");
        onView(withText("Common Name : Japanese redbud, Scientific Name : Cercis chinensis"));
        LogUITest.info("Japanese Redbud Found");
    }

    @After
    public void tearDownMockedTests() throws IOException {
        server.shutdown();
    }

}
