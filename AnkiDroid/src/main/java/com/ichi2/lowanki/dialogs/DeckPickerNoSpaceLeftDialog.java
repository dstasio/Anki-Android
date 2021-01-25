package com.ichi2.lowanki.dialogs;

import android.content.res.Resources;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ichi2.lowanki.DeckPicker;
import com.ichi2.lowanki.R;
import com.ichi2.lowanki.analytics.AnalyticsDialogFragment;

import androidx.annotation.NonNull;

public class DeckPickerNoSpaceLeftDialog extends AnalyticsDialogFragment {
    public static DeckPickerNoSpaceLeftDialog newInstance() {
        return new DeckPickerNoSpaceLeftDialog();
    }
    
    @NonNull
    @Override
    public MaterialDialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        return new MaterialDialog.Builder(getActivity())
                .title(res.getString(R.string.sd_card_full_title))
                .content(res.getString(R.string.backup_deck_no_space_left))
                .cancelable(true)
                .positiveText(R.string.dialog_ok)
                .onPositive((dialog, which) -> ((DeckPicker) getActivity()).startLoadingCollection())
                .cancelListener(dialog -> ((DeckPicker) getActivity()).startLoadingCollection())
                .show();
    }
}