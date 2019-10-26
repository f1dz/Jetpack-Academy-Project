package in.khofid.academy.data.source;

import java.util.List;

import in.khofid.academy.data.CourseEntity;
import in.khofid.academy.data.ModuleEntity;

public interface AcademyDataSource {

    List<CourseEntity> getAllCourses();

    CourseEntity getCourseWithModules(String courseId);

    List<ModuleEntity> getAllModulesByCourse(String courseId);

    List<CourseEntity> getBookmarkedCourses();

    ModuleEntity getContent(String courseId, String moduleId);
}
