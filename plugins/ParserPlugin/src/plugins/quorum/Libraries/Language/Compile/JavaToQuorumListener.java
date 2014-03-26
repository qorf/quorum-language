/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plugins.quorum.Libraries.Language.Compile;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import quorum.Libraries.Language.Compile.QuorumSourceListener$Interface;

/**
 *
 * @author stefika
 */
public class JavaToQuorumListener implements QuorumListener{
    private QuorumSourceListener$Interface listener;
    
    @Override
    public void enterSelector(QuorumParser.SelectorContext ctx) {
        listener.EnterSelector();
    }

    @Override
    public void exitSelector(QuorumParser.SelectorContext ctx) {
        listener.ExitSelector();
    }

    @Override
    public void enterDecimal(QuorumParser.DecimalContext ctx) {
        listener.EnterDecimal();
    }

    @Override
    public void exitDecimal(QuorumParser.DecimalContext ctx) {
        listener.ExitDecimal();
    }

    @Override
    public void enterParenthesisExpression(QuorumParser.ParenthesisExpressionContext ctx) {
        listener.EnterParenthesisExpression();
    }

    @Override
    public void exitParenthesisExpression(QuorumParser.ParenthesisExpressionContext ctx) {
        listener.ExitParenthesisExpression();
    }

    @Override
    public void enterCast(QuorumParser.CastContext ctx) {
        listener.EnterCast();
    }

    @Override
    public void exitCast(QuorumParser.CastContext ctx) {
        listener.ExitCast();
    }

    @Override
    public void enterSpeak_statement(QuorumParser.Speak_statementContext ctx) {
        listener.EnterSpeakStatement();
    }

    @Override
    public void exitSpeak_statement(QuorumParser.Speak_statementContext ctx) {
        listener.ExitSpeakStatement();
    }

    @Override
    public void enterParentFunctionCall(QuorumParser.ParentFunctionCallContext ctx) {
        listener.EnterParentFunctionCall();
    }

    @Override
    public void exitParentFunctionCall(QuorumParser.ParentFunctionCallContext ctx) {
        listener.ExitParentFunctionCall();
    }

    @Override
    public void enterCheck_statement(QuorumParser.Check_statementContext ctx) {
        listener.EnterCheckStatement();
    }

    @Override
    public void exitCheck_statement(QuorumParser.Check_statementContext ctx) {
        listener.ExitCheckStatement();
    }

    @Override
    public void enterParentFieldAccess(QuorumParser.ParentFieldAccessContext ctx) {
        listener.EnterParentFieldAccess();
    }

    @Override
    public void exitParentFieldAccess(QuorumParser.ParentFieldAccessContext ctx) {
        listener.ExitParentFieldAccess();
    }

    @Override
    public void enterClass_declaration(QuorumParser.Class_declarationContext ctx) {
        listener.EnterClassDeclaration();
    }

    @Override
    public void exitClass_declaration(QuorumParser.Class_declarationContext ctx) {
        listener.ExitClassDeclaration();
    }

    @Override
    public void enterObjectFunctionCall(QuorumParser.ObjectFunctionCallContext ctx) {
        listener.EnterObjectFunctionCall();
    }

    @Override
    public void exitObjectFunctionCall(QuorumParser.ObjectFunctionCallContext ctx) {
        listener.ExitObjectFunctionCall();
    }

    @Override
    public void enterSolo_method_call(QuorumParser.Solo_method_callContext ctx) {
        listener.EnterSoloMethodCall();
    }

    @Override
    public void exitSolo_method_call(QuorumParser.Solo_method_callContext ctx) {
        listener.ExitSoloMethodCall();
    }

    @Override
    public void enterFormal_parameter(QuorumParser.Formal_parameterContext ctx) {
        listener.EnterFormalParameter();
    }

    @Override
    public void exitFormal_parameter(QuorumParser.Formal_parameterContext ctx) {
        listener.ExitFormalParameter();
    }

