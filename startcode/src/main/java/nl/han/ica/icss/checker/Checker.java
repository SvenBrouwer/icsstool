package nl.han.ica.icss.checker;

import nl.han.ica.datastructures.IHANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.types.ExpressionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Checker {

    private IHANLinkedList<HashMap<String, ExpressionType>> variableTypes;

    List<String> list;
    public void check(AST ast) {
        // variableTypes = new HANLinkedList<>();

        list = getList(ast.root);
        System.out.println(list);
        checkStylesheet(ast.root);

    }

    private void checkStylesheet(Stylesheet sheet) {
        for (ASTNode child : sheet.getChildren()) {
            if (child instanceof Stylerule) {
                checkStylerule(child);
            }
            if (child instanceof VariableAssignment) {
                checkVariableAssignment((VariableAssignment) child);
            }
        }
    }



    private void checkStylerule(ASTNode node) {
        for (ASTNode child : node.getChildren()) {
            if (child instanceof Declaration) {
                checkDeclaration((Declaration) child);
            }
        }
    }
    private void checkVariableAssignment(VariableAssignment node) {
        for (ASTNode child : node.getChildren()) {

        }
    }
    private void checkDeclaration(Declaration node) {
        for (ASTNode child : node.getChildren()) {
            if (child instanceof VariableReference) {
                checkVariableReference(child, ((VariableReference) child).name);
            }
        }
    }

    private void checkVariableReference(ASTNode variableReference, String value) {
        if (!list.contains(value)) {
            variableReference.setError(variableReference.toString() + " niet gedeclareerd");
        }
    }


    private List<String> getList(Stylesheet sheet){
        List<String> list = new ArrayList<>();
        for (ASTNode child : sheet.getChildren()) {
            if (child instanceof VariableAssignment) {
                for (ASTNode child2 : child.getChildren()) {
                    if (child2 instanceof VariableReference) {
                        list.add(((VariableReference) child2).name);
                    }
                }
            }
        }
        return list;
    }

    private void checkIfClause() {

    }

    private void checkElseClause() {

    }


}
