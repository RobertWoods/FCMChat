package edu.temple.fcmchat;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFireBaseIdService extends FirebaseInstanceIdService {
    public MyFireBaseIdService() {

    }

    @Override
    public void onTokenRefresh(){
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Firebase", "Token: " + token);

    }

}
