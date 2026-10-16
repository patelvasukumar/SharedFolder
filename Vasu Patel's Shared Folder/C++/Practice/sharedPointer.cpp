#include <iostream>
#include <memory>

class MyClass {
public:
    MyClass() { std::cout << "Object created\n"; }
    ~MyClass() { std::cout << "Object destroyed\n"; }
};

int main() {
    
    {
        std::shared_ptr<MyClass> ptr1 = std::make_shared<MyClass>();
        std::shared_ptr<MyClass> ptr2 = ptr1;
        std::cout << "Inside inner block\n";
    } 
    /* Both ptr1 and ptr2 go out of scope here → object destroyed automatically */

    std::cout << "Outside inner block\n";
}