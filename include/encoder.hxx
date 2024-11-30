#include <cstdint>
#include <vector>

template <typename Item>
class Encoder {
public:
    auto encode(const Item &item) -> std::vector<uint8_t>;
};
