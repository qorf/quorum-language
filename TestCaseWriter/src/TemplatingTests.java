import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class TemplatingTests {
    static String path = "C:/Repositories/quorum-language/Quorum3/";
    static String testsPath = path + "Library/Tests/TemplatingGenerated/";
    static String testerPath = path + "SourceCode/";
    String passTestStr = new String();
    String failTestStr = new String();
    String testActionsStr = new String();

    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("TemplatingTests.txt");
        BufferedReader testsFile = new BufferedReader(file);
        TemplatingTests t = new TemplatingTests();
        String nextLine = new String();
        nextLine = testsFile.readLine();
        while (nextLine != null) {
            t.generateTests(nextLine);
            nextLine = testsFile.readLine();
        }
        testsFile.close();
        file.close();
        t.writeTesterClass();
        t.writeYodaClass();
        t.writeListNodeClass();
    }
    
    public void generateTests(String nextLine) throws IOException {
        StringTokenizer strTok = new StringTokenizer(nextLine);
        String testName = strTok.nextToken(",");
        String toRetType;
        String type1Type;
        String type2Type;
        String retType;
        String testType;
        String filePath;
        String testFileName;
        boolean isRetObject = false;
        if (testName.contains("ListNode")) {
            toRetType = new String();
            type1Type = "";
            type2Type = "";
            retType = strTok.nextToken(",");
            testType = strTok.nextToken();
            filePath = (testsPath + testType + "/");
            testFileName = "ListNode";
            testFileName += retType;
        } else {
            toRetType = new String();
            type1Type = strTok.nextToken(",");
            type2Type = strTok.nextToken(",");
            retType = strTok.nextToken(",");
            testType = strTok.nextToken();
            filePath = (testsPath + testType + "/");
            testFileName = "TwoType";
            testFileName += (type1Type + type2Type + "BooleanRet" + retType);
        }
        String testValue = new String();
        if (retType.contains("Obj")) {
            isRetObject = true;
            if (retType.equals("BoolObj")) {
                retType = "Boolean";
                testValue = "true";
            }
            if (retType.equals("IntObj")) {
                retType = "Integer";
                testValue = "66";
            }
            if (retType.equals("NumObj")) {
                retType = "Number";
                testValue = "900.0";
            }
            if (retType.equals("TextObj")) {
                retType = "Text";
                testValue = "\"green\"";
            }
        } else {
            if (retType.equals("boolean")) {
                testValue = "true";
            }
            if (retType.equals("integer")) {
                testValue = "66";
            }
            if (retType.equals("number")) {
                testValue = "900.0";
            }
            if (retType.equals("text")) {
                testValue = "\"green\"";
            }
            if (retType.equals("undefined")) {
                testValue = "undefined";
            }
        }

        String typeOne = new String();
        String typeTwo = new String();
        String valOneTwo = new String();
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    if (type1Type.equals("Obj")) {
                        typeOne = "Boolean";
                    } else {
                        typeOne = "boolean";
                    }
                    if (type2Type.equals("Obj")) {
                        typeTwo = "Boolean";
                    } else {
                        typeTwo = "boolean";
                    }
                    valOneTwo = "true";
                    if (testType.equals("Fail")) testFileName += "Fail";
                    filePath += (testFileName + ".quorum");
                    break;
                case 1:
                    if (type1Type.equals("Obj")) {
                        typeOne = "Integer";
                    } else {
                        typeOne = "integer";
                    }
                    if (type2Type.equals("Obj")) {
                        typeTwo = "Integer";
                    } else {
                        typeTwo = "integer";
                    }
                    valOneTwo = "66";
                    testFileName = testFileName.replace("BooleanRet", "IntegerRet");
                    filePath = filePath.replace("BooleanRet", "IntegerRet");
                    break;
                case 2:
                    if (type1Type.equals("Obj")) {
                        typeOne = "Text";
                    } else {
                        typeOne = "text";
                    }
                    if (type2Type.equals("Obj")) {
                        typeTwo = "Text";
                    } else {
                        typeTwo = "text";
                    }
                    valOneTwo = "\"green\"";
                    testFileName = testFileName.replace("IntegerRet", "TextRet");
                    filePath = filePath.replace("IntegerRet", "TextRet");
                    break;
            }
            System.out.println(filePath);
            if (testType.equals("Pass")) {
                passTestStr += ("        " + testFileName + "()\r\n");
            } else {
                failTestStr += ("        " + testFileName + "()\r\n");
            }

            FileWriter testFile = new FileWriter(filePath, false);
            PrintWriter testWriter = new PrintWriter(testFile);
            testWriter.printf("%s%n", "class Main");
            testWriter.printf("%s%n", "    action Main");
            if (type1Type.equals("Obj")) {
                testWriter.printf("%s%n", "        " + typeOne + " one");
                testWriter.printf("%s%n", "        one:SetValue(" + valOneTwo + ")");
            } else {
                testWriter.printf("%s%n", "        " + typeOne + " one = " + valOneTwo);
            }
            if (type2Type.equals("Obj")) {
                testWriter.printf("%s%n", "        " + typeTwo + " two");
                testWriter.printf("%s%n", "        two:SetValue(" + valOneTwo + ")");
            } else {
                testWriter.printf("%s%n", "        " + typeTwo + " two = " + valOneTwo);
            }
            testWriter.printf("%s%n", "        Yoda<" + typeOne + ", " + typeTwo + ", " + retType + "> yoda");
            if (testType.equals("Pass"))  {
                if (isRetObject) {
                    testWriter.printf("%s%n", "        " + retType + " ret = yoda:useForce(one, two)");
                    testWriter.printf("%s%n", "        output \"passed\"");
                } else {
                    testWriter.printf("%s%n", "        check");
                    testWriter.printf("%s%n", "            " + retType + " ret = yoda:useForce(one, two)");
                    testWriter.printf("%s%n", "        detect e");
                    testWriter.printf("%s%n", "            output \"passed\"");
                    testWriter.printf("%s%n", "        end");
                }
            } else {
                if (retType.equals("Boolean") || retType.equals("boolean")) {
                    testWriter.printf("%s%n", "        integer wrongRet = yoda(one, two)");
                } else {
                    testWriter.printf("%s%n", "        boolean wrongRet = yoda(one, two)");
                }
            }
            testWriter.printf("%s%n", "     end");
            testWriter.printf("%s%n", "end");
            testWriter.close();

            testActionsStr += ("\r\n");
            testActionsStr += ("    action " + testFileName + "\r\n");
            testActionsStr += ("        Array<File> files\r\n");
            if (testType.equals("Pass")) {
                testActionsStr += ("        Array<text> results\r\n");
            }
            testActionsStr += ("        File main\r\n");
            testActionsStr += ("        File yoda\r\n");
            testActionsStr += ("        test = path + \"" + testType + "/" + testFileName + ".quorum\"\r\n");
            testActionsStr += ("        yodaPath = path + \"Classes/Yoda.quorum\"\r\n");
            testActionsStr += ("        main:SetPath(test)\r\n");
            testActionsStr += ("        yoda:SetPath(yodaPath)\r\n");
            testActionsStr += ("        files:Add(main)\r\n");
            testActionsStr += ("        files:Add(yoda)\r\n");
            if (testType.equals("Pass")) {
                testActionsStr += ("        results:Add(0, \"passed\")\r\n");
                testActionsStr += ("        suite:Test(files, results)\r\n");
                testActionsStr += ("        suite:TestJavaScript(files, results)\r\n");
            } else {
                testActionsStr += ("        suite:TestFail(files)\r\n");
                testActionsStr += ("        suite:TestJavaScriptFail(files)\r\n");
            }
            testActionsStr += ("    end\r\n");
        }
    }
    
    public void writeTesterClass() throws IOException {
        FileWriter finalTestFW = new FileWriter(testerPath + "TemplatingTesterGenerated.quorum", false);
        PrintWriter finalWriter = new PrintWriter(finalTestFW);
        finalWriter.println("package Libraries.Language.Compile.Test");
        finalWriter.println("");
        finalWriter.println("use Libraries.Language.Compile.all");
        finalWriter.println("use Libraries.Containers.HashTable");
        finalWriter.println("use Libraries.Containers.Array");
        finalWriter.println("use Libraries.Language.Compile.Parser");
        finalWriter.println("use Libraries.System.File");
        finalWriter.println("use Libraries.Containers.Blueprints.Iterator");
        finalWriter.println("use Libraries.Language.Compile.Translate.JarGenerator");
        finalWriter.println("use Libraries.System.StackTraceItem");
        finalWriter.println("");
        finalWriter.println("class TemplatingTesterGenerated");
        finalWriter.println("    CompilerTestSuite suite = undefined");
        finalWriter.println("    text path = \"Library/Tests/TemplatingGenerated/\"");
        finalWriter.println("");
        finalWriter.println("    action Test");
        finalWriter.println("        //PASS TESTS");
        finalWriter.println(passTestStr);
        finalWriter.println("        //FAIL TESTS");
        finalWriter.println(failTestStr);
        finalWriter.println("    end");
        finalWriter.println("");
        finalWriter.println("    action GetName returns text");
        finalWriter.println("        return \"Templating Generated Tests\"");
        finalWriter.println("    end");
        finalWriter.println("    ");
        finalWriter.println("    action GetCompilerTestSuite returns CompilerTestSuite");
        finalWriter.println("        return suite");
        finalWriter.println("    end");
        finalWriter.println("    ");
        finalWriter.println("    action SetCompilerTestSuite(CompilerTestSuite suite)");
        finalWriter.println("        me:suite = suite");
        finalWriter.println("    end");
        finalWriter.println("");
        finalWriter.println(testActionsStr);
        finalWriter.println("end");
        finalTestFW.close();        
    }
    
    public void writeYodaClass() throws IOException {
        FileWriter yodaFW = new FileWriter(testsPath + "Classes/Yoda.quorum", false);
        PrintWriter yodaWriter = new PrintWriter(yodaFW);
        yodaWriter.println("class Yoda<Type1, Type2, Type3>");
        yodaWriter.println("    action useForce(Type1 one, Type2 two) returns Type3");
        yodaWriter.println("        Type3 three = undefined");
//        yodaWriter.println("        if three is Integer");
//        yodaWriter.println("            three = 66");
//        yodaWriter.println("        else if three is Number");
//        yodaWriter.println("            three = 900.0");
//        yodaWriter.println("        else if three is Text");
//        yodaWriter.println("            three = \"green\"");
//        yodaWriter.println("        else if three is Boolean");
//        yodaWriter.println("            three = true");
//        yodaWriter.println("        end");
        yodaWriter.println("        return three");
        yodaWriter.println("    end");
        yodaWriter.println("end");
        yodaFW.close();        
    }

    public void writeListNodeClass() throws IOException {
        FileWriter lnFW = new FileWriter(testsPath + "Classes/ListNode.quorum", false);
        PrintWriter lnWriter = new PrintWriter(lnFW);
        lnWriter.println("class ListNode<Type>");
        lnWriter.println("    Type value");
        lnWriter.println("end");
        lnFW.close();        
    }
}
