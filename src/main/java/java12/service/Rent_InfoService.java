package java12.service;

import java12.entity.Rent_Info;

import java.util.List;

public interface Rent_InfoService {
    List<Rent_Info> getAllRent_Infos();
    Rent_Info getRent_InfoById(Long id);
}
