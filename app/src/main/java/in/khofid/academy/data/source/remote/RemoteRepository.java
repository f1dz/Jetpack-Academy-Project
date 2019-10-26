package in.khofid.academy.data.source.remote;

import java.util.List;

import in.khofid.academy.data.source.remote.response.ContentResponse;
import in.khofid.academy.data.source.remote.response.CourseResponse;
import in.khofid.academy.data.source.remote.response.ModuleResponse;
import in.khofid.academy.utils.JsonHelper;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;


    private RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper jsonHelper) {
        if(INSTANCE == null) {
            INSTANCE = new RemoteRepository(jsonHelper);
        }
        return INSTANCE;
    }

    public List<CourseResponse> getAllCourses() {
        return jsonHelper.loadCourses();
    }

    public List<ModuleResponse> getModules(String courseId) {
        return jsonHelper.loadModule(courseId);
    }

    public ContentResponse getContent(String moduleId) {
        return jsonHelper.loadContent(moduleId);
    }
}
