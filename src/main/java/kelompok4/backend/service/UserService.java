package kelompok4.backend.service;

import kelompok4.backend.entity.User;
import kelompok4.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object create(User user){
        return userRepository.save(user);
    }

    public Object getListData(){
        return userRepository.findAll();
    }

    public Object getDataDetail(Long id){
        return userRepository.findById(id).get();
    }

    public void deleted(Long id){
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }
    public User update(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());
        if (existingUser.isPresent()) {
            User u = existingUser.get();
            u.setName(user.getName());
            u.setAge(user.getAge());
            u.setEmail(user.getEmail());
            return userRepository.save(u);
        } else {
            throw new RuntimeException("User not found");
        }
    }


}