    @Override
    public void enterStatement(QuorumParser.StatementContext ctx) {
        listener.EnterStatement();
    }

    @Override
    public void exitStatement(QuorumParser.StatementContext ctx) {
        listener.ExitStatement();
    }

    @Override
    public void enterMe(QuorumParser.MeContext ctx) {
        listener.EnterMe();
    }

    @Override
    public void exitMe(QuorumParser.MeContext ctx) {
        listener.ExitMe();
    }

    @Override
    public void enterFunction_expression_list(QuorumParser.Function_expression_listContext ctx) {
        listener.EnterFunctionExpressionList();
    }

    @Override
    public void exitFunction_expression_list(QuorumParser.Function_expression_listContext ctx) {
        listener.ExitFunctionExpressionList();
    }

    @Override
    public void enterStart(QuorumParser.StartContext ctx) {
        listener.EnterStart();
    }

    @Override
    public void exitStart(QuorumParser.StartContext ctx) {
        listener.ExitStart();
    }

    @Override
    public void enterString(QuorumParser.StringContext ctx) {
        listener.EnterString();
    }

    @Override
    public void exitString(QuorumParser.StringContext ctx) {
        listener.ExitString();
    }

    @Override
    public void enterOr(QuorumParser.OrContext ctx) {
        listener.EnterOr();
    }

    @Override
    public void exitOr(QuorumParser.OrContext ctx) {
        listener.ExitOr();
    }

    @Override
    public void enterPrint_statement(QuorumParser.Print_statementContext ctx) {
        listener.EnterPrintStatement();
    }

    @Override
    public void exitPrint_statement(QuorumParser.Print_statementContext ctx) {
        listener.ExitPrintStatement();
    }

    @Override
    public void enterMethod_declaration(QuorumParser.Method_declarationContext ctx) {
        listener.EnterMethodDeclaration();
    }

    @Override
    public void exitMethod_declaration(QuorumParser.Method_declarationContext ctx) {
        listener.ExitMethodDeclaration();
    }

    @Override
    public void enterGeneric_statement(QuorumParser.Generic_statementContext ctx) {
        listener.EnterGenericStatement();
    }

    @Override
    public void exitGeneric_statement(QuorumParser.Generic_statementContext ctx) {
        listener.ExitGenericStatement();
    }

    @Override
    public void enterAnd(QuorumParser.AndContext ctx) {
        listener.EnterAnd();
    }

    @Override
    public void exitAnd(QuorumParser.AndContext ctx) {
        listener.ExitAnd();
    }

    @Override
    public void enterNull(QuorumParser.NullContext ctx) {
        listener.EnterNull();
    }

    @Override
    public void exitNull(QuorumParser.NullContext ctx) {
        listener.ExitNull();
    }

    @Override
    public void enterInherits(QuorumParser.InheritsContext ctx) {
        listener.EnterInherits();
    }

    @Override
    public void exitInherits(QuorumParser.InheritsContext ctx) {
        listener.ExitInherits();
    }

    @Override
    public void enterVariableOrFieldAccess(QuorumParser.VariableOrFieldAccessContext ctx) {
        listener.EnterVariableOrFieldAccess();
    }

    @Override
    public void exitVariableOrFieldAccess(QuorumParser.VariableOrFieldAccessContext ctx) {
        listener.ExitVariableOrFieldAccess();
    }

    @Override
    public void enterMeFunctionCall(QuorumParser.MeFunctionCallContext ctx) {
        listener.EnterMeFunctionCall();
    }

    @Override
    public void exitMeFunctionCall(QuorumParser.MeFunctionCallContext ctx) {
        listener.ExitMeFunctionCall();
    }

    @Override
    public void enterAssign_right_hand_side(QuorumParser.Assign_right_hand_sideContext ctx) {
        listener.EnterAssignRightHandSide();
    }

    @Override
    public void exitAssign_right_hand_side(QuorumParser.Assign_right_hand_sideContext ctx) {
        listener.ExitAssignRightHandSide();
    }

