# Polynomials
 Exercise for OOP classes 

Program reads an expression written in reverse polish notation whose value is polynomial in one variable with integer coefficients. 
The result is value of this expression written with infix notation.

Input data need to be given in one line. It can contain:
* integers representing constant polynomials
* x letter representing linear polynomial x
* operators +, * and @ representing addition, multiplication and composition of polynomials

There is at least one space character between subsequent elements of expression.

Example:  
**Input:**  
x 1 + x 1 + * x 1 + x 1 + * x 1 + x 1 + * @ @  
**Output:**  
x^8+8x^7+32x^6+80x^5+138x^4+168x^3+144x^2+80x+25
