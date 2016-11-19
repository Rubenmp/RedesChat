SRC=./src/

all: Chat

Chat: $(SRC)/*.java
	javac *.java

clean:
    rm -f *.class