    @Override
    public void enterIf_statement(QuorumParser.If_statementContext ctx) {
        listener.EnterIfStatement();
    }

    @Override
    public void exitIf_statement(QuorumParser.If_statementContext ctx) {
        listener.ExitIfStatement();
    }

    @Override
    public void enterAccess_modifier(QuorumParser.Access_modifierContext ctx) {
        listener.EnterAccessModifier();
    }

    @Override
    public void exitAccess_modifier(QuorumParser.Access_modifierContext ctx) {
        listener.ExitAccessModifier();
    }

    @Override
    public void enterBlock(QuorumParser.BlockContext ctx) {
        listener.EnterBlock();
    }

    @Override
    public void exitBlock(QuorumParser.BlockContext ctx) {
        listener.ExitBlock();
    }

    @Override
    public void enterClass_stmnts(QuorumParser.Class_stmntsContext ctx) {
        listener.EnterClassStatements();
    }

    @Override
    public void exitClass_stmnts(QuorumParser.Class_stmntsContext ctx) {
        listener.ExitClassStatements();
    }

    @Override
    public void enterQualified_name(QuorumParser.Qualified_nameContext ctx) {
        listener.EnterQualifiedName();
    }

    @Override
    public void exitQualified_name(QuorumParser.Qualified_nameContext ctx) {
        listener.ExitQualifiedName();
    }

    @Override
    public void enterLoop_statement(QuorumParser.Loop_statementContext ctx) {
        listener.EnterLoopStatement();
    }

    @Override
    public void exitLoop_statement(QuorumParser.Loop_statementContext ctx) {
        listener.ExitLoopStatement();
    }

    @Override
    public void enterAssignment_declaration(QuorumParser.Assignment_declarationContext ctx) {
        listener.EnterAssignmentDeclaration();
    }

    @Override
    public void exitAssignment_declaration(QuorumParser.Assignment_declarationContext ctx) {
        listener.ExitAssignmentDeclaration();
    }

    @Override
    public void enterFieldAccess(QuorumParser.FieldAccessContext ctx) {
        listener.EnterFieldAccess();
    }

    @Override
    public void exitFieldAccess(QuorumParser.FieldAccessContext ctx) {
        listener.ExitFieldAccess();
    }

    @Override
    public void enterInherit_stmnts(QuorumParser.Inherit_stmntsContext ctx) {
        listener.EnterInheritStatements();
    }

    @Override
    public void exitInherit_stmnts(QuorumParser.Inherit_stmntsContext ctx) {
        listener.ExitInheritStatements();
    }

    @Override
    public void enterDetect_parameter(QuorumParser.Detect_parameterContext ctx) {
        listener.EnterDetectParameter();
    }

    @Override
    public void exitDetect_parameter(QuorumParser.Detect_parameterContext ctx) {
        listener.ExitDetectParameter();
    }

    @Override
    public void enterAddition(QuorumParser.AdditionContext ctx) {
        listener.EnterAddition();
    }

    @Override
    public void exitAddition(QuorumParser.AdditionContext ctx) {
        listener.ExitAddition();
    }

    @Override
    public void enterNo_class_stmnts(QuorumParser.No_class_stmntsContext ctx) {
        listener.EnterNoClassStatements();
    }

    @Override
    public void exitNo_class_stmnts(QuorumParser.No_class_stmntsContext ctx) {
        listener.ExitNoClassStatements();
    }

    @Override
    public void enterReturn_statement(QuorumParser.Return_statementContext ctx) {
        listener.EnterReturnStatement();
    }

    @Override
    public void exitReturn_statement(QuorumParser.Return_statementContext ctx) {
        listener.ExitReturnStatement();
    }

    @Override
    public void enterInput(QuorumParser.InputContext ctx) {
        listener.EnterInput();
    }

    @Override
    public void exitInput(QuorumParser.InputContext ctx) {
        listener.ExitInput();
    }

