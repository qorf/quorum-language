/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Compile;

import java.util.Iterator;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import quorum.Libraries.Language.Compile.Context.*;
import quorum.Libraries.Language.Compile.Location;
import quorum.Libraries.Language.Compile.QuorumSourceListener$Interface;
import quorum.Libraries.Language.Compile.Symbol.Type;
import quorum.Libraries.Language.Compile.Symbol.Type$Interface;
import quorum.Libraries.Language.Compile.Symbol.Variable;
import quorum.Libraries.Language.Types.Text;
import quorum.Libraries.System.File$Interface;

/**
 *
 * @author stefika
 */
public class JavaToQuorumListener implements QuorumListener {

    private QuorumSourceListener$Interface listener;
    private File$Interface file;
    
    @Override
    public void enterDecimal(QuorumParser.DecimalContext ctx) {
        double val = Double.parseDouble(ctx.DECIMAL().getText());
        NumberContext context = new NumberContext();
        context.Set$Libraries$Language$Compile$Context$NumberContext$value(val);
        setLocation(ctx, context);
        listener.EnterNumber(context);
    }

    @Override
    public void exitDecimal(QuorumParser.DecimalContext ctx) {
        double val = Double.parseDouble(ctx.DECIMAL().getText());
        NumberContext context = new NumberContext();
        context.Set$Libraries$Language$Compile$Context$NumberContext$value(val);
        setLocation(ctx, context);
        listener.ExitNumber(context);
    }

    @Override
    public void enterParenthesisExpression(QuorumParser.ParenthesisExpressionContext ctx) {
        quorum.Libraries.Language.Compile.Context.ParenthesisContext context = 
                new quorum.Libraries.Language.Compile.Context.ParenthesisContext();
        setLocation(ctx, context);
        listener.EnterParenthesisExpression(context);
    }

    @Override
    public void exitParenthesisExpression(QuorumParser.ParenthesisExpressionContext ctx) {
        quorum.Libraries.Language.Compile.Context.ParenthesisContext context = 
                new quorum.Libraries.Language.Compile.Context.ParenthesisContext();
        setLocation(ctx, context);
        listener.ExitParenthesisExpression(context);
    }

    @Override
    public void enterCast(QuorumParser.CastContext ctx) {
        quorum.Libraries.Language.Compile.Context.CastContext context = 
                new quorum.Libraries.Language.Compile.Context.CastContext();
        setLocation(ctx, context);
        listener.EnterCast(context);
    }

    @Override
    public void exitCast(QuorumParser.CastContext ctx) {
        quorum.Libraries.Language.Compile.Context.CastContext context = 
                new quorum.Libraries.Language.Compile.Context.CastContext();
        setLocation(ctx, context);
        listener.ExitCast(context);
    }

    @Override
    public void enterSpeak_statement(QuorumParser.Speak_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.SayContext context = 
                new quorum.Libraries.Language.Compile.Context.SayContext();
        setLocation(ctx, context);
        listener.EnterSayStatement(context);
    }

    @Override
    public void exitSpeak_statement(QuorumParser.Speak_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.SayContext context = 
                new quorum.Libraries.Language.Compile.Context.SayContext();
        setLocation(ctx, context);
        listener.ExitSayStatement(context);
    }

    @Override
    public void enterCheck_statement(QuorumParser.Check_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.CheckContext context = 
                new quorum.Libraries.Language.Compile.Context.CheckContext();
        setLocation(ctx, context);
        listener.EnterCheckStatement(context);
    }

    @Override
    public void exitCheck_statement(QuorumParser.Check_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.CheckContext context = 
                new quorum.Libraries.Language.Compile.Context.CheckContext();
        setLocation(ctx, context);
        listener.ExitCheckStatement(context);
    }

    @Override
    public void enterFullClassDeclaration(@NotNull QuorumParser.FullClassDeclarationContext ctx) {
        FullClassDeclarationContext context = new FullClassDeclarationContext();
        context.className = ctx.ID().getText();
        setLocation(ctx, context);
        listener.EnterFullClassDeclaration(context);
    }

    @Override
    public void exitFullClassDeclaration(@NotNull QuorumParser.FullClassDeclarationContext ctx) {
        FullClassDeclarationContext context = new FullClassDeclarationContext();
        context.className = ctx.ID().getText();
        setLocation(ctx, context);
        listener.ExitFullClassDeclaration(context);
    }

    @Override
    public void enterNoClassDeclaration(@NotNull QuorumParser.NoClassDeclarationContext ctx) {
        NoClassDeclarationContext context = new NoClassDeclarationContext();
        setLocation(ctx, context);
        listener.EnterNoClassDeclaration(context);
    }

    @Override
    public void exitNoClassDeclaration(@NotNull QuorumParser.NoClassDeclarationContext ctx) {
        NoClassDeclarationContext context = new NoClassDeclarationContext();
        setLocation(ctx, context);
        listener.ExitNoClassDeclaration(context);
    }

    @Override
    public void enterSolo_method_call(QuorumParser.Solo_method_callContext ctx) {
        quorum.Libraries.Language.Compile.Context.ActionCallContext context = 
                new quorum.Libraries.Language.Compile.Context.ActionCallContext();
        setLocation(ctx, context);
        context.isSolo = true;
        listener.EnterSoloActionCall(context);
    }

    @Override
    public void exitSolo_method_call(QuorumParser.Solo_method_callContext ctx) {
        quorum.Libraries.Language.Compile.Context.ActionCallContext context = 
                new quorum.Libraries.Language.Compile.Context.ActionCallContext();
        setLocation(ctx, context);
        context.isSolo = true;
        listener.ExitSoloActionCall(context);
    }

