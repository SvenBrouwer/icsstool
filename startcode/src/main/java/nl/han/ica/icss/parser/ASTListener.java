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

	@Override public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		Declaration declaration = new Declaration();
		stack.push(declaration);
	}

	@Override public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		Declaration declaration = (Declaration) stack.pop();
		stack.peek().addChild(declaration);
	}

	@Override public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule stylerule = new Stylerule();
		stack.push(stylerule);
	}

	@Override public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule stylerule = (Stylerule) stack.pop();
		stack.peek().addChild(stylerule);
	}

	@Override public void enterIfclause(ICSSParser.IfclauseContext ctx) {
		IfClause ifClause = new IfClause();
		stack.push(ifClause);
	}

	@Override public void exitIfclause(ICSSParser.IfclauseContext ctx) {
		IfClause ifClause = (IfClause) stack.pop();
		stack.peek().addChild(ifClause);
	}

	@Override public void enterElseclause(ICSSParser.ElseclauseContext ctx) {
		ElseClause elseClause = new ElseClause();
		stack.push(elseClause);
	}

	@Override public void exitElseclause(ICSSParser.ElseclauseContext ctx) {
		ElseClause elseClause = (ElseClause) stack.pop();
		stack.peek().addChild(elseClause);
	}

	@Override public void enterVariablereference(ICSSParser.VariablereferenceContext ctx) {
		VariableReference variableReference = new VariableReference(ctx.getText());
		stack.push(variableReference);
	}

	@Override public void exitVariablereference(ICSSParser.VariablereferenceContext ctx) {
		VariableReference variableReference = (VariableReference) stack.pop();
		stack.peek().addChild(variableReference);
	}

	@Override public void enterTagselector(ICSSParser.TagselectorContext ctx) {
		TagSelector tagSelector = new TagSelector(ctx.getText());
		stack.push(tagSelector);
	}

	@Override public void exitTagselector(ICSSParser.TagselectorContext ctx) {
		TagSelector tagSelector = (TagSelector) stack.pop();
		stack.peek().addChild(tagSelector);
	}

	@Override public void enterMultiplyoperation(ICSSParser.MultiplyoperationContext ctx) {
		MultiplyOperation multiplyOperation = new MultiplyOperation();
		stack.push(multiplyOperation);
	}

	@Override public void exitMultiplyoperation(ICSSParser.MultiplyoperationContext ctx) {
		MultiplyOperation multiplyOperation = (MultiplyOperation) stack.pop();
		stack.peek().addChild(multiplyOperation);
	}

	@Override public void enterAddoperation(ICSSParser.AddoperationContext ctx) {
		AddOperation addOperation = new AddOperation();
		stack.push(addOperation);
	}

	@Override public void exitAddoperation(ICSSParser.AddoperationContext ctx) {
		AddOperation addOperation = (AddOperation) stack.pop();
		stack.peek().addChild(addOperation);
	}

	@Override public void enterSubstractoperation(ICSSParser.SubstractoperationContext ctx) {
		SubtractOperation subtractOperation = new SubtractOperation();
		stack.push(subtractOperation);
	}

	@Override public void exitSubstractoperation(ICSSParser.SubstractoperationContext ctx) {
		SubtractOperation subtractOperation = (SubtractOperation) stack.pop();
		stack.peek().addChild(subtractOperation);
	}

	@Override public void enterIdselector(ICSSParser.IdselectorContext ctx) {
		IdSelector idSelector = new IdSelector(ctx.getText());
		stack.push(idSelector);
	}

	@Override public void exitIdselector(ICSSParser.IdselectorContext ctx) {
		IdSelector idSelector = (IdSelector) stack.pop();
		stack.peek().addChild(idSelector);
	}

	@Override public void enterClassselector(ICSSParser.ClassselectorContext ctx) {
		ClassSelector classSelector = new ClassSelector(ctx.getText());
		stack.push(classSelector);
	}

	@Override public void exitClassselector(ICSSParser.ClassselectorContext ctx) {
		ClassSelector classSelector = (ClassSelector) stack.pop();
		stack.peek().addChild(classSelector);
	}

	@Override public void enterScalarliteral(ICSSParser.ScalarliteralContext ctx) {
		ScalarLiteral scalarLiteral = new ScalarLiteral(ctx.getText());
		stack.push(scalarLiteral);
	}

	@Override public void exitScalarliteral(ICSSParser.ScalarliteralContext ctx) {
		ScalarLiteral scalarLiteral = (ScalarLiteral) stack.pop();
		stack.peek().addChild(scalarLiteral);
	}

	@Override public void enterPixelliteral(ICSSParser.PixelliteralContext ctx) {
		PixelLiteral pixelLiteral = new PixelLiteral(ctx.getText());
		stack.push(pixelLiteral);
	}

	@Override public void exitPixelliteral(ICSSParser.PixelliteralContext ctx) {
		PixelLiteral pixelLiteral = (PixelLiteral) stack.pop();
		stack.peek().addChild(pixelLiteral);
	}

	@Override public void enterColorliteral(ICSSParser.ColorliteralContext ctx) {
		ColorLiteral colorLiteral = new ColorLiteral(ctx.getText());
		stack.push(colorLiteral);
	}

	@Override public void exitColorliteral(ICSSParser.ColorliteralContext ctx) {
		ColorLiteral colorLiteral = (ColorLiteral) stack.pop();
		stack.peek().addChild(colorLiteral);
	}

	@Override public void enterT(ICSSParser.TContext ctx) {
		BoolLiteral boolLiteral = new BoolLiteral(true);
		stack.push(boolLiteral);
	}

	@Override public void exitT(ICSSParser.TContext ctx) {
		BoolLiteral boolLiteral = (BoolLiteral) stack.pop();
		stack.peek().addChild(boolLiteral);
	}

	@Override public void enterF(ICSSParser.FContext ctx) {
		BoolLiteral boolLiteral = new BoolLiteral(false);
		stack.push(boolLiteral);
	}

	@Override public void exitF(ICSSParser.FContext ctx) {
		BoolLiteral boolLiteral = (BoolLiteral) stack.pop();
		stack.peek().addChild(boolLiteral);
	}

	@Override public void enterVariableassignment(ICSSParser.VariableassignmentContext ctx) {
		VariableAssignment variableAssignment = new VariableAssignment();
		stack.push(variableAssignment);
	}

	@Override public void exitVariableassignment(ICSSParser.VariableassignmentContext ctx) {
		VariableAssignment variableAssignment = (VariableAssignment) stack.pop();
		stack.peek().addChild(variableAssignment);
	}




}