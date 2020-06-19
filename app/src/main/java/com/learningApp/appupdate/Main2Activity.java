package com.learningApp.appupdate;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    UpdateManager2 mUpdateManager;
    TextView txtCurrentVersion,txtAvailableVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

         txtCurrentVersion = findViewById(R.id.txt_current_version);
         txtAvailableVersion = findViewById(R.id.txt_available_version);

        txtCurrentVersion.setText(String.valueOf(BuildConfig.VERSION_CODE));

        // Initialize the Update Manager with the Activity and the Update Mode
        mUpdateManager = UpdateManager2.Builder(this);

        // Callback from Available version code
        mUpdateManager.getAvailableVersionCode(new UpdateManager2.onVersionCheckListener() {
            @Override
            public void onReceiveVersionCode(final int code) {
                txtAvailableVersion.setText(String.valueOf(code));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Continue updates when resumed
        mUpdateManager.continueUpdate();
    }

    public void callFlexibleUpdate(View view) {
        // Start a Flexible Update
        mUpdateManager.mode(UpdateManagerConstant.FLEXIBLE).start();
    }

    public void callImmediateUpdate(View view) {
        // Start a Immediate Update
        mUpdateManager.mode(UpdateManagerConstant.IMMEDIATE).start();
    }
}
