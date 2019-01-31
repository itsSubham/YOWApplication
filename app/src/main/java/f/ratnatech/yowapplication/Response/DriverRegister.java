package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 1/8/2019.
 */

public class DriverRegister {
    public int getSuccess() {
        return Success;
    }

    public void setSuccess(int success) {
        Success = success;
    }

    public data getData() {
        return Data;
    }

    public void setData(data data) {
        Data = data;
    }

    @SerializedName("Success")
    private int Success;
    @SerializedName("Data")
    private data Data;

    public class data{
        @SerializedName("Id")
        private int Id;
        @SerializedName("First_Name")
        private String First_Name;
        @SerializedName("Last_Name")
        private String Last_Name;
        @SerializedName("Mobile_No")
        private String Mobile_No;
        @SerializedName("Mobile_Verified")
        private int Mobile_Verified;
        @SerializedName("Email_Id")
        private String Email_Id;
        @SerializedName("Email_Verified")
        private int Email_Verified;
        @SerializedName("Vehicle_Type")
        private String Vehicle_Type;
        @SerializedName("Vehicle_Brand")
        private String Vehicle_Brand;
        @SerializedName("Regd_No")
        private String Regd_No;
        @SerializedName("Doc_Verified")
        private int Doc_Verified;
        @SerializedName("Approved")
        private int Approved;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public String getFirst_Name() {
            return First_Name;
        }

        public void setFirst_Name(String first_Name) {
            First_Name = first_Name;
        }

        public String getLast_Name() {
            return Last_Name;
        }

        public void setLast_Name(String last_Name) {
            Last_Name = last_Name;
        }

        public String getMobile_No() {
            return Mobile_No;
        }

        public void setMobile_No(String mobile_No) {
            Mobile_No = mobile_No;
        }

        public int getMobile_Verified() {
            return Mobile_Verified;
        }

        public void setMobile_Verified(int mobile_Verified) {
            Mobile_Verified = mobile_Verified;
        }

        public String getEmail_Id() {
            return Email_Id;
        }

        public void setEmail_Id(String email_Id) {
            Email_Id = email_Id;
        }

        public int getEmail_Verified() {
            return Email_Verified;
        }

        public void setEmail_Verified(int email_Verified) {
            Email_Verified = email_Verified;
        }

        public String getVehicle_Type() {
            return Vehicle_Type;
        }

        public void setVehicle_Type(String vehicle_Type) {
            Vehicle_Type = vehicle_Type;
        }

        public String getVehicle_Brand() {
            return Vehicle_Brand;
        }

        public void setVehicle_Brand(String vehicle_Brand) {
            Vehicle_Brand = vehicle_Brand;
        }

        public String getRegd_No() {
            return Regd_No;
        }

        public void setRegd_No(String regd_No) {
            Regd_No = regd_No;
        }

        public int getDoc_Verified() {
            return Doc_Verified;
        }

        public void setDoc_Verified(int doc_Verified) {
            Doc_Verified = doc_Verified;
        }

        public int getApproved() {
            return Approved;
        }

        public void setApproved(int approved) {
            Approved = approved;
        }

        public int getBlocked() {
            return Blocked;
        }

        public void setBlocked(int blocked) {
            Blocked = blocked;
        }

        public String getAdded_On() {
            return Added_On;
        }

        public void setAdded_On(String added_On) {
            Added_On = added_On;
        }

        public String getUpdated_On() {
            return Updated_On;
        }

        public void setUpdated_On(String updated_On) {
            Updated_On = updated_On;
        }

        @SerializedName("Blocked")
        private int Blocked;
        @SerializedName("Added_On")
        private String Added_On;
        @SerializedName("Updated_On")
        private String Updated_On;

    }
}
