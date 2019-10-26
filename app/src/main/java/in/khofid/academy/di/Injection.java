package in.khofid.academy.di;

import android.app.Application;

import in.khofid.academy.data.source.AcademyRepository;
import in.khofid.academy.data.source.remote.RemoteRepository;
import in.khofid.academy.utils.JsonHelper;

public class Injection {
    public static AcademyRepository provideRepository(Application application) {
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        return AcademyRepository.getInstance(remoteRepository);
    }
}
