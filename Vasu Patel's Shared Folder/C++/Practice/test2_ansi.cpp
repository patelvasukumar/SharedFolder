#include <iostream>

int main() {
    int x = 1;
    int y = x++ + ++x;  // Undefined behavior in C++98
    std::cout << y << " " << x << std::endl;
    return 0;
}
