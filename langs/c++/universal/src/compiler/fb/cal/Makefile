DEPS_DIR:=../../../../deps
LIB_DIR:=$(DEPS_DIR)/lib
BIN_DIR:=$(DEPS_DIR)/bin
PATH:=$(DEPS_DIR)/bin:$(PATH)
LD_LIBRARY_PATH=$(LIB_DIR)
LIBRARY_PATH=$(LIB_DIR)
DYLD_LIBRARY_PATH=$(LIB_DIR)

.PHONY: calc

calc: cal.l cal.y
#	$(eval PATH=$DEPS_DIR/bin:$PATH)
#	$(eval LD_LIBRARY_PATH=$(LIB_DIR))
#	$(eval LIBRARY_PATH=$(LIB_DIR))
#	$(eval DYLD_LIBRARY_PATH=$(LIB_DIR))
#	@echo $(DYLD_LIBRARY_PATH)
#	bison -d cal.y
#	flex cal.l
#	@ls $(LIB_DIR)
	@export DYLD_LIBRARY_PATH=$(DYLD_LIBRARY_PATH) && \
		export LIBRARY_PATH=$(LIBRARY_PATH) && \
		gcc -Wno-parentheses -L $(LIB_DIR) -o calc lex.yy.c -lfl
	@export DYLD_LIBRARY_PATH=$(DYLD_LIBRARY_PATH) && \
		export LIBRARY_PATH=$(LIBRARY_PATH) && \
	./calc

