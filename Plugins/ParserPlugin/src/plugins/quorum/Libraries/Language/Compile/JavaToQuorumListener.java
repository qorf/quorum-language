/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Compile;

import java.util.Iterator;
import java.util.List;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import quorum.Libraries.Language.Compile.CompilerError;
import quorum.Libraries.Language.Compile.CompilerErrorManager_;
import quorum.Libraries.Language.Compile.CompilerErrorType;
import quorum.Libraries.Language.Compile.Context.*;
import quorum.Libraries.Language.Compile.Location;
import quorum.Libraries.Language.Compile.Location_;
import quorum.Libraries.Language.Compile.QualifiedName;
import quorum.Libraries.Language.Compile.QuorumSourceListener_;
import quorum.Libraries.Language.Compile.Symbol.Type;
import quorum.Libraries.Language.Compile.Symbol.Variable;
import quorum.Libraries.Language.Compile.Symbol.Variable_;
import quorum.Libraries.Language.Types.Text;
import quorum.Libraries.System.File_;

/**
 *
 * @author stefika
 */
public class JavaToQuorumListener implements QuorumListener {

    private QuorumSourceListener_ listener;
    private File_ file;
    private CommonTokenStream tokens;
    
    @Override
    public void enterDecimal(QuorumParser.DecimalContext ctx) {
        double value = 0.0;
        NumberContext context = new NumberContext();
        setLocation(ctx, context);
        try {
            value = Double.parseDouble(ctx.DECIMAL().getText());
        } catch(NumberFormatException e) {
            CompilerErrorManager_ manager = listener.GetCompilerErrorManager();
            CompilerError error = new CompilerError();
            CompilerErrorType type = new CompilerErrorType();
            type.SetCurrentType(type.PARSER_NO_VIABLE_ALTERNATIVE);
            error.SetCompilerErrorType(type);
            error.SetLocation(context.GetLocation());
            error.SetErrorMessage("The value of " + ctx.DECIMAL().getText() + " does not fit in a number. " +
            "The range of a number is from " + Double.MIN_VALUE + " to " + Double.MAX_VALUE + ".");
            manager.Add(error);
        }
        
        context.Set_Libraries_Language_Compile_Context_NumberContext__value_(value);
        listener.EnterNumber(context);
    }

    @Override
    public void exitDecimal(QuorumParser.DecimalContext ctx) {
        double value = 0.0;
        NumberContext context = new NumberContext();
        setLocation(ctx, context);
        try {
            value = Double.parseDouble(ctx.DECIMAL().getText());
        } catch(NumberFormatException e) {
        }
        
        context.Set_Libraries_Language_Compile_Context_NumberContext__value_(value);
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
        if(ctx.type != null) {
            context.type = ctx.type.type;
        }
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
        if(ctx.ID() != null) {
            context.className = ctx.ID().getText();
            setLocation(ctx.ID().getSymbol(), context.classNameLocation);
        }
        setLocation(ctx, context);
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
        listener.EnterFullClassDeclaration(context);
    }

