package in.khofid.academy.ui.reader;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import in.khofid.academy.FakeDataDummy;
import in.khofid.academy.data.ContentEntity;
import in.khofid.academy.data.CourseEntity;
import in.khofid.academy.data.ModuleEntity;
import in.khofid.academy.data.source.AcademyRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CourseReaderViewModelTest {

    private CourseReaderViewModel viewModel;
    private AcademyRepository academyRepository = mock(AcademyRepository.class);
    private CourseEntity courseEntity = FakeDataDummy.generateDummyCourses().get(0);
    private String courseId = courseEntity.getCourseId();
    private ArrayList<ModuleEntity> dummyModules = FakeDataDummy.generateDummyModules(courseId);
    private String moduleId = dummyModules.get(0).getModuleId();


    @Before
    public void setUp() {
        viewModel = new CourseReaderViewModel(academyRepository);
        viewModel.setCourseId(courseId);
    }

    @Test
    public void getModules() {
        when(academyRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules);
        ArrayList<ModuleEntity> moduleEntities = viewModel.getModules();
        verify(academyRepository).getAllModulesByCourse(courseId);
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }

    @Test
    public void getSelectedModule() {
        ModuleEntity moduleEntity = dummyModules.get(0);
        String content = "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>";
        moduleEntity.contentEntity = new ContentEntity(content);
        viewModel.setSelectedModule(moduleId);

        when(academyRepository.getContent(courseId, moduleId)).thenReturn(moduleEntity);
        ModuleEntity entity = viewModel.getSelectedModule();
        verify(academyRepository).getContent(courseId, moduleId);
        assertNotNull(entity);

        ContentEntity contentEntity = entity.contentEntity;
        assertNotNull(contentEntity);

        String resultContent = contentEntity.getContent();
        assertNotNull(resultContent);

        assertEquals(content, resultContent);
    }

}