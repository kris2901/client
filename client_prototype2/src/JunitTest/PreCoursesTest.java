package JunitTest;

import static org.junit.Assert.*;
import org.junit.Test;

import controllers.AssignPupilToClassController;

public class PreCoursesTest
{

	@Test
	public void testPreCourses()
	{
		//����� ������� ��� ����� ������ ������
		AssignPupilToClassController Ineed = new AssignPupilToClassController();
		AssignPupilToClassController Ihave = new AssignPupilToClassController();
		AssignPupilToClassController expected = new AssignPupilToClassController();
		
		//����� ����� ��� ������� ��� ����� �����
		AssignPupilToClassController Result= null;
		//������ ������ ��� ������� ���� (�"� assert.)
		
		//Assert.assertTrue(expected.equals(result));
	
		fail("Not yet implemented");
	}

}
