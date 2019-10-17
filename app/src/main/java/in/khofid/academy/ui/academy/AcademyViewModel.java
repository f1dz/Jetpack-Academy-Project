package in.khofid.academy.ui.academy;

import androidx.lifecycle.ViewModel;

import java.util.List;

import in.khofid.academy.data.CourseEntity;
import in.khofid.academy.utils.DataDummy;

public class AcademyViewModel extends ViewModel {

    public List<CourseEntity> getCourses() {
        return DataDummy.generateDummyCourses();
    }
}
