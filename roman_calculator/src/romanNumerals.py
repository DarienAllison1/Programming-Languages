def roman_to_int(roman):
    """
    Converts a Roman numeral to an integer.

    Args:
        roman (str): The Roman numeral as a string.

    Returns:
        int: The integer representation of the Roman numeral.

    Raises:
        KeyError: If the input string contains invalid Roman numeral characters.
    """
    roman_values = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
    total = 0
    prev_value = 0
    
    for char in reversed(roman):
        value = roman_values[char]
        if value >= prev_value:
            total += value
        else:
            total -= value
        prev_value = value
    
    return total

def int_to_roman(num):
    """
    Converts an integer to a Roman numeral.

    Args:
        num (int): The integer to convert, must be between 1 and 3999.

    Returns:
        str: The Roman numeral representation of the integer or an error message 
             if the input exceeds the valid range.

    Raises:
        ValueError: If the input integer is less than 1.
    """
    if num > 3999:
        return "You’re going to need a bigger calculator."
    
    roman_numerals = [
        ('M', 1000), ('CM', 900), ('D', 500), ('CD', 400),
        ('C', 100), ('XC', 90), ('L', 50), ('XL', 40),
        ('X', 10), ('IX', 9), ('V', 5), ('IV', 4), ('I', 1)
    ]
    result = ''
    
    for roman, value in roman_numerals:
        while num >= value:
            result += roman
            num -= value
    
    return result
