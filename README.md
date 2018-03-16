# Information about SyntaxAnalyzer assignment

# Part 1

In this assignment, you will create a Parser for a small programming language using the recursive descent technique discussed in class. The EBNF grammar for the language is included below. On Blackboard, I have included a zipped folder with five classes to help you start the parser. A complete lexical analyzer along with a start for your recursive descent parser. I have also included three sample programs that satisfy the grammar for this language to use for testing purposes.

Please make sure to supply an appropriate error message if a syntax error is found. Also, make sure to name your project **YourLastNameAssignment02** (filling in your last name appropriately). **Projects named incorrectly will get a zero**. You will not turn this project into Blackboard. This will be added on to and you will submit after completing the remaining pieces.

## The Grammar

program::= statement_list
statement_list ::= statement { statement } statement ::= var-decl | print-statement | if-statement | loop-statement | assign-statement
var-decl ::= DECLARE var AS INTEGER \[= expression\]
print-statement ::= PRINT expression
if-statement ::= IF condition THEN statement_list ENDIF
loop-statement ::= LOOP statement_list UNTIL condition
assign-statement ::= LET var = expression
condition ::= expression relop expression
expression ::= simple-expression |
simple-expression ( + | - | * | / | % ) expression
simple-expression ::= var | number
relop ::= < \[>|=\] | > \[ = \] | =  =



# Part 2

## The Task

You will modify the recursive-descent parser from Part 01 to build an Abstract Syntax Tree (AST). To create the AST, you will use a series of classes to represent each grammar rule. Object-Oriented programming will really play to your favor in this project. When a grammar rule has multiple choices, you can create a base class (sometimes just an abstract class) and have each option in the grammar as a separate class that inherits from your base class.  That way when you are parsing, you can create an object of the appropriate class as you parse through the syntax. **We will build the class hierarchy together in class**.

In order to build the AST, you will create all the classes we have identified in the class hierarchy, as well as modify Parser.java to create the appropriate nodes for the syntax tree. (Hint: each recursive descent method in the parser can be modified to return an object of the appropriate type to help build the tree). Once the Syntax tree is built, you will traverse the tree and use the knowledge you gain from each tree node to translate the statements into matching Java statements. Each class must have a convertToJava method which will return a String of the appropriate Java statement for that class. In addition to printing the corresponding Java statements, you must also keep proper formatting and indentation. You will do this through the use of a tabLevel property every class must have. In order to ensure that all of the classes are forced to have a convertToJava method and a tabLevel property, all of the class will inherit from an abstract class called ASTNode.

## Sample Output

Below you will find the expected output for each of the three input files provided on Blackboard. Your output must match this exactly. Any programs which do not provide the expected output will receive an automatic deduction of 50%. **Programs which do not compile will receive a zero.**
![expected output](https://i.imgur.com/OMySmQ5.jpg)


## Submission

When finished, you will submit a zipped folder of your entire Eclipse project to Blackboard. Make sure you named the project **YourLastNameAssign02**. Programs named incorrectly, turned in incorrectly, and programs that do not compile **will receive a zero**. Programs that do not provide the appropriate output will receive a 50% deduction in grade. **This is an individual assignment**. Any discussions on the assignment should be very high level and not discuss particular implementations.

