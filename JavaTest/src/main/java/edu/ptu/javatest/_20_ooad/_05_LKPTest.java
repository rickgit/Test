package edu.ptu.javatest._20_ooad;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//最少知识原则（Least Knowledge Principle），或者称迪米特法则（Law of Demeter）
//最小知识原则，与直接朋友联系
// teacher
//      Committee班委
//           studen
//降低耦合
public class _05_LKPTest {
    @Test
    public void testLKPNone() {
        Assert.assertFalse(new TeacherKnowAll().checkWork(new Committe(new ArrayList<Student>() {{
            add(new Student());
        }})));
    }

    public static class Student {
        boolean isDoneHomeWork;
    }

    public static class Committe {
        List<Student> studentList;

        Committe(List<Student> students) {
            this.studentList = students;
        }

    }

    public static class TeacherKnowAll {
        public boolean checkWork(Committe committe) {
            if (committe == null || committe.studentList == null) {
                return false;//throw exception
            }
            int size = committe.studentList.size();
            for (int i = 0; i < size; i++) {
                if (!committe.studentList.get(i).isDoneHomeWork)
                    return false;
            }
            return true;
        }
    }

    @Test
    public void testLKP() {
        Assert.assertFalse(new TeacherKnowCommitte().checkWork(new CommitteCheckStudent(new ArrayList<Student>() {{
            add(new Student());
        }})));
    }

    public static class CommitteCheckStudent {
        List<Student> studentList;

        CommitteCheckStudent(List<Student> students) {
            this.studentList = students;
        }

        public boolean checkWork() {
            if (studentList == null) {
                return false;//throw exception
            }
            int size = studentList.size();
            for (int i = 0; i < size; i++) {
                if (!studentList.get(i).isDoneHomeWork)
                    return false;
            }
            return true;
        }
    }

    public static class TeacherKnowCommitte {
        public boolean checkWork(CommitteCheckStudent committe) {
            if (committe == null) {
                return false;//throw exception
            }
            committe.checkWork();

            return true;
        }
    }
}
