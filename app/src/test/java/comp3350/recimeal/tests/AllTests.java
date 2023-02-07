package comp3350.recimeal.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.recimeal.tests.objects.CourseTest;
import comp3350.recimeal.tests.objects.SCTest;
import comp3350.recimeal.tests.objects.StudentTest;
import comp3350.recimeal.tests.business.CalculateGPATest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentTest.class,
        CourseTest.class,
        SCTest.class,
        CalculateGPATest.class
})
public class AllTests
{

}
