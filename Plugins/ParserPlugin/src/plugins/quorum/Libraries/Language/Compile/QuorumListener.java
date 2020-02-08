// Generated from Quorum.g4 by ANTLR 4.2.2
package plugins.quorum.Libraries.Language.Compile;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuorumParser}.
 */
public interface QuorumListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuorumParser#formal_parameter}.
	 * @param ctx the parse tree
	 */
	void enterFormal_parameter(@NotNull QuorumParser.Formal_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#formal_parameter}.
	 * @param ctx the parse tree
	 */
	void exitFormal_parameter(@NotNull QuorumParser.Formal_parameterContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#always_statement}.
	 * @param ctx the parse tree
	 */
	void enterAlways_statement(@NotNull QuorumParser.Always_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#always_statement}.
	 * @param ctx the parse tree
	 */
	void exitAlways_statement(@NotNull QuorumParser.Always_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#inherit_stmt}.
	 * @param ctx the parse tree
	 */
	void enterInherit_stmt(@NotNull QuorumParser.Inherit_stmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#inherit_stmt}.
	 * @param ctx the parse tree
	 */
	void exitInherit_stmt(@NotNull QuorumParser.Inherit_stmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Null}.
	 * @param ctx the parse tree
	 */
	void enterNull(@NotNull QuorumParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Null}.
	 * @param ctx the parse tree
	 */
	void exitNull(@NotNull QuorumParser.NullContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Multiplication}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(@NotNull QuorumParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Multiplication}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(@NotNull QuorumParser.MultiplicationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Or}.
	 * @param ctx the parse tree
	 */
	void enterOr(@NotNull QuorumParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Or}.
	 * @param ctx the parse tree
	 */
	void exitOr(@NotNull QuorumParser.OrContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#solo_method_required_method_part}.
	 * @param ctx the parse tree
	 */
	void enterSolo_method_required_method_part(@NotNull QuorumParser.Solo_method_required_method_partContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#solo_method_required_method_part}.
	 * @param ctx the parse tree
	 */
	void exitSolo_method_required_method_part(@NotNull QuorumParser.Solo_method_required_method_partContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#String}.
	 * @param ctx the parse tree
	 */
	void enterString(@NotNull QuorumParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#String}.
	 * @param ctx the parse tree
	 */
	void exitString(@NotNull QuorumParser.StringContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#NoActionsNoClass}.
	 * @param ctx the parse tree
	 */
	void enterNoActionsNoClass(@NotNull QuorumParser.NoActionsNoClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#NoActionsNoClass}.
	 * @param ctx the parse tree
	 */
	void exitNoActionsNoClass(@NotNull QuorumParser.NoActionsNoClassContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#method_shared}.
	 * @param ctx the parse tree
	 */
	void enterMethod_shared(@NotNull QuorumParser.Method_sharedContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#method_shared}.
	 * @param ctx the parse tree
	 */
	void exitMethod_shared(@NotNull QuorumParser.Method_sharedContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReference(@NotNull QuorumParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReference(@NotNull QuorumParser.ReferenceContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Input}.
	 * @param ctx the parse tree
	 */
	void enterInput(@NotNull QuorumParser.InputContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Input}.
	 * @param ctx the parse tree
	 */
	void exitInput(@NotNull QuorumParser.InputContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#generic_declaration}.
	 * @param ctx the parse tree
	 */
	void enterGeneric_declaration(@NotNull QuorumParser.Generic_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#generic_declaration}.
	 * @param ctx the parse tree
	 */
	void exitGeneric_declaration(@NotNull QuorumParser.Generic_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#class_type}.
	 * @param ctx the parse tree
	 */
	void enterClass_type(@NotNull QuorumParser.Class_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#class_type}.
	 * @param ctx the parse tree
	 */
	void exitClass_type(@NotNull QuorumParser.Class_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Decimal}.
	 * @param ctx the parse tree
	 */
	void enterDecimal(@NotNull QuorumParser.DecimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Decimal}.
	 * @param ctx the parse tree
	 */
	void exitDecimal(@NotNull QuorumParser.DecimalContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#ParentAssignment}.
	 * @param ctx the parse tree
	 */
	void enterParentAssignment(@NotNull QuorumParser.ParentAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#ParentAssignment}.
	 * @param ctx the parse tree
	 */
	void exitParentAssignment(@NotNull QuorumParser.ParentAssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#NativeAction}.
	 * @param ctx the parse tree
	 */
	void enterNativeAction(@NotNull QuorumParser.NativeActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#NativeAction}.
	 * @param ctx the parse tree
	 */
	void exitNativeAction(@NotNull QuorumParser.NativeActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#ParentVariableSoloFunctionCall}.
	 * @param ctx the parse tree
	 */
	void enterParentVariableSoloFunctionCall(@NotNull QuorumParser.ParentVariableSoloFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#ParentVariableSoloFunctionCall}.
	 * @param ctx the parse tree
	 */
	void exitParentVariableSoloFunctionCall(@NotNull QuorumParser.ParentVariableSoloFunctionCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#action_call}.
	 * @param ctx the parse tree
	 */
	void enterAction_call(@NotNull QuorumParser.Action_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#action_call}.
	 * @param ctx the parse tree
	 */
	void exitAction_call(@NotNull QuorumParser.Action_callContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull QuorumParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull QuorumParser.BlockContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Boolean}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(@NotNull QuorumParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Boolean}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(@NotNull QuorumParser.BooleanContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#BlueprintAction}.
	 * @param ctx the parse tree
	 */
	void enterBlueprintAction(@NotNull QuorumParser.BlueprintActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#BlueprintAction}.
	 * @param ctx the parse tree
	 */
	void exitBlueprintAction(@NotNull QuorumParser.BlueprintActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#InputNoParameters}.
	 * @param ctx the parse tree
	 */
	void enterInputNoParameters(@NotNull QuorumParser.InputNoParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#InputNoParameters}.
	 * @param ctx the parse tree
	 */
	void exitInputNoParameters(@NotNull QuorumParser.InputNoParametersContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#check_statement}.
	 * @param ctx the parse tree
	 */
	void enterCheck_statement(@NotNull QuorumParser.Check_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#check_statement}.
	 * @param ctx the parse tree
	 */
	void exitCheck_statement(@NotNull QuorumParser.Check_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statement(@NotNull QuorumParser.Return_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#return_statement}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statement(@NotNull QuorumParser.Return_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Addition}.
	 * @param ctx the parse tree
	 */
	void enterAddition(@NotNull QuorumParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Addition}.
	 * @param ctx the parse tree
	 */
	void exitAddition(@NotNull QuorumParser.AdditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#VariableSoloFunctionCall}.
	 * @param ctx the parse tree
	 */
	void enterVariableSoloFunctionCall(@NotNull QuorumParser.VariableSoloFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#VariableSoloFunctionCall}.
	 * @param ctx the parse tree
	 */
	void exitVariableSoloFunctionCall(@NotNull QuorumParser.VariableSoloFunctionCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Inherits}.
	 * @param ctx the parse tree
	 */
	void enterInherits(@NotNull QuorumParser.InheritsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Inherits}.
	 * @param ctx the parse tree
	 */
	void exitInherits(@NotNull QuorumParser.InheritsContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void enterElse_statement(@NotNull QuorumParser.Else_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#else_statement}.
	 * @param ctx the parse tree
	 */
	void exitElse_statement(@NotNull QuorumParser.Else_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#IntegerAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterIntegerAssignmentDeclaration(@NotNull QuorumParser.IntegerAssignmentDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#IntegerAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitIntegerAssignmentDeclaration(@NotNull QuorumParser.IntegerAssignmentDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Not}.
	 * @param ctx the parse tree
	 */
	void enterNot(@NotNull QuorumParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Not}.
	 * @param ctx the parse tree
	 */
	void exitNot(@NotNull QuorumParser.NotContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#print_statement}.
	 * @param ctx the parse tree
	 */
	void enterPrint_statement(@NotNull QuorumParser.Print_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#print_statement}.
	 * @param ctx the parse tree
	 */
	void exitPrint_statement(@NotNull QuorumParser.Print_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#And}.
	 * @param ctx the parse tree
	 */
	void enterAnd(@NotNull QuorumParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#And}.
	 * @param ctx the parse tree
	 */
	void exitAnd(@NotNull QuorumParser.AndContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Me}.
	 * @param ctx the parse tree
	 */
	void enterMe(@NotNull QuorumParser.MeContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Me}.
	 * @param ctx the parse tree
	 */
	void exitMe(@NotNull QuorumParser.MeContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void enterIf_statement(@NotNull QuorumParser.If_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#if_statement}.
	 * @param ctx the parse tree
	 */
	void exitIf_statement(@NotNull QuorumParser.If_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#ActionsNoClass}.
	 * @param ctx the parse tree
	 */
	void enterActionsNoClass(@NotNull QuorumParser.ActionsNoClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#ActionsNoClass}.
	 * @param ctx the parse tree
	 */
	void exitActionsNoClass(@NotNull QuorumParser.ActionsNoClassContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#GenericAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterGenericAssignmentDeclaration(@NotNull QuorumParser.GenericAssignmentDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#GenericAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitGenericAssignmentDeclaration(@NotNull QuorumParser.GenericAssignmentDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#inherit_stmnts}.
	 * @param ctx the parse tree
	 */
	void enterInherit_stmnts(@NotNull QuorumParser.Inherit_stmntsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#inherit_stmnts}.
	 * @param ctx the parse tree
	 */
	void exitInherit_stmnts(@NotNull QuorumParser.Inherit_stmntsContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Minus}.
	 * @param ctx the parse tree
	 */
	void enterMinus(@NotNull QuorumParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Minus}.
	 * @param ctx the parse tree
	 */
	void exitMinus(@NotNull QuorumParser.MinusContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Cast}.
	 * @param ctx the parse tree
	 */
	void enterCast(@NotNull QuorumParser.CastContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Cast}.
	 * @param ctx the parse tree
	 */
	void exitCast(@NotNull QuorumParser.CastContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#class_stmnts}.
	 * @param ctx the parse tree
	 */
	void enterClass_stmnts(@NotNull QuorumParser.Class_stmntsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#class_stmnts}.
	 * @param ctx the parse tree
	 */
	void exitClass_stmnts(@NotNull QuorumParser.Class_stmntsContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Action}.
	 * @param ctx the parse tree
	 */
	void enterAction(@NotNull QuorumParser.ActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Action}.
	 * @param ctx the parse tree
	 */
	void exitAction(@NotNull QuorumParser.ActionContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#function_expression_list}.
	 * @param ctx the parse tree
	 */
	void enterFunction_expression_list(@NotNull QuorumParser.Function_expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#function_expression_list}.
	 * @param ctx the parse tree
	 */
	void exitFunction_expression_list(@NotNull QuorumParser.Function_expression_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#package_rule}.
	 * @param ctx the parse tree
	 */
	void enterPackage_rule(@NotNull QuorumParser.Package_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#package_rule}.
	 * @param ctx the parse tree
	 */
	void exitPackage_rule(@NotNull QuorumParser.Package_ruleContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#access_modifier}.
	 * @param ctx the parse tree
	 */
	void enterAccess_modifier(@NotNull QuorumParser.Access_modifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#access_modifier}.
	 * @param ctx the parse tree
	 */
	void exitAccess_modifier(@NotNull QuorumParser.Access_modifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#generic_statement}.
	 * @param ctx the parse tree
	 */
	void enterGeneric_statement(@NotNull QuorumParser.Generic_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#generic_statement}.
	 * @param ctx the parse tree
	 */
	void exitGeneric_statement(@NotNull QuorumParser.Generic_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#NoTypeAssignment}.
	 * @param ctx the parse tree
	 */
	void enterNoTypeAssignment(@NotNull QuorumParser.NoTypeAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#NoTypeAssignment}.
	 * @param ctx the parse tree
	 */
	void exitNoTypeAssignment(@NotNull QuorumParser.NoTypeAssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#VariableFunctionCall}.
	 * @param ctx the parse tree
	 */
	void enterVariableFunctionCall(@NotNull QuorumParser.VariableFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#VariableFunctionCall}.
	 * @param ctx the parse tree
	 */
	void exitVariableFunctionCall(@NotNull QuorumParser.VariableFunctionCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#alert_statement}.
	 * @param ctx the parse tree
	 */
	void enterAlert_statement(@NotNull QuorumParser.Alert_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#alert_statement}.
	 * @param ctx the parse tree
	 */
	void exitAlert_statement(@NotNull QuorumParser.Alert_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#ObjectAssignment}.
	 * @param ctx the parse tree
	 */
	void enterObjectAssignment(@NotNull QuorumParser.ObjectAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#ObjectAssignment}.
	 * @param ctx the parse tree
	 */
	void exitObjectAssignment(@NotNull QuorumParser.ObjectAssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#TextAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTextAssignmentDeclaration(@NotNull QuorumParser.TextAssignmentDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#TextAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTextAssignmentDeclaration(@NotNull QuorumParser.TextAssignmentDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull QuorumParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull QuorumParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#detect_statement}.
	 * @param ctx the parse tree
	 */
	void enterDetect_statement(@NotNull QuorumParser.Detect_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#detect_statement}.
	 * @param ctx the parse tree
	 */
	void exitDetect_statement(@NotNull QuorumParser.Detect_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#NormalAssignment}.
	 * @param ctx the parse tree
	 */
	void enterNormalAssignment(@NotNull QuorumParser.NormalAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#NormalAssignment}.
	 * @param ctx the parse tree
	 */
	void exitNormalAssignment(@NotNull QuorumParser.NormalAssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#ParentVariableFunctionCall}.
	 * @param ctx the parse tree
	 */
	void enterParentVariableFunctionCall(@NotNull QuorumParser.ParentVariableFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#ParentVariableFunctionCall}.
	 * @param ctx the parse tree
	 */
	void exitParentVariableFunctionCall(@NotNull QuorumParser.ParentVariableFunctionCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#elseif_statement}.
	 * @param ctx the parse tree
	 */
	void enterElseif_statement(@NotNull QuorumParser.Elseif_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#elseif_statement}.
	 * @param ctx the parse tree
	 */
	void exitElseif_statement(@NotNull QuorumParser.Elseif_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Constructor}.
	 * @param ctx the parse tree
	 */
	void enterConstructor(@NotNull QuorumParser.ConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Constructor}.
	 * @param ctx the parse tree
	 */
	void exitConstructor(@NotNull QuorumParser.ConstructorContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#ParenthesisExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesisExpression(@NotNull QuorumParser.ParenthesisExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#ParenthesisExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesisExpression(@NotNull QuorumParser.ParenthesisExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(@NotNull QuorumParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(@NotNull QuorumParser.StartContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#NoClassDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNoClassDeclaration(@NotNull QuorumParser.NoClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#NoClassDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNoClassDeclaration(@NotNull QuorumParser.NoClassDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#BooleanAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterBooleanAssignmentDeclaration(@NotNull QuorumParser.BooleanAssignmentDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#BooleanAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitBooleanAssignmentDeclaration(@NotNull QuorumParser.BooleanAssignmentDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#speak_statement}.
	 * @param ctx the parse tree
	 */
	void enterSpeak_statement(@NotNull QuorumParser.Speak_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#speak_statement}.
	 * @param ctx the parse tree
	 */
	void exitSpeak_statement(@NotNull QuorumParser.Speak_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Integer}.
	 * @param ctx the parse tree
	 */
	void enterInteger(@NotNull QuorumParser.IntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Integer}.
	 * @param ctx the parse tree
	 */
	void exitInteger(@NotNull QuorumParser.IntegerContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#initial_parent_action_call}.
	 * @param ctx the parse tree
	 */
	void enterInitial_parent_action_call(@NotNull QuorumParser.Initial_parent_action_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#initial_parent_action_call}.
	 * @param ctx the parse tree
	 */
	void exitInitial_parent_action_call(@NotNull QuorumParser.Initial_parent_action_callContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#NumberAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNumberAssignmentDeclaration(@NotNull QuorumParser.NumberAssignmentDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#NumberAssignmentDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNumberAssignmentDeclaration(@NotNull QuorumParser.NumberAssignmentDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Equals}.
	 * @param ctx the parse tree
	 */
	void enterEquals(@NotNull QuorumParser.EqualsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Equals}.
	 * @param ctx the parse tree
	 */
	void exitEquals(@NotNull QuorumParser.EqualsContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#FullClassDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFullClassDeclaration(@NotNull QuorumParser.FullClassDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#FullClassDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFullClassDeclaration(@NotNull QuorumParser.FullClassDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#qualified_name}.
	 * @param ctx the parse tree
	 */
	void enterQualified_name(@NotNull QuorumParser.Qualified_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#qualified_name}.
	 * @param ctx the parse tree
	 */
	void exitQualified_name(@NotNull QuorumParser.Qualified_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void enterLoop_statement(@NotNull QuorumParser.Loop_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#loop_statement}.
	 * @param ctx the parse tree
	 */
	void exitLoop_statement(@NotNull QuorumParser.Loop_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#Greater}.
	 * @param ctx the parse tree
	 */
	void enterGreater(@NotNull QuorumParser.GreaterContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#Greater}.
	 * @param ctx the parse tree
	 */
	void exitGreater(@NotNull QuorumParser.GreaterContext ctx);
}