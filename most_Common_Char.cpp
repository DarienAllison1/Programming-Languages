#include <iostream>
#include <string>
#include <vector>
using namespace std;

// Function to find and print the most common character and its frequency
void findMostCommonCharacter(const string &input) {
    // Create a vector to count occurrences of each character (128 ASCII values)
    vector<int> indexes(128, 0);

    // 2. Iterate over each character and count occurrences
    for (char ch : input) {
        indexes[toupper(ch)]++;
    }

    // 3. Find the character with the highest counter
    int maxCount = 0;
    char mostCommonChar = ' ';
    for (int i = 0; i < 128; i++) {
        if (indexes[i] > maxCount) {
            maxCount = indexes[i];
            mostCommonChar = i;
        }
    }

    // 4. Print the most common character and its count
    cout << "'" << mostCommonChar << "' " << maxCount << endl;
}

int main() {
    // 1. Ask user for input
    string input;
    cout << "Enter a string: ";
    getline(cin, input);

    // Call the function to find and print the most common character
    findMostCommonCharacter(input);

    return 0;
}
