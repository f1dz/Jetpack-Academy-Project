package in.khofid.academy.ui.reader;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import in.khofid.academy.R;
import in.khofid.academy.data.CourseEntity;
import in.khofid.academy.utils.FakeDataDummy;
import in.khofid.academy.utils.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class CourseReaderActivityTest {

    private CourseEntity dummyCourse = FakeDataDummy.generateDummyCourses().get(0);

    @Rule
    public ActivityTestRule<CourseReaderActivity> activityRule = new ActivityTestRule<CourseReaderActivity>(CourseReaderActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, CourseReaderActivity.class);
            result.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, dummyCourse.getCourseId());
            return result;
        }
    };

    @Test
    public void loadModules() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_module)).check(new RecyclerViewItemCountAssertion(7));
    }

    @Test
    public void clickModule() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.rv_module)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_module)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.web_view)).check(matches(isDisplayed()));
    }
}