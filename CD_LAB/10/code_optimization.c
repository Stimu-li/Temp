#include <stdio.h>
#include <stdbool.h>
#include <ctype.h>
#include <string.h>

#define MAX_LINE 256

int main()
{
    char declared[26] = {0}; // Track declared variables (a-z)
    char used[26] = {0};     // Track used variables
    char line[MAX_LINE];
    FILE *file;
    
    file = fopen("test.txt", "r");
    if (!file) {
        printf("Error opening file\n");
        return 1;
    }
    
    printf("Program:\n");
    
    // Process each line
    while (fgets(line, MAX_LINE, file)) {
        printf("%s", line);
        
        // Create a clean version without whitespace
        size_t len = strlen(line);
        char cleaned[MAX_LINE] = {0};
        int cleanIndex = 0;
        
        for (int i = 0; i < len; i++) {
            if (!isspace(line[i])) {
                cleaned[cleanIndex++] = line[i];
            }
        }
        
        // Check for declarations (format: "a;")
        if (strlen(cleaned) == 2 && isalpha(cleaned[0]) && cleaned[1] == ';') {
            char var = tolower(cleaned[0]);
            if (var >= 'a' && var <= 'z') {
                declared[var - 'a'] = 1;
            }
        }
        
        // Check for assignments (format: "a=34;")
        for (int i = 0; i < strlen(cleaned) - 1; i++) {
            if (cleaned[i + 1] == '=' && isalpha(cleaned[i])) {
                char var = tolower(cleaned[i]);
                if (var >= 'a' && var <= 'z') {
                    used[var - 'a'] = 1;
                }
            }
        }
    }
    
    // Print unused variables
    printf("\nThe unused variables are: ");
    bool foundUnused = false;
    
    for (int i = 0; i < 26; i++) {
        if (declared[i] && !used[i]) {
            printf("%c ", 'a' + i);
            foundUnused = true;
        }
    }
    
    if (!foundUnused) {
        printf("None");
    }
    printf("\n");
    
    fclose(file);
    return 0;
}