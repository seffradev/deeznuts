#include <cassert>
#include <decoder.hxx>
#include <format>
#include <memory>
#include <print>

class InvalidName : public std::exception {};

class InvalidInput : public std::exception {};

struct Name {
    Name(const std::string &&name) {
        if (name.empty()) {
            [[unlikely]] throw InvalidName();
        }

        auto index = name.find(' ');
        firstname  = name.substr(0, index);
        lastname   = name.substr(index + 1, std::string::npos);
    }

    Name(const Name &)            = delete;
    Name(Name &&)                 = default;
    Name &operator=(const Name &) = delete;
    Name &operator=(Name &&)      = default;
    ~Name()                       = default;

private:
    std::string firstname;
    std::string lastname;
    friend std::formatter<Name>;
};

template <>
struct std::formatter<Name> {
    constexpr auto parse(std::format_parse_context &context) {
        auto position = context.begin();

        while (position != context.end() && *position != '}') {
            if (*position == 'l' || *position == 'L') {
                lastnameFirst = true;
            }
            ++position;
        }

        return position;
    }

    auto
    format(const Name &name, std::format_context &context) const {
        if (lastnameFirst) {
            return std::format_to(context.out(), "{}, {}",
                                  name.lastname, name.firstname);
        }

        return std::format_to(context.out(), "{} {}",
                              name.firstname, name.lastname);
    }

    bool lastnameFirst = false;
};

struct Age {
    Age(int age) : age(age) {}

    Age(const Age &)            = delete;
    Age(Age &&)                 = default;
    Age &operator=(const Age &) = delete;
    Age &operator=(Age &&)      = default;
    ~Age()                      = default;

private:
    int age;
    friend std::formatter<Age>;
};

template <>
struct std::formatter<Age> {
    constexpr auto parse(std::format_parse_context &context) {
        return context.begin();
    }

    auto
    format(const Age &age, std::format_context &context) const {
        return std::format_to(context.out(), "{}", age.age);
    }
};

struct Gender {
    Gender(char gender) : gender(gender) {}

    Gender(const Gender &)            = delete;
    Gender(Gender &&)                 = default;
    Gender &operator=(const Gender &) = delete;
    Gender &operator=(Gender &&)      = default;
    ~Gender()                         = default;

private:
    char gender;
    friend std::formatter<Gender>;
};

template <>
struct std::formatter<Gender> {
    constexpr auto parse(std::format_parse_context &context) {
        return context.begin();
    }

    auto format(const Gender        &gender,
                std::format_context &context) const {
        return std::format_to(context.out(), "{}", gender.gender);
    }
};

struct Person {
    Name   name;
    Age    age;
    Gender gender;

    static const size_t MINIMUM_PACKET_SIZE = 3;
};

template <>
auto decoder::decode<Person>(const std::vector<uint8_t> &buffer)
    -> Person {
    if (buffer.size() < Person::MINIMUM_PACKET_SIZE) {
        throw InvalidInput();
    }

    auto iter = buffer.begin();

    if (buffer.size() < peek<uint8_t>(iter)) {
        throw InvalidInput();
    }

    auto packetLength = take<uint8_t>(iter);
    auto age          = take<uint8_t>(iter);
    auto gender       = take<char>(iter);
    auto name = take(iter, packetLength - sizeof(packetLength) -
                               sizeof(age) - sizeof(gender));

    return Person(std::string(name.begin(), name.end()), age,
                  gender);
}

template <>
struct std::formatter<Person> {
    constexpr auto parse(std::format_parse_context &context) {
        return context.begin();
    }

    auto format(const Person        &person,
                std::format_context &context) const {
        return std::format_to(context.out(), "{:l} ({}{})",
                              person.name, person.gender,
                              person.age);
    }
};

int main(int, char *[]) {
    std::vector<uint8_t> buffer{sizeof("John Doe") + 2,
                                35,
                                'M',
                                'J',
                                'o',
                                'h',
                                'n',
                                ' ',
                                'D',
                                'o',
                                'e'};

    auto person = decoder::decode<Person>(buffer);
    std::print("{}", person);

    auto t = std::make_unique<int>(5);

    return 0;
}
