package cbook.service;

import cbook.domain.entities.User;
import cbook.domain.models.service.UserServiceModel;
import cbook.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserServiceImpl() {
    }

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserServiceModel entity) {

        User user = this.modelMapper.map(entity,User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        this.userRepository.add(user);

    }
    @Override
    public List<UserServiceModel> getAll() {

      return this.userRepository.getAll().stream().map(a-> this.modelMapper.map(a,UserServiceModel.class))
                .collect(Collectors.toList());
    }
    @Override
    public UserServiceModel getById(String id) {
        return this.modelMapper.map(this.userRepository.getById(id),UserServiceModel.class);
    }

    @Override
    public UserServiceModel getByName(String name) {
        return this.modelMapper.map(this.userRepository.getByName(name),UserServiceModel.class);
    }

    @Override
    public void update(UserServiceModel user) {

        this.userRepository.update(this.modelMapper.map(user,User.class));

    }
}
