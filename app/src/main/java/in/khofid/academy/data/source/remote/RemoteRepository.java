package in.khofid.academy.data.source.remote;

import android.os.Handler;

import java.util.List;

import in.khofid.academy.data.source.remote.response.ContentResponse;
import in.khofid.academy.data.source.remote.response.CourseResponse;
import in.khofid.academy.data.source.remote.response.ModuleResponse;
import in.khofid.academy.utils.JsonHelper;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;


    private RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper jsonHelper) {
        if(INSTANCE == null) {
            INSTANCE = new RemoteRepository(jsonHelper);
        }
        return INSTANCE;
    }

    public void getAllCourses(LoadCoursesCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(() -> callback.onAllCoursesReceived(jsonHelper.loadCourses()), SERVICE_LATENCY_IN_MILLIS);
    }

    public void getModules(String courseId, LoadModulesCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(() -> callback.onAllModulesReceived(jsonHelper.loadModule(courseId)), SERVICE_LATENCY_IN_MILLIS);
    }

    public void getContent(String moduleId, GetContentCallback callback) {
        Handler handler = new Handler();
        handler.postDelayed(() -> callback.onContentReceived(jsonHelper.loadContent(moduleId)), SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadCoursesCallback {
        void onAllCoursesReceived(List<CourseResponse> courseResponses);

        void onDataNotAvailable();
    }

    public interface LoadModulesCallback {
        void onAllModulesReceived(List<ModuleResponse> moduleResponses);

        void onDataNotAvailable();
    }

    public interface GetContentCallback {
        void onContentReceived(ContentResponse contentResponse);

        void onDataNotAvailable();
    }
}
