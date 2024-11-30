#include <array>
#include <cstdint>
#include <expected>
#include <ranges>
#include <vector>

#include "integral_concepts.hxx"

enum class DecodeError {
    InvalidInput,
    Overflow,
};

template <typename Item>
class Decoder {
public:
    virtual auto decode() noexcept
        -> std::expected<std::optional<Item>, DecodeError> = 0;

    template <UnsignedIntegral N>
    auto peek() -> N {
        N value = 0;

        auto i = 0;
        for (auto &element : std::ranges::subrange(iter, iter + sizeof(N))) {
            auto shift = (sizeof(N) - ++i) * 8;
            value += static_cast<N>(element) << shift;
        }

        return value;
    }

    template <UnsignedIntegral N>
    auto take() -> N {
        auto value = peek<N>();
        iter += sizeof(N);
        return value;
    }

    template <size_t N>
    auto take() -> std::array<uint8_t, N> {
        return std::ranges::subrange(iter, iter + N);
    }

    auto take(size_t n) -> std::vector<uint8_t> {
        auto range = std::ranges::subrange(iter, iter + n);
        return std::vector<uint8_t>(range.begin(), range.end());
    }

    Decoder(const std::vector<uint8_t> &buffer) : iter(buffer.begin()) {}

private:
    std::vector<uint8_t>::const_iterator iter;
};
