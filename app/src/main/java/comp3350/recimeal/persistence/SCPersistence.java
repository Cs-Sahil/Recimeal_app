package comp3350.recimeal.persistence;

import java.util.List;

import comp3350.recimeal.objects.SC;

public interface SCPersistence {
    List<SC> getSC(final String studentID);

    List<SC> getCS(final String courseID);
}