    @Override
    public void enterFormal_parameter(QuorumParser.Formal_parameterContext ctx) {
        FormalParameterContext context = new FormalParameterContext();
        setLocation(ctx, context);
        context.name = ctx.ID().getText();
        listener.EnterFormalParameter(context);
    }

    @Override
    public void exitFormal_parameter(QuorumParser.Formal_parameterContext ctx) {
        FormalParameterContext context = new FormalParameterContext();
        setLocation(ctx, context);
        context.name = ctx.ID().getText();
        context.type = ctx.assignment_declaration().type;

        listener.ExitFormalParameter(context);
    }

    @Override
    public void enterStatement(QuorumParser.StatementContext ctx) {
        quorum.Libraries.Language.Compile.Context.StatementContext context = 
                new quorum.Libraries.Language.Compile.Context.StatementContext();
        setLocation(ctx, context);
        listener.EnterStatement(context);
    }

    @Override
    public void exitStatement(QuorumParser.StatementContext ctx) {
        quorum.Libraries.Language.Compile.Context.StatementContext context = 
                new quorum.Libraries.Language.Compile.Context.StatementContext();
        setLocation(ctx, context);
        listener.ExitStatement(context);
    }

    @Override
    public void enterMe(QuorumParser.MeContext ctx) {
        quorum.Libraries.Language.Compile.Context.MeContext context = 
                new quorum.Libraries.Language.Compile.Context.MeContext();
        setLocation(ctx, context);
        listener.EnterMe(context);
    }

    @Override
    public void exitMe(QuorumParser.MeContext ctx) {
        quorum.Libraries.Language.Compile.Context.MeContext context = 
                new quorum.Libraries.Language.Compile.Context.MeContext();
        setLocation(ctx, context);
        listener.ExitMe(context);
    }

    @Override
    public void enterFunction_expression_list(QuorumParser.Function_expression_listContext ctx) {
        quorum.Libraries.Language.Compile.Context.ActionExpressionListContext context = 
                new quorum.Libraries.Language.Compile.Context.ActionExpressionListContext();
        setLocation(ctx, context);
        int size = ctx.expression().size();
        context.size = size;
        listener.EnterActionExpressionList(context);
    }

    @Override
    public void exitFunction_expression_list(QuorumParser.Function_expression_listContext ctx) {
        quorum.Libraries.Language.Compile.Context.ActionExpressionListContext context = 
                new quorum.Libraries.Language.Compile.Context.ActionExpressionListContext();
        setLocation(ctx, context);
        int size = ctx.expression().size();
        context.size = size;
        listener.ExitActionExpressionList(context);
    }

    @Override
    public void enterStart(QuorumParser.StartContext ctx) {
        StartContext context = new StartContext();
        setLocation(ctx, context);
        listener.EnterStart(context);
    }

    @Override
    public void exitStart(QuorumParser.StartContext ctx) {
        StartContext context = new StartContext();
        setLocation(ctx, context);
        listener.ExitStart(context);
    }

    @Override
    public void enterString(QuorumParser.StringContext ctx) {
        String val = ctx.STRING().getText();
        val = val.substring(1, val.length() - 1);
        TextContext context = new TextContext();
        context.Set$Libraries$Language$Compile$Context$TextContext$value(val);
        setLocation(ctx, context);
        listener.EnterText(context);
    }

    @Override
    public void exitString(QuorumParser.StringContext ctx) {
        String val = ctx.STRING().getText();
        val = val.substring(1, val.length() - 1);
        TextContext context = new TextContext();
        context.Set$Libraries$Language$Compile$Context$TextContext$value(val);
        setLocation(ctx, context);
        listener.ExitText(context);
    }

    @Override
    public void enterOr(QuorumParser.OrContext ctx) {
        quorum.Libraries.Language.Compile.Context.AndOrContext context = 
                new quorum.Libraries.Language.Compile.Context.AndOrContext();
        setLocation(ctx, context);
        context.isAnd = false;
        listener.EnterOr(context);
    }

    @Override
    public void exitOr(QuorumParser.OrContext ctx) {
        quorum.Libraries.Language.Compile.Context.AndOrContext context = 
                new quorum.Libraries.Language.Compile.Context.AndOrContext();
        setLocation(ctx, context);
        context.isAnd = false;
        listener.ExitOr(context);
    }

    @Override
    public void enterPrint_statement(QuorumParser.Print_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.OutputContext context = 
                new quorum.Libraries.Language.Compile.Context.OutputContext();
        setLocation(ctx, context);
        listener.EnterOutputStatement(context);
    }

    @Override
    public void exitPrint_statement(QuorumParser.Print_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.OutputContext context = 
                new quorum.Libraries.Language.Compile.Context.OutputContext();
        setLocation(ctx, context);
        listener.ExitOutputStatement(context);
    }

    @Override
    public void enterGeneric_statement(QuorumParser.Generic_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.GenericContext context = 
                new quorum.Libraries.Language.Compile.Context.GenericContext();
        setLocation(ctx, context);
        listener.EnterGenericStatement(context);
    }

    @Override
    public void exitGeneric_statement(QuorumParser.Generic_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.GenericContext context = 
                new quorum.Libraries.Language.Compile.Context.GenericContext();
        setLocation(ctx, context);
        listener.ExitGenericStatement(context);
    }

