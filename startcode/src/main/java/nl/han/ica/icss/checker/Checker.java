package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.HashMap;


public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    public void check(AST ast) {
        // variableTypes = new HANLinkedList<>();
        checkStylesheet(ast.root);

    }

    private void checkStylesheet(Stylesheet sheet) {
        checkStylerule(sheet.getChildren().get(0));
    }

    private void checkStylerule(ASTNode node) {
        for(ASTNode child: node.getChildren()) {
            if(child instanceof Declaration){
                checkDeclaration((Declaration) child);
            }
        }
    }

    private void checkDeclaration(Declaration node) {
        if(node.property.name.equals("width")) {
            System.out.println(node.expression);
            if(!(node.expression instanceof PixelLiteral)){
                node.setError("verkeerde type .................");
            }

        }
    }


}