    @Override
    public void exitFullClassDeclaration(@NotNull QuorumParser.FullClassDeclarationContext ctx) {
        FullClassDeclarationContext context = new FullClassDeclarationContext();
        if(ctx.ID() != null) {
            context.className = ctx.ID().getText();
            setLocation(ctx.ID().getSymbol(), context.classNameLocation);
        }
        setLocation(ctx, context);
        
        QualifiedName name = new QualifiedName();
        QuorumParser.Generic_declarationContext generic = ctx.generic_declaration();
        
        context.name = name;
        TerminalNode ID = ctx.ID();
        if(ID != null) {
            name.Add(ID.getText());
        }
        
        if(generic != null) {
            List<Token> tokenList = generic.ids;
            Iterator<Token> tokens = tokenList.iterator();
            while(tokens.hasNext()) {
                Token token = tokens.next();
                String value = token.getText();
                context.name.AddGeneric(value);
            }
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, false);
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
    public void enterFormal_parameter(QuorumParser.Formal_parameterContext ctx) {
        FormalParameterContext context = new FormalParameterContext();
        setLocation(ctx, context);
        TerminalNode ID = ctx.ID();
        if(ID != null) {
            context.name = ID.getText();
        }
        if(ctx.assignment_declaration() != null) {
            QuorumParser.Assignment_declarationContext decl = ctx.assignment_declaration();
            if(decl.start != null) {
                setLocation(decl.start, context.typeLocation);
            }
        }
        
        listener.EnterFormalParameter(context);
    }

    @Override
    public void exitFormal_parameter(QuorumParser.Formal_parameterContext ctx) {
        FormalParameterContext context = new FormalParameterContext();
        setLocation(ctx, context);
        TerminalNode ID = ctx.ID();
        if(ID != null) {
            context.name = ID.getText();
        }
        context.type = ctx.assignment_declaration().type;
        if(ctx.assignment_declaration() != null) {
            QuorumParser.Assignment_declarationContext decl = ctx.assignment_declaration();
            if(decl.start != null) {
                setLocation(decl.start, context.typeLocation);
            }
        }

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
        context.Set_Libraries_Language_Compile_Context_TextContext__value_(val);
        setLocation(ctx, context);
        listener.EnterText(context);
    }

    @Override
    public void exitString(QuorumParser.StringContext ctx) {
        String val = ctx.STRING().getText();
        val = val.substring(1, val.length() - 1);
        TextContext context = new TextContext();
        context.Set_Libraries_Language_Compile_Context_TextContext__value_(val);
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
        quorum.Libraries.Language.Compile.Context.IsContext_ context = 
                new quorum.Libraries.Language.Compile.Context.IsContext();
        //QualifiedName name = ctx.name.qualified_name().qualifiedName;
        //context.Set$Libraries$Language$Compile$Context$InheritStatementContext$name(name);
        setLocation(ctx, context);
        listener.EnterIs(context);
    }

    @Override
    public void exitInherits(QuorumParser.InheritsContext ctx) {
        quorum.Libraries.Language.Compile.Context.IsContext_ context = 
                new quorum.Libraries.Language.Compile.Context.IsContext();
        
        QualifiedName name = Convert(ctx.name.qualified_name());
        context.Set_Libraries_Language_Compile_Context_IsContext__name_(name);
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
        quorum.Libraries.Language.Compile.Context.InheritStatementsContext context = 
                new quorum.Libraries.Language.Compile.Context.InheritStatementsContext();
        setLocation(ctx, context);
        listener.EnterInheritStatements(context);
    }

    @Override
    public void exitInherit_stmnts(QuorumParser.Inherit_stmntsContext ctx) {
        quorum.Libraries.Language.Compile.Context.InheritStatementsContext context = 
                new quorum.Libraries.Language.Compile.Context.InheritStatementsContext();
        setLocation(ctx, context);
        listener.ExitInheritStatements(context);
    }
    
    @Override
    public void enterInherit_stmt(QuorumParser.Inherit_stmtContext ctx) {
        quorum.Libraries.Language.Compile.Context.InheritStatementContext context = 
                new quorum.Libraries.Language.Compile.Context.InheritStatementContext();
        
        setLocation(ctx, context);
        listener.EnterInheritStatement(context);
    }

    @Override
    public void exitInherit_stmt(QuorumParser.Inherit_stmtContext ctx) {
        quorum.Libraries.Language.Compile.Context.InheritStatementContext context = 
                new quorum.Libraries.Language.Compile.Context.InheritStatementContext();
        
        //first get the name
        QuorumParser.Qualified_nameContext name = ctx.qualified_name();        
        context.name = Convert(name);
        setLocation(name.start, name.stop, context.name);
        
        //add generics if there are any.
        QuorumParser.Generic_statementContext generic = ctx.generic_statement();
        if(generic != null) {
            List<QuorumParser.Assignment_declarationContext> decls = generic.assignment_declaration();
            if(decls != null) {
                Iterator<QuorumParser.Assignment_declarationContext> it = decls.iterator();
                while(it.hasNext()) {
                    QuorumParser.Assignment_declarationContext next = it.next();
                    Type type = next.type;
                    context.generics.Add(type);
                }
            }
        }
        setLocation(ctx, context);
        listener.ExitInheritStatement(context);
    }
    
    public QualifiedName Convert(QuorumParser.Qualified_nameContext next) {
        QualifiedName name = new QualifiedName();
        if(next != null) {
            List<Token> ids = next.ids;
            Iterator<Token> tokens = ids.iterator();
            while(tokens.hasNext()) {
                Token token = tokens.next();
                String value = token.getText();
                name.Add(value);
            }
        }
        return name;
    }

     @Override
    public void enterAlways_statement(QuorumParser.Always_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.AlwaysStatementContext context = 
                new quorum.Libraries.Language.Compile.Context.AlwaysStatementContext();
        setLocation(ctx, context);
        listener.EnterAlwaysStatement(context);
    }

    @Override
    public void exitAlways_statement(QuorumParser.Always_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.AlwaysStatementContext context = 
                new quorum.Libraries.Language.Compile.Context.AlwaysStatementContext();
        setLocation(ctx, context);
        listener.ExitAlwaysStatement(context);
    }

    @Override
    public void enterDetect_statement(QuorumParser.Detect_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.DetectStatementContext context = 
                new quorum.Libraries.Language.Compile.Context.DetectStatementContext();
        setLocation(ctx, context);
        String text = ctx.name.getText();
        context.name = text;
        List<QuorumParser.Qualified_nameContext> names = ctx.qualified_name();
        if(names != null) {
            Iterator<QuorumParser.Qualified_nameContext> iterator = names.iterator();
            while(iterator.hasNext()) {
                QuorumParser.Qualified_nameContext next = iterator.next();
                QualifiedName qn = Convert(next);
                if(qn != null) {
                    context.parents.Add(qn);
                }
            }
        }
        listener.EnterDetectStatement(context);
    }

    @Override
    public void exitDetect_statement(QuorumParser.Detect_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.DetectStatementContext context = 
                new quorum.Libraries.Language.Compile.Context.DetectStatementContext();
        setLocation(ctx, context);
        String text = ctx.name.getText();
        context.name = text;
        List<QuorumParser.Qualified_nameContext> names = ctx.qualified_name();
        if(names != null) {
            Iterator<QuorumParser.Qualified_nameContext> iterator = names.iterator();
            while(iterator.hasNext()) {
                QuorumParser.Qualified_nameContext next = iterator.next();
                QualifiedName qn = Convert(next);
                if(qn != null) {
                    context.parents.Add(qn);
                }
            }
        }
        listener.ExitDetectStatement(context);
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
    public void enterReturn_statement(QuorumParser.Return_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.ReturnContext context = 
                new quorum.Libraries.Language.Compile.Context.ReturnContext();
        
        if(ctx.NOW() != null) {
            context.isReturnNow = true;
        }
        setLocation(ctx, context);
        listener.EnterReturnStatement(context);
    }

    @Override
    public void exitReturn_statement(QuorumParser.Return_statementContext ctx) {
        quorum.Libraries.Language.Compile.Context.ReturnContext context = 
                new quorum.Libraries.Language.Compile.Context.ReturnContext();
        
        if(ctx.NOW() != null) {
            context.isReturnNow = true;
        }
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
    public void enterInputNoParameters(QuorumParser.InputNoParametersContext ctx) {
        quorum.Libraries.Language.Compile.Context.InputContext context = 
                new quorum.Libraries.Language.Compile.Context.InputContext();
        setLocation(ctx, context);
        listener.EnterInputNoParameters(context);
    }

    @Override
    public void exitInputNoParameters(QuorumParser.InputNoParametersContext ctx) {
        quorum.Libraries.Language.Compile.Context.InputContext context = 
                new quorum.Libraries.Language.Compile.Context.InputContext();
        setLocation(ctx, context);
        listener.ExitInputNoParameters(context);
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
        context.Set_Libraries_Language_Compile_Context_BooleanContext__value_(val);
        setLocation(ctx, context);
        listener.EnterBoolean(context);
    }

    @Override
    public void exitBoolean(QuorumParser.BooleanContext ctx) {
        boolean val = Boolean.parseBoolean(ctx.BOOLEAN().getText());
        BooleanContext context = new BooleanContext();
        context.Set_Libraries_Language_Compile_Context_BooleanContext__value_(val);
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
        int value = 0;
        IntegerContext context = new IntegerContext();
        setLocation(ctx, context);
        try {
            value = Integer.parseInt(ctx.INT().getText());
        } catch(NumberFormatException e) {
            CompilerErrorManager_ manager = listener.GetCompilerErrorManager();
            CompilerError error = new CompilerError();
            CompilerErrorType type = new CompilerErrorType();
            type.SetCurrentType(type.PARSER_NO_VIABLE_ALTERNATIVE);
            error.SetCompilerErrorType(type);
            error.SetLocation(context.GetLocation());
            error.SetErrorMessage("The value of " + ctx.INT().getText() + " does not fit in an integer. " +
            "The range of an integer is from " + + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE + ".");
            manager.Add(error);
        }
        
        context.Set_Libraries_Language_Compile_Context_IntegerContext__value_(value);
        listener.EnterInteger(context);
    }

    private void setLocation(ParserRuleContext context,
            quorum.Libraries.Language.Compile.Context.ParseContext_ quorumContext) {
        quorum.Libraries.Language.Compile.Location_ location = quorumContext.GetLocation();
        Token start = context.getStart();
        Token stop = context.getStop();
        
        if(start != null) {
            location.SetLineNumber(start.getLine());
            location.SetColumnNumber(start.getCharPositionInLine());
            location.SetIndex(start.getStartIndex());
        }
        
        if(stop != null) {
            location.SetLineNumberEnd(stop.getLine());
            location.SetColumnNumberEnd(stop.getCharPositionInLine());
            location.SetIndexEnd(stop.getStopIndex());
        }
        
        location.SetFile(file);
    }
    
    private void setLocation(Token token, quorum.Libraries.Language.Compile.Location_ location) {
        if(token != null) {
            location.SetLineNumber(token.getLine());
            location.SetColumnNumber(token.getCharPositionInLine());
            location.SetIndex(token.getStartIndex());
            location.SetLineNumberEnd(token.getLine());
            location.SetColumnNumberEnd(token.getCharPositionInLine());
            location.SetIndexEnd(token.getStopIndex());
        }
        
        location.SetFile(file);
    }
    
    private void setLocation(Token start, Token stop, quorum.Libraries.Language.Compile.Location_ location) {
        if(start != null && stop != null) {
            location.SetLineNumber(start.getLine());
            location.SetColumnNumber(start.getCharPositionInLine());
            location.SetIndex(start.getStartIndex());
            location.SetLineNumberEnd(stop.getLine());
            location.SetColumnNumberEnd(stop.getCharPositionInLine());
            location.SetIndexEnd(stop.getStopIndex());
        }
        
        location.SetFile(file);
    }

    @Override
    public void exitInteger(QuorumParser.IntegerContext ctx) {
        int value = 0;
        IntegerContext context = new IntegerContext();
        setLocation(ctx, context);
        try {
            value = Integer.parseInt(ctx.INT().getText());
        } catch(NumberFormatException e) {
        }
        
        context.Set_Libraries_Language_Compile_Context_IntegerContext__value_(value);
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
    public void enterEveryRule(ParserRuleContext ctx) {
    }
    
    private void fireDocumentationToken(int index, ParserRuleContext ctx, boolean enter) {
        Token comment = findLeftCommentToken(index);
        if(comment != null) {
            DocumentationContext context = new DocumentationContext();
            context.Set_Libraries_Language_Compile_Context_DocumentationContext__document_(comment.getText());
            if(index - 1 > 0) {
                Token endToken = tokens.get(index - 1);
                quorum.Libraries.Language.Compile.Location_ location = context.GetLocation();
                Token start = comment;
                Token stop = endToken;

                if(start != null) {
                    location.SetLineNumber(start.getLine());
                    location.SetColumnNumber(start.getCharPositionInLine());
                    location.SetIndex(start.getStartIndex());
                }

                if(stop != null) {
                    location.SetLineNumberEnd(stop.getLine());
                    location.SetColumnNumberEnd(stop.getCharPositionInLine());
                    location.SetIndexEnd(stop.getStopIndex());
                }

                location.SetFile(file);
            }
            
            if(enter) {
                listener.EnterDocumentation(context);
            } else {
                listener.ExitDocumentation(context);
            }
        }
    }
    
    private Token findLeftCommentToken(int index) {
        Token token = null;
        int i = index;
        while(i > 0) { 
            Token toke = tokens.get(i);
            if(toke.getChannel() == QuorumLexer.COMMENT_CHANNEL) {
                return toke;
            } else if(toke.getChannel() == QuorumLexer.WHITESPACE_CHANNEL) {
                i--;
            } else {
                return null;
            }
        }
        return token;
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    /**
     * @return the listener
     */
    public QuorumSourceListener_ getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    public void setListener(QuorumSourceListener_ listener) {
        this.listener = listener;
    }

    /**
     * @return the file
     */
    public File_ getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File_ file) {
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
            TerminalNode ID = next.ID();
            if(ID != null) {
                variable.SetName(ID.getText());
                setLocation(ID.getSymbol(), variable);
            }
            variable.AddUseLocation(variable.CreateLocationCopy());
            variable.SetType(type);
            variable.SetIsParameter(true);
            if(next.assignment_declaration() != null) {
                QuorumParser.Assignment_declarationContext decl = next.assignment_declaration();
                if(decl.start != null) {
                    Location_ location = new Location();
                    setLocation(decl.start, location);
                    variable.SetTypeLocation(location);
                }
            }
            context.parameters.Add(variable);
        }
        TerminalNode ID = ctx.ID();
        if(ID != null) {
            context.actionName = ID.getText();
            setLocation(ID.getSymbol(), context.actionNameLocation);
        }
        
        if(ctx.RETURNS() != null && ctx.return_type != null) {
            context.returnType = ctx.return_type.type;
            Location_ returnLocation = new Location();
            setLocation(ctx.return_type.start, returnLocation);
            context.returnLocation = returnLocation;
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
            Variable_ variable = new Variable();
            TerminalNode ID = next.ID();
            if(ID != null) {
                variable.SetName(ID.getText());
                setLocation(ID.getSymbol(), variable);
            }
            variable.AddUseLocation(variable.CreateLocationCopy());
            variable.SetType(type);
            variable.SetIsParameter(true);
            if(next.assignment_declaration() != null) {
                QuorumParser.Assignment_declarationContext decl = next.assignment_declaration();
                if(decl.start != null) {
                    Location_ location = new Location();
                    setLocation(decl.start, location);
                    variable.SetTypeLocation(location);
                }
            }
            context.parameters.Add(variable);
        }
        
        TerminalNode ID = ctx.ID();
        if(ID != null) {
            context.actionName = ID.getText();
            setLocation(ID.getSymbol(), context.actionNameLocation);
        }
        if(ctx.RETURNS() != null && ctx.return_type != null) {
            context.returnType = ctx.return_type.type;
            Location_ returnLocation = new Location();
            setLocation(ctx.return_type.start, returnLocation);
            context.returnLocation = returnLocation;
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
        
        if(ctx.modifier != null) {
            TerminalNode PUBLIC = ctx.modifier.PUBLIC();
            if(PUBLIC != null) {
                context.isPublic = true;
                context.isPrivate = false;
            }
            TerminalNode PRIVATE = ctx.modifier.PRIVATE();
            if(PRIVATE != null) {
                context.isPublic = false;
                context.isPrivate = true;
            }
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
        listener.EnterSystemAction(context);
    }

    @Override
    public void exitNativeAction(QuorumParser.NativeActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        
        if(ctx.modifier != null) {
            TerminalNode PUBLIC = ctx.modifier.PUBLIC();
            if(PUBLIC != null) {
                context.isPublic = true;
                context.isPrivate = false;
            }
            TerminalNode PRIVATE = ctx.modifier.PRIVATE();
            if(PRIVATE != null) {
                context.isPublic = false;
                context.isPrivate = true;
            }
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
        listener.ExitSystemAction(context);
    }

    @Override
    public void enterAction(QuorumParser.ActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        if(ctx.modifier != null) {
            TerminalNode PUBLIC = ctx.modifier.PUBLIC();
            if(PUBLIC != null) {
                context.isPublic = true;
                context.isPrivate = false;
            }
            TerminalNode PRIVATE = ctx.modifier.PRIVATE();
            if(PRIVATE != null) {
                context.isPublic = false;
                context.isPrivate = true;
            }
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
        listener.EnterAction(context);
    }

    @Override
    public void exitAction(QuorumParser.ActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        
        if(ctx.modifier != null) {
            TerminalNode PUBLIC = ctx.modifier.PUBLIC();
            if(PUBLIC != null) {
                context.isPublic = true;
                context.isPrivate = false;
            }
            TerminalNode PRIVATE = ctx.modifier.PRIVATE();
            if(PRIVATE != null) {
                context.isPublic = false;
                context.isPrivate = true;
            }
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, false);
        listener.ExitAction(context);
    }

    @Override
    public void enterBlueprintAction(QuorumParser.BlueprintActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        
        if(ctx.modifier != null) {
            TerminalNode PUBLIC = ctx.modifier.PUBLIC();
            if(PUBLIC != null) {
                context.isPublic = true;
                context.isPrivate = false;
            }
            TerminalNode PRIVATE = ctx.modifier.PRIVATE();
            if(PRIVATE != null) {
                context.isPublic = false;
                context.isPrivate = true;
            }
        }
        
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
        listener.EnterBlueprintAction(context);
    }

    @Override
    public void exitBlueprintAction(QuorumParser.BlueprintActionContext ctx) {
        ActionContext context = new ActionContext();
        setLocation(ctx, context);
        
        if(ctx.modifier != null) {
            TerminalNode PUBLIC = ctx.modifier.PUBLIC();
            if(PUBLIC != null) {
                context.isPublic = true;
                context.isPrivate = false;
            }
            TerminalNode PRIVATE = ctx.modifier.PRIVATE();
            if(PRIVATE != null) {
                context.isPublic = false;
                context.isPrivate = true;
            }
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, false);
        listener.ExitBlueprintAction(context);
    }

    @Override
    public void enterConstructor(QuorumParser.ConstructorContext ctx) {
        quorum.Libraries.Language.Compile.Context.ConstructorContext context = 
                new quorum.Libraries.Language.Compile.Context.ConstructorContext();
        setLocation(ctx, context);
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
        listener.EnterConstructor(context);
    }

    @Override
    public void exitConstructor(QuorumParser.ConstructorContext ctx) {
        quorum.Libraries.Language.Compile.Context.ConstructorContext context = 
                new quorum.Libraries.Language.Compile.Context.ConstructorContext();
        setLocation(ctx, context);
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, false);
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
        QuorumParser.Qualified_nameContext qx = ctx.qualified_name();
        QualifiedName name = new QualifiedName();
        List<TerminalNode> ids = qx.ID();
        Iterator<TerminalNode> it = ids.iterator();
        while(it.hasNext()) {
            String value = it.next().getText();
            name.Add(value);
        }
        
        type.SetToObject(name);
        QuorumParser.Generic_statementContext generic_statement = ctx.generic_statement();
        if(generic_statement != null) {
            List<QuorumParser.Assignment_declarationContext> assignments = generic_statement.assignment_declaration();
            Iterator<QuorumParser.Assignment_declarationContext> iterator = assignments.iterator();
            //get all the subtypes, which themselves may have subtypes
            //add them to the current type
            while(iterator.hasNext()) {
                QuorumParser.Assignment_declarationContext next = iterator.next();
                Type t = next.type;
                type.AddGeneric(t);
            }
        }
        
        ctx.type = type;
    }

    @Override
    public void exitGenericAssignmentDeclaration(QuorumParser.GenericAssignmentDeclarationContext ctx) {
        Type type = new Type();
        QuorumParser.Qualified_nameContext qx = ctx.qualified_name();
        QualifiedName name = new QualifiedName();
        List<TerminalNode> ids = qx.ID();
        Iterator<TerminalNode> it = ids.iterator();
        while(it.hasNext()) {
            String value = it.next().getText();
            name.Add(value);
        }
        
        type.SetToObject(name);
        QuorumParser.Generic_statementContext generic_statement = ctx.generic_statement();
        if(generic_statement != null) {
            List<QuorumParser.Assignment_declarationContext> assignments = generic_statement.assignment_declaration();
            Iterator<QuorumParser.Assignment_declarationContext> iterator = assignments.iterator();
            //get all the subtypes, which themselves may have subtypes
            //add them to the current type
            while(iterator.hasNext()) {
                QuorumParser.Assignment_declarationContext next = iterator.next();
                Type t = next.type;
                type.AddGeneric(t);
            }
        }
            
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
        Token name = ctx.name;
        if(name != null) {
            context.name = ctx.name.getText();
            setLocation(name, context.variableLocation);
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
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
        
        Token name = ctx.name;
        if(name != null) {
            context.name = ctx.name.getText();
            setLocation(name, context.variableLocation);            
        }
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
            QuorumParser.Assignment_declarationContext decl = ctx.assignment_declaration();
            if(decl.start != null) {
                setLocation(decl.start, context.typeLocation);
            }
            context.leftHandSide = ctx.assignment_declaration().type;
        }
        
        if(ctx.EQUALITY() == null) {
            context.hasRightHandSide = false;
        } else {
            context.hasRightHandSide = true;
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, false);
        listener.ExitNormalAssignment(context);
    }

    @Override
    public void enterParentAssignment(QuorumParser.ParentAssignmentContext ctx) {
        ParentAssignmentContext context = new ParentAssignmentContext();
        setLocation(ctx, context);
        context.parentName = Convert(ctx.parent);
        if(ctx.name != null) {
            context.name = ctx.name.getText();
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
        listener.EnterParentAssignment(context);
    }

    @Override
    public void exitParentAssignment(QuorumParser.ParentAssignmentContext ctx) {
        ParentAssignmentContext context = new ParentAssignmentContext();
        setLocation(ctx, context);
        
        context.parentName = Convert(ctx.parent);
        if(ctx.name != null) {
            context.name = ctx.name.getText();
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, false);
        listener.ExitParentAssignment(context);
    }

    @Override
    public void enterObjectAssignment(QuorumParser.ObjectAssignmentContext ctx) {
        ObjectAssignmentContext context = new ObjectAssignmentContext();
        setLocation(ctx, context);
        if(ctx.object != null) {
            context.object = ctx.object.getText();
            setLocation(ctx.object, context.objectLocation);
        }
        if(ctx.parent != null) {
            QualifiedName name = Convert(ctx.parent);
            context.parentName = name;
        }
        if(ctx.name != null) {
            context.name = ctx.name.getText();
            setLocation(ctx.name, context.variableLocation);  
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
        listener.EnterObjectAssignment(context);
    }

    @Override
    public void exitObjectAssignment(QuorumParser.ObjectAssignmentContext ctx) {
        ObjectAssignmentContext context = new ObjectAssignmentContext();
        setLocation(ctx, context);
        if(ctx.object != null) {
            context.object = ctx.object.getText();
            setLocation(ctx.object, context.objectLocation);
        }
        if(ctx.parent != null) {
            QualifiedName name = Convert(ctx.parent);
            context.parentName = name;
        }
        if(ctx.name != null) {
            context.name = ctx.name.getText();
            setLocation(ctx.name, context.variableLocation);  
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, false);
        listener.ExitObjectAssignment(context);
    }

    @Override
    public void enterNoTypeAssignment(QuorumParser.NoTypeAssignmentContext ctx) {
        NoTypeAssignmentContext context = new NoTypeAssignmentContext();
        setLocation(ctx, context);
        if(ctx.name != null) {
            context.name = ctx.name.getText();
            setLocation(ctx.name, context.variableLocation);  
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, true);
        listener.EnterNoTypeAssignment(context);
    }

    @Override
    public void exitNoTypeAssignment(QuorumParser.NoTypeAssignmentContext ctx) {
        NoTypeAssignmentContext context = new NoTypeAssignmentContext();
        setLocation(ctx, context);
        if(ctx.name != null) {
            context.name = ctx.name.getText();
            setLocation(ctx.name, context.variableLocation);  
        }
        
        if(ctx.ME() != null) {
            context.isField = true;
            context.hasMe = true;
        }
        fireDocumentationToken(ctx.getStart().getTokenIndex() - 1, ctx, false);
        listener.ExitNoTypeAssignment(context);
    }

    @Override
    public void enterSolo_method_required_method_part(QuorumParser.Solo_method_required_method_partContext ctx) {
        ActionCallContext context = new ActionCallContext();
        setLocation(ctx, context);
        setLocation(ctx.var, context.nameLocation);
        listener.EnterActionCall(context);
    }

    @Override
    public void exitSolo_method_required_method_part(QuorumParser.Solo_method_required_method_partContext ctx) {
        ActionCallContext context = new ActionCallContext();
        setLocation(ctx, context);
        String name = ctx.var.getText();
        boolean isActionCall = ctx.LEFT_PAREN() != null;
        context.name = name;
        setLocation(ctx.var, context.nameLocation);
        context.isActionCall = isActionCall;

        listener.ExitActionCall(context);
    }
    
    @Override
    public void enterVariableSoloFunctionCall(QuorumParser.VariableSoloFunctionCallContext ctx) {
        VariableFunctionCallContext context = new VariableFunctionCallContext();
        boolean hasMe = ctx.ME() != null;
        context.hasMe = hasMe;
        if(ctx.object != null) {
            context.objectName = ctx.object.getText();
            setLocation(ctx.object, context.objectLocation);
        }
        setLocation(ctx, context);
        listener.EnterVariableSoloFunctionCall(context);
    }

    @Override
    public void exitVariableSoloFunctionCall(QuorumParser.VariableSoloFunctionCallContext ctx) {
        VariableFunctionCallContext context = new VariableFunctionCallContext();
        boolean hasMe = ctx.ME() != null;
        context.hasMe = hasMe;
        if(ctx.object != null) {
            context.objectName = ctx.object.getText();
            setLocation(ctx.object, context.objectLocation);
        }
        setLocation(ctx, context);
        listener.ExitVariableSoloFunctionCall(context);
    }

    @Override
    public void enterParentVariableSoloFunctionCall(QuorumParser.ParentVariableSoloFunctionCallContext ctx) {
        ParentVariableFunctionCallContext context = new ParentVariableFunctionCallContext();
        setLocation(ctx, context);
        if(ctx.ME() != null) {
            context.hasMe = true;
        }
        
        if(ctx.fieldName != null) {
            context.variableName = ctx.fieldName.getText();
            if(context.variableName.isEmpty()) {
                context.variableName = null;
            }
        }
        
        QualifiedName name = Convert(ctx.parent);
        context.parentName = name;
        listener.EnterParentVariableSoloFunctionCall(context);
    }

    @Override
    public void exitParentVariableSoloFunctionCall(QuorumParser.ParentVariableSoloFunctionCallContext ctx) {
        ParentVariableFunctionCallContext context = new ParentVariableFunctionCallContext();
        setLocation(ctx, context);
        if(ctx.ME() != null) {
            context.hasMe = true;
        }
        
        if(ctx.fieldName != null) {
            context.variableName = ctx.fieldName.getText();
            if(context.variableName.isEmpty()) {
                context.variableName = null;
            }
        }
        
        QualifiedName name = Convert(ctx.parent);
        context.parentName = name;
        listener.ExitParentVariableSoloFunctionCall(context);
    }
    
    @Override
    public void enterVariableFunctionCall(QuorumParser.VariableFunctionCallContext ctx) {
        VariableFunctionCallContext context = new VariableFunctionCallContext();
        setLocation(ctx, context);
        boolean hasMe = ctx.ME() != null;
        context.hasMe = hasMe;
        listener.EnterVariableFunctionCall(context);
    }

    @Override
    public void exitVariableFunctionCall(QuorumParser.VariableFunctionCallContext ctx) {
        VariableFunctionCallContext context = new VariableFunctionCallContext();
        setLocation(ctx, context);
        boolean hasMe = ctx.ME() != null;
        context.hasMe = hasMe;
        listener.ExitVariableFunctionCall(context);
    }

    @Override
    public void enterParentVariableFunctionCall(QuorumParser.ParentVariableFunctionCallContext ctx) {
        ParentVariableFunctionCallContext context = new ParentVariableFunctionCallContext();
        setLocation(ctx, context);
        
        if(ctx.ME() != null) {
            context.hasMe = true;
        }
        
        if(ctx.fieldName != null) {
            context.variableName = ctx.fieldName.getText();
            if(context.variableName.isEmpty()) {
                context.variableName = null;
            }
        }
        
        QualifiedName name = Convert(ctx.parent);
        context.parentName = name;
        listener.EnterParentVariableFunctionCall(context);
    }

    @Override
    public void exitParentVariableFunctionCall(QuorumParser.ParentVariableFunctionCallContext ctx) {
        ParentVariableFunctionCallContext context = new ParentVariableFunctionCallContext();
        setLocation(ctx, context);
        
        if(ctx.ME() != null) {
            context.hasMe = true;
        }
        
        if(ctx.fieldName != null) {
            context.variableName = ctx.fieldName.getText();
            if(context.variableName.isEmpty()) {
                context.variableName = null;
            }
        }
        
        QualifiedName name = Convert(ctx.parent);
        context.parentName = name;
        
        listener.ExitParentVariableFunctionCall(context);
    }

    @Override
    public void enterAction_call(QuorumParser.Action_callContext ctx) {
        ActionCallContext context = new ActionCallContext();
        setLocation(ctx, context);
        setLocation(ctx.var, context.nameLocation);
        listener.EnterActionCall(context);
    }

    @Override
    public void exitAction_call(QuorumParser.Action_callContext ctx) {
        ActionCallContext context = new ActionCallContext();
        setLocation(ctx, context);
        String name = ctx.var.getText();
        boolean isActionCall = ctx.LEFT_PAREN() != null;
        context.name = name;
        setLocation(ctx.var, context.nameLocation);
        context.isActionCall = isActionCall;

        listener.ExitActionCall(context);
    }
    
    @Override 
    public void enterInitial_parent_action_call(@NotNull QuorumParser.Initial_parent_action_callContext ctx) {
        ActionCallContext context = new ActionCallContext();
        setLocation(ctx, context);
        listener.EnterInitialParentActionCall(context);
    }

    @Override 
    public void exitInitial_parent_action_call(@NotNull QuorumParser.Initial_parent_action_callContext ctx) {
        ActionCallContext context = new ActionCallContext();
        setLocation(ctx, context);
        String name = ctx.var.getText();
        boolean isActionCall = ctx.LEFT_PAREN() != null;
        context.name = name;
        context.isActionCall = isActionCall;

        listener.ExitInitialParentActionCall(context);
    }

    @Override
    public void enterNoActionsNoClass(QuorumParser.NoActionsNoClassContext ctx) {
        NoActionsNoClassContext context = new NoActionsNoClassContext();
        setLocation(ctx, context);
        listener.EnterNoActionsNoClass(context);
    }

    @Override
    public void exitNoActionsNoClass(QuorumParser.NoActionsNoClassContext ctx) {
        NoActionsNoClassContext context = new NoActionsNoClassContext();
        setLocation(ctx, context);
        listener.ExitNoActionsNoClass(context);
    }

    @Override
    public void enterActionsNoClass(QuorumParser.ActionsNoClassContext ctx) {
        ActionsNoClassContext context = new ActionsNoClassContext();
        setLocation(ctx, context);
        listener.EnterActionsNoClass(context);
    }

    @Override
    public void exitActionsNoClass(QuorumParser.ActionsNoClassContext ctx) {
        ActionsNoClassContext context = new ActionsNoClassContext();
        setLocation(ctx, context);
        listener.ExitActionsNoClass(context);
    }

    /**
     * @return the tokens
     */
    public CommonTokenStream getTokens() {
        return tokens;
    }

    /**
     * @param tokens the tokens to set
     */
    public void setTokens(CommonTokenStream tokens) {
        this.tokens = tokens;
    }
}
