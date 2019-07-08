## Bitwise calculator using LL1 parser

This simple calculator accepts expressions with the bitwise AND(&) and XOR(^) operators, as well as parentheses. It parses the given input using hand-written LL(1) parser and produces the result. It can accept multiple expressions at once (hence the input files inlcuded for testing). 

#### Compile and run
- make compile
- make execute < filename
- make clean
</br></br>

The LL(1)  grammar:
```
goal  --> exp
exp   --> term exp2
expr2   -> ^ term expr2
        | ε
term  --> factor term2
term2   -> & factor term2
        | ε
factor--> num
        | ( exp )  
        | ε    
num   --> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
```
