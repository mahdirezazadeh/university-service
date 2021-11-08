package ir.mahdi.universityservice.service.impl;

import ir.mahdi.universityservice.base.service.impl.BaseServiceImpl;
import ir.mahdi.universityservice.domain.Role;
import ir.mahdi.universityservice.repository.RoleRepository;
import ir.mahdi.universityservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer, RoleRepository> implements RoleService {

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Role save(Role admin) {
        return super.save(admin);
    }


    @Override
    @Transactional
    public List<Role> saveAll(Collection<Role> admins) {
        return super.saveAll(admins);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        super.deleteById(id);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }

}
