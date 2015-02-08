JAVAC=javac -encoding UTF-8
JFLAGS = -cfe
SOURCES = $(wildcard *.java)
CLASSES = $(SOURCES:.java=.class)
MAIN = HashMapTest
EXECUTABLE = TP3-2.jar


PHONY: all clean

all: $(EXECUTABLE)

$(EXECUTABLE): $(CLASSES)
	jar $(JFLAGS) $@ $(MAIN) *.class

.SUFFIXES: .java .class

%.class: %.java
	$(JAVAC) $<

clean:
	$(RM) *.class
