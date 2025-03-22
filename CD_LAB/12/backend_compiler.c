#include <stdio.h>
#include <string.h>

int main() {
    int n, reg = 1;
    char inp[100][100];

    printf("Enter the no of statements: ");
    scanf("%d", &n);

    for (int i = 0; i < n; i++)
        scanf("%s", inp[i]);

    for (int i = 0; i < n; i++) {
        char dest = inp[i][0];  // Variable to store result
        char op1 = inp[i][2];   // First operand
        char op = inp[i][3];    // Operator (+, -, *, /)
        char op2 = inp[i][4];   // Second operand

        printf("LOAD R%d %c\n", reg, op1);
        int r1 = reg++;

        printf("LOAD R%d %c\n", reg, op2);
        int r2 = reg++;

        if (op == '+')
            printf("ADD R%d R%d\n", r1, r2);
        else if (op == '-')
            printf("SUB R%d R%d\n", r1, r2);
        else if (op == '*')
            printf("MUL R%d R%d\n", r1, r2);
        else if (op == '/')
            printf("DIV R%d R%d\n", r1, r2);

        printf("STORE %c R%d\n", dest, r1);
    }

    return 0;
}
