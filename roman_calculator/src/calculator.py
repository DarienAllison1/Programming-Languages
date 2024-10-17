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
    
    # Validate that all tokens are either valid Roman numerals or operators
    for token in tokens:
        if re.fullmatch(r'[IVXLCDM]+', token):
            try:
                roman_to_int(token)
            except KeyError:
                raise ValueError(f"Invalid Roman numeral: {token}")
    
    return tokens

def precedence(op):
    """
    Returns the precedence of operators.
    """
    if op in ('+', '-'):
        return 1
    if op in ('*', '/'):
        return 2
    return 0

def apply_operator(operators, values):
    """
    Applies an operator to the top two values in the values stack.
    """
    operator = operators.pop()
    right = values.pop()
    left = values.pop()
    
    if operator == '+':
        values.append(left + right)
    elif operator == '-':
        values.append(left - right)
    elif operator == '*':
        values.append(left * right)
    elif operator == '/':
        if right == 0:
            raise ZeroDivisionError("Division by zero is undefined.")
        values.append(left // right)

def evaluate_expression(tokens):
    """
    Evaluates a list of tokens representing a Roman numeral expression.

    Args:
        tokens (list): A list of tokens obtained from tokenizing a
                       Roman numeral expression.

    Returns:
        int: The result of the evaluation in integer format.
    """
    values = []
    operators = []
    i = 0
    
    while i < len(tokens):
        token = tokens[i]
        
        if re.fullmatch(r'[IVXLCDM]+', token):
            values.append(roman_to_int(token))
        elif token == '(':
            operators.append(token)
        elif token == ')':
            while operators and operators[-1] != '(':
                apply_operator(operators, values)
            operators.pop()  # Remove '(' from stack
        elif token in ('+', '-', '*', '/'):
            while (operators and operators[-1] != '(' and
                   precedence(operators[-1]) >= precedence(token)):
                apply_operator(operators, values)
            operators.append(token)
        i += 1
    
    # Apply remaining operators
    while operators:
        apply_operator(operators, values)
    
    return values[0]

def calculate(roman_expression):
    """
    Calculates the result of a Roman numeral expression.

    Args:
        roman_expression (str): The Roman numeral expression to evaluate.

    Returns:
        tuple: The result of the calculation in both integer and Roman numeral format,
               or an error message if an error occurs.
    """
    try:
        tokens = tokenize(roman_expression)
        result = evaluate_expression(tokens)
        
        # Convert result back to Roman numerals
        roman_result = int_to_roman(result)
        
        return result, roman_result
    
    except ZeroDivisionError as e:
        return str(e), str(e)
    
    except Exception as e:
        return "I don’t know how to read this.", "I don’t know how to read this."

def is_single_numeral(expression):
    """
    Determines if the input is a single Roman numeral.

    Args:
        expression (str): The input expression to check.

    Returns:
        bool: True if the expression is a single Roman numeral, False otherwise.
    """
    return re.fullmatch(r'[IVXLCDM]+', expression.strip()) is not None

# Main part of the script to get user input and show result
if __name__ == "__main__":
    import sys
    
    input_expression = ' '.join(sys.argv[1:]).strip()

    if is_single_numeral(input_expression):
        # If it's a single Roman numeral, print its integer value
        try:
            int_value = roman_to_int(input_expression)
            print(f"({int_value})")  # Output the integer in parentheses
        except ValueError:
            print("(Invalid Roman numeral)")
    else:
        # Calculate the result of the Roman numeral equation
        int_result, roman_result = calculate(input_expression)
        
        # Only print the Roman numeral result for equations
        print(f"({roman_result})")

