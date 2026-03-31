package utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {
    private static final Faker faker = new Faker(new Locale("en-GB"));

    //public String firstName = getRandomString(10);
    //public String lastName = getRandomString(10);
    //public String userEmail = getRandomEmail();
    //public String streetAddress = getRandomAddress();

    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = getRandomGender(),
            phoneNumber = getRandomPhoneNumber(),
            invalidPhoneNumber = "99999999",
            randomString = getRandomString(2),
            day = getRandomDay(),
            month = getRandomMonth(),
            year = getRandomYear(),
            subjects = getRandomSubjects(),
            hobbies = getRandomHobbies(),
            uploadFile = getRandomFile(),
            streetAddress = faker.address().streetAddress(),
            state = getRandomState(),
            city = getRandomCity();

    public String getRandomString(int len) {
        //String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

     /*public String getRandomEmail() {
        return getRandomString(10) + "@gmail.com";
    }*/

    public String getRandomPhoneNumber() {
        /*String AB = "0123456789";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();*/

        long num = ThreadLocalRandom.current().nextLong(9_000_000_000L, 10_000_000_000L);
        return Long.toString(num);
    }

    public String getRandomDay() {
        return String.valueOf(faker.number().numberBetween(1, 28));
    }

    public String getRandomMonth() {
        String[] month = {"December", "January", "February", "March",
                "April", "May", "June", "July",
                "August", "September", "October", "November"};
        return faker.options().option(month);
    }

    public String getRandomYear() {
        return String.valueOf(faker.number().numberBetween(1950, 2012));
    }

    public int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /*public String getRandomPhone() {
        return String.format("+%s (%s) %s - %s - %s", getRandomInt(1, 9), getRandomInt (111, 999),
                getRandomInt(111, 999), getRandomInt(11, 99), getRandomInt(11, 99));
    }*/

    public String getRandomItemFromArray(String[] array){
        int index = getRandomInt(0, array.length - 1);
        return array[index];
    }

    public String getRandomGender(){
        String[] genders = {"Male", "Female", "Other"};
        return getRandomItemFromArray(genders);
    }

    public String getRandomSubjects(){
        String[] subjects = {"Maths", "Chemistry", "Computer Science",
                "Commerce", "Accounting", "Economics",
                "Social Studies", "Civics"};
        return getRandomItemFromArray(subjects);
    }

    public String getRandomHobbies(){
        String[] hobbies = {"Sports", "Reading", "Music"};
        return getRandomItemFromArray(hobbies);
    }

     /*public String getRandomAddress() {
        return getRandomString(10) + " " + getRandomString(10) + " " + getRandomString(10);
    }*/

    public String getRandomState(){
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String getRandomCity(){
        if (state.equals("NCR")) city = faker.options().option("Delhi", "Gurgaon", "Noida");
        if (state.equals("Uttar Pradesh")) city = faker.options().option("Agra", "Lucknow", "Merrut");
        if (state.equals("Haryana")) city = faker.options().option("Karnal", "Panipat");
        if (state.equals("Rajasthan")) city = faker.options().option("Jaipur", "Jaiselmer");
        return city;
    }

    public String getRandomFile(){
        return faker.options().option("test1.jpg", "test2.png");
    }
}
