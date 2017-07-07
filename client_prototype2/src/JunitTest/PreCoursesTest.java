package JunitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import application.SchoolClient;
import controllers.AssignPupilToClassController;

public class PreCoursesTest
{

	@Test
	public void testPreCoursesOK() throws IOException
	{
		String Class_ID = "1111";
		String Pupil_ID = "666666666";
		ArrayList<String> result = new ArrayList<>();
		AssignPupilToClassController res = new AssignPupilToClassController();

		result = res.checkTest(Class_ID);

		Assert.assertTrue("pupil has pre courses",result.contains(Pupil_ID));
	}

	@Test
	public void testPreCoursesFAIL() throws IOException
	{
		String Class_ID = "3232";
		String Pupil_ID = "757575757";
		ArrayList<String> result = new ArrayList<>();
		AssignPupilToClassController res = new AssignPupilToClassController();

		result = res.checkTest(Class_ID);

		Assert.assertFalse(result.contains(Pupil_ID));
	}

}
