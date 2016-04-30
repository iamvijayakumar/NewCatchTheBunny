package bunnycatch.com.catchthebunny.beans;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bunnycatch.com.catchthebunny.helper.Config;
import bunnycatch.com.catchthebunny.helper.Helper;

/**
 * Created by user on 12/15/2015.
 */
public class UserDetails {

    private String userName;
    private String lastName;
    private String deviceToken;
    private String userToken;
    private String userEmailId;
    private String userEnergyLevel;
    private String userStreak;
    private String userScore;
    private String userProfile;
    private String todaysUserScore;
    //Temporary scores which gets added once verified from the server
    private String userEnergyLevelTemp;
    private String userStreakTemp;
    private String userStreakTarget;
    private String userStreakBanner;
    private String userStreakDes;
    private String userScoreTemp = 0+"";

    private String inviteId;
    private String fbId;
    private String dateOfBirth;
    private String userLocation;
    private String gender;
    private String TAG;
    private String duelEnergy;
    private String fbShare;
    private String inviteEnergy;
    private String profileEnergy;
    private String streakId;
    private String myUserId;
    private int lastSetShownRate =0;

    String address;
    String city;
    String state;
    String country;
    String pin;
    String age;
    String status;
    String occupation;
    String mobile;
    Date dob;
    String child;
    String trizzioPoints;
    String occupationPosition;
    String cityPosition;
    String statePosition;
    String bannerDesc;
    Boolean rateUsStatus=false;
    String attendedQuestions;
    public String getAttendedQuestions() {
        return attendedQuestions;
    }

    public int getLastSetShownRate() {
        return lastSetShownRate;
    }

    public void setLastSetShownRate(int lastSetShownRate) {
        this.lastSetShownRate = lastSetShownRate;
    }

    public void setAttendedQuestions(String attendedQuestions) {
        this.attendedQuestions = attendedQuestions;
    }
    public Boolean getRateUsStatus() {
        return rateUsStatus;
    }

    public void setRateUsStatus(Boolean rateUsStatus) {
        this.rateUsStatus = rateUsStatus;
    }
    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public String getBannerDesc() {
        return bannerDesc;
    }

    public void setBannerDesc(String bannerDesc) {
        this.bannerDesc = bannerDesc;
    }



    public String getUserStreakBanner() {
        return userStreakBanner;
    }

    public void setUserStreakBanner(String userStreakBanner) {
        this.userStreakBanner = userStreakBanner;
    }

    public String getUserStreakDes() {
        return userStreakDes;
    }

    public void setUserStreakDes(String userStreakDes) {
        this.userStreakDes = userStreakDes;
    }

    public String getCityPosition() {
        return cityPosition;
    }

    public void setCityPosition(String cityPosition) {
        this.cityPosition = cityPosition;
    }

    public String getStatePosition() {
        return statePosition;
    }

    public void setStatePosition(String statePosition) {
        this.statePosition = statePosition;
    }

    public String getOccupationPosition() {
        return occupationPosition;
    }

    public void setOccupationPosition(String occupationPosition) {
        this.occupationPosition = occupationPosition;
    }


    public String getStreakId() {
        return streakId;
    }

    public void setStreakId(String streakId) {
        this.streakId = streakId;
    }

    String referalLink;

    public String getTrizzioPoints() {
        return trizzioPoints;
    }

