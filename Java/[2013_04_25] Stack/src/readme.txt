Expression Evaluator User Guide
[2013_05_07]
Jason Khamphila

Purpose:
This program evaluates an infix expression input by the user.
The program will also display a postfix version of the expression.

Note:
-The expression input by the user must be in infix format. (EX: 2+3+4)
-The expression can be evaluated using an array, single linked list, or double linked list.
-Unfortunately, the results are exactly the same no matter which stack type you are using. Using nanoTime, it is inconclusive which one is faster.
-There are still quite a few problems with error handling. The program can still not handle some invalid expressions such as ")2+2(" or "2++2".

Usage:
-The program will prompt the user to enter an option.
-The user should first enter '1' ("1 > Enter new infix expression").
-The infix expression can use numbers, +, -, *, /, and () (for both precedence and multiplication)
-The user then can choose to evaluate the expression by using 2, 3, or 4. The choice doesn't really matter, because they all give the same result.
-The user can enter a new expression at any time with '1' from the main prompt.
-Entering '5' from the main prompt will quit hte program.