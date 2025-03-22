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


#### Running Compiled file
```bash
./valid
```


#### Compiling the values

```bash
yacc -d valid.y # -d is used to create the header file for the c for better linking and usage.

lex valid.l
gcc -o valid y.tab.c lex.yy.c -ll #-ll is used to link the lex library

# To run the compiled file 
./valid
```

#### Necessary packages should be there in putty 

```bash
flex bison 
```

### Exercise 6


#### Running Compiled file
```bash
./calc
```


#### Compiling the values

```bash
yacc -d calc.y # -d is used to create the header file for the c for better linking and usage.

lex calc.l
gcc -o calc y.tab.c lex.yy.c -ll #-ll is used to link the lex library

# To run the compiled file 
./calc
```

#### Necessary packages should be there in putty 

```bash
flex bison 
```

### Exercise 7


#### Running Compiled file
```bash
./validator
```


#### Compiling the values

```bash
yacc -d validator.y # -d is used to create the header file for the c for better linking and usage.

lex validator.l
gcc -o validator y.tab.c lex.yy.c -ll #-ll is used to link the lex library

# To run the compiled file 
./validator
```

#### Necessary packages should be there in putty 

```bash
flex bison 
```

### Exercise 8

```bash
gcc type_checking.c -o type_checking && ./type_checking
```

### Exercise 9

```bash
gcc -o frontend frontend.c && ./frontend
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
