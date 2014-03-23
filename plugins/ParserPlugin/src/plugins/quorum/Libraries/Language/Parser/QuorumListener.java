// Generated from /Users/stefika/Repositories/quorum-language/plugins/ParserPlugin/src/quorum/Libraries/Language/Parser/Quorum.g4 by ANTLR 4.1
package plugins.quorum.Libraries.Language.Parser;;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link QuorumParser}.
 */
public interface QuorumListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link QuorumParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull QuorumParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull QuorumParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(@NotNull QuorumParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(@NotNull QuorumParser.SelectorContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(@NotNull QuorumParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(@NotNull QuorumParser.AtomContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#assign_right_hand_side}.
	 * @param ctx the parse tree
	 */
	void enterAssign_right_hand_side(@NotNull QuorumParser.Assign_right_hand_sideContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#assign_right_hand_side}.
	 * @param ctx the parse tree
	 */
	void exitAssign_right_hand_side(@NotNull QuorumParser.Assign_right_hand_sideContext ctx);

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
	 * Enter a parse tree produced by {@link QuorumParser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(@NotNull QuorumParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(@NotNull QuorumParser.AddContext ctx);

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
	 * Enter a parse tree produced by {@link QuorumParser#assignment_declaration}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_declaration(@NotNull QuorumParser.Assignment_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#assignment_declaration}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_declaration(@NotNull QuorumParser.Assignment_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#root_expression}.
	 * @param ctx the parse tree
	 */
	void enterRoot_expression(@NotNull QuorumParser.Root_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#root_expression}.
	 * @param ctx the parse tree
	 */
	void exitRoot_expression(@NotNull QuorumParser.Root_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#equality}.
	 * @param ctx the parse tree
	 */
	void enterEquality(@NotNull QuorumParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#equality}.
	 * @param ctx the parse tree
	 */
	void exitEquality(@NotNull QuorumParser.EqualityContext ctx);

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
	 * Enter a parse tree produced by {@link QuorumParser#detect_parameter}.
	 * @param ctx the parse tree
	 */
	void enterDetect_parameter(@NotNull QuorumParser.Detect_parameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#detect_parameter}.
	 * @param ctx the parse tree
	 */
	void exitDetect_parameter(@NotNull QuorumParser.Detect_parameterContext ctx);

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
	 * Enter a parse tree produced by {@link QuorumParser#no_class_stmnts}.
	 * @param ctx the parse tree
	 */
	void enterNo_class_stmnts(@NotNull QuorumParser.No_class_stmntsContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#no_class_stmnts}.
	 * @param ctx the parse tree
	 */
	void exitNo_class_stmnts(@NotNull QuorumParser.No_class_stmntsContext ctx);

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
	 * Enter a parse tree produced by {@link QuorumParser#class_declaration}.
	 * @param ctx the parse tree
	 */
	void enterClass_declaration(@NotNull QuorumParser.Class_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#class_declaration}.
	 * @param ctx the parse tree
	 */
	void exitClass_declaration(@NotNull QuorumParser.Class_declarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#or}.
	 * @param ctx the parse tree
	 */
	void enterOr(@NotNull QuorumParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#or}.
	 * @param ctx the parse tree
	 */
	void exitOr(@NotNull QuorumParser.OrContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#solo_method_call}.
	 * @param ctx the parse tree
	 */
	void enterSolo_method_call(@NotNull QuorumParser.Solo_method_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#solo_method_call}.
	 * @param ctx the parse tree
	 */
	void exitSolo_method_call(@NotNull QuorumParser.Solo_method_callContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#multiply}.
	 * @param ctx the parse tree
	 */
	void enterMultiply(@NotNull QuorumParser.MultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#multiply}.
	 * @param ctx the parse tree
	 */
	void exitMultiply(@NotNull QuorumParser.MultiplyContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#assignment_statement}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_statement(@NotNull QuorumParser.Assignment_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#assignment_statement}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_statement(@NotNull QuorumParser.Assignment_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(@NotNull QuorumParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(@NotNull QuorumParser.ComparisonContext ctx);

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
	 * Enter a parse tree produced by {@link QuorumParser#and}.
	 * @param ctx the parse tree
	 */
	void enterAnd(@NotNull QuorumParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#and}.
	 * @param ctx the parse tree
	 */
	void exitAnd(@NotNull QuorumParser.AndContext ctx);

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
	 * Enter a parse tree produced by {@link QuorumParser#isa_operation}.
	 * @param ctx the parse tree
	 */
	void enterIsa_operation(@NotNull QuorumParser.Isa_operationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#isa_operation}.
	 * @param ctx the parse tree
	 */
	void exitIsa_operation(@NotNull QuorumParser.Isa_operationContext ctx);

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
	 * Enter a parse tree produced by {@link QuorumParser#combo_expression}.
	 * @param ctx the parse tree
	 */
	void enterCombo_expression(@NotNull QuorumParser.Combo_expressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#combo_expression}.
	 * @param ctx the parse tree
	 */
	void exitCombo_expression(@NotNull QuorumParser.Combo_expressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link QuorumParser#method_declaration}.
	 * @param ctx the parse tree
	 */
	void enterMethod_declaration(@NotNull QuorumParser.Method_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#method_declaration}.
	 * @param ctx the parse tree
	 */
	void exitMethod_declaration(@NotNull QuorumParser.Method_declarationContext ctx);

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
	 * Enter a parse tree produced by {@link QuorumParser#package_rule}.
	 * @param ctx the parse tree
	 */
	void enterPackage_rule(@NotNull QuorumParser.Package_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link QuorumParser#package_rule}.
	 * @param ctx the parse tree
	 */
	void exitPackage_rule(@NotNull QuorumParser.Package_ruleContext ctx);
}