JAVAC=javac -encoding UTF-8
JFLAGS = -cfe
#SOURCES = $(wildcard *.java)
SOURCES = CircularSinglyLinkedList.java
CLASSES = $(SOURCES:.java=.class)
MAIN = CircularSinglyLinkedList
EXECUTABLE = TP2.jar


PHONY: all clean

all: $(EXECUTABLE)

$(EXECUTABLE): $(CLASSES)
	jar $(JFLAGS) $@ $(MAIN) *.class

.SUFFIXES: .java .class

%.class: %.java
	$(JAVAC) $<

clean:
	$(RM) *.class
