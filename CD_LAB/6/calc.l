%{
    #include "y.tab.h"
    #include <stdlib.h>

    void yyerror(char *);
%}

%%

[a-z] {
    printf("Identifier: %s\n", yytext);
    return VARIABLE;
}

[0-9]+ {
    yylval = atoi(yytext);
    return INTEGER;
}

[-+()=*/\n] { return *yytext; }

[ \t] ; /* Ignore whitespace */

. { yyerror("Unknown character"); }

%%

int yywrap(void) {
    return 1;
}