    @Override
    public void enterAnd(QuorumParser.AndContext ctx) {
        quorum.Libraries.Language.Compile.Context.AndOrContext context = 
                new quorum.Libraries.Language.Compile.Context.AndOrContext();
        setLocation(ctx, context);
        context.isAnd = true;
        listener.EnterAnd(context);
    }

    @Override
    public void exitAnd(QuorumParser.AndContext ctx) {
        quorum.Libraries.Language.Compile.Context.AndOrContext context = 
                new quorum.Libraries.Language.Compile.Context.AndOrContext();
        setLocation(ctx, context);
        context.isAnd = true;
        listener.ExitAnd(context);
    }

    @Override
    public void enterNull(QuorumParser.NullContext ctx) {
        quorum.Libraries.Language.Compile.Context.UndefinedContext context = 
                new quorum.Libraries.Language.Compile.Context.UndefinedContext();
        setLocation(ctx, context);
        listener.EnterUndefined(context);
    }

    @Override
    public void exitNull(QuorumParser.NullContext ctx) {
        quorum.Libraries.Language.Compile.Context.UndefinedContext context = 
                new quorum.Libraries.Language.Compile.Context.UndefinedContext();
        setLocation(ctx, context);
        listener.ExitUndefined(context);
    }

    @Override
    public void enterInherits(QuorumParser.InheritsContext ctx) {
        quorum.Libraries.Language.Compile.Context.IsContext context = 
                new quorum.Libraries.Language.Compile.Context.IsContext();
        setLocation(ctx, context);
        listener.EnterIs(context);
    }

    @Override
    public void exitInherits(QuorumParser.InheritsContext ctx) {
        quorum.Libraries.Language.Compile.Context.IsContext context = 
                new quorum.Libraries.Language.Compile.Context.IsContext();
        setLocation(ctx, context);
        listener.ExitIs(context);
    }

    @Override
    public void enterIf_statement(QuorumParser.If_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.IfContext context = 
                new quorum.Libraries.Language.Compile.Context.IfContext();
        setLocation(ctx, context);
        listener.EnterIfStatement(context);
    }

    @Override
    public void exitIf_statement(QuorumParser.If_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.IfContext context = 
                new quorum.Libraries.Language.Compile.Context.IfContext();
        setLocation(ctx, context);
        listener.ExitIfStatement(context);
    }
    
    @Override
    public void enterElseif_statement(QuorumParser.Elseif_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.IfContext context = 
                new quorum.Libraries.Language.Compile.Context.IfContext();
        setLocation(ctx, context);
        listener.EnterElseIfStatement(context);
    }

    @Override
    public void exitElseif_statement(QuorumParser.Elseif_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.IfContext context = 
                new quorum.Libraries.Language.Compile.Context.IfContext();
        setLocation(ctx, context);
        listener.ExitElseIfStatement(context);
    }

    @Override
    public void enterElse_statement(QuorumParser.Else_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.IfContext context = 
                new quorum.Libraries.Language.Compile.Context.IfContext();
        setLocation(ctx, context);
        listener.EnterElseStatement(context);
    }

    @Override
    public void exitElse_statement(QuorumParser.Else_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.IfContext context = 
                new quorum.Libraries.Language.Compile.Context.IfContext();
        setLocation(ctx, context);
        listener.ExitElseStatement(context);
    }

    @Override
    public void enterAccess_modifier(QuorumParser.Access_modifierContext ctx) {
        quorum.Libraries.Language.Compile.Context.AccessModifierContext context = 
                new quorum.Libraries.Language.Compile.Context.AccessModifierContext();
        setLocation(ctx, context);
        listener.EnterAccessModifier(context);
    }

    @Override
    public void exitAccess_modifier(QuorumParser.Access_modifierContext ctx) {
        quorum.Libraries.Language.Compile.Context.AccessModifierContext context = 
                new quorum.Libraries.Language.Compile.Context.AccessModifierContext();
        setLocation(ctx, context);
        listener.ExitAccessModifier(context);
    }

    @Override
    public void enterBlock(QuorumParser.BlockContext ctx) {
        quorum.Libraries.Language.Compile.Context.BlockContext context = 
                new quorum.Libraries.Language.Compile.Context.BlockContext();
        setLocation(ctx, context);
        listener.EnterBlock(context);
    }

    @Override
    public void exitBlock(QuorumParser.BlockContext ctx) {
        quorum.Libraries.Language.Compile.Context.BlockContext context = 
                new quorum.Libraries.Language.Compile.Context.BlockContext();
        setLocation(ctx, context);
        listener.ExitBlock(context);
    }

    @Override
    public void enterClass_stmnts(QuorumParser.Class_stmntsContext ctx) {
        quorum.Libraries.Language.Compile.Context.ClassStatementsContext context = 
                new quorum.Libraries.Language.Compile.Context.ClassStatementsContext();
        setLocation(ctx, context);
        listener.EnterClassStatements(context);
    }

    @Override
    public void exitClass_stmnts(QuorumParser.Class_stmntsContext ctx) {
        quorum.Libraries.Language.Compile.Context.ClassStatementsContext context = 
                new quorum.Libraries.Language.Compile.Context.ClassStatementsContext();
        setLocation(ctx, context);
        listener.ExitClassStatements(context);
    }

    @Override
    public void enterQualified_name(QuorumParser.Qualified_nameContext ctx) {
        quorum.Libraries.Language.Compile.Context.QualifiedNameContext context = 
                new quorum.Libraries.Language.Compile.Context.QualifiedNameContext();
        setLocation(ctx, context);
        listener.EnterQualifiedName(context);
    }

