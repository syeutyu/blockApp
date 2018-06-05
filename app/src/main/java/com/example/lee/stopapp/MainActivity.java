package com.example.lee.stopapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Item> list;
    private Adapter adapter;
    private PackageManager manager;

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        list = new ArrayList<>();
        manager = getPackageManager();
        adapter = new Adapter();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> AppInfos = manager.queryIntentActivities(intent, 0);
        for (ResolveInfo info : AppInfos) {
            ActivityInfo activityInfo = info.activityInfo;
            list.add(new Item(activityInfo.loadIcon(manager), activityInfo.loadLabel(manager).toString(), activityInfo.packageName));
        }
        adapter.setList(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }
}

/*
*         boolean granted = false;
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), getPackageName());

        if (mode == AppOpsManager.MODE_DEFAULT) {
            granted = (checkCallingOrSelfPermission(android.Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
        } else {
            granted = (mode == AppOpsManager.MODE_ALLOWED);
        }

        Log.e("tag", "===== CheckPhoneState isRooting granted = " + granted);

        if (granted == false) {
            Intent intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        UsageStatsManager usage = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
        assert usage != null;
        List<UsageStats> stats = usage.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - SystemClock.uptimeMillis(), time);
        if (stats != null) {
            //      SortedMap<Long, UsageStats> runningTask = new TreeMap<>();
            for (UsageStats usageStats : stats) {
                //        runningTask.put(usageStats.getLastTimeUsed(), usageStats);
                Item item = new Item();
                try {
                    item.setImage(getPackageManager().getApplicationIcon(usageStats.getPackageName()));
                    item.setRoot(usageStats.getPackageName());
                    item.setName(String.valueOf(usageStats.getTotalTimeInForeground()));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                list.add(item);
            }
        }
        adapter = new Adapter(list);
* */