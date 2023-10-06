package com.javatodev.backend.util.filldb;

import com.javatodev.backend.activity.entity.Activity;
import com.javatodev.backend.activity.repository.ActivityRepository;
import com.javatodev.backend.booking.entity.Booking;
import com.javatodev.backend.booking.repository.BookingRepository;
import com.javatodev.backend.commons.DatabaseUtilException;
import com.javatodev.backend.commons.HashMD5;
import com.javatodev.backend.rating.entity.Rating;
import com.javatodev.backend.rating.repository.RatingRepository;
import com.javatodev.backend.user.entity.Guide;
import com.javatodev.backend.user.entity.Tourist;
import com.javatodev.backend.user.entity.User;
import com.javatodev.backend.user.repository.GuideRepository;
import com.javatodev.backend.user.repository.TouristRepository;
import com.javatodev.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DatabaseFillService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  TouristRepository touristRepository;
    @Autowired
    private  GuideRepository guideRepository;

    @Autowired
    private  ActivityRepository activityRepository;
    @Autowired
    private  BookingRepository bookingRepository;
    @Autowired
    private  RatingRepository ratingRepository;

    private boolean isAlreadyFilled=false;


    // fills the Database with the example data
    public void fillDatabase() throws NoSuchAlgorithmException {

        //check whether the data already fill or not
        if (!isAlreadyFilled){
            createUsers();
            createActivity();
            createBooking();
            createRating();
            isAlreadyFilled=true;
        } else {
            //data already filled.
            throw new DatabaseUtilException("Database has already been filled");
        }

    }


    //user's records
    private void createUsers() throws NoSuchAlgorithmException {
        //set users records
        //we are using String array
        String[] USERNAME = new String[]{"Gottschalk88", "Thomas1 ", "David", "Anna", "Sophia",
                "Lisa", "Bernd", "Alex25", "Elisa","Bellinda"};
        String[] EMAIL = new String[]{"gottscha@email.com", "thomas@gmail.com", "david@gmail.com", "anna@gmail.com",
                "sophia@gmail.com", "lisa@gmail.com", "bernd@gmail.com", "alex@gmail.com", "elisa@gmail.com","Bellinda@gmail.com"};
       //hashing the given password
        String[] PASSWORD = new String[]{new HashMD5().giveHash("password"), new HashMD5().giveHash("password"), new HashMD5().giveHash("password"),new HashMD5().giveHash("password") ,new HashMD5().giveHash("adoijado") ,new HashMD5().giveHash("daojado") ,
                new HashMD5().giveHash("oadoajoad"), new HashMD5().giveHash("oadojadja"),new HashMD5().giveHash("adojaodijaj"),new HashMD5().giveHash("Bellasda")};
        String[] BIO = new String[]{"I am a hiking lover", "I am a hiking lover", "I am a Guide", "I am a hiking lover", "I am a Guide", "I am a hiking lover",
                "I am a Guide", "I am a Guide", "I am a Guide","I am a Guide"};
        String[] USERTYPE = new String[]{"Tourist", "Tourist", "Guide", "Tourist", "Guide", "Tourist", "Guide", "Guide",
                "Guide","Guide"};

        //set tourist birthday
        Map<String, String> touristBirthDay = new LinkedHashMap<>();
        touristBirthDay.put("Gottschalk88", "1970-03-20");
        touristBirthDay.put("Thomas1", "1980-06-20");
        touristBirthDay.put("Anna", "1990-03-20");
        touristBirthDay.put("Lisa", "1998-03-20");

        //set tourist LivingLocation
        Map<String, String> touristLivingLocation = new LinkedHashMap<>();
        touristLivingLocation.put("Gottschalk88", "Seegasse 14,Wien");
        touristLivingLocation.put("Thomas1", "Seestrasse 33,Salzburg");
        touristLivingLocation.put("Anna", "Kirchgasse 21, Linz");
        touristLivingLocation.put("Lisa", "Baugasse 1A, Wien");

        User user;

        //filling the register_user (In we can't use "users" as table name. because users is  postgressql one of preserve keyword)
        for (int i = 0; i < 10; i++) {
            user = new User();
            user.setUsername(USERNAME[i]);
            user.setEmail(EMAIL[i]);
            user.setPassword(PASSWORD[i]);
            user.setBio(BIO[i]);
            user.setUserType(USERTYPE[i]);
            user.setRegistrationDate(new Date());

            //create User- Save the guide and tourist common data
            User newUser = userRepository.save(user);

            //check whether user is guide or tourist
            if (USERTYPE[i] == "Tourist") {
                Tourist tourist = new Tourist();
                tourist.setId(newUser.getId());
                //set the tourist's birth day
                if (touristBirthDay.containsKey(USERNAME[i])) {
                    tourist.setBirthday(touristBirthDay.get(USERNAME[i]));
                }
                //set the tourist's living location
                if (touristLivingLocation.containsKey(USERNAME[i])) {
                    tourist.setLivingLocation(touristLivingLocation.get(USERNAME[i]));
                }
                //save tourist other details
                touristRepository.save(tourist);
            } else {
                //user is a guide
                Guide guide = new Guide();
                guide.setId(user.getId());
                guide.setAverageRating(0.0);
                guide.setCountOfTours(0);
                //save guide other details
                guideRepository.save(guide);
            }

        }
    }

    //activity records
    private void createActivity() {

        //set activity records
        //we are using String array
        String[] TITLE = new String[]{"Bike Riding", "Hiking", "Watch Cricket Match", "Hiking", "Hiking","Ice Skating","Yoga","Sunbathe","Ice Skating","Sunbathe"};
        String[] DESCRIPTION = new String[]{"Bike Riding", "Hiking", "Watch Cricket Match", "Hiking", "Hiking","Ice Skating","Yoga","Sunbathe","Ice Skating","Sunbathe"};
        String[] LOCATION = new String[]{"Wien", "Salzburg", "Linz", "Wien", "Linz","Vienna","Basilona","Mielna","Ottawa","Bibione"};
        String[] ACTIVITYDATE = new String[]{"2022-06-26", "2022-07-26", "2022-06-28", "2022-06-29", "2022-07-20","2022-06-26", "2022-07-26", "2022-06-28", "2022-06-29", "2022-07-20"};

        //create guides object
        Guide guide1 = new Guide();
        guide1.setId(3L);
        guide1.setAverageRating(0.0);
        guide1.setCountOfTours(0);

        Guide guide2 = new Guide();
        guide2.setId(5L);
        guide2.setAverageRating(0.0);
        guide2.setCountOfTours(0);

        Guide guide3 = new Guide();
        guide3.setId(7L);
        guide3.setAverageRating(0.0);
        guide3.setCountOfTours(0);

        Guide guide4 = new Guide();
        guide4.setId(8L);
        guide4.setAverageRating(0.0);
        guide4.setCountOfTours(0);

        Guide guide5 = new Guide();
        guide5.setId(9L);
        guide5.setAverageRating(0.0);
        guide5.setCountOfTours(0);

        Guide guide6 = new Guide();
        guide6.setId(10L);
        guide6.setAverageRating(0.0);
        guide6.setCountOfTours(0);


        Guide[] GUIDEID = new Guide[]{
                guide3, guide1, guide2, guide3, guide4, guide1, guide2, guide3, guide5, guide6};
        Activity activity;

        //iteration
        for (int i = 0; i < 10; i++) {
            activity = new Activity();
            activity.setTitle(TITLE[i]);
            activity.setDescription(DESCRIPTION[i]);
            activity.setLocation(LOCATION[i]);
            activity.setListingDate(new Date());
            activity.setActivityDate(ACTIVITYDATE[i]);
            activity.setGuides(GUIDEID[i]);

            //create Activity
            activityRepository.save(activity);
        }
    }

    //booking records
    private void createBooking() {
        String[] RESERVASATIONDATE = new String[]{"2022-07-12", "2022-08-16", "2022-07-27", "2022-08-27", "2022-09-15", "2022-07-22"};

        //create tourist
        Tourist tourist1 = new Tourist();
        tourist1.setId(1L);
        tourist1.setBirthday("1970-03-20");
        tourist1.setLivingLocation("Street 14,Wien");

        Tourist tourist2 = new Tourist();
        tourist2.setId(2L);
        tourist2.setBirthday("1980-06-20");
        tourist2.setLivingLocation("Seestrasse 33,Salzburg");

        Tourist tourist3 = new Tourist();
        tourist3.setId(4L);
        tourist3.setBirthday("1990-03-20");
        tourist3.setLivingLocation("Kirchgasse 41, Linz");

        Tourist tourist4 = new Tourist();
        tourist4.setId(6L);
        tourist4.setBirthday("1998-03-20");
        tourist4.setLivingLocation("Street 1A, Wien");

        Tourist[] TOURISTS = new Tourist[]{tourist1, tourist2, tourist3, tourist4, tourist1, tourist2};


        //create activity
        Activity activity1 = new Activity();
        activity1.setId(1L);
        activity1.setTitle("Bike Riding");
        activity1.setDescription("Bike Riding");
        activity1.setActivityDate("2022-06-26");
        activity1.setLocation("Wien");
        activity1.setListingDate(new Date(2020 - 06 - 21));

        Activity activity2 = new Activity();
        activity2.setId(2L);
        activity2.setTitle("Hiking");
        activity2.setDescription("Hiking");
        activity2.setActivityDate("2022-07-26");
        activity2.setLocation("Salzburg");
        activity2.setListingDate(new Date(2020 - 06 - 21));

        Activity activity3 = new Activity();
        activity3.setId(3L);
        activity3.setTitle("Watch Cricket Match");
        activity3.setDescription("Watch Cricket Match");
        activity3.setActivityDate("2022-06-28");
        activity3.setLocation("Linz");
        activity3.setListingDate(new Date(2020 - 06 - 21));

        Activity activity4 = new Activity();
        activity4.setId(3L);
        activity4.setTitle("Hiking");
        activity4.setDescription("Hiking");
        activity4.setActivityDate("2022-06-29");
        activity4.setLocation("Wien");
        activity4.setListingDate(new Date(2020 - 06 - 21));

        Activity activity5 = new Activity();
        activity5.setId(5L);
        activity5.setTitle("Hiking");
        activity5.setDescription("Hiking");
        activity5.setActivityDate("2022-07-20");
        activity5.setLocation("Linz");
        activity5.setListingDate(new Date(2020 - 06 - 21));

        Activity activity6 = new Activity();
        activity6.setId(5L);
        activity6.setTitle("Ice Skating");
        activity6.setDescription("Ice Skating");
        activity6.setActivityDate("2022-06-26");
        activity6.setLocation("Vienna");
        activity6.setListingDate(new Date(2020 - 06 - 21));

        Activity[] ACTIVITIES = new Activity[]{activity1, activity2, activity3, activity4, activity5, activity6};

        Booking booking;
        for (int i = 0; i < 6; i++) {
            booking = new Booking();
            booking.setBookingDate(new Date());
            booking.setReservationDate(RESERVASATIONDATE[i]);
            booking.setTourist(TOURISTS[i]);
            booking.setActivity(ACTIVITIES[i]);

            //create Booking
            bookingRepository.save(booking);
        }

    }

    //rating records
    private void createRating() {
        String[] COMMENT = new String[]{"Very good", "Bad", "Good", "Very good", "Bad","Very good" };
        String[] SAFETY = new String[]{"Very good", "Good", "Very good", "Good", "Good","Good" };
        String[] QUALITY = new String[]{"Very good", "Bad", "Very good", "Very good", "Bad","Very good" };

        //create tourist
        Tourist tourist1 = new Tourist();
        tourist1.setId(1L);
        tourist1.setBirthday("1970-03-20");
        tourist1.setLivingLocation("Street 14,Wien");

        Tourist tourist2 = new Tourist();
        tourist2.setId(2L);
        tourist2.setBirthday("1980-06-20");
        tourist2.setLivingLocation("Seestrasse 33,Salzburg");

        Tourist tourist3 = new Tourist();
        tourist3.setId(4L);
        tourist3.setBirthday("1990-03-20");
        tourist3.setLivingLocation("Kirchgasse 41, Linz");

        Tourist tourist4 = new Tourist();
        tourist4.setId(6L);
        tourist4.setBirthday("1998-03-20");
        tourist4.setLivingLocation("Street 1A, Wien");

        Tourist[] TOURISTS = new Tourist[]{tourist1, tourist2, tourist3, tourist4, tourist1, tourist2};


        //create activity
        Activity activity1 = new Activity();
        activity1.setId(1L);
        activity1.setTitle("Bike Riding");
        activity1.setDescription("Bike Riding");
        activity1.setActivityDate("2022-06-26");
        activity1.setLocation("Wien");
        activity1.setListingDate(new Date(2020 - 06 - 21));

        Activity activity2 = new Activity();
        activity2.setId(2L);
        activity2.setTitle("Hiking");
        activity2.setDescription("Hiking");
        activity2.setActivityDate("2022-07-26");
        activity2.setLocation("Salzburg");
        activity2.setListingDate(new Date(2020 - 06 - 21));

        Activity activity3 = new Activity();
        activity3.setId(3L);
        activity3.setTitle("Watch Cricket Match");
        activity3.setDescription("Watch Cricket Match");
        activity3.setActivityDate("2022-06-28");
        activity3.setLocation("Linz");
        activity3.setListingDate(new Date(2020 - 06 - 21));

        Activity activity4 = new Activity();
        activity4.setId(3L);
        activity4.setTitle("Hiking");
        activity4.setDescription("Hiking");
        activity4.setActivityDate("2022-06-29");
        activity4.setLocation("Wien");
        activity4.setListingDate(new Date(2020 - 06 - 21));

        Activity activity5 = new Activity();
        activity5.setId(5L);
        activity5.setTitle("Hiking");
        activity5.setDescription("Hiking");
        activity5.setActivityDate("2022-07-20");
        activity5.setLocation("Linz");
        activity5.setListingDate(new Date(2020 - 06 - 21));

        Activity activity6 = new Activity();
        activity6.setId(5L);
        activity6.setTitle("Ice Skating");
        activity6.setDescription("Ice Skating");
        activity6.setActivityDate("2022-06-26");
        activity6.setLocation("Vienna");
        activity6.setListingDate(new Date(2020 - 06 - 21));

        Activity[] ACTIVITIES = new Activity[]{activity1, activity2, activity3, activity4, activity5, activity6};

        Rating rating;
        for (int i = 0; i < 6; i++) {
            rating = new Rating();
            rating.setComment(COMMENT[i]);
            rating.setSafety(SAFETY[i]);
            rating.setQuality(QUALITY[i]);
            rating.setActivity(ACTIVITIES[i]);
            rating.setTourist(TOURISTS[i]);

            //create Rating
            ratingRepository.save(rating);
        }
    }

}














