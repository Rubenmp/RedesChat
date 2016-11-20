SRC=./src/

all: Chat

Chat: $(SRC)/chat/*.java
	javac $(SRC)/chat/*.java
	javac $(SRC)/*.java

clean:
	rm -f $(SRC)/chat/*.class
	rm -f $(SRC)/*.class

