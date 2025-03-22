# Compile and Execute Exercises 1 to 12

## Compilation and Execution Commands

### Exercise 1

```bash
gcc symbol_table.c -o symbol_table && ./symbol_table
```

### Exercise 2

```bash
gcc lexical_analyzer.c -o lexical_analyzer && ./lexical_analyzer
```

### Exercise 3

```bash
lex echo.l && gcc lex.yy.c -o echo && ./echo
```

```bash
lex identifiers.l && gcc lex.yy.c -o identifiers && ./identifiers
```

```bash
lex line_number.l && gcc lex.yy.c -o line_number && ./line_number
```

```bash
lex vowel_consonant.l && gcc lex.yy.c -o vowel_consonant && ./vowel_consonant
```

### Exercise 4

```bash
lex lexical_analyzer.l && gcc lex.yy.c -o lexical_analyzer && ./lexical_analyzer
```

### Exercise 5

```bash
yacc -d valid.y && lex valid.l && gcc lex.yy.c y.tab.c -o valid -ll && ./valid
```

### Exercise 6

```bash
yacc -d calc.y && lex calc.l && gcc lex.yy.c y.tab.c -o calc -ll && ./calc
```

### Exercise 7

```bash
yacc -d validator.y && lex validator.l && gcc lex.yy.c y.tab.c -o validator -ll && ./validator
```

### Exercise 8

```bash
gcc type_checking.c -o type_checking && ./type_checking
```

### Exercise 9

```bash
gcc frontend.c -o frontend && ./frontend
```

### Exercise 10

```bash
gcc code_optimization.c -o code_optimization && ./code_optimization
```

### Exercise 11

```bash
gcc stack_allocation.c -o stack_allocation && ./stack_allocation
```

### Exercise 12

```bash
gcc backend_compiler.c -o backend_compiler && ./backend_compiler
```
