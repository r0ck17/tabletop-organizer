package by.javaguru.mapper;

public interface Mapper<T, S> {
    S mapFrom(T object);
}
