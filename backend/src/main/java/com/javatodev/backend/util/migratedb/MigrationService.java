package com.javatodev.backend.util.migratedb;


import com.javatodev.backend.activity.entity.Activity;
import com.javatodev.backend.activity.repository.ActivityRepository;
import com.javatodev.backend.booking.entity.Booking;
import com.javatodev.backend.booking.repository.BookingRepository;
import com.javatodev.backend.nosql.entity.ActivityNoSql;
import com.javatodev.backend.nosql.entity.BookingNoSql;
import com.javatodev.backend.nosql.entity.RatingNoSql;
import com.javatodev.backend.nosql.entity.UserNoSql;
import com.javatodev.backend.nosql.repository.ActivityNoSqlRepository;
import com.javatodev.backend.nosql.repository.BookingNoSqlRepository;
import com.javatodev.backend.nosql.repository.RatingNoSqlRepository;
import com.javatodev.backend.nosql.repository.UserNoSqlRepository;
import com.javatodev.backend.rating.entity.Rating;
import com.javatodev.backend.rating.repository.RatingRepository;
import com.javatodev.backend.user.entity.Guide;
import com.javatodev.backend.user.entity.Tourist;
import com.javatodev.backend.user.entity.User;
import com.javatodev.backend.user.repository.GuideRepository;
import com.javatodev.backend.user.repository.TouristRepository;
import com.javatodev.backend.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MigrationService {
    private final MongoTemplate mongoTemplate;
    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final BookingRepository bookingRepository;
    private final RatingRepository ratingRepository;
    private final GuideRepository guideRepository;
    private final TouristRepository touristRepository;


    private final UserNoSqlRepository userNoSqlRepository;
    private final ActivityNoSqlRepository activityNoSqlRepository;
    private final BookingNoSqlRepository bookingNoSqlRepository;
    private final RatingNoSqlRepository ratingNoSqlRepository;


    public void databaseDrop() {
        //delete mongoDB database if exists
        mongoTemplate.getDb().drop();
    }

    public void migrateData() {
        //migrate user data
        migrateUserData();
        //migrate activity data
        migrateActivityData();
        //migrate booking data
        migrateBookingData();
        //migrate rating data
        migrateRatingData();
    }

    // migrate the user data
    public void migrateUserData() {
        int page = 0;
        while (true) {
            //set the pagination default pagination size=10
            Pageable pageable = PageRequest.of(page, 10);
            //get 10 records(user records) from database
            Page<User> users = userRepository.findAll(pageable);
            //check whether users records empty or not. if users records empty then terminate while loop
            if (users.isEmpty()) {
                //the terminate while loop
                return;
            }
            //saving users list to database
            users.stream().forEach(user -> {
                UserNoSql userNoSql = new UserNoSql();
                userNoSql.setId(user.getId());
                userNoSql.setUsername(user.getUsername());
                userNoSql.setEmail(user.getEmail());
                userNoSql.setPassword(user.getPassword());
                userNoSql.setBio(user.getBio());
                userNoSql.setUserType(user.getUserType());
                //check user is guide or tourist
                if (user.getUserType().equals("Guide")) {
                    Guide guide = guideRepository.getById(user.getId());
                    userNoSql.setAverageRating(guide.getAverageRating());
                    userNoSql.setCountOfTours(guide.getCountOfTours());
                } else {
                    Tourist tourist = touristRepository.getById(user.getId());
                    userNoSql.setBirthday(tourist.getBirthday());
                    userNoSql.setLivingLocation(tourist.getLivingLocation());
                }
                userNoSqlRepository.save(userNoSql);
            });
            //increment page=0 -> page=1,...
            page++;
        }

    }

    // migrate the activity data
    public void migrateActivityData() {
        int page = 0;
        while (true) {
            //set the pagination default pagination size=10
            Pageable pageable = PageRequest.of(page, 10);
            //get 10 records(activity records) from database
            Page<Activity> activities = activityRepository.findAll(pageable);
            //check whether activity records empty or not. if activity records empty then terminate while loop
            if (activities.isEmpty()) {
                //the terminate while loop
                return;
            }
            //saving activity list to database
            activities.stream().forEach(activity -> {
                ActivityNoSql activityNoSql = new ActivityNoSql();
                activityNoSql.setId(activity.getId());
                activityNoSql.setTitle(activity.getTitle());
                activityNoSql.setDescription(activity.getDescription());
                activityNoSql.setLocation(activity.getLocation());
                activityNoSql.setActivityDate(activity.getActivityDate());
                activityNoSql.setListingDate(activity.getListingDate());
                activityNoSql.setGuideId(activity.getGuides().getId());
                activityNoSqlRepository.save(activityNoSql);
            });
            //increment page=0 -> page=1,...
            page++;
        }
    }

    // migrate the booking data
    public void migrateBookingData() {
        int page = 0;
        while (true) {
            //set the pagination default pagination size=10
            Pageable pageable = PageRequest.of(page, 10);
            //get 10 records(booking records) from database
            Page<Booking> bookings = bookingRepository.findAll(pageable);
            //check whether booking records empty or not. if booking records empty then terminate while loop
            if (bookings.isEmpty()) {
                //the terminate while loop
                return;
            }
            //saving booking list to database
            bookings.stream().forEach(booking -> {
                BookingNoSql bookingNoSql = new BookingNoSql();
                bookingNoSql.setBookingId(booking.getBookingId());
                bookingNoSql.setBookingDate(booking.getReservationDate());
                bookingNoSql.setReservationDate(booking.getReservationDate());
                bookingNoSql.setTouristId(booking.getTourist().getId());
                bookingNoSql.setActivityId(booking.getActivity().getId());
                bookingNoSqlRepository.save(bookingNoSql);
            });
            //increment page=0 -> page=1,...
            page++;
        }
    }

    // migrate the rating data
    public void migrateRatingData() {
        int page = 0;
        while (true) {
            //set the pagination default pagination size=10
            Pageable pageable = PageRequest.of(page, 10);
            //get 10 records(rating records) from database
            Page<Rating> ratings = ratingRepository.findAll(pageable);
            //check whether rating records empty or not. if rating records empty then terminate while loop
            if (ratings.isEmpty()) {
                //the terminate while loop
                return;
            }
            //saving rating list to database
            ratings.stream().forEach(rating -> {
                RatingNoSql ratingNoSql = new RatingNoSql();
                ratingNoSql.setRatingId(rating.getRatingId());
                ratingNoSql.setComment(rating.getComment());
                ratingNoSql.setSafety(rating.getSafety());
                ratingNoSql.setQuality(rating.getQuality());
                ratingNoSql.setRatingDate(rating.getRatingDate());
                ratingNoSql.setActivityId(rating.getActivity().getId());
                ratingNoSql.setTouristId(rating.getTourist().getId());
                ratingNoSqlRepository.save(ratingNoSql);
            });
            //increment page=0 -> page=1,... maven clean install -DskipTests=true
            page++;
        }
    }
}
