#include <iostream>
#include <vector>

using namespace std;

void swap(int &a, int &b) {
    int t = a;
    a = b;
    b = t;
}

class Node {
public:
    int val;
    Node *next;
    Node() = default;
    Node(int v) : val(v), next(nullptr) {};
};

struct Info {
    Node *head;
    Node *tail;
};

/**
 * 单链表快速排序，选取链表第一个元素作为基准元素，将链表以基准元素划分为两个链表
 * 对两链表进行递归排序，最后将排序好的链表和基准元素拼接
 */
Info qsortSolver(Node *root){
    if (root == nullptr || root->next == nullptr) {
        return Info{root, root};
    }

    Node *pivot = root;
    Node leftHead, rightHead;
    Node *leftTail = &leftHead, *rightTail = &rightHead;
    Node *cur = root->next;

    while (cur != nullptr) {
        if (cur->val < pivot->val) {
            leftTail->next = cur;
            leftTail = cur;
        } else {
            rightTail->next = cur;
            rightTail = cur;
        }
        cur = cur->next;
    }

    // 将两个链表队尾元素的 next 字段置为 nullptr
    leftTail->next = nullptr;
    rightTail->next = nullptr;

    Info li = qsortSolver(leftHead.next);
    Info ri = qsortSolver(rightHead.next);

    // 拼接两个链表，左 + pivot + 右
    if (li.tail != nullptr) li.tail->next = pivot;
    pivot->next = ri.head;

    // 判断左右
    return Info{li.head == nullptr ? pivot : li.head, ri.tail == nullptr ? pivot : ri.tail};
}

Node *qsort(Node *root) {
    return qsortSolver(root).head;
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

void printLL(Node *root) {
    while (root != nullptr) {
        cout << root->val << " ";
        root = root->next;
    }
    cout << endl;
}

int main() {
    vector<int> nums{3, 1, 2, 5, 6, 9, 0, -1};
    // vector<int> nums{};
    // vector<int> nums{3};
    // vector<int> nums{3, 3, 1, 1, 2, 2, 2, 3};
    Node *root = buildLL(nums);
    Node *head = qsort(root);
    printLL(head);
    return 0;
}