package com.abhishek.admin.safepilepsy;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

import static android.widget.Toast.LENGTH_SHORT;

public class MyLocationService extends BroadcastReceiver {

    public static final String ACTION_PROCESS_UPDATE="edmt.dev.googlelocationbackground.UPDATE_LOCATION";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null)
        {
            final String action = intent.getAction();
            if (ACTION_PROCESS_UPDATE.equals(action))
            {
                LocationResult result = LocationResult.extractResult(intent);

                if(result != null)
                {
                    Location location = result.getLastLocation();
                    String location_string = new StringBuilder(""+location.getLatitude()).append(", ").append(location.getLongitude()).toString();

                    try
                    {
                        AccountActivity.getInstance().updateTextView(location_string);
                        AccountActivity.getInstance().storeToFireBase(location.getLatitude(),location.getLongitude());

                    }

                    catch (Exception e)
                    {
                        Toast.makeText(context, location_string, LENGTH_SHORT).show();
                    }

                }
            }
        }

    }
}
