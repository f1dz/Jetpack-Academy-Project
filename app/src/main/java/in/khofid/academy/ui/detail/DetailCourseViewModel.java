package in.khofid.academy.ui.detail;

import androidx.lifecycle.ViewModel;

import java.util.List;

import in.khofid.academy.data.CourseEntity;
import in.khofid.academy.data.ModuleEntity;
import in.khofid.academy.utils.DataDummy;

public class DetailCourseViewModel extends ViewModel {
    private CourseEntity mCourse;

    private String courseId;

    public CourseEntity getCourse() {
        for (int i = 0; i < DataDummy.generateDummyCourses().size(); i++) {
            CourseEntity courseEntity = DataDummy.generateDummyCourses().get(i);
            if(courseEntity.getCourseId().equals(courseId)) {
                mCourse = courseEntity;
            }
        }

        return mCourse;
    }

    public List<ModuleEntity> getModules(){
        return DataDummy.generateDummyModules(getCourseId());
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
