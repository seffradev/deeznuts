#include <cstdint>
#include <vector>

namespace encoder {

template <typename Item>
auto encode(const Item &item) -> std::vector<uint8_t>;

}
