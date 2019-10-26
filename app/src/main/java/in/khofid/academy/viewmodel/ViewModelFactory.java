package in.khofid.academy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import in.khofid.academy.data.source.AcademyRepository;
import in.khofid.academy.di.Injection;
import in.khofid.academy.ui.academy.AcademyViewModel;
import in.khofid.academy.ui.bookmark.BookmarkViewModel;
import in.khofid.academy.ui.detail.DetailCourseViewModel;
import in.khofid.academy.ui.reader.CourseReaderViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final AcademyRepository mAcademyRepository;


    public ViewModelFactory(AcademyRepository academyRepository) {
        this.mAcademyRepository = academyRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if(INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if(INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if(modelClass.isAssignableFrom(AcademyViewModel.class)){
            return (T) new AcademyViewModel(mAcademyRepository);
        } else if(modelClass.isAssignableFrom(DetailCourseViewModel.class)) {
            return (T) new DetailCourseViewModel(mAcademyRepository);
        } else if (modelClass.isAssignableFrom(BookmarkViewModel.class)) {
            return (T) new BookmarkViewModel(mAcademyRepository);
        } else if (modelClass.isAssignableFrom(CourseReaderViewModel.class)) {
            //noinspection unchecked
            return (T) new CourseReaderViewModel(mAcademyRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
