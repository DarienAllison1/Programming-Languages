import re
from romanNumerals import roman_to_int, int_to_roman

def tokenize(expression):
    """
    Tokenizes a Roman numeral expression into individual components.

    Args:
        expression (str): The Roman numeral expression to tokenize.

    Returns:
        list: A list of tokens representing the individual components
              of the expression, including operators and numerals.
    """
    tokens = re.findall(r'\(|\)|\+|\-|\*|\/|[IVXLCDM]+', expression)
    return tokens

def evaluate_expression(tokens):
    """
    Evaluates a list of tokens representing a Roman numeral expression.

    Args:
        tokens (list): A list of tokens obtained from tokenizing a
                       Roman numeral expression.

    Returns:
        str or int: The result of the evaluation in Roman numeral format
                    or an error message if an error occurs.

    Raises:
        ZeroDivisionError: If there is an attempt to divide by zero.
    """
    def eval_simple(tokens):
        """
        Evaluates simple expressions without grouping.

        Args:
            tokens (list): A list of tokens representing a simple 
                           Roman numeral expression.

        Returns:
            int: The evaluated total of the expression.

        Raises:
            ZeroDivisionError: If division by zero is attempted.
        """
        total = roman_to_int(tokens[0])
        i = 1
        while i < len(tokens):
            op = tokens[i]
            next_val = roman_to_int(tokens[i + 1])
            if op == '+':
                total += next_val
            elif op == '-':
                total -= next_val
            elif op == '*':
                total *= next_val
            elif op == '/':
                if next_val == 0:
                    raise ZeroDivisionError("There is no concept of a fractional number in Roman numerals.")
                total //= next_val  # Integer division since Roman numerals don't have fractions
            
            # Check for specific error conditions
            if total == 0:
                return "0 does not exist in Roman numerals."
            if total < 0:
                return "Negative numbers can’t be represented in Roman numerals."
            if total > 3999:
                return "You’re going to need a bigger calculator."
            
            i += 2
        return total

    def eval_group(tokens):
        """
        Evaluates expressions containing grouped tokens.

        Args:
            tokens (list): A list of tokens that may include grouped
                           expressions.

        Returns:
            int: The evaluated total of the expression.
        """
        stack = []
        i = 0
        while i < len(tokens):
            token = tokens[i]
            if token == '(' or token == '[':
                sub_expr = []
                open_paren = token
                close_paren = ')' if open_paren == '(' else ']'
                i += 1
                while tokens[i] != close_paren:
                    sub_expr.append(tokens[i])
                    i += 1
                stack.append(int_to_roman(evaluate_expression(sub_expr)))
            else:
                stack.append(token)
            i += 1
        return eval_simple(stack)

    return eval_group(tokens)

def calculate(roman_expression):
    """
    Calculates the result of a Roman numeral expression.

    Args:
        roman_expression (str): The Roman numeral expression to evaluate.

    Returns:
        str: The result of the calculation in Roman numeral format or
              an error message if an error occurs.

    Raises:
        Exception: If the input cannot be parsed.
    """
    try:
        tokens = tokenize(roman_expression)
        result = evaluate_expression(tokens)

        # If the result is a string (error message), return it
        if isinstance(result, str):
            return result
        
        # Convert result back to Roman numerals
        return int_to_roman(result)
    
    except ZeroDivisionError as e:
        return str(e)  # Handle division by zero for fractions
    
    except Exception as e:
        return "I don’t know how to read this."


# Main part of the script to get user input and show result
if __name__ == "__main__":
    # Take the input equation from the user
    input_equation = input("Enter a Roman numeral equation: ")

    # Calculate the result of the Roman numeral equation
    result = calculate(input_equation)

    # Print just the result (no "The result of" prefix)
    print(result)

