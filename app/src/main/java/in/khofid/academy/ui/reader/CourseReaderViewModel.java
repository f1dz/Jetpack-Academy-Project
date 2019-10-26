package in.khofid.academy.ui.reader;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import in.khofid.academy.data.ModuleEntity;
import in.khofid.academy.data.source.AcademyRepository;

public class CourseReaderViewModel extends ViewModel {

    private String courseId;
    private String moduleId;
    private AcademyRepository academyRepository;

    public CourseReaderViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public ArrayList<ModuleEntity> getModules() {

        return academyRepository.getAllModulesByCourse(courseId);
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public ModuleEntity getSelectedModule() {
        return academyRepository.getContent(courseId, moduleId);
    }

    public void setSelectedModule(String moduleId) {
        this.moduleId = moduleId;
    }

}
