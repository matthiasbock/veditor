/* Generated By:JavaCC: Do not edit this line. VhdlParserCoreConstants.java */
package net.sourceforge.veditor.parser;

public interface VhdlParserCoreConstants {

  int EOF = 0;
  int MULTI_LINE_COMMENT = 2;
  int SINGLE_LINE_COMMENT = 9;
  int LIBRARY = 10;
  int USE = 11;
  int ENTITY = 12;
  int ARCHITECTURE = 13;
  int IS = 14;
  int OF = 15;
  int GENERIC = 16;
  int PORT = 17;
  int PROCESS = 18;
  int BEGIN = 19;
  int END = 20;
  int CONSTANT = 21;
  int PROCEDURE = 22;
  int FUNCTION = 23;
  int SIGNAL = 24;
  int SHARED = 25;
  int VARIABLE = 26;
  int TYPE = 27;
  int SUBTYPE = 28;
  int FILE = 29;
  int ALIAS = 30;
  int ATTRIBUTE = 31;
  int COMPONENT = 32;
  int FOR = 33;
  int IF = 34;
  int CASE = 35;
  int DISCONNECT = 36;
  int GROUP = 37;
  int BLOCK = 38;
  int ASSERT = 39;
  int GENERATE = 40;
  int TO = 41;
  int RANGE = 42;
  int WITH = 43;
  int MODE = 44;
  int LPAREN = 45;
  int RPAREN = 46;
  int LBRACE = 47;
  int RBRACE = 48;
  int LBRACKET = 49;
  int RBRACKET = 50;
  int EOS = 51;
  int PARA = 52;
  int AT = 53;
  int BQ = 54;
  int DOLLAR = 55;
  int COMMA = 56;
  int COLON = 57;
  int ASSIGN = 58;
  int EQUAL = 59;
  int IDENT = 60;
  int SPC_CHAR = 61;
  int OTHER = 62;

  int DEFAULT = 0;
  int IN_MULTI_LINE_COMMENT = 1;

  String[] tokenImage = {
    "<EOF>",
    "\"/*\"",
    "\"*/\"",
    "<token of kind 3>",
    "\" \"",
    "\"\\t\"",
    "\"\\r\"",
    "\"\\n\"",
    "\"\\f\"",
    "<SINGLE_LINE_COMMENT>",
    "\"library\"",
    "\"use\"",
    "\"entity\"",
    "\"architecture\"",
    "\"is\"",
    "\"of\"",
    "\"generic\"",
    "\"port\"",
    "\"process\"",
    "\"begin\"",
    "\"end\"",
    "\"constant\"",
    "\"procedure\"",
    "\"function\"",
    "\"signal\"",
    "\"shared\"",
    "\"variable\"",
    "\"type\"",
    "\"subtype\"",
    "\"file\"",
    "\"alias\"",
    "\"attribute\"",
    "\"component\"",
    "\"for\"",
    "\"if\"",
    "\"case\"",
    "\"disconnect\"",
    "\"group\"",
    "\"block\"",
    "\"assert\"",
    "\"generate\"",
    "\"to\"",
    "\"range\"",
    "\"with\"",
    "<MODE>",
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
    "\"<=\"",
    "\"=\"",
    "<IDENT>",
    "<SPC_CHAR>",
    "\"[.]\"",
  };

}