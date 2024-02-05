package java12.dao;

import java12.entity.Agency;

import java.util.List;

public interface AgencyDao {
    String saveAgency(Agency newAgency);
    String removeAgencyById(long id);
    List<Agency> getAllAgency();
    Agency getAgencyById(Long agencyId);
    String updateAgencyById(Long id, Agency agency);
}
