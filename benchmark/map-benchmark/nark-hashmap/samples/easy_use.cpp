#include <stdio.h>
#include <stdlib.h>
#include <nark/easy_use_hash_map.hpp>
#include <nark/fstring.cpp>
#include <nark/hash_common.hpp>

int main() {
	nark::easy_use_hash_map<std::string, int> str2int;
	nark::easy_use_hash_map<int, int> int2int;
	str2int["111"] = 111;
	int2int[11111] = 111;
	printf("DONE\n");
	return 0;
}

