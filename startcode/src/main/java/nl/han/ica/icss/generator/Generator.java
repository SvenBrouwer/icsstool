package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;

public class Generator {

	public String generate(AST ast) {
        return generateStylesheet(ast.root);


	}
	private String generateStylesheet(Stylesheet node) {
		return generateStylerule((Stylerule)node.getChildren().get(0));
	}

	private String generateStylerule(Stylerule node) {
		String s = node.selectors.get(0).toString() + " {\n";
		s += "\t " + generateDeclaration((Declaration)node.body.get(0));
		return s;
	}

	private String generateDeclaration(Declaration declaration){
		return declaration.property.name + " : " + declaration.expression.toString();
	}
}
