package com.agnihotri.planttester;

import android.app.Activity;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.agnihotri.planttester.MockUtils.MockWebServerUtils;
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
        baseUrl = server.url("api/v1/plants");
        BaseUrl.setBaseUrl(baseUrl.toString());
        System.out.println("# URL Base : " +  BaseUrl.getBaseUrl() + " #");

    }

    @Test
    public void testSearchPlantActivity_searchingRedbudReturnsAtLeastTwoResults(){
//        givenPlantDAOBaseURLPointingToMockWebServer();
        whenUserSearchesForRedbud();
        thenVerifyAtLeastTwoRedbudResultsInList();
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
        onView(withId(R.id.btnSearchPlant)).perform(click());
        // On View
        onView(withText("Common Name : middle eastern redbud, Scientific Name : Cercis canadensis"));
        onView(withText("Common Name : Japanese redbud, Scientific Name : Cercis chinensis"));
    }

    public void thenVerifyAtLeastTwoRedbudResultsInList(){
//        assertTrue();
    }

    @After
    public void tearDownMockedTests() throws IOException {
        server.shutdown();
    }

}
