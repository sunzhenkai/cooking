//
// Created by Wii on 2021/4/8.
//
#include <iostream>
#include <vector>

using namespace std;

class Node {
public:
    int val;
    Node *next;

    Node() = default;

    Node(int v) : val(v), next(nullptr) {};
};

void printLL(Node *root) {
    while (root != nullptr) {
        cout << root->val << " ";
        root = root->next;
    }
    cout << endl;
}

void qsort(Node *root) {
    if (root == nullptr || root->next == nullptr) {
        return;
    }

    Node *cur = root->next;         // 遍历链表的游标
    int pivot = root->val;          // 以第一个元素为基准，划分链表
    Node *partition = root;         // 记录链表划分的边界
    while (cur != nullptr) {
        if (cur->val <
            pivot) {     // 如果当前值比基准小，则和partition的值交换，同时将partition指向下一个位置
            partition = partition->next;
            swap(partition->val, cur->val);
        }
        cur = cur->next;
    }

    swap(root->val, partition->val);
    Node *pn = partition->next;
    partition->next = nullptr;   // 划分为两个链表
    qsort(root);
    qsort(pn);
    partition->next = pn;        // 由于只改动了链表节点记录的值，可以通过保存节点地址，然后赋值，来拼接链表
}

Node *buildLL(vector<int> &nums) {
    Node *root = nullptr, *cur = nullptr;
    for (auto i : nums) {
        Node *n = new Node(i);
        if (root == nullptr) {
            root = n;
            cur = n;
        }
        cur->next = n;
        cur = n;
    }

    if (cur != nullptr) {
        cur->next = nullptr;
    }

    return root;
}

int main() {
//     vector<int> nums{};
//     vector<int> nums{3};
//     vector<int> nums{3, 3, 1, 1, 2, 2, 2, 3};
//    vector<int> nums{3, 3, 1, 1, 2, 2, 2, 3};
//    vector<int> nums{3, 5, 2, 5, 6, 9, 0, -1};
    vector<int> nums{3, 1, 2, 5, 6, 9, 0, -1};
    Node *root = buildLL(nums);
    printLL(root);
    qsort(root);
    printLL(root);
    return 0;
}