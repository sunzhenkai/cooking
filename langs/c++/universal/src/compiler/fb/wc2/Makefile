DEPS_DIR:=../../../../deps
LIB_DIR:=$(DEPS_DIR)/lib
BIN_DIR:=$(DEPS_DIR)/bin
INCLUDE_DIR:=$(DEPS_DIR)/include
PATH:=$(DEPS_DIR)/bin:$(PATH)
LD_LIBRARY_PATH=$(LIB_DIR)
LIBRARY_PATH=$(LIB_DIR)
DYLD_LIBRARY_PATH=$(LIB_DIR)
CXXFLAGS=-std=c++11 -L $(LIB_DIR) -I $(INCLUDE_DIR) -Wno-unused-command-line-argument
FBF=parser lexer
PJF=driver
FILES=$(addsuffix .cpp, $(PJF))

.PHONY: wc

all: wc

wc: $(FILES)
	$(MAKE) $(FBF)

parser: parser.yy
	@export PATH=$(PATH) && \
		export DYLD_LIBRARY_PATH=$(DYLD_LIBRARY_PATH) && \
		export LIBRARY_PATH=$(LIBRARY_PATH) && \
		bison -v -d $< && \
		$(CXX) $(CXXFLAGS) -c -o parser.o parser.tab.cc && \
		echo "[PROCESS] compile parser done"

lexer: lexer.l
	@export PATH=$(PATH) && \
		export DYLD_LIBRARY_PATH=$(DYLD_LIBRARY_PATH) && \
		export LIBRARY_PATH=$(LIBRARY_PATH) && \
		flex -V && \
		flex --outfile=lexer.yy.cc $< && \
		$(CXX) $(CXXFLAGS) -c -o lexer.o lexer.yy.cc && \
      	echo "[PROCESS] compile lexer done"

clean:
	@rm parser.tab.* parser.output location.hh position.hh stack.hh lexer.yy.cc