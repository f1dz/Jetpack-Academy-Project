package in.khofid.academy.ui.academy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.khofid.academy.data.CourseEntity;
import in.khofid.academy.data.source.AcademyRepository;

public class AcademyViewModel extends ViewModel {

    private AcademyRepository academyRepository;

    public AcademyViewModel(AcademyRepository mAcademyRepository) {
        this.academyRepository = mAcademyRepository;
    }

    public LiveData<List<CourseEntity>> getCourses() {
        return academyRepository.getAllCourses();
    }
}
