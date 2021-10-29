package com.inferrules.core.languageAdapters;

import com.inferrules.core.Node;
import com.inferrules.parsers.PythonLexer;
import com.inferrules.parsers.PythonParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.util.List;
import java.util.stream.Collectors;

import static com.inferrules.core.languageAdapters. Language.Python;

public class PythonAdapter implements ILanguageAdapter {
    @Override
    public Node parse(String codeSnippet) {
        List<String> tokenList = tokenize(codeSnippet);
        var tokens = new CommonTokenStream(new PythonLexer(CharStreams.fromString(codeSnippet)));
        return parseTree2Node(new PythonParser(tokens).file_input(), Python, tokenList);
    }

    @Override
    public List<String> tokenize(String codeSnippet) {
        CommonTokenStream x = new CommonTokenStream(new PythonLexer(CharStreams.fromString(codeSnippet)));
        x.getNumberOfOnChannelTokens();
        return x.getTokens().stream().map(Token::getText).collect(Collectors.toList());
    }
}
