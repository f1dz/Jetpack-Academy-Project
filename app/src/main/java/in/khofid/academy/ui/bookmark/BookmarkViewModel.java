package in.khofid.academy.ui.bookmark;

import androidx.lifecycle.ViewModel;

import java.util.List;

import in.khofid.academy.data.CourseEntity;
import in.khofid.academy.utils.DataDummy;

public class BookmarkViewModel extends ViewModel {

    List<CourseEntity> getBookmarks() {
        return DataDummy.generateDummyCourses();
    }
}
