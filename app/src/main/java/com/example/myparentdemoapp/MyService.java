package com.example.myparentdemoapp;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.Nullable;

public class MyService extends AccessibilityService {

    private WindowManager windowManager;
    private WindowManager.LayoutParams params;

    @Override
    public void onCreate() {
        super.onCreate();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Check if the event is related to other app activities
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOWS_CHANGED ||
                event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED ||
                event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            Log.d("Check here -------> ", event.getPackageName().toString());
            List<ApplicationInfo> packages;
            String packageName = event.getPackageName().toString();
            //get a list of installed apps.
            createOverlayView(packageName);
        }
    }

    @Override
    public void onInterrupt() {

    }

    private void createOverlayView(String packageName) {
        Intent intent = new Intent(MyService.this, BlockViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("BLOCKED_APP_NAME_EXTRA", packageName);
        startActivity(intent);
    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {

        int action = event.getAction();
        int keyCode = event.getKeyCode();
        if (action == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
                Log.d("Check", "KeyUp");
                Toast.makeText(getBaseContext(), "KeyUp", Toast.LENGTH_SHORT).show();
            } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
                Log.d("Check", "KeyDown");
                Toast.makeText(this, "KeyDown", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onKeyEvent(event);
    }
}
