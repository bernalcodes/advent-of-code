#include <algorithm>
#include <iostream>
#include <map>
#include <sstream>
#include <string>
#include <vector>

std::string DELIM_1 = ":";
std::string DELIM_2 = ";";
std::string DELIM_3 = ",";

int solve_part_1(std::string ln) {
	std::map<std::string, int> CONFIG_PT_1 = {
		{"red",   12},
		{"green", 13},
		{"blue",  14}
	};

    std::stringstream st;
    st << ln.substr(ln.find(" ") + 1, ln.find(":") - ln.find(" ") - 1);
    int game_id = std::stoi(st.str());

    std::string sets_string = ln.erase(0, ln.find(DELIM_1) + 2);

	std::vector<std::string> sets;
    int pos = 0;
    while ((pos = sets_string.find(DELIM_2)) != std::string::npos) {
        std::string set = sets_string.substr(0, pos);
		if (set[0] == ' ') set = set.substr(1, set.length());
		sets.push_back(set);
        sets_string.erase(0, pos + DELIM_2.length());
    }

    if (sets_string.length() != 0)
        sets.push_back(
            sets_string[0] == ' ' ? sets_string.substr(1, sets_string.length()) : sets_string);

	std::map<std::string, int> color_count = {
        {"red",   0},
        {"green", 0},
        {"blue",  0}
	};
    std::vector<std::string> set_data;
    for (std::string set : sets) {
        int pos = 0;
        while ((pos = set.find(DELIM_3)) != std::string::npos) {
            std::string data = set.substr(0, pos);
            if (data[0] == ' ') data = data.substr(1, data.length());
            if (data[(int)data.length() - 1] == 13) data = data.substr(0, data.length() - 1);
            set_data.push_back(data);
            set.erase(0, pos + DELIM_3.length());
        }

        if (set.length() != 0) {
            if (set[0] == ' ') set = set.substr(1, set.length());
            if (set[(int)set.length() - 1] == 13) set = set.substr(0, set.length() - 1);
            set_data.push_back(set);
        }

        for (std::string data : set_data) {
            if (data[1] == ' ')
                color_count[data.substr(2, data.length() - 2)] = std::stoi(data.substr(0, 1));
            else if (data[2] == ' ')
                color_count[data.substr(3, data.length() - 3)] = std::stoi(data.substr(0, 2));
        }

        for (std::pair<std::string, int> kv : color_count)
            if (color_count[kv.first] > CONFIG_PT_1[kv.first]) return 0;
    }

    return game_id;
}

int solve_part_2(std::string ln) {
	std::string sets_string = ln.erase(0, ln.find(DELIM_1) + 2);

	std::vector<std::string> sets;
    int pos = 0;
    while ((pos = sets_string.find(DELIM_2)) != std::string::npos) {
        std::string set = sets_string.substr(0, pos);
		if (set[0] == ' ') set = set.substr(1, set.length());
		sets.push_back(set);
        sets_string.erase(0, pos + DELIM_2.length());
    }

    if (sets_string.length() != 0)
        sets.push_back(
            sets_string[0] == ' ' ? sets_string.substr(1, sets_string.length()) : sets_string);

	std::map<std::string, int> color_count = {
        {"red",   0},
        {"green", 0},
        {"blue",  0}
	};
    std::vector<std::string> set_data;
    for (std::string set : sets) {
        int pos = 0;
        while ((pos = set.find(DELIM_3)) != std::string::npos) {
            std::string data = set.substr(0, pos);
            if (data[0] == ' ') data = data.substr(1, data.length());
            if (data[(int)data.length() - 1] == 13) data = data.substr(0, data.length() - 1);
            set_data.push_back(data);
            set.erase(0, pos + DELIM_3.length());
        }

        if (set.length() != 0) {
            if (set[0] == ' ') set = set.substr(1, set.length());
            if (set[(int)set.length() - 1] == 13) set = set.substr(0, set.length() - 1);
            set_data.push_back(set);
        }

        for (std::string data : set_data) {
            int max;
            if (data[1] == ' ') {
                max = std::max(std::stoi(data.substr(0, 1)), color_count[data.substr(2, data.length() - 2)]);
                color_count[data.substr(2, data.length() - 2)] = max;
            } else if (data[2] == ' ') {
                max = std::max(std::stoi(data.substr(0, 2)), color_count[data.substr(3, data.length() - 3)]);
                color_count[data.substr(3, data.length() - 3)] = max;
            }
        }
    }
	int total = 1;
	for (std::pair<std::string, int> kv : color_count) {
		total *= color_count[kv.first];
	}
	return total;
}

int main(void) {
    std::string ln;
    int cal_sum_1 = 0;
    int cal_sum_2 = 0;
    while (std::getline(std::cin, ln, '\n')) {
        cal_sum_1 += solve_part_1(ln);
        cal_sum_2 += solve_part_2(ln);
    }
    std::cout << "cal_sum_1 = " << cal_sum_1 << std::endl;
    std::cout << "cal_sum_2 = " << cal_sum_2 << std::endl;
    return 0;
}