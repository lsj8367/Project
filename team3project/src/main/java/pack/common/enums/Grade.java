package pack.common.enums;

public enum Grade {
    READY("0"),
    FIRST_GRADE("1"),
    SECOND_GRADE("2"),
    THIRD_GRADE("3"),
    FORTH_GRADE("4"),
    FIFTH_GRADE("5");

    private String grade;

    Grade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

}