    @Override
    public void exitQualified_name(QuorumParser.Qualified_nameContext ctx) {
        quorum.Libraries.Language.Compile.Context.QualifiedNameContext context = 
                new quorum.Libraries.Language.Compile.Context.QualifiedNameContext();
        setLocation(ctx, context);
        listener.ExitQualifiedName(context);
    }

    @Override
    public void enterLoop_statement(QuorumParser.Loop_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.LoopContext context = 
                new quorum.Libraries.Language.Compile.Context.LoopContext();
        setLocation(ctx, context);
        if(ctx.TIMES() != null) {
            context.isTimes = true;
        } else if (ctx.WHILE() != null) {
            context.isWhile = true;
        } else {
            context.isUntil = true;
        }
        listener.EnterLoopStatement(context);
    }

    @Override
    public void exitLoop_statement(QuorumParser.Loop_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.LoopContext context = 
                new quorum.Libraries.Language.Compile.Context.LoopContext();
        setLocation(ctx, context);
        listener.ExitLoopStatement(context);
    }

    @Override
    public void enterInherit_stmnts(QuorumParser.Inherit_stmntsContext ctx) {
        quorum.Libraries.Language.Compile.Context.InheritStatementContext context = 
                new quorum.Libraries.Language.Compile.Context.InheritStatementContext();
        setLocation(ctx, context);
        listener.EnterInheritStatements(context);
    }

    @Override
    public void exitInherit_stmnts(QuorumParser.Inherit_stmntsContext ctx) {
        quorum.Libraries.Language.Compile.Context.InheritStatementContext context = 
                new quorum.Libraries.Language.Compile.Context.InheritStatementContext();
        setLocation(ctx, context);
        listener.ExitInheritStatements(context);
    }

    @Override
    public void enterDetect_parameter(QuorumParser.Detect_parameterContext ctx) {
        quorum.Libraries.Language.Compile.Context.DetectParameterContext context = 
                new quorum.Libraries.Language.Compile.Context.DetectParameterContext();
        setLocation(ctx, context);
        listener.EnterDetectParameter(context);
    }

    @Override
    public void exitDetect_parameter(QuorumParser.Detect_parameterContext ctx) {
        quorum.Libraries.Language.Compile.Context.DetectParameterContext context = 
                new quorum.Libraries.Language.Compile.Context.DetectParameterContext();
        setLocation(ctx, context);
        listener.ExitDetectParameter(context);
    }

    @Override
    public void enterAddition(QuorumParser.AdditionContext ctx) {
        quorum.Libraries.Language.Compile.Context.AdditionContext context = new quorum.Libraries.Language.Compile.Context.AdditionContext();
        setLocation(ctx, context);
        if(ctx.PLUS() != null) {
            context.isPlus = true;
        } else {
            context.isPlus = false;
        }
        listener.EnterAddition(context);
    }

    @Override
    public void exitAddition(QuorumParser.AdditionContext ctx) {
        quorum.Libraries.Language.Compile.Context.AdditionContext context = new quorum.Libraries.Language.Compile.Context.AdditionContext();
        setLocation(ctx, context);
        if(ctx.PLUS() != null) {
            context.isPlus = true;
        } else {
            context.isPlus = false;
        }
        listener.ExitAddition(context);
    }

    @Override
    public void enterNo_class_stmnts(QuorumParser.No_class_stmntsContext ctx) {
        quorum.Libraries.Language.Compile.Context.NoClassStatementsContext context = 
                new quorum.Libraries.Language.Compile.Context.NoClassStatementsContext();
        setLocation(ctx, context);
        listener.EnterNoClassStatements(context);
    }

    @Override
    public void exitNo_class_stmnts(QuorumParser.No_class_stmntsContext ctx) {
        quorum.Libraries.Language.Compile.Context.NoClassStatementsContext context = 
                new quorum.Libraries.Language.Compile.Context.NoClassStatementsContext();
        setLocation(ctx, context);
        listener.ExitNoClassStatements(context);
    }

    @Override
    public void enterReturn_statement(QuorumParser.Return_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.ReturnContext context = 
                new quorum.Libraries.Language.Compile.Context.ReturnContext();
        setLocation(ctx, context);
        listener.EnterReturnStatement(context);
    }

    @Override
    public void exitReturn_statement(QuorumParser.Return_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.ReturnContext context = 
                new quorum.Libraries.Language.Compile.Context.ReturnContext();
        setLocation(ctx, context);
        listener.ExitReturnStatement(context);
    }

    @Override
    public void enterInput(QuorumParser.InputContext ctx) {
        quorum.Libraries.Language.Compile.Context.InputContext context = 
                new quorum.Libraries.Language.Compile.Context.InputContext();
        setLocation(ctx, context);
        listener.EnterInput(context);
    }

    @Override
    public void exitInput(QuorumParser.InputContext ctx) {
        quorum.Libraries.Language.Compile.Context.InputContext context = 
                new quorum.Libraries.Language.Compile.Context.InputContext();
        setLocation(ctx, context);
        listener.ExitInput(context);
    }

    @Override
    public void enterMultiplication(QuorumParser.MultiplicationContext ctx) {
        quorum.Libraries.Language.Compile.Context.MultiplicationContext context = 
                new quorum.Libraries.Language.Compile.Context.MultiplicationContext();
        setLocation(ctx, context);
        if(ctx.MULTIPLY()!= null) {
            context.isMultiply = true;
        } else if (ctx.DIVIDE() != null){
            context.isDivide = true;
        } else {
            context.isModulus = true;
        }
        listener.EnterMultiplication(context);
    }

