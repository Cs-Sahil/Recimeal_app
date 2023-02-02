package comp3350.recimeal.objects;

import java.util.Objects;

public class Course
{
	private final String courseID;
	private final String courseName;

	public Course(final String newID)
	{
		courseID = newID;
		courseName = null;
	}

	public Course(final String newID, final String newCourseName)
	{
		courseID = newID;
		courseName = newCourseName;
	}

	public String getCourseID()
	{
		return (courseID);
	}

	public String getCourseName()
	{
		return (courseName);
	}

	public String toString()
	{
		//return String.format("Course: %s %s", courseID, courseName);
		return String.format("%s\n%s\nhere is some extra text to show you can scroll" +
				"according to all known laws of aviation, there is no way a bee should be able to fly" +
				"its wings are to small to get its fat little body off the ground",courseID,courseName);
	}

	public boolean equals(Object other)
	{
		boolean equals = false;
		if (other instanceof Course) {

			final Course otherCourse = (Course) other;
			equals = Objects.equals(this.courseID, otherCourse.courseID);

		}

		return equals;
	}
}