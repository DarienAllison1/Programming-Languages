def roman_to_int(roman):
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