    @Override
    public void enterMultiplication(QuorumParser.MultiplicationContext ctx) {
        listener.EnterMultiplication();
    }

    @Override
    public void exitMultiplication(QuorumParser.MultiplicationContext ctx) {
        listener.ExitMultiplication();
    }

    @Override
    public void enterMinus(QuorumParser.MinusContext ctx) {
        listener.EnterMinus();
    }

    @Override
    public void exitMinus(QuorumParser.MinusContext ctx) {
        listener.ExitMinus();
    }

    @Override
    public void enterAssignment_statement(QuorumParser.Assignment_statementContext ctx) {
        listener.EnterAssignmentStatement();
    }

    @Override
    public void exitAssignment_statement(QuorumParser.Assignment_statementContext ctx) {
        listener.ExitAssignmentStatement();
    }

    @Override
    public void enterBoolean(QuorumParser.BooleanContext ctx) {
        listener.EnterBoolean();
    }

    @Override
    public void exitBoolean(QuorumParser.BooleanContext ctx) {
        listener.ExitBoolean();
    }

    @Override
    public void enterAlert_statement(QuorumParser.Alert_statementContext ctx) {
        listener.EnterAlertStatement();
    }

    @Override
    public void exitAlert_statement(QuorumParser.Alert_statementContext ctx) {
        listener.ExitAlertStatement();
    }

    @Override
    public void enterReference(QuorumParser.ReferenceContext ctx) {
        listener.EnterReference();
    }

    @Override
    public void exitReference(QuorumParser.ReferenceContext ctx) {
        listener.ExitReference();
    }

    @Override
    public void enterNot(QuorumParser.NotContext ctx) {
        listener.EnterNot();
    }

    @Override
    public void exitNot(QuorumParser.NotContext ctx) {
        listener.ExitNot();
    }

    @Override
    public void enterGeneric_declaration(QuorumParser.Generic_declarationContext ctx) {
        listener.EnterGenericDeclaration();
    }

    @Override
    public void exitGeneric_declaration(QuorumParser.Generic_declarationContext ctx) {
        listener.ExitGenericDeclaration();
    }

    @Override
    public void enterClass_type(QuorumParser.Class_typeContext ctx) {
        listener.EnterClassType();
    }

    @Override
    public void exitClass_type(QuorumParser.Class_typeContext ctx) {
        listener.ExitClassType();
    }

    @Override
    public void enterEquals(QuorumParser.EqualsContext ctx) {
        listener.EnterEquals();
    }

    @Override
    public void exitEquals(QuorumParser.EqualsContext ctx) {
        listener.ExitEquals();
    }

    @Override
    public void enterGreater(QuorumParser.GreaterContext ctx) {
        listener.EnterGreater();
    }

    @Override
    public void exitGreater(QuorumParser.GreaterContext ctx) {
        listener.ExitGreater();
    }

    @Override
    public void enterInteger(QuorumParser.IntegerContext ctx) {
        listener.EnterInteger();
    }

    @Override
    public void exitInteger(QuorumParser.IntegerContext ctx) {
        listener.ExitInteger();
    }

    @Override
    public void enterPackage_rule(QuorumParser.Package_ruleContext ctx) {
        listener.EnterPackageRule();
    }

    @Override
    public void exitPackage_rule(QuorumParser.Package_ruleContext ctx) {
        listener.ExitPackageRule();
    }

    @Override
    public void visitTerminal(TerminalNode tn) {
    
    }

    @Override
    public void visitErrorNode(ErrorNode en) {
        
    }

    @Override
    public void enterEveryRule(ParserRuleContext prc) {
        
    }

    @Override
    public void exitEveryRule(ParserRuleContext prc) {
        
    }

    /**
     * @return the listener
     */
    public QuorumSourceListener$Interface getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    public void setListener(QuorumSourceListener$Interface listener) {
        this.listener = listener;
    }
    
}
