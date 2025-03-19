%{
int vowels = 0, consonants = 0;
%}

%%

[aeiouAEIOU] { vowels++; printf("Vowel "); }
[a-zA-Z]      { consonants++; printf("Consonant "); }

[^a-zA-Z]     { printf("\n"); }

%%

int yywrap(void) {
    return 1;
}

int main() {
    printf("Enter text (Ctrl+D to stop input on Linux/macOS, Ctrl+Z on Windows):\n");
    yylex();
    printf("\nTotal Vowels: %d\nTotal Consonants: %d\n", vowels, consonants);
    return 0;
}