    @Override
    public void exitMultiplication(QuorumParser.MultiplicationContext ctx) {
        quorum.Libraries.Language.Compile.Context.MultiplicationContext context = 
                new quorum.Libraries.Language.Compile.Context.MultiplicationContext();
        setLocation(ctx, context);
        if(ctx.MULTIPLY()!= null) {
            context.isMultiply = true;
        } else if (ctx.DIVIDE() != null){
            context.isDivide = true;
        } else {
            context.isModulus = true;
        }
        listener.ExitMultiplication(context);
    }

    @Override
    public void enterMinus(QuorumParser.MinusContext ctx) {
        quorum.Libraries.Language.Compile.Context.UnaryMinusContext context = 
                new quorum.Libraries.Language.Compile.Context.UnaryMinusContext();
        setLocation(ctx, context);
        listener.EnterMinus(context);
    }

    @Override
    public void exitMinus(QuorumParser.MinusContext ctx) {
        quorum.Libraries.Language.Compile.Context.UnaryMinusContext context = 
                new quorum.Libraries.Language.Compile.Context.UnaryMinusContext();
        setLocation(ctx, context);
        listener.ExitMinus(context);
    }

    @Override
    public void enterBoolean(QuorumParser.BooleanContext ctx) {
        boolean val = Boolean.parseBoolean(ctx.BOOLEAN().getText());
        BooleanContext context = new BooleanContext();
        context.Set$Libraries$Language$Compile$Context$BooleanContext$value(val);
        setLocation(ctx, context);
        listener.EnterBoolean(context);
    }

    @Override
    public void exitBoolean(QuorumParser.BooleanContext ctx) {
        boolean val = Boolean.parseBoolean(ctx.BOOLEAN().getText());
        BooleanContext context = new BooleanContext();
        context.Set$Libraries$Language$Compile$Context$BooleanContext$value(val);
        setLocation(ctx, context);
        listener.ExitBoolean(context);
    }

    @Override
    public void enterAlert_statement(QuorumParser.Alert_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.AlertContext context = 
                new quorum.Libraries.Language.Compile.Context.AlertContext();
        setLocation(ctx, context);
        listener.EnterAlertStatement(context);
    }

    @Override
    public void exitAlert_statement(QuorumParser.Alert_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.AlertContext context = 
                new quorum.Libraries.Language.Compile.Context.AlertContext();
        setLocation(ctx, context);
        listener.ExitAlertStatement(context);
    }

    @Override
    public void enterReference(QuorumParser.ReferenceContext ctx) {
        quorum.Libraries.Language.Compile.Context.UseContext context = 
                new quorum.Libraries.Language.Compile.Context.UseContext();
        QuorumParser.Qualified_nameContext name = ctx.name;
        List<TerminalNode> ID = name.ID();
        for (int i = 0; i < ID.size(); i++) {
            context.name.Add(ID.get(i).getText());
        }
        setLocation(ctx, context);
        listener.EnterUse(context);
    }

    @Override
    public void exitReference(QuorumParser.ReferenceContext ctx) {
        quorum.Libraries.Language.Compile.Context.UseContext context = 
                new quorum.Libraries.Language.Compile.Context.UseContext();
        QuorumParser.Qualified_nameContext name = ctx.name;
        List<TerminalNode> ID = name.ID();
        for (int i = 0; i < ID.size(); i++) {
            context.name.Add(ID.get(i).getText());
        }
        setLocation(ctx, context);
        listener.ExitUse(context);
    }

    @Override
    public void enterNot(QuorumParser.NotContext ctx) {
        quorum.Libraries.Language.Compile.Context.NotContext context = 
                new quorum.Libraries.Language.Compile.Context.NotContext();
        setLocation(ctx, context);
        listener.EnterNot(context);
    }

    @Override
    public void exitNot(QuorumParser.NotContext ctx) {
        quorum.Libraries.Language.Compile.Context.NotContext context = 
                new quorum.Libraries.Language.Compile.Context.NotContext();
        setLocation(ctx, context);
        listener.ExitNot(context);
    }

    @Override
    public void enterGeneric_declaration(QuorumParser.Generic_declarationContext ctx) {
        quorum.Libraries.Language.Compile.Context.GenericDeclarationContext context = 
                new quorum.Libraries.Language.Compile.Context.GenericDeclarationContext();
        List<Token> ids = ctx.ids;
        Iterator<Token> tokens = ids.iterator();
        while (tokens.hasNext()) {
            Token next = tokens.next();
            String value = next.getText();
            Text text = new Text();
            text.value = value;
            context.tokens.Add(text);
        }
        setLocation(ctx, context);
        listener.EnterGenericDeclaration(context);
    }

    @Override
    public void exitGeneric_declaration(QuorumParser.Generic_declarationContext ctx) {
        quorum.Libraries.Language.Compile.Context.GenericDeclarationContext context = 
                new quorum.Libraries.Language.Compile.Context.GenericDeclarationContext();
        List<Token> ids = ctx.ids;
        Iterator<Token> tokens = ids.iterator();
        while (tokens.hasNext()) {
            Token next = tokens.next();
            String value = next.getText();
            Text text = new Text();
            text.value = value;
            context.tokens.Add(text);
        }
        setLocation(ctx, context);
        listener.ExitGenericDeclaration(context);
    }

    @Override
    public void enterClass_type(QuorumParser.Class_typeContext ctx) {
        quorum.Libraries.Language.Compile.Context.ClassTypeContext context = 
                new quorum.Libraries.Language.Compile.Context.ClassTypeContext();
        setLocation(ctx, context);
        listener.EnterClassType(context);
    }

