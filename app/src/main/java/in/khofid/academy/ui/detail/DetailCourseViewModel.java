package in.khofid.academy.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.khofid.academy.data.CourseEntity;
import in.khofid.academy.data.ModuleEntity;
import in.khofid.academy.data.source.AcademyRepository;

public class DetailCourseViewModel extends ViewModel {
    private CourseEntity mCourse;

    private String courseId;

    private AcademyRepository academyRepository;

    public DetailCourseViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    LiveData<CourseEntity> getCourse() {
        return academyRepository.getCourseWithModules(courseId);
    }

    public LiveData<List<ModuleEntity>> getModules(){
        return academyRepository.getAllModulesByCourse(courseId);
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
