#include <iostream>

#ifndef VERSION
#define VERSION "0.0.0"
#endif

int main(int, char *[]) {
    std::cout << VERSION << std::endl;
    return 0;
}