    @Override
    public void exitClass_type(QuorumParser.Class_typeContext ctx) {
        quorum.Libraries.Language.Compile.Context.ClassTypeContext context = 
                new quorum.Libraries.Language.Compile.Context.ClassTypeContext();
        setLocation(ctx, context);
        listener.ExitClassType(context);
    }

    @Override
    public void enterEquals(QuorumParser.EqualsContext ctx) {
        quorum.Libraries.Language.Compile.Context.EqualsContext context = new quorum.Libraries.Language.Compile.Context.EqualsContext();
        setLocation(ctx, context);
        if(ctx.EQUALITY() != null) {
            context.equalsTo = true;
        } else {
            context.equalsTo = false;
        }
        listener.EnterEquals(context);
    }

    @Override
    public void exitEquals(QuorumParser.EqualsContext ctx) {
        quorum.Libraries.Language.Compile.Context.EqualsContext context = new quorum.Libraries.Language.Compile.Context.EqualsContext();
        setLocation(ctx, context);
        if(ctx.EQUALITY() != null) {
            context.equalsTo = true;
        } else {
            context.equalsTo = false;
        }
        listener.ExitEquals(context);
    }

    @Override
    public void enterGreater(QuorumParser.GreaterContext ctx) {
        quorum.Libraries.Language.Compile.Context.InequalityContext context = 
                new quorum.Libraries.Language.Compile.Context.InequalityContext();
        setLocation(ctx, context);
        if(ctx.GREATER() != null) {
            context.isGreater = true;
        } else if (ctx.GREATER_EQUAL() != null) {
            context.isGreaterEquals = true;
        } else if (ctx.LESS() != null) {
            context.isLess = true;
        } else {
            context.isLessEquals = true;
        }
        listener.EnterInequality(context);
    }

    @Override
    public void exitGreater(QuorumParser.GreaterContext ctx) {
        quorum.Libraries.Language.Compile.Context.InequalityContext context = 
                new quorum.Libraries.Language.Compile.Context.InequalityContext();
        setLocation(ctx, context);
        if(ctx.GREATER() != null) {
            context.isGreater = true;
        } else if (ctx.GREATER_EQUAL() != null) {
            context.isGreaterEquals = true;
        } else if (ctx.LESS() != null) {
            context.isLess = true;
        } else {
            context.isLessEquals = true;
        }
        listener.ExitInequality(context);
    }

    @Override
    public void enterInteger(QuorumParser.IntegerContext ctx) {
        int val = Integer.parseInt(ctx.INT().getText());
        IntegerContext context = new IntegerContext();
        context.Set$Libraries$Language$Compile$Context$IntegerContext$value(val);
        setLocation(ctx, context);
        listener.EnterInteger(context);
    }

    private void setLocation(ParserRuleContext context,
            quorum.Libraries.Language.Compile.Context.ParseContext$Interface quorumContext) {
        quorum.Libraries.Language.Compile.Location$Interface location = quorumContext.GetLocation();
        location.SetLineNumber(context.getStart().getLine());
        location.SetLineNumberEnd(context.getStop().getLine());
        location.SetColumnNumber(context.getStart().getCharPositionInLine());
        location.SetColumnNumberEnd(context.getStop().getCharPositionInLine());
        location.SetIndex(context.getStart().getStartIndex());
        location.SetIndex(context.getStop().getStartIndex());
        location.SetFile(file);
    }

    @Override
    public void exitInteger(QuorumParser.IntegerContext ctx) {
        int val = Integer.parseInt(ctx.INT().getText());
        IntegerContext context = new IntegerContext();
        context.Set$Libraries$Language$Compile$Context$IntegerContext$value(val);
        setLocation(ctx, context);
        listener.ExitInteger(context);
    }

    @Override
    public void enterPackage_rule(QuorumParser.Package_ruleContext ctx) {
        PackageContext context = new PackageContext();
        QuorumParser.Qualified_nameContext name = ctx.name;
        List<TerminalNode> ID = name.ID();
        for (int i = 0; i < ID.size(); i++) {
            context.name.Add(ID.get(i).getText());
        }
        setLocation(ctx, context);
        listener.EnterPackageRule(context);
    }

    @Override
    public void exitPackage_rule(QuorumParser.Package_ruleContext ctx) {
        PackageContext context = new PackageContext();
        QuorumParser.Qualified_nameContext name = ctx.name;
        List<TerminalNode> ID = name.ID();
        for (int i = 0; i < ID.size(); i++) {
            context.name.Add(ID.get(i).getText());
        }
        setLocation(ctx, context);
        listener.ExitPackageRule(context);
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

    /**
     * @return the file
     */
    public File$Interface getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File$Interface file) {
        this.file = file;
    }

    @Override
    public void enterMethod_shared(QuorumParser.Method_sharedContext ctx) {
       ActionContext context = new ActionContext();
        setLocation(ctx, context);
        List<QuorumParser.Formal_parameterContext> params = ctx.formal_parameter();

        Iterator<QuorumParser.Formal_parameterContext> it = params.iterator();
        while(it.hasNext()) {
            QuorumParser.Formal_parameterContext next = it.next();
            Type type = next.assignment_declaration().type;
            Variable variable = new Variable();
            variable.SetName(next.ID().getText());
            variable.SetType(type);
            variable.SetIsParameter(true);
            context.parameters.Add(variable);
        }
        
        context.actionName = ctx.ID().getText();
        if(ctx.RETURNS() != null) {
            context.returnType = ctx.return_type.type;
        } else {
            Type type = new Type();
            type.SetToVoid();
            context.returnType = type;
        }
        ctx.actionContext = context;
        listener.EnterActionHeader(context);
    }

