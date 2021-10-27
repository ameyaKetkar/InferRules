package com.inferrules.core;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.inferrules.core.languageAdapters.ILanguageAdapter;
import com.inferrules.core.languageAdapters.LanguageSpecificInfo;

import static java.util.stream.Collectors.toMap;

public class RewriteRule {

    private final Template Before;
    private final Template After;
    private String beforeSnippet;
    private String afterSnippet;

    public RewriteRule(String beforeSnippet, String afterSnippet, LanguageSpecificInfo.Language language) {
        this.beforeSnippet = beforeSnippet;
        this.afterSnippet = afterSnippet;
        ILanguageAdapter adapter = LanguageSpecificInfo.getAdapter(language);
        VariableNameGenerator l = new VariableNameGenerator('l');
        this.Before = new Template(beforeSnippet, adapter, l);
        l.resetButKeepCache('r');
        this.After = new Template(afterSnippet, adapter, l);
    }
}
