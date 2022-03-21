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
stylesheet: (((idtag | classtag) | LOWER_IDENT) meth | var)+;

meth:  OPEN_BRACE (var | ifstmt)+ CLOSE_BRACE;

ifstmt: IF BOX_BRACKET_OPEN CAPITAL_IDENT BOX_BRACKET_CLOSE OPEN_BRACE (var | ifstmt | elsestmt)+ CLOSE_BRACE;

elsestmt: ELSE OPEN_BRACE (var | ifstmt)+ CLOSE_BRACE;

var: (decl | upper) (COLON | ASSIGNMENT_OPERATOR) (pixel | color|
t | f | decl | upper | mul | plus | minus | scalar)+ SEMICOLON;

upper: CAPITAL_IDENT;

decl: LOWER_IDENT;

mul: MUL;

plus: PLUS;

minus: MIN;

idtag: ID_IDENT;

classtag: CLASS_IDENT;

scalar: SCALAR;

pixel: PIXELSIZE;

color: COLOR;

t: TRUE;

f: FALSE;






