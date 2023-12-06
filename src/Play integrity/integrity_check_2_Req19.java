package com.example.checkintegrity;

import java.io.File;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class integrity_check_2_Req19 extends AppCompatActivity {
    private static final String TAG = "IntegrityVerification";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = getPackageManager();
        
        try {
            // Insert your package name
            String packageName = "com.example.package.name";
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Signature[] sigs = packageInfo.signatures;
            for (Signature sig : sigs) {
                Log.i(TAG, "Signature : " + sig.hashCode());
            }
        } 
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}