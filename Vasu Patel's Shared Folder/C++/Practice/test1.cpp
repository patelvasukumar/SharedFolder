#include <iostream>

int main() {
    int x = 1;
    int y = x++ + ++x; 
    std::cout << y << " " << x << std::endl;
    return 0;
}