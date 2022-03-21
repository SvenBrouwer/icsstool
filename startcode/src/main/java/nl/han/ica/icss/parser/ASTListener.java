package nl.han.ica.icss.parser;

import java.util.Stack;


import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	
	//Accumulator attributes:
	private AST ast;

	//Use this to keep track of the parent nodes when recursively traversing the ast
	private IHANStack<ASTNode> stack;

	public ASTListener() {
		ast = new AST();
		stack = new HANStack<>();
	}
    public AST getAST() {
        return ast;
    }


    @Override public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		Stylesheet stylesheet = new Stylesheet();
		stack.push(stylesheet);

	}

	@Override public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		ast.root = (Stylesheet) stack.pop();
	}

	@Override public void enterMeth(ICSSParser.MethContext ctx) {
		Stylerule stylerule = new Stylerule();
		stack.push(stylerule);

	}

	@Override public void exitMeth(ICSSParser.MethContext ctx) {
		Stylerule stylerule = (Stylerule) stack.pop();
		stack.peek().addChild(stylerule);
	}

	@Override public void enterVar(ICSSParser.VarContext ctx) {
		Declaration declaration = new Declaration();
		stack.push(declaration);
	}

	@Override public void exitVar(ICSSParser.VarContext ctx) {
		Declaration declaration = (Declaration) stack.pop();
		stack.peek().addChild(declaration);
	}

	@Override public void enterDecl(ICSSParser.DeclContext ctx) {
		TagSelector tagSelector = new TagSelector();
		stack.push(tagSelector);

	}

	@Override public void exitDecl(ICSSParser.DeclContext ctx) { }

	@Override public void enterIfstmt(ICSSParser.IfstmtContext ctx) { }

	@Override public void exitIfstmt(ICSSParser.IfstmtContext ctx) { }

	@Override public void enterElsestmt(ICSSParser.ElsestmtContext ctx) { }

	@Override public void enterUpper(ICSSParser.UpperContext ctx) { }

	@Override public void exitUpper(ICSSParser.UpperContext ctx) { }

	@Override public void enterMul(ICSSParser.MulContext ctx) { }

	@Override public void exitMul(ICSSParser.MulContext ctx) { }

	@Override public void enterPlus(ICSSParser.PlusContext ctx) { }

	@Override public void exitPlus(ICSSParser.PlusContext ctx) { }

	@Override public void enterMinus(ICSSParser.MinusContext ctx) { }

	@Override public void exitMinus(ICSSParser.MinusContext ctx) { }

	@Override public void enterIdtag(ICSSParser.IdtagContext ctx) { }

	@Override public void exitIdtag(ICSSParser.IdtagContext ctx) { }

	@Override public void enterClasstag(ICSSParser.ClasstagContext ctx) { }

	@Override public void exitClasstag(ICSSParser.ClasstagContext ctx) { }

	@Override public void enterScalar(ICSSParser.ScalarContext ctx) { }

	@Override public void exitScalar(ICSSParser.ScalarContext ctx) { }

	@Override public void enterPixel(ICSSParser.PixelContext ctx) { }

	@Override public void exitPixel(ICSSParser.PixelContext ctx) { }

	@Override public void enterColor(ICSSParser.ColorContext ctx) { }

	@Override public void exitColor(ICSSParser.ColorContext ctx) { }

	@Override public void enterT(ICSSParser.TContext ctx) { }

	@Override public void exitT(ICSSParser.TContext ctx) { }

	@Override public void enterF(ICSSParser.FContext ctx) {

	}

	@Override public void exitF(ICSSParser.FContext ctx) {

	}




}