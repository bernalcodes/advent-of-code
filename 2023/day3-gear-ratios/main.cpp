#include <cctype>
#include <iostream>
#include <set>
#include <sstream>
#include <string>
#include <vector>

std::vector<std::string> input;
std::set<std::pair<int, int>> symbol_coords;
std::set<std::pair<std::pair<int, int>, int>> number_coords;
std::set<std::set<std::pair<std::pair<int, int>, int>>> gear_coords;
int sum_pt1 = 0;
int sum_pt2 = 0;

void process_number() {
	for (std::pair<std::pair<int, int>, int> p : number_coords) {
		std::stringstream st;
        for (int i = 0; i < p.second; i++) st << input[p.first.first][p.first.second + i];
		sum_pt1 += stoi(st.str());
    }
}

void process_symbol(std::pair<int, int> pair) {
    for (int row = pair.first - 1; row <= pair.first + 1; row++) {
        for (int col = pair.second - 1; col <= pair.second + 1; col++) {
			if (row == pair.first && col == pair.second) continue;
            if (isdigit(input[row][col])) {
				if (isdigit(input[row][col - 2]) && isdigit(input[row][col - 1])) number_coords.insert({ {row, col - 2}, 3 });
				else if (isdigit(input[row][col - 1]) && isdigit(input[row][col + 1])) number_coords.insert({ {row, col - 1}, 3 });
				else if (isdigit(input[row][col + 1]) && isdigit(input[row][col + 2])) number_coords.insert({ {row, col}, 3 });
				else if (isdigit(input[row][col - 1])) number_coords.insert({ {row, col - 1}, 2 });
				else if (isdigit(input[row][col + 1])) number_coords.insert({ {row, col}, 2 });
				else number_coords.insert({ {row, col}, 1 });
			}
        }
    }
}

void process_number_gear() {
	for (std::set<std::pair<std::pair<int, int>, int>> s : gear_coords) {
		int res = 1;
		for (std::pair<std::pair<int, int>, int> p : s) {
			std::stringstream st;
			for (int i = 0; i < p.second; i++) st << input[p.first.first][p.first.second + i];
			res *= stoi(st.str());
		}
		sum_pt2 += res;
	}
}

void process_gear(std::pair<int, int> pair) {
	std::set<std::pair<std::pair<int, int>, int>> part_numbers;
	for (int row = pair.first - 1; row <= pair.first + 1; row++) {
        for (int col = pair.second - 1; col <= pair.second + 1; col++) {
			if (row == pair.first && col == pair.second) continue;
            if (isdigit(input[row][col])) {
				if (isdigit(input[row][col - 2]) && isdigit(input[row][col - 1])) part_numbers.insert({ {row, col - 2}, 3 });
				else if (isdigit(input[row][col - 1]) && isdigit(input[row][col + 1])) part_numbers.insert({ {row, col - 1}, 3 });
				else if (isdigit(input[row][col + 1]) && isdigit(input[row][col + 2])) part_numbers.insert({ {row, col}, 3 });
				else if (isdigit(input[row][col - 1])) part_numbers.insert({ {row, col - 1}, 2 });
				else if (isdigit(input[row][col + 1])) part_numbers.insert({ {row, col}, 2 });
				else part_numbers.insert({ {row, col}, 1 });
			}
        }
    }
	if (part_numbers.size() == 2) gear_coords.insert(part_numbers);
}

void process_input() {
    for (int i = 0; i < input.size(); i++) {
        for (int j = 0; j < input[i].length(); j++) {
            if (input[i][j] != '.' && !isdigit(input[i][j]) && input[i][j] != '\r') process_symbol({i, j});
			if (input[i][j] == '*') process_gear({i, j});
        }
    }
}

int main(void) {
    std::string ln;
    int row = 0;
    while (getline(std::cin, ln)) {
        input.push_back(ln);
        row++;
    }
    process_input();
    process_number();
    process_number_gear();
	std::cout << "sum_pt1: " << sum_pt1 << std::endl;
	std::cout << "sum_pt2: " << sum_pt2 << std::endl;
    return 0;
}