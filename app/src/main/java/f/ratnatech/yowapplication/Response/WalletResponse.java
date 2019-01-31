package f.ratnatech.yowapplication.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RatnaDev008 on 1/3/2019.
 */

public class WalletResponse {
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
        @SerializedName("Customer_Id")
        private int Customer_Id;

        public int getCustomer_Id() {
            return Customer_Id;
        }

        public void setCustomer_Id(int customer_Id) {
            Customer_Id = customer_Id;
        }

        public String getMy_Cash() {
            return My_Cash;
        }

        public void setMy_Cash(String my_Cash) {
            My_Cash = my_Cash;
        }

        public String getBonus_Cash() {
            return Bonus_Cash;
        }

        public void setBonus_Cash(String bonus_Cash) {
            Bonus_Cash = bonus_Cash;
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

        @SerializedName("My_Cash")
        private String My_Cash;
        @SerializedName("Bonus_Cash")
        private String Bonus_Cash;
        @SerializedName("Added_On")
        private String Added_On;
        @SerializedName("Updated_On")
        private String Updated_On;
    }
}
