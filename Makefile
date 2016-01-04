all: clean deps build run

clean:
	rebar clean

deps:
	rebar get-deps

build:
	rebar compile

run:
	./deps/lfe/bin/lfe -pa ./ebin \
	-sname test -s top

.PHONY: deps
