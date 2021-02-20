/*
 *  Copyright (c) 2020 David Allison <davidallisongithub@gmail.com>
 *
 *  This program is free software; you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 3 of the License, or (at your option) any later
 *  version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with
 *  this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.ichi2.testutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ichi2.lowanki.CardBrowser;
import com.ichi2.lowanki.CardInfo;
import com.ichi2.lowanki.CardTemplateBrowserAppearanceEditor;
import com.ichi2.lowanki.CardTemplateEditor;
import com.ichi2.lowanki.CardTemplatePreviewer;
import com.ichi2.lowanki.DeckOptions;
import com.ichi2.lowanki.DeckPicker;
import com.ichi2.lowanki.FilteredDeckOptions;
import com.ichi2.lowanki.Info;
import com.ichi2.lowanki.IntentHandler;
import com.ichi2.lowanki.ModelBrowser;
import com.ichi2.lowanki.ModelFieldEditor;
import com.ichi2.lowanki.MyAccount;
import com.ichi2.lowanki.NoteEditor;
import com.ichi2.lowanki.Preferences;
import com.ichi2.lowanki.Previewer;
import com.ichi2.lowanki.Reviewer;
import com.ichi2.lowanki.Statistics;
import com.ichi2.lowanki.StudyOptionsActivity;
import com.ichi2.lowanki.VideoPlayer;
import com.ichi2.lowanki.multimediacard.activity.LoadPronounciationActivity;
import com.ichi2.lowanki.multimediacard.activity.MultimediaEditFieldActivity;
import com.ichi2.lowanki.multimediacard.activity.TranslationActivity;
import com.ichi2.lowanki.services.ReminderService;
import com.ichi2.utils.FunctionalInterfaces.Function;

import org.robolectric.Robolectric;
import org.robolectric.android.controller.ActivityController;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.CheckResult;

import static com.ichi2.lowanki.CardTemplateBrowserAppearanceEditor.INTENT_ANSWER_FORMAT;
import static com.ichi2.lowanki.CardTemplateBrowserAppearanceEditor.INTENT_QUESTION_FORMAT;
import static com.ichi2.testutils.ActivityList.ActivityLaunchParam.get;

public class ActivityList {
    // TODO: This needs a test to ensure that all activities are valid with the given intents
    // Otherwise, ActivityStartupUnderBackup and other classes could be flaky
    @CheckResult
    public static List<ActivityLaunchParam> allActivitiesAndIntents() {
        return Arrays.asList(
                get(DeckPicker.class),
                // IntentHandler has unhandled intents
                get(IntentHandler.class, ctx -> ReminderService.getReviewDeckIntent(ctx, 1L)),
                get(StudyOptionsActivity.class),
                get(CardBrowser.class),
                get(ModelBrowser.class),
                get(ModelFieldEditor.class),
                // Likely has unhandled intents
                get(Reviewer.class),
                get(VideoPlayer.class),
                get(MyAccount.class),
                get(Preferences.class),
                get(DeckOptions.class),
                get(FilteredDeckOptions.class),
                // Info has unhandled intents
                get(Info.class),
                // NoteEditor has unhandled intents
                get(NoteEditor.class),
                get(Statistics.class),
                get(Previewer.class),
                get(CardTemplatePreviewer.class),
                get(MultimediaEditFieldActivity.class),
                get(TranslationActivity.class),
                get(LoadPronounciationActivity.class),
                get(CardInfo.class),
                get(CardTemplateEditor.class, ActivityList::intentForCardTemplateEditor),
                get(CardTemplateBrowserAppearanceEditor.class, ActivityList::intentForCardTemplateBrowserAppearanceEditor)
        );
    }


    private static Intent intentForCardTemplateBrowserAppearanceEditor(Context context) {
        // bundle != null
        Intent intent = new Intent();
        intent.putExtra(INTENT_QUESTION_FORMAT, "{{Front}}");
        intent.putExtra(INTENT_ANSWER_FORMAT, "{{FrontSide}}\n{{Back}}");
        return intent;
    }


    private static Intent intentForCardTemplateEditor(Context ctx) {
        Intent intent = new Intent();
        intent.putExtra("modelId", 1L);
        return intent;
    }


    public static class ActivityLaunchParam {
        public Class<? extends Activity> mActivity;
        public Function<Context, Intent> mIntentBuilder;


        public ActivityLaunchParam(Class<? extends Activity> clazz, Function<Context, Intent> intent) {
            mActivity = clazz;
            mIntentBuilder = intent;
        }


        public static ActivityLaunchParam get(Class<? extends Activity> clazz) {
            return get(clazz, c -> new Intent());
        }

        public static ActivityLaunchParam get(Class<? extends Activity> clazz, Function<Context, Intent> i) {
            return new ActivityLaunchParam(clazz, i);
        }

        public String getSimpleName() {
            return mActivity.getSimpleName();
        }


        public ActivityController<? extends Activity> build(Context context) {
            return Robolectric.buildActivity(mActivity, mIntentBuilder.apply(context));
        }


        public String getClassName() {
            return this.mActivity.getName();
        }
    }
}