package enums;

public enum ResultTableEnums {
    STUDENT_NAME("Student Name"),
    STUDENT_EMAIL("Student Email"),
    GENDER("Gender"),
    MOBILE("Mobile"),
    DATE_OF_BIRTH("Date of Birth"),
    SUBJECTS("Subjects"),
    HOBBIES("Hobbies"),
    PICTURE("Picture"),
    ADDRESS("Address"),
    STATE_AND_CITY("State and City");

    private final String value;

    ResultTableEnums(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}