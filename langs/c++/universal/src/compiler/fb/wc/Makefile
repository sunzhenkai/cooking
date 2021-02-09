DEPS_DIR:=../../../../deps
LIB_DIR:=$(DEPS_DIR)/lib
BIN_DIR:=$(DEPS_DIR)/bin
PATH:=$(DEPS_DIR)/bin:$(PATH)
LD_LIBRARY_PATH=$(LIB_DIR)
LIBRARY_PATH=$(LIB_DIR)
DYLD_LIBRARY_PATH=$(LIB_DIR)

#.PHONY: wc-file

all: wc-file

wc-file: wc-file.l
	@flex wc-file.l
	@export DYLD_LIBRARY_PATH=$(DYLD_LIBRARY_PATH) && \
    		export LIBRARY_PATH=$(LIBRARY_PATH) && \
    		gcc -Wno-parentheses -L $(LIB_DIR) -o wc-file lex.yy.c -lfl
	@export DYLD_LIBRARY_PATH=$(DYLD_LIBRARY_PATH) && \
		export LIBRARY_PATH=$(LIBRARY_PATH) && \
		./wc-file