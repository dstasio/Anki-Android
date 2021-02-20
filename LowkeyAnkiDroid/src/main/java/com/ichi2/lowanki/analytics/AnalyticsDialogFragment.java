package com.ichi2.lowanki.analytics;

import androidx.fragment.app.DialogFragment;

public abstract class AnalyticsDialogFragment extends DialogFragment {
    @Override
    public void onResume() {
        super.onResume();
        UsageAnalytics.sendAnalyticsScreenView(this);
    }
}
