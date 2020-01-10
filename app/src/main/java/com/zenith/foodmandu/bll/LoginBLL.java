package com.zenith.foodmandu.bll;

import com.zenith.foodmandu.api.UsersAPI;
import com.zenith.foodmandu.serverresponse.SignUpResponse;
import com.zenith.foodmandu.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.checkUser(username, password);

        try {
            Response<SignUpResponse> loginresponse = usersCall.execute();
            if (loginresponse.isSuccessful() &&
                loginresponse.body().getStatus().equals("Login success!")) {

                Url.token += loginresponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
