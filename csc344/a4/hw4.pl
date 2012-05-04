instance(s, string).
instance(l, list).
instance(i, int).

subClass(object, list).
subClass(collection, list).
subClass(iterable, list).
subClass(object, string).

hasMethod(string, length).
hasMethod(string, charAt).
hasMethod(string, equals).
hasMethod(string, indexOf).

hasMethod(object, finalize).
hasMethod(object, getClass).
hasMethod(object, notify).
hasMethod(object, wait).

hasMethod(list, size).
hasMethod(list, add).
hasMethod(list, hashCode).
hasMethod(list, get).

hasMethod(collection, size).
hasMethod(collection, add).
hasMethod(collection, hashCode).

hasMethod(iterable, iterator).

print(X).

check(X, M):- instance(X, C), hasMethod(C, M); instance(X, C), subClass(E, C), hasMethod(E, M).