    @Override
    public void exitMethod_shared(QuorumParser.Method_sharedContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        List<QuorumParser.Formal_parameterContext> params = ctx.formal_parameter();

        Iterator<QuorumParser.Formal_parameterContext> it = params.iterator();
        while(it.hasNext()) {
            QuorumParser.Formal_parameterContext next = it.next();
            Type type = next.assignment_declaration().type;
            Variable variable = new Variable();
            variable.SetName(next.ID().getText());
            variable.SetType(type);
            variable.SetIsParameter(true);
            context.parameters.Add(variable);
        }
        
        context.actionName = ctx.ID().getText();
        if(ctx.RETURNS() != null) {
            context.returnType = ctx.return_type.type;
        } else {
            Type type = new Type();
            type.SetToVoid();
            context.returnType = type;
        }
        ctx.actionContext = context;
        listener.ExitActionHeader(context);
    }

    @Override
    public void enterNativeAction(QuorumParser.NativeActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        listener.EnterSystemAction(context);
    }

    @Override
    public void exitNativeAction(QuorumParser.NativeActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        listener.ExitSystemAction(context);
    }

    @Override
    public void enterAction(QuorumParser.ActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        listener.EnterAction(context);
    }

    @Override
    public void exitAction(QuorumParser.ActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        listener.ExitAction(context);
    }

    @Override
    public void enterBlueprintAction(QuorumParser.BlueprintActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        listener.EnterBlueprintAction(context);
    }

    @Override
    public void exitBlueprintAction(QuorumParser.BlueprintActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        listener.ExitBlueprintAction(context);
    }

    @Override
    public void enterConstructor(QuorumParser.ConstructorContext ctx) {
        quorum.Libraries.Language.Compile.Context.ConstructorContext context = 
                new quorum.Libraries.Language.Compile.Context.ConstructorContext();
        setLocation(ctx, context);
        listener.EnterConstructor(context);
    }

    @Override
    public void exitConstructor(QuorumParser.ConstructorContext ctx) {
        quorum.Libraries.Language.Compile.Context.ConstructorContext context = 
                new quorum.Libraries.Language.Compile.Context.ConstructorContext();
        setLocation(ctx, context);
        listener.ExitConstructor(context);
    }

    @Override
    public void enterNumberAssignmentDeclaration(QuorumParser.NumberAssignmentDeclarationContext ctx) {
        AssignmentDeclaractionContext context = new AssignmentDeclaractionContext();
        setLocation(ctx, context);
        Type type = new Type();
        type.SetToNumber();
        ctx.type = type;
    }

    @Override
    public void exitNumberAssignmentDeclaration(QuorumParser.NumberAssignmentDeclarationContext ctx) {
        Type type = new Type();
        type.SetToNumber();
        ctx.type = type;
    }

    @Override
    public void enterIntegerAssignmentDeclaration(QuorumParser.IntegerAssignmentDeclarationContext ctx) {
        Type type = new Type();
        type.SetToInteger();
        ctx.type = type;
    }

    @Override
    public void exitIntegerAssignmentDeclaration(QuorumParser.IntegerAssignmentDeclarationContext ctx) {
        Type type = new Type();
        type.SetToInteger();
        ctx.type = type;
    }

    @Override
    public void enterBooleanAssignmentDeclaration(QuorumParser.BooleanAssignmentDeclarationContext ctx) {
        Type type = new Type();
        type.SetToBoolean();
        ctx.type = type;
    }

    @Override
    public void exitBooleanAssignmentDeclaration(QuorumParser.BooleanAssignmentDeclarationContext ctx) {
        Type type = new Type();
        type.SetToBoolean();
        ctx.type = type;
    }

    @Override
    public void enterGenericAssignmentDeclaration(QuorumParser.GenericAssignmentDeclarationContext ctx) {
        Type type = new Type();

        ctx.type = type;
    }

    @Override
    public void exitGenericAssignmentDeclaration(QuorumParser.GenericAssignmentDeclarationContext ctx) {
        Type type = new Type();
        ctx.type = type;
    }

    @Override
    public void enterTextAssignmentDeclaration(QuorumParser.TextAssignmentDeclarationContext ctx) {
        Type type = new Type();
        type.SetToText();
        ctx.type = type;
    }

    @Override
    public void exitTextAssignmentDeclaration(QuorumParser.TextAssignmentDeclarationContext ctx) {
        Type type = new Type();
        type.SetToText();
        ctx.type = type;
    }

    @Override
    public void enterNormalAssignment(QuorumParser.NormalAssignmentContext ctx) {
        NormalAssignmentContext context = new NormalAssignmentContext();
        setLocation(ctx, context);
        context.name = ctx.name.getText();
        listener.EnterNormalAssignment(context);
    }

    @Override
    public void exitNormalAssignment(QuorumParser.NormalAssignmentContext ctx) {
        NormalAssignmentContext context = new NormalAssignmentContext();
        setLocation(ctx, context);
        TerminalNode constant = ctx.CONSTANT();
        if(constant != null) {
            context.isConstant = true;
        }
        
        context.name = ctx.name.getText();
        if(ctx.modifier != null) {
            TerminalNode PUBLIC = ctx.modifier.PUBLIC();
            if(PUBLIC != null) {
                context.isPublic = true;
                context.isPrivate = false;
                context.hasModifier = true;
            }
            TerminalNode PRIVATE = ctx.modifier.PRIVATE();
            if(PRIVATE != null) {
                context.hasModifier = true;
            }
        }
        if(ctx.assignment_declaration() != null) {
            context.leftHandSide = ctx.assignment_declaration().type;
        }
        
        if(ctx.rhs == null) {
            context.hasRightHandSide = false;
        } else {
            context.hasRightHandSide = true;
        }
        listener.ExitNormalAssignment(context);
    }

