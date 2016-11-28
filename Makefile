SRC=./src

all: Chat

Chat: $(SRC)/chat/*.java $(SRC)/GUI/*.java
	javac $(SRC)/chat/*.java
	javac $(SRC)/GUI/*.java
	javac $(SRC)/*.java

clean:
	rm -f $(SRC)/chat/*.class
	rm -f $(SRC)/GUI/*.class
	rm -f $(SRC)/*.class

