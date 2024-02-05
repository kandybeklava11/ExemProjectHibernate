package java12.service.impl;

import java12.dao.AgencyDao;
import java12.dao.impl.AgencyDaoImpl;
import java12.entity.Agency;
import java12.service.AgencyService;

import java.util.List;

public class AgencyServiceImpl implements AgencyService {
    private final AgencyDao agencyDao=new AgencyDaoImpl();
    @Override
    public String saveAgency(Agency newAgency) {
        return agencyDao.saveAgency(newAgency);
    }

    @Override
    public String removeAgencyById(long id) {
        return agencyDao.removeAgencyById(id);
    }

    @Override
    public List<Agency> getAllAgency() {
        return agencyDao.getAllAgency();
    }

    @Override
    public Agency getAgencyById(Long agencyId) {
        return agencyDao.getAgencyById(agencyId);
    }

    @Override
    public String updateAgencyById(Long id, Agency agency) {
        return agencyDao.updateAgencyById(id,agency);
    }
}
