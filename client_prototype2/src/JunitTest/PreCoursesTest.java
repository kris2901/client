package JunitTest;

import static org.junit.Assert.*;
import org.junit.Test;

import controllers.AssignPupilToClassController;

public class PreCoursesTest
{

	@Test
	public void testPreCourses()
	{
		//יצירת אובייקט שבו ניעזר לביצוע הבדיקה
		AssignPupilToClassController Ineed = new AssignPupilToClassController();
		AssignPupilToClassController Ihave = new AssignPupilToClassController();
		AssignPupilToClassController expected = new AssignPupilToClassController();
		
		//ביצוע פעולה שאת התוצאות שלה אנחנו נבדוק
		AssignPupilToClassController Result= null;
		//השוואת התוצאה למה שציפיתי לקבל (ע"י assert.)
		
		//Assert.assertTrue(expected.equals(result));
	
		fail("Not yet implemented");
	}

}
