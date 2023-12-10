#include <iostream>
#include <map>
#include <sstream>
#include <string>

std::map<std::string, char> NUM_MAP = {
    {"one",   '1'},
    {"two",   '2'},
    {"three", '3'},
    {"four",  '4'},
    {"five",  '5'},
    {"six",   '6'},
    {"seven", '7'},
    {"eight", '8'},
    {"nine",  '9'}
};

int solve_part_1(std::string ln) {
    std::string cal = "";
    for (int i = 0; i < ln.length(); ++i) {
        int num = (int)ln[i];
        if (num >= 48 && num <= 57) {
            cal += ln[i];
        }
    }
    std::stringstream st;
    st << cal[0] << cal[cal.length() - 1];
    return std::stoi(st.str());
}

int solve_part_2(std::string ln) {
    std::string cal = "";
    for (int i = 0; i < ln.length(); i++) {
        int num = (int)ln[i];
        if (num >= 48 && num <= 57) {
            cal += ln[i];
        } else {
            if (NUM_MAP[ln.substr(i, 3)]) {
                cal += NUM_MAP[ln.substr(i, 3)];
            } else if (NUM_MAP[ln.substr(i, 4)]) {
                cal += NUM_MAP[ln.substr(i, 4)];
            } else if (NUM_MAP[ln.substr(i, 5)]) {
                cal += NUM_MAP[ln.substr(i, 5)];
            }
        }
    }
    std::stringstream st;
    st << cal[0] << cal[cal.length() - 1];
    return std::stoi(st.str());
}

int main(void) {
    std::string ln;
    int cal_sum_1 = 0;
    int cal_sum_2 = 0;
    while (std::cin >> ln) {
        cal_sum_1 += solve_part_1(ln);
        cal_sum_2 += solve_part_2(ln);
    }
    std::cout << "cal_sum_1 = " << cal_sum_1 << std::endl;
    std::cout << "cal_sum_2 = " << cal_sum_2 << std::endl;
    return 0;
}