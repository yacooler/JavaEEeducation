package ru.vyazankin.mappers;

public interface BaseMapper<E, D> {
    E toEntity(D dto);
    D toDto(E entity);
}
