package nl.han.ica.icss.gui;

import nl.han.ica.icss.ast.Expression;
import nl.han.ica.icss.parser.ASTListener;
import nl.han.ica.icss.parser.ICSSLexer;
import nl.han.ica.icss.parser.ICSSParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        MainGui.launch(MainGui.class, args);

        String expression = "3 + 1 * 4 + 1 + 5 * 9 + 2 * 6 * 5 + 3 + 5";

        //Setup pipeline
        CharStream input = CharStreams.fromString(expression);
        ICSSLexer lexer = new ICSSLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ICSSParser parser = new ICSSParser(tokens);

        //Parse
        ParseTree tree = parser.stylesheet();

        // Walk using listener
        ParseTreeWalker walker = new ParseTreeWalker();
        ASTListener listener = new ASTListener();
        walker.walk(listener, tree);

        System.out.println(listener.getAST());
    }



}
