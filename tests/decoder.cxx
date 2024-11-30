#include <decoder.hxx>
#include <iostream>

class Person {
public:
    Person(std::string name, uint8_t age) : name(name), age(age) {}

    auto print() {
        std::cout << name << " is " << age << " years old." << std::endl;
    }

private:
    std::string name;
    int         age;
};

class PersonCodec : public Decoder<Person> {
public:
    const size_t MINIMUM_PACKET_SIZE = 3;

    using Decoder::Decoder;

    auto decode() noexcept
        -> std::expected<std::optional<Person>, DecodeError> override {
        if (peek<uint8_t>() < MINIMUM_PACKET_SIZE) {
            return std::unexpected(DecodeError::InvalidInput);
        }

        auto packetLength = take<uint8_t>();
        auto age          = take<uint8_t>();
        auto name = take(packetLength - sizeof(packetLength) - sizeof(age));

        return Person(std::string(name.begin(), name.end()), age);
    }
};

int main(int, char *[]) {
    std::vector<uint8_t> buffer{
        0x0A, 0x17, 'J', 'o', 'h', 'n', ' ', 'D', 'o', 'e',
    };

    PersonCodec codec(buffer);

    auto person = codec.decode();

    if (person.has_value()) {
        if (person.value()) {
            person.value()->print();
        }
    }

    return 0;
}
