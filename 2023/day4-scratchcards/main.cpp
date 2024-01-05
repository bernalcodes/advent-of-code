#include <cctype>
#include <iostream>
#include <map>
#include <math.h>
#include <string>
#include <vector>

int process_ln(std::string ln) {
    std::string card = ln.substr(0, ln.find('|') - 1);
    std::string numbers = ln.substr(ln.find('|') + 2);
    std::map<std::string, int> point_map;
    std::vector<std::string> card_numbers;

    for (int i = 0; i < numbers.length(); i++) {
        if (!isdigit(numbers[i])) continue;
        std::string key = "";
        if (!isdigit(numbers[i - 1]) && isdigit(numbers[i]) && isdigit(numbers[i + 1]) && !isdigit(numbers[i + 2])) {
            key.append(1, numbers[i]);
            key.append(1, numbers[i + 1]);
            if (point_map.find(key) == point_map.end()) point_map[key] = 0;
        }
        if (!isdigit(numbers[i - 1]) && isdigit(numbers[i]) && !isdigit(numbers[i + 1])) {
            key.append(1, numbers[i]);
            if (point_map.find(key) == point_map.end()) point_map[key] = 0;
        }
    }

    for (int i = 0; i < card.length(); i++) {
        if (!isdigit(card[i])) continue;
        std::string num = "";
        if (!isdigit(card[i - 1]) && isdigit(card[i]) && isdigit(card[i + 1]) && !isdigit(card[i + 2])) {
            num.append(1, card[i]);
            num.append(1, card[i + 1]);
            card_numbers.push_back(num);
        }
        if (!isdigit(card[i - 1]) && isdigit(card[i]) && !isdigit(card[i + 1])) {
            num.append(1, card[i]);
            card_numbers.push_back(num);
        }
    }

    int score = 0;
    for (std::string num : card_numbers) {
        if (point_map.find(num) == point_map.end()) continue;
		score++;
    }
    return score;
}

int process_pair_vector(std::vector<std::pair<int, int>> v) {
    int res = 0;
	for (int i = 0; i < v.size(); i++) {
        int j = v[i].second;
        while (j--) {
            int score = v[i].first, k = i + 1;
			while ((k + score < v.size()) && score--) {
				v[k].second++;
				k++;
			}
        }
        res += v[i].second;
	}
    return res;
}

int main(void) {
    int res_pt1 = 0, i = 1;
    std::string ln;
	std::vector<std::pair<int, int>> pair_vector;
    while (getline(std::cin, ln)) {
		ln = ln.substr(10);
		int score = process_ln(ln);
        res_pt1 += (score > 1) ? pow(2, score - 1) : score;
        std::pair<int, int> pair = {score, 1};
		pair_vector.push_back(pair);
    }
	int res_pt2 = process_pair_vector(pair_vector);
	std::cout << "res_pt1 = " << res_pt1 << std::endl;
	std::cout << "res_pt2 = " << res_pt2 << std::endl;
    return 0;
}