    @Override
    public void enterParentAssignment(QuorumParser.ParentAssignmentContext ctx) {
        ParentAssignmentContext context = new ParentAssignmentContext();
        setLocation(ctx, context);
        context.parentName = ctx.parent.qualifiedName;
        context.name = ctx.name.getText();
        listener.EnterParentAssignment(context);
    }

    @Override
    public void exitParentAssignment(QuorumParser.ParentAssignmentContext ctx) {
        ParentAssignmentContext context = new ParentAssignmentContext();
        setLocation(ctx, context);
        context.parentName = ctx.parent.qualifiedName;
        context.name = ctx.name.getText();
        listener.ExitParentAssignment(context);
    }

    @Override
    public void enterObjectAssignment(QuorumParser.ObjectAssignmentContext ctx) {
        ObjectAssignmentContext context = new ObjectAssignmentContext();
        setLocation(ctx, context);
        context.object = ctx.object.getText();
        context.parentName = ctx.parent.qualifiedName;
        context.name = ctx.name.getText();
        listener.EnterObjectAssignment(context);
    }

    @Override
    public void exitObjectAssignment(QuorumParser.ObjectAssignmentContext ctx) {
        ObjectAssignmentContext context = new ObjectAssignmentContext();
        setLocation(ctx, context);
        context.object = ctx.object.getText();
        context.parentName = ctx.parent.qualifiedName;
        context.name = ctx.name.getText();
        listener.ExitObjectAssignment(context);
    }

    @Override
    public void enterNoTypeAssignment(QuorumParser.NoTypeAssignmentContext ctx) {
        NoTypeAssignmentContext context = new NoTypeAssignmentContext();
        setLocation(ctx, context);
        context.name = ctx.name.getText();
        listener.EnterNoTypeAssignment(context);
    }

    @Override
    public void exitNoTypeAssignment(QuorumParser.NoTypeAssignmentContext ctx) {
        NoTypeAssignmentContext context = new NoTypeAssignmentContext();
        setLocation(ctx, context);
        context.name = ctx.name.getText();
        if(ctx.ME() != null) {
            context.isField = true;
            context.hasMe = true;
        }
        listener.ExitNoTypeAssignment(context);
    }

    @Override
    public void enterVariableFunctionCall(QuorumParser.VariableFunctionCallContext ctx) {
        VariableFunctionCallContext context = new VariableFunctionCallContext();
        setLocation(ctx, context);
        boolean hasMe = ctx.ME() != null;
        context.hasMe = hasMe;
//        List<QuorumParser.Action_callContext> calls = ctx.action_call();
//        if(calls != null) {
//            if(calls.size() == 1) {
//                QuorumParser.Action_callContext ctx2 = calls.get(0);
//                context.name = ctx2.ID().getText();
//                if(ctx2.LEFT_PAREN() != null) {
//                    context.isActionCall = true;
//                }
//            }
//        }
//        QuorumParser.Parent_callContext parent = ctx.parent_call();
//        if(parent != null) {
//            context.isParentCall = true;
//        }
        listener.EnterVariableFunctionCall(context);
    }

    @Override
    public void exitVariableFunctionCall(QuorumParser.VariableFunctionCallContext ctx) {
        VariableFunctionCallContext context = new VariableFunctionCallContext();
        setLocation(ctx, context);
        listener.ExitVariableFunctionCall(context);
    }

    @Override
    public void enterParentVariableFunctionCall(QuorumParser.ParentVariableFunctionCallContext ctx) {
        ParentVariableFunctionCallContext context = new ParentVariableFunctionCallContext();
        setLocation(ctx, context);
        listener.EnterParentVariableFunctionCall(context);
    }

    @Override
    public void exitParentVariableFunctionCall(QuorumParser.ParentVariableFunctionCallContext ctx) {
        ParentVariableFunctionCallContext context = new ParentVariableFunctionCallContext();
        setLocation(ctx, context);
        listener.ExitParentVariableFunctionCall(context);
    }

    @Override
    public void enterAction_call(QuorumParser.Action_callContext ctx) {
        ActionCallContext context = new ActionCallContext();
        setLocation(ctx, context);
        String name = ctx.var.getText();
        boolean isActionCall = ctx.LEFT_PAREN() != null;
        context.name = name;
        context.isActionCall = isActionCall;
        
        listener.EnterActionCall(context);
    }

    @Override
    public void exitAction_call(QuorumParser.Action_callContext ctx) {
        ActionCallContext context = new ActionCallContext();
        setLocation(ctx, context);
        String name = ctx.var.getText();
        boolean isActionCall = ctx.LEFT_PAREN() != null;
        context.name = name;
        context.isActionCall = isActionCall;

        listener.ExitActionCall(context);
    }

    @Override
    public void enterParent_call(QuorumParser.Parent_callContext ctx) {
        ParentCallContext context = new ParentCallContext();
        setLocation(ctx, context);
        listener.EnterParentCall(context);
    }

    @Override
    public void exitParent_call(QuorumParser.Parent_callContext ctx) {
        ParentCallContext context = new ParentCallContext();
        setLocation(ctx, context);
        listener.ExitParentCall(context);
    }
}
