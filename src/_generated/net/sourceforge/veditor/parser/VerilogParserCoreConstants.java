/* Generated By:JavaCC: Do not edit this line. VerilogParserCoreConstants.java */
package net.sourceforge.veditor.parser;

public interface VerilogParserCoreConstants {

  int EOF = 0;
  int MULTI_LINE_COMMENT = 2;
  int ENDIF = 4;
  int SINGLE_LINE_COMMENT = 11;
  int DELSE = 12;
  int DIRECTIVE = 13;
  int MODULE = 14;
  int ENDMODULE = 15;
  int BEGIN = 16;
  int END = 17;
  int FORK = 18;
  int JOIN = 19;
  int SPECIFY = 20;
  int ENDSPECIFY = 21;
  int IF = 22;
  int ELSE = 23;
  int WHILE = 24;
  int FOR = 25;
  int FOREVER = 26;
  int REPEAT = 27;
  int CASE = 28;
  int CASEX = 29;
  int ENDCASE = 30;
  int FUNCTION = 31;
  int ENDFUNCTION = 32;
  int TASK = 33;
  int ENDTASK = 34;
  int GENERATE = 35;
  int ENDGENERATE = 36;
  int PORT_HEAD = 37;
  int VARIABLE_HEAD = 38;
  int PARAMETER = 39;
  int SIGNED = 40;
  int STMT_HEAD = 41;
  int BLOCK_HEAD = 42;
  int LPAREN = 43;
  int RPAREN = 44;
  int LBRACE = 45;
  int RBRACE = 46;
  int LBRACKET = 47;
  int RBRACKET = 48;
  int EOS = 49;
  int PARA = 50;
  int AT = 51;
  int BQ = 52;
  int DOLLAR = 53;
  int COMMA = 54;
  int COLON = 55;
  int EQUAL = 56;
  int IDENT = 57;
  int SPC_CHAR = 58;
  int OTHER = 59;

  int DEFAULT = 0;
  int IN_MULTI_LINE_COMMENT = 1;
  int IN_ELSE_BLOCK = 2;

  String[] tokenImage = {
    "<EOF>",
    "\"/*\"",
    "\"*/\"",
    "<token of kind 3>",
    "\"`endif\"",
    "<token of kind 5>",
    "\" \"",
    "\"\\t\"",
    "\"\\r\"",
    "\"\\n\"",
    "\"\\f\"",
    "<SINGLE_LINE_COMMENT>",
    "\"\\n`else\"",
    "<DIRECTIVE>",
    "\"module\"",
    "\"endmodule\"",
    "\"begin\"",
    "\"end\"",
    "\"fork\"",
    "\"join\"",
    "\"specify\"",
    "\"endspecify\"",
    "\"if\"",
    "\"else\"",
    "\"while\"",
    "\"for\"",
    "\"forever\"",
    "\"repeat\"",
    "\"case\"",
    "\"casex\"",
    "\"endcase\"",
    "\"function\"",
    "\"endfunction\"",
    "\"task\"",
    "\"endtask\"",
    "\"generate\"",
    "\"endgenerate\"",
    "<PORT_HEAD>",
    "<VARIABLE_HEAD>",
    "\"parameter\"",
    "\"signed\"",
    "<STMT_HEAD>",
    "<BLOCK_HEAD>",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "\";\"",
    "\"#\"",
    "\"@\"",
    "\"`\"",
    "\"$\"",
    "\",\"",
    "\":\"",
    "\"=\"",
    "<IDENT>",
    "<SPC_CHAR>",
    "\"[.]\"",
  };

}