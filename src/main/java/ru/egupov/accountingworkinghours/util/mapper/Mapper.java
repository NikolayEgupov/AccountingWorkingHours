package ru.egupov.accountingworkinghours.util.mapper;

/**
 * Общий интерфейс для всех mapper
 * @param <E> класс entity
 * @param <D> класс dto
 */
public interface Mapper <E, D>{
    E toEntity(D dto);
    D toDto(E entity);
}
