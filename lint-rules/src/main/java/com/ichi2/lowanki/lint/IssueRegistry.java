package com.ichi2.lowanki.lint;

import com.android.tools.lint.detector.api.ApiKt;
import com.android.tools.lint.detector.api.Issue;
import com.ichi2.lowanki.lint.rules.DirectCalendarInstanceUsage;
import com.ichi2.lowanki.lint.rules.DirectSystemTimeInstantiation;
import com.ichi2.lowanki.lint.rules.DirectSystemCurrentTimeMillisUsage;
import com.ichi2.lowanki.lint.rules.DirectDateInstantiation;
import com.ichi2.lowanki.lint.rules.DirectGregorianInstantiation;
import com.ichi2.lowanki.lint.rules.DuplicateTextInPreferencesXml;
import com.ichi2.lowanki.lint.rules.InconsistentAnnotationUsage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IssueRegistry extends com.android.tools.lint.client.api.IssueRegistry {
    @NotNull
    @Override
    public List<Issue> getIssues() {
        List<Issue> issues = new ArrayList<>();
        issues.add(DirectCalendarInstanceUsage.ISSUE);
        issues.add(DirectSystemCurrentTimeMillisUsage.ISSUE);
        issues.add(DirectDateInstantiation.ISSUE);
        issues.add(DirectGregorianInstantiation.ISSUE);
        issues.add(DirectSystemTimeInstantiation.ISSUE);
        issues.add(InconsistentAnnotationUsage.ISSUE);
        issues.add(DuplicateTextInPreferencesXml.ISSUE);
        return issues;
    }

    @Override
    public int getApi() {
        return ApiKt.CURRENT_API;
    }
}