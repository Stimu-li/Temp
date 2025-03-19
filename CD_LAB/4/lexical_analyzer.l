%{
int k = 0, i = 0, e = 0, c = 0, d = 0;
%}

%%

int|float|double|void|scanf|printf|return { printf("Keyword "); i++; }
[a-zA-Z_][a-zA-Z0-9_]* { printf("Identifier "); k++; }
[0-9]+ { printf("Digit "); e++; }
[+\-*/%=] { printf("Operator "); c++; }
[(){},;[\]] { printf("Delimiter "); d++; }

%%

int yywrap(void) {
    return 1;
}

int main() {
    yyin = fopen("lexy.txt", "r");
    yylex();
    
    printf("\n\nIdentifier count: %d\n", k);
    printf("Keyword count: %d\n", i);
    printf("Digit count: %d\n", e);
    printf("Operator count: %d\n", c);
    printf("Delimiter count: %d\n", d);
    
    fclose(yyin);
    return 0;
}
