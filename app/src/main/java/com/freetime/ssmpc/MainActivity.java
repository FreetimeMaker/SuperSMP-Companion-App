package com.freetime.ssmpc;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.freetime.ssmpc.databinding.ActivityMainBinding;
import com.sanojpunchihewa.updatemanager.BuildConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Immersive fullscreen
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        // Navigation setup
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_servercmd, R.id.navigation_links, R.id.navigation_map, R.id.navigation_shop)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // 🔄 GitHub-based update check
        checkForUpdate();
    }

    private void checkForUpdate() {
        new Thread(() -> {
            try {
                URL url = new URL("https://api.github.com/repos/FreetimeMaker/SuperSMP-Companion-App/releases/latest");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                JSONObject json = new JSONObject(result.toString());
                String tag = json.getString("tag_name").replace("v", "");
                JSONArray assets = json.getJSONArray("assets");
                String apkUrl = assets.getJSONObject(0).getString("browser_download_url");

                String currentVersion = BuildConfig.VERSION_NAME;
                if (!tag.equals(currentVersion)) {
                    runOnUiThread(() -> promptUpdate(apkUrl));
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(this, "Update check failed", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void promptUpdate(String apkUrl) {
        new AlertDialog.Builder(this)
                .setTitle("Update Available")
                .setMessage("A new version is available. Would you like to update?")
                .setPositiveButton("Update", (dialog, which) -> downloadAndInstall(apkUrl))
                .setNegativeButton("Later", null)
                .show();
    }

    private void downloadAndInstall(String apkUrl) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(apkUrl));
        request.setTitle("Downloading update");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "update.apk");

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = manager.enqueue(request);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "update.apk");
                Uri uri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                installIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(installIntent);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                    receiver,
                    new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
                    Context.RECEIVER_NOT_EXPORTED
            );
        }
    }
}