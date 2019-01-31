package f.ratnatech.yowapplication.client;


import f.ratnatech.yowapplication.Response.DriverRegister;
import f.ratnatech.yowapplication.Response.GetLoginOTP;
import f.ratnatech.yowapplication.Response.GoogleDistanceResponse;
import f.ratnatech.yowapplication.Response.MobileUpdateResponse;
import f.ratnatech.yowapplication.Response.NewLoginSuccessData;
import f.ratnatech.yowapplication.Response.OtpResponse;
import f.ratnatech.yowapplication.Response.RateDataResponse;
import f.ratnatech.yowapplication.Response.RateResponse;
import f.ratnatech.yowapplication.Response.SaveUserResponse;
import f.ratnatech.yowapplication.Response.UpdateProfileRespionse;
import f.ratnatech.yowapplication.Response.VehicalResponse;
import f.ratnatech.yowapplication.Response.WalletResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by RatnaDev008 on 11/5/2018.
 */

public interface ApiInterface {
    @POST("Services/User/get_otp")
    @FormUrlEncoded
    Call<GetLoginOTP> getLoginResponse(@Field("Mobile_No") String Mobile_No);

    @POST("Services/User/validate_otp")
    @FormUrlEncoded
    Call<OtpResponse> getOtpResponse(@Field("Mobile_No") String Mobile_No,
                                     @Field("OTP") String OTP);

    @POST("Services/User/validate_user")
    @FormUrlEncoded
    Call<NewLoginSuccessData> getNewLoginResponse(@Field("Mobile_No") String Mobile_No);

    @POST("Services/User/save_user.php")
    @FormUrlEncoded
    Call<SaveUserResponse> getSaveUserResponse(@Field("Mobile_No") String Mobile_No,
                                               @Field("OTP") String OTP);

    @POST("Services/User/get_rate.php")
    @FormUrlEncoded
    Call<RateResponse> getRateResponse(@Field("Pincode") String Pincode,
                                       @Field("KM") String KM);

    @POST("Services/User/update_mobile.php")
    @FormUrlEncoded
    Call<MobileUpdateResponse> getMobileUpdateResponse(@Field("Id") String Id,
                                                       @Field("Mobile_No") String Mobile_No,
                                                       @Field("OTP") String OTP);

    @POST("Services/User/get_vehicle_type.php")
    @FormUrlEncoded
    Call<VehicalResponse> getVehicalTypeResponse(@Field("Pincode") String Pincode);

    @POST("Services/User/update_profile.php")
    @FormUrlEncoded
    Call<UpdateProfileRespionse> getProfileResponse(@Field("Id") String Id,
                                                    @Field("First_Name") String First_Name,
                                                    @Field("Last_Name") String Last_Name,
                                                    @Field("Email_Id") String Email_Id);

    @POST("Services/User/get_wallet.php")
    @FormUrlEncoded
    Call<WalletResponse> getWalletResponse(@Field("Id") String Id);


    @POST("Services/Driver/validate_driver.php")
    @FormUrlEncoded
    Call<DriverRegister> getDriverRegisterResponse(@Field("Regd_No") String Regd_No);

    @GET("api/directions/json")
    Call<GoogleDistanceResponse> getDistanceDuration(@Query("origin") String origin1, @Query("destination") String destination1, @Query("key") String key);


}
