package ru.egupov.accountingworkinghours.util.mapper;

import java.util.List;

public interface MapperImpl <E, D>{
    List<E> toEntity(List<D> dto);
    E toEntity(D dto);

    List<D> toDto(List<E> entity);
    D toDto(E entity);
}