//package com.javatodev.backend.util.filldb;
//
//import com.javatodev.backend.activity.entity.Activity;
//import com.javatodev.backend.activity.repository.ActivityRepository;
//import com.javatodev.backend.booking.entity.Booking;
//import com.javatodev.backend.booking.repository.BookingRepository;
//import com.javatodev.backend.commons.DatabaseUtilException;
//import com.javatodev.backend.rating.entity.Rating;
//import com.javatodev.backend.rating.repository.RatingRepository;
//import com.javatodev.backend.user.entity.Guide;
//import com.javatodev.backend.user.entity.Tourist;
//import com.javatodev.backend.user.entity.User;
//import com.javatodev.backend.user.repository.GuideRepository;
//import com.javatodev.backend.user.repository.TouristRepository;
//import com.javatodev.backend.user.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//@Service
//public class DatabaseFillService {
//
//    @Autowired
//    private  UserRepository userRepository;
//    @Autowired
//    private  TouristRepository touristRepository;
//    @Autowired
//    private  GuideRepository guideRepository;
//
//    @Autowired
//    private  ActivityRepository activityRepository;
//    @Autowired
//    private  BookingRepository bookingRepository;
//    @Autowired
//    private  RatingRepository ratingRepository;
//
//    private boolean isAlreadyFilled=false;
//
//
//    public void fillDatabase() {
//
//        if (!isAlreadyFilled){
//            createUsers();
//            createActivity();
//            createBooking();
//            createRating();
//            isAlreadyFilled=true;
//        }else {
//            throw new DatabaseUtilException("Database has already been filled");
//        }
//
//    }
//
//
//    private void createUsers() {
//        String[] USERNAME = new String[]{"Gottschalk88", "Thomas1 ", "David", "Anna", "Sophia",
//                "Lisa", "Bernd", "Alex25", "Elisa"};
//        String[] EMAIL = new String[]{"gottscha@email.com", "thomas@gmail.com", "david@gmail.com", "anna@gmail.com",
//                "sophia@gmail.com", "lisa@gmail.com", "bernd@gmail.com", "alex@gmail.com", "elisa@gmail.com",};
//        String[] PASSWORD = new String[]{"password", "abc12345", "david444", "aojdoajwod", "adoijado", "daojado",
//                "oadoajoad", "oadojadja", "adojaodijaj"};
//        String[] BIO = new String[]{"1010", "1100", "1050", "1060", "1180", "1010", "5020", "4040", "6800"};
//        String[] USERTYPE = new String[]{"Tourist", "Tourist", "Guide", "Tourist", "Guide", "Tourist", "Guide", "Guide",
//                "Guide"};
//
//        //tourist birthday
//        Map<String, String> touristBirthDay = new LinkedHashMap<>();
//        touristBirthDay.put("Gottschalk88", "1970-03-20");
//        touristBirthDay.put("Thomas1", "1980-06-20");
//        touristBirthDay.put("Anna", "1990-03-20");
//        touristBirthDay.put("Lisa", "1998-03-20");
//
//        //tourist LivingLocation
//        Map<String, String> touristLivingLocation = new LinkedHashMap<>();
//        touristBirthDay.put("Gottschalk88", "Street 14,Wien");
//        touristBirthDay.put("Thomas1", "Seestrasse 33,Salzburg");
//        touristBirthDay.put("Anna", "Kirchgasse 41, Linz");
//        touristBirthDay.put("Lisa", "Street 1A, Wien");
//
//        User user;
//
//        for (int i = 0; i < 9; i++) {
//            user = new User();
//            user.setUsername(USERNAME[i]);
//            user.setEmail(EMAIL[i]);
//            user.setPassword(PASSWORD[i]);
//            user.setBio(BIO[i]);
//            user.setUserType(USERTYPE[i]);
//            user.setRegistrationDate(new Date());
//
//            //create User- Save the guide and tourist common data
//            User newUser = userRepository.save(user);
//
//            if (USERTYPE[i] == "Tourist") {
//                Tourist tourist = new Tourist();
//                tourist.setId(newUser.getId());
//                if (touristBirthDay.containsKey(USERNAME[i])) {
//                    tourist.setBirthday(touristBirthDay.get(USERNAME[i]));
//                }
//                if (touristLivingLocation.containsKey(USERNAME[i])) {
//                    tourist.setBirthday(touristLivingLocation.get(USERNAME[i]));
//                }
//                //save tourist other details
//                touristRepository.save(tourist);
//            } else {
//                Guide guide = new Guide();
//                guide.setId(user.getId());
//                guide.setAverageRating(0.0);
//                guide.setCountOfTours(0);
//                guideRepository.save(guide);
//            }
//
//        }
//    }
//
//    private void createActivity() {
//
//        String[] TITLE = new String[]{"Bike Riding", "Hiking", "Watch Cricket Match", "Hiking", "Hiking"};
//        String[] DESCRIPTION = new String[]{"Bike Riding", "Hiking", "Watch Cricket Match", "Hiking", "Hiking"};
//        String[] LOCATION = new String[]{"Wien", "Salzburg", "Linz", "Wien", "Linz"};
//        String[] ACTIVITYDATE = new String[]{"2022-06-26", "2022-07-26", "2022-06-28", "2022-06-29", "2022-07-20"};
//
//        Guide guideDavid = new Guide();
//        guideDavid.setId(3L);
//        guideDavid.setAverageRating(0.0);
//        guideDavid.setCountOfTours(0);
//
//
//        Guide[] GUIDEID = new Guide[]{
//                guideDavid, guideDavid, guideDavid, guideDavid, guideDavid};
//        Activity activity;
//
//        for (int i = 0; i < 5; i++) {
//            activity = new Activity();
//            activity.setTitle(TITLE[i]);
//            activity.setDescription(DESCRIPTION[i]);
//            activity.setLocation(LOCATION[i]);
//            activity.setListingDate(new Date());
//            activity.setActivityDate(ACTIVITYDATE[i]);
//            activity.setGuides(GUIDEID[i]);
//
//            //create Activity
//            activityRepository.save(activity);
//        }
//    }
//
//    private void createBooking() {
//        String[] RESERVASATIONDATE = new String[]{"2022-07-26"};
//
//        //create tourist
//        Tourist tourist = new Tourist();
//        tourist.setId(1L);
//        tourist.setBirthday("1970-03-20");
//        tourist.setLivingLocation("Street 14,Wien");
//        Tourist[] TOURISTS = new Tourist[]{tourist};
//
//        //create activity
//        Activity activity = new Activity();
//        activity.setId(1L);
//        activity.setTitle("Bike Riding");
//        activity.setDescription("Bike Riding");
//        activity.setActivityDate("2022-06-26");
//        activity.setLocation("Wien");
//        activity.setListingDate(new Date(2020 - 06 - 21));
//        Activity[] ACTIVITIES = new Activity[]{activity};
//
//        Booking booking;
//        for (int i = 0; i < 1; i++) {
//            booking = new Booking();
//            booking.setBookingDate(new Date());
//            booking.setReservationDate(RESERVASATIONDATE[i]);
//            booking.setTourist(TOURISTS[i]);
//            booking.setActivity(ACTIVITIES[i]);
//
//            //create Booking
//            bookingRepository.save(booking);
//        }
//
//    }
//
//    private void createRating() {
//        String[] COMMENT = new String[]{"Very good"};
//        String[] SAFETY = new String[]{"Very good"};
//        String[] QUALITY = new String[]{"Very good"};
//
//        //create tourist
//        Tourist tourist = new Tourist();
//        tourist.setId(1L);
//        tourist.setBirthday("1970-03-20");
//        tourist.setLivingLocation("Street 14,Wien");
//        Tourist[] TOURISTS = new Tourist[]{tourist};
//
//        //create activity
//        Activity activity = new Activity();
//        activity.setId(1L);
//        activity.setTitle("Bike Riding");
//        activity.setDescription("Bike Riding");
//        activity.setActivityDate("2022-06-26");
//        activity.setLocation("Wien");
//        activity.setListingDate(new Date(2020 - 06 - 21));
//        Activity[] ACTIVITIES = new Activity[]{activity};
//
//        Rating rating;
//        for (int i = 0; i < 1; i++) {
//            rating = new Rating();
//            rating.setComment(COMMENT[i]);
//            rating.setSafety(SAFETY[i]);
//            rating.setQuality(QUALITY[i]);
//            rating.setActivity(ACTIVITIES[i]);
//            rating.setTourist(TOURISTS[i]);
//
//            //create Rating
//            ratingRepository.save(rating);
//        }
//    }
//
//}
