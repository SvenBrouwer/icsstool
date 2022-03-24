grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';
//Literals
TRUE: 'TRUE';
FALSE: 'FALSE';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;
//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];
//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9\-]+;
CLASS_IDENT: '.' [a-z0-9\-]+;
//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;
//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;
//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//--- PARSER: ---
stylesheet: (variableassignment | stylerule)+;

stylerule:  ((idselector | classselector) | tagselector) OPEN_BRACE (declaration | ifclause | variableassignment)+ CLOSE_BRACE;

ifclause: IF BOX_BRACKET_OPEN variablereference BOX_BRACKET_CLOSE OPEN_BRACE (declaration | ifclause | elseclause | variableassignment)+ CLOSE_BRACE;

elseclause: ELSE OPEN_BRACE (declaration | ifclause | variableassignment)+ CLOSE_BRACE;

declaration: LOWER_IDENT COLON (t | f | tagselector |
variablereference | operation) SEMICOLON;

variableassignment: variablereference ASSIGNMENT_OPERATOR ((t | f) | operation) SEMICOLON;

variablereference: CAPITAL_IDENT;

operation: (colorliteral | scalarliteral | pixelliteral | variablereference) | operation multiplyoperation operation | operation (addoperation | subtractoperation) operation;

tagselector: LOWER_IDENT;

multiplyoperation: MUL;

addoperation: PLUS;

subtractoperation: MIN;

idselector: ID_IDENT;

classselector: CLASS_IDENT;

scalarliteral: SCALAR;

pixelliteral: PIXELSIZE;

colorliteral: COLOR;

t: TRUE;

f: FALSE;










