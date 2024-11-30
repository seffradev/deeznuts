#include <iostream>

#ifdef PROJECT_VERSION
#define VERSION PROJECT_VERSION
#else
#define VERSION "none"
#endif

int main(int argc, char *argv[]) {
    std::cout << VERSION << std::endl;

    return 0;
}