    public void setTrizzioPoints(String trizzioPoints) {
        this.trizzioPoints = trizzioPoints;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getReferalLink() {

        return referalLink;

    }

    public void setReferalLink(String referalLink) {
        this.referalLink = referalLink;
    }

    public String getUserStreakTarget() {
        return userStreakTarget;
    }

    public void setUserStreakTarget(String userStreakTarget) {
        this.userStreakTarget = userStreakTarget;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTodaysUserScore() {
        if(todaysUserScore==null || todaysUserScore.equals("") || todaysUserScore.equals("null")){
            todaysUserScore = 0+"";
        }
        return todaysUserScore;
    }

    public void setTodaysUserScore(String todaysUserScore) {
        this.todaysUserScore = todaysUserScore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAge() {

        Calendar cal = Calendar.getInstance();

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dob = sdf.parse(age);
            Log.d("dob in gety", String.valueOf(dob));
        }
        catch (Exception e)
        {
            Log.e("error age",e.getMessage());
        }
        cal.setTime(dob);
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        int ageYr = today.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < cal.get(Calendar.DAY_OF_YEAR))
        {--ageYr;}

        Log.d("age year", String.valueOf(ageYr));
        String ageYear=String.valueOf(ageYr);
        return ageYear;
    }


    public void setAge(String age) {
        this.age=age;
        Log.d("age in set age",age);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        Log.d("status in set",status);
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDuelEnergy() {
        return duelEnergy;
    }

    public void setDuelEnergy(String duelEnergy) {
        this.duelEnergy = duelEnergy;
    }

    public String getFbShare() {

        return fbShare;
    }

    public void setFbShare(String fbShare) {
        this.fbShare = fbShare;
    }

    public String getInviteEnergy() {
        return inviteEnergy;
    }

    public void setInviteEnergy(String inviteEnergy) {
        this.inviteEnergy = inviteEnergy;
    }

    public String getProfileEnergy() {
        return profileEnergy;
    }

    public void setProfileEnergy(String profileEnergy) {
        this.profileEnergy = profileEnergy;
    }



    private static final String my_user_details = "my_user_details";
    public UserDetails(String TAG) {
        this.TAG = TAG +" ->userDetails";
    }

    public UserDetails() {

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserProfile() {
        Helper.nullChecker("getUserProfile",userProfile,TAG);
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        Helper.nullChecker("setUserProfile",userProfile,TAG);
        this.userProfile = userProfile;
    }

    public String getUserEnergyLevelTemp() {
        return userEnergyLevelTemp;
    }

    public void setUserEnergyLevelTemp(String userEnergyLevelTemp) {
        this.userEnergyLevelTemp = userEnergyLevelTemp;
    }

    public String getUserStreakTemp() {
        if(userStreakTemp==null || userStreakTemp=="null"){
            userStreakTemp = 0+"";
        }
        return userStreakTemp;
    }

    public void setUserStreakTemp(String userStreakTemp) {
        this.userStreakTemp = userStreakTemp;
    }

    public String getUserScoreTemp() {

        return userScoreTemp;
    }

    public void setUserScoreTemp(String userScoreTemp) {
        this.userScoreTemp = userScoreTemp;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public String getUserEnergyLevel() {
        Helper.nullChecker("getUserEnergyLevel",userEnergyLevel,TAG);
        return userEnergyLevel;
    }

    public void setUserEnergyLevel(String userEnergyLevel) {
        Helper.nullChecker("setUserEnergyLevel",userEnergyLevel,TAG);
        this.userEnergyLevelTemp =  userEnergyLevel;
        this.userEnergyLevel = userEnergyLevel;
    }

    public String getUserStreak() {
        Helper.nullChecker("getUserStreak",userStreak,TAG);
        return userStreak;
    }

    public void setUserStreak(String userStreak) {
        Helper.nullChecker("setUserStreak",userStreak,TAG);
        if(userStreak.equals(null) || userStreak.equals("null") ) {
            this.userStreakTemp = 0+"";
            this.userStreak = 0+"";
        }
    }

    public String getUserName() {
        Helper.nullChecker("getUserName",userName,TAG);
        return userName;
    }

    public void setUserName(String userName) {
        Helper.nullChecker("setUserName",userName,TAG);
        this.userName = userName;
    }

    public String getDeviceToken() {
        Helper.nullChecker("getDeviceToken",deviceToken,TAG);
        return deviceToken;
    }



    public void setDeviceToken(String deviceToken) {
        Helper.nullChecker("setDeviceToken",deviceToken,TAG);
        this.deviceToken = deviceToken;
    }


    public String getUserToken() {
        Helper.nullChecker("getUserToken",userToken,TAG);
        return userToken;
    }

    public void setUserToken(String userToken) {
        Helper.nullChecker("setUserToken",userToken,TAG);
        this.userToken = userToken;
    }

    public String getUserEmailId() {
        Helper.nullChecker("getUserEmailId",userEmailId,TAG);
        Log.d("user email in det",userEmailId);
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        Helper.nullChecker("setUserEmailId",userEmailId,TAG);
        this.userEmailId = userEmailId;
    }

    public String getInviteId() {
        Helper.nullChecker("getInviteId",inviteId,TAG);
        return inviteId;
    }

    public void setInviteId(String inviteId) {
        Helper.nullChecker("setInviteId",inviteId,TAG);
        this.inviteId = inviteId;
    }

    public String getFbId() {
        Helper.nullChecker("getFbId",fbId,TAG);
        return fbId;
    }

    public void setFbId(String fbId) {
        Helper.nullChecker("setFbId",fbId,TAG);
        this.fbId = fbId;
    }

    public String getDateOfBirth() {
        Helper.nullChecker("getDateOfBirth",dateOfBirth,TAG);

        return dateOfBirth;
    }

    public void setDateOfBirth(String age) {
        Helper.nullChecker("setDateOfBirth",dateOfBirth,TAG);
        this.dateOfBirth = age;
    }

    public String getUserLocation() {
        Helper.nullChecker("getUserLocation",userLocation,TAG);
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        Helper.nullChecker("setUserLocation",userLocation,TAG);
        this.userLocation = userLocation;
    }

    public String getGender() {
        Helper.nullChecker("getGender",gender,TAG);
        return gender;
    }

    public void setGender(String gender) {
        Helper.nullChecker("setGender",gender,TAG);
        this.gender = gender;
    }

    public static void setMyUserDetails(Context context,UserDetails myUserDetails){

        final SharedPreferences pref = context.getSharedPreferences(Config.USER_SESSION, 0);
        SharedPreferences.Editor editor = pref.edit();
        String myRidesDetailsJsonString = new GsonBuilder().create().toJson(myUserDetails);
        editor.putString(my_user_details, myRidesDetailsJsonString);
//        Log.d("myRide Json ", myRidesDetailsJsonString);
        editor.commit();

    }
    public static void clearSession(Context context){
        final SharedPreferences pref = context.getSharedPreferences(Config.USER_SESSION, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public static UserDetails getMyUserDetails(Context context){

        final SharedPreferences pref = context.getSharedPreferences(Config.USER_SESSION, 0);
        String tempDetails = pref.getString(my_user_details, null);

        Gson gson = new Gson();
        if(tempDetails==null || tempDetails.equals("")){
            return null;
        }else {
            UserDetails myRidesDetailsJsonObj = gson.fromJson(tempDetails, UserDetails.class);
            //Log.d("temp", tempDetails);
            return myRidesDetailsJsonObj;
        }
    }
}
