from calculator import calculate

if __name__ == '__main__':
    expression = input("Enter a Roman numeral expression: ")
    result = calculate(expression)
    print(result)
