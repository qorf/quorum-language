package com.quorum.testapplication;

import android.app.Activity;
import android.os.Bundle;

import com.quorum.TestApplication.R;

import quorum.Libraries.Game.AndroidApplication;
import plugins.quorum.Libraries.Game.GameStateManager;
import quorum.Main;

public class MainActivity extends Activity {

    private static boolean initialized = false;
    private static Main main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plugins.quorum.Libraries.Game.AndroidApplication.SetActivity(this);

        if (savedInstanceState == null)
        {
            main = new Main();
            main.Main();
        }
        else
        {
            main.StartAndroidGame();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);

        if (initialized)
            ((AndroidApplication)GameStateManager.application).plugin_.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onPause()
    {
        // Call AndroidApplication
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        if (initialized)
            ((AndroidApplication)GameStateManager.application).plugin_.onResume();

        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        // Call AndroidApplication
        super.onDestroy();
    }
}
