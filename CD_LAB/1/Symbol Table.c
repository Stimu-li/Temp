#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    char data[500];
    struct Node *next;
};

struct Node *start = NULL;

int main() {
    int choice;
    
    while (1) {
        printf("\nSymbol Table Management:\n");
        printf("1. Insert\n2. Display\n3. Search\n4. Exit\nEnter choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1: {
                struct Node *temp = malloc(sizeof(struct Node));
                printf("Enter identifier: ");
                scanf("%s", temp->data);
                temp->next = start;
                start = temp;
                printf("Inserted.\n");
                break;
            }
            case 2: {
                if (!start) {
                    printf("Symbol table is empty.\n");
                    break;
                }
                printf("\nIdentifier\tAddress\n------------------------\n");
                for (struct Node *cur = start; cur; cur = cur->next)
                    printf("%s\t\t%p\n", cur->data, (void *)cur);
                break;
            }
            case 3: {
                char key[500];
                printf("Enter identifier to search: ");
                scanf("%s", key);
                struct Node *cur = start;
                while (cur) {
                    if (strcmp(cur->data, key) == 0) {
                        printf("Found at address: %p\n", (void *)cur);
                        break;
                    }
                    cur = cur->next;
                }
                if (!cur) printf("Not found.\n");
                break;
            }
            case 4:
                while (start) {
                    struct Node *temp = start;
                    start = start->next;
                    free(temp);
                }
                printf("Memory freed. Exiting...\n");
                return 0;
            default:
                printf("Invalid choice.\n");
        }
    }
}
