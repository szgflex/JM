package com.jm.launcher;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupManager;
import android.content.Context;

public class LauncherBackupAgentHelper extends BackupAgentHelper {

    private static BackupManager sBackupManager;

    /**
     * Notify the backup manager that out database is dirty.
     *
     * <P>This does not force an immediate backup.
     *
     * @param context application context
     */
    public static void dataChanged(Context context) {
        if (sBackupManager == null) {
            sBackupManager = new BackupManager(context);
        }
        sBackupManager.dataChanged();
    }


    @Override
    public void onCreate() {
        addHelper(LauncherBackupHelper.LAUNCHER_PREFIX, new LauncherBackupHelper(this));
    }
}
