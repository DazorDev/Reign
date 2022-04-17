package dazor.framework.interfaces;

public interface Identifiable {
	
    int getId();

    int setId(int id);

    Identifiable getPrev();

    Identifiable setPrev(Identifiable prev);

    Identifiable getNext();

    Identifiable setNext(Identifiable next);
}
