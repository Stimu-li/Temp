%{
int l = 1;
%}

%%

^(.*)\n  { printf("%d\t%s", l++, yytext); }  // Print line number and text

%%

int yywrap(void) {
    return 1;
}

int main() {
    yyin = fopen("input.txt", "r");
    yylex();
    return 0;
}
