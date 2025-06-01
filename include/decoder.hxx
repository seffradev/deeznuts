#include <array>
#include <cstdint>
#include <expected>
#include <ranges>
#include <vector>

#include "integral-concepts.hxx"

namespace decoder {

template <class Item>
auto decode(const std::vector<uint8_t> &buffer) -> Item;

inline auto advance(std::vector<uint8_t>::const_iterator &iter, size_t n) {
    iter += n;
}

template <Integral N>
auto peek(std::vector<uint8_t>::const_iterator &iter) -> N {
    N value = 0;

    auto i = 0;
    for (auto &element : std::ranges::subrange(iter, iter + sizeof(N))) {
        auto shift = (sizeof(N) - ++i) * 8;
        value += static_cast<N>(element) << shift;
    }

    return value;
}

template <Integral N>
auto take(std::vector<uint8_t>::const_iterator &iter) -> N {
    auto value = peek<N>(iter);
    advance(iter, sizeof(N));
    return value;
}

template <size_t N>
auto take(std::vector<uint8_t>::const_iterator &iter)
    -> std::array<uint8_t, N> {
    auto range = std::ranges::subrange(iter, iter + N);
    advance(iter, N);
    return range;
}

inline auto take(std::vector<uint8_t>::const_iterator &iter,
                 size_t n) -> std::vector<uint8_t> {
    auto vector = std::vector<uint8_t>(iter, iter + n);
    advance(iter, n);
    return vector;
}

}
