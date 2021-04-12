#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

int apr = INT_MAX;

void rec(vector<int> &nums, int target, int cur, vector<int> &path, int sum,
         vector<vector<int>> &res) {
    if (cur >= nums.size()) {
        int tap = abs(target - sum);

        if (tap <= apr) {
            if (tap != apr) {
                res.clear();
                apr = tap;
            }
            res.push_back(path);
        }

        return;
    }

    rec(nums, target, cur + 1, path, sum, res);

    path.push_back(cur);
    rec(nums, target, cur + 1, path, sum + nums[cur], res);
    path.pop_back();
}

vector<vector<int>> solve(vector<int> &nums, int target) {
    vector<int> path(0, nums.size());
    vector<vector<int>> res;
    rec(nums, target, 0, path, 0, res);
    return res;
}

void prt(vector<vector<int>> &vv) {
    for (vector<int> v : vv) {
        for (int i : v) {
            cout << i << " ";
        }
        cout << endl;
    }
}

int main() {
//    vector<int> nums{1, 2, 3, 4, 5, 6, 7, 8, 9}; // 1 2; 0 3
//    vector<int> nums{};
//    vector<int> nums{1};          // 0
//    vector<int> nums{1, 2};       // 0 1
    vector<int> nums{1, 2, 3};      // 1 2

    vector<vector<int>> res = solve(nums, 5);
    prt(res);
    return 0;
}
