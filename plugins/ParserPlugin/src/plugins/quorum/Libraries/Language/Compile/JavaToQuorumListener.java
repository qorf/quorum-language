/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugins.quorum.Libraries.Language.Compile;

import java.util.Iterator;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import quorum.Libraries.Language.Compile.Context.*;
import quorum.Libraries.Language.Compile.Location;
import quorum.Libraries.Language.Compile.QuorumSourceListener$Interface;
import quorum.Libraries.Language.Compile.Symbol.Type;
import quorum.Libraries.Language.Compile.Symbol.Type$Interface;
import quorum.Libraries.Language.Compile.Symbol.Variable;
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
        StartContext context = new StartContext();
        listener.EnterStart(context);
    }

    @Override
    public void exitStart(QuorumParser.StartContext ctx) {
        StartContext context = new StartContext();
        listener.ExitStart(context);
    }

    @Override
    public void enterString(QuorumParser.StringContext ctx) {
        String val = ctx.STRING().getText();
        TextContext context = new TextContext();
        context.Set$Libraries$Language$Compile$Context$TextContext$value(val);
        setLocation(ctx, context);
        listener.EnterText(context);
    }

    @Override
    public void exitString(QuorumParser.StringContext ctx) {
        String val = ctx.STRING().getText();
        TextContext context = new TextContext();
        context.Set$Libraries$Language$Compile$Context$TextContext$value(val);
        setLocation(ctx, context);
        listener.ExitText(context);
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
        quorum.Libraries.Language.Compile.Context.Addition addition = new quorum.Libraries.Language.Compile.Context.Addition();
        listener.EnterAddition(addition);
    }

    @Override
    public void exitAddition(QuorumParser.AdditionContext ctx) {
        quorum.Libraries.Language.Compile.Context.Addition addition = new quorum.Libraries.Language.Compile.Context.Addition();
        listener.ExitAddition(addition);
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
        listener.ExitAction(context);
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
        listener.ExitAction(context);
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
        listener.EnterSystemAction(ctx.method_shared().actionContext);
    }

    @Override
    public void exitNativeAction(QuorumParser.NativeActionContext ctx) {
        listener.ExitSystemAction(ctx.method_shared().actionContext);
    }

    @Override
    public void enterAction(QuorumParser.ActionContext ctx) {
        listener.EnterAction(ctx.method_shared().actionContext);
    }

    @Override
    public void exitAction(QuorumParser.ActionContext ctx) {
        listener.ExitAction(ctx.method_shared().actionContext);
    }

    @Override
    public void enterBlueprintAction(QuorumParser.BlueprintActionContext ctx) {
        listener.EnterBlueprintAction(ctx.method_shared().actionContext);
    }

    @Override
    public void exitBlueprintAction(QuorumParser.BlueprintActionContext ctx) {
        listener.ExitBlueprintAction(ctx.method_shared().actionContext);
    }

    @Override
    public void enterConstructor(QuorumParser.ConstructorContext ctx) {
        listener.EnterConstructor();
    }

    @Override
    public void exitConstructor(QuorumParser.ConstructorContext ctx) {
        listener.ExitConstructor();
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
        
        if(ctx.expression() != null) {
            context.rightHandSide = ctx.expression().type;
        }
        listener.ExitNormalAssignment(context);
    }

    @Override
    public void enterParentAssignment(QuorumParser.ParentAssignmentContext ctx) {
        ParentAssignmentContext context = new ParentAssignmentContext();
        setLocation(ctx, context);
        context.parentName = ctx.parent.qualifiedName;
        context.name = ctx.name.getText();
        if(ctx.expression() != null) {
            context.rightHandSide = ctx.expression().type;
        }
        listener.EnterParentAssignment(context);
    }

    @Override
    public void exitParentAssignment(QuorumParser.ParentAssignmentContext ctx) {
        ParentAssignmentContext context = new ParentAssignmentContext();
        setLocation(ctx, context);
        context.parentName = ctx.parent.qualifiedName;
        context.name = ctx.name.getText();
        if(ctx.expression() != null) {
            context.rightHandSide = ctx.expression().type;
        }
        listener.ExitParentAssignment(context);
    }

    @Override
    public void enterObjectAssignment(QuorumParser.ObjectAssignmentContext ctx) {
        ObjectAssignmentContext context = new ObjectAssignmentContext();
        setLocation(ctx, context);
        context.object = ctx.object.getText();
        context.parentName = ctx.parent.qualifiedName;
        context.name = ctx.name.getText();
        if(ctx.expression() != null) {
            context.rightHandSide = ctx.expression().type;
        }
        listener.EnterObjectAssignment(context);
    }

    @Override
    public void exitObjectAssignment(QuorumParser.ObjectAssignmentContext ctx) {
        ObjectAssignmentContext context = new ObjectAssignmentContext();
        setLocation(ctx, context);
        context.object = ctx.object.getText();
        context.parentName = ctx.parent.qualifiedName;
        context.name = ctx.name.getText();
        if(ctx.expression() != null) {
            context.rightHandSide = ctx.expression().type;
        }
        listener.ExitObjectAssignment(context);
    }

    @Override
    public void enterNoTypeAssignment(QuorumParser.NoTypeAssignmentContext ctx) {
        NoTypeAssignmentContext context = new NoTypeAssignmentContext();
        setLocation(ctx, context);
        context.name = ctx.name.getText();
        if(ctx.expression() != null) {
            context.rightHandSide = ctx.expression().type;
        }
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
        if(ctx.expression() != null) {
            context.rightHandSide = ctx.expression().type;
        }
        listener.ExitNoTypeAssignment(context);
    }

    @Override
    public void enterMeVariableAccess(QuorumParser.MeVariableAccessContext ctx) {
        MeVariableAccessContext context = new MeVariableAccessContext();
        setLocation(ctx, context);
        listener.EnterMeVariableAccess(context);
    }

    @Override
    public void exitMeVariableAccess(QuorumParser.MeVariableAccessContext ctx) {
        MeVariableAccessContext context = new MeVariableAccessContext();
        setLocation(ctx, context);
        listener.ExitMeVariableAccess(context);
    }
}
