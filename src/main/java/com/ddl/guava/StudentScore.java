package com.ddl.guava;

import java.util.Objects;

/**
 * description: 该文件说明
 * @author dongdongliu
 * @version 1.0
 * @date 2018-08-28 18:14:13
 */
public class StudentScore {
    public int CourseId;

    public int score;

    @Override
    public String toString() {
        return "StudentScore{" +
            "CourseId=" + CourseId +
            ", score=" + score +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentScore that = (StudentScore) o;
        return CourseId == that.CourseId &&
            score == that.score;
    }

    @Override
    public int hashCode() {

        return Objects.hash(CourseId, score);
    }
}
