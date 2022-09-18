package com.unittest.retrofit2java;

import android.app.Application;
import android.os.Bundle;

import org.conscrypt.Conscrypt;

import java.security.Security;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Security.insertProviderAt(Conscrypt.newProvider(), 1);
    }
}
