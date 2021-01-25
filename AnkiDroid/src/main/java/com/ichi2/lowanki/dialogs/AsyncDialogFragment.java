package com.ichi2.lowanki.dialogs;
import android.content.res.Resources;
import android.os.Message;

import com.ichi2.lowanki.LowkeyAnkiDroidApp;
import com.ichi2.lowanki.analytics.AnalyticsDialogFragment;

import timber.log.Timber;

public abstract class AsyncDialogFragment extends AnalyticsDialogFragment {
    /* provide methods for text to show in notification bar when the DialogFragment
       can't be shown due to the host activity being in stopped state.
       This can happen when the DialogFragment is shown from 
       the onPostExecute() method of an AsyncTask */
    
    public abstract String getNotificationMessage();
    public abstract String getNotificationTitle();

    public Message getDialogHandlerMessage() {
        return null;
    }

    protected Resources res() {
        try {
            return LowkeyAnkiDroidApp.getAppResources();
        } catch (Exception e) {
            Timber.w(e, "AnkiDroidApp.getAppResources failure. Returning Fragment resources as fallback.");
            return getResources();
        }
    }
} 