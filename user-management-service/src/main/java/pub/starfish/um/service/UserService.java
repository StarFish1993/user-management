package pub.starfish.um.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pub.starfish.um.dal._do.User;
import pub.starfish.um.dal._do.UserExample;
import pub.starfish.um.dal.mapper.UserMapper;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = getUserByEmailOrPhoneOrUsername(s);
        if(user==null){

        }
        return null;
    }

    /**
     * 根据email、phone或username查找用户
     * @param query
     * email or phone or username
     * @return
     * 用户信息，找不到返回null
     */
    public User getUserByEmailOrPhoneOrUsername(String query){
        UserExample userExample = new UserExample();
        userExample.or().andEmailEqualTo(query);
        userExample.or().andUsernameEqualTo(query);
        userExample.or().andPhoneEqualTo(query);
        List<User> users = userMapper.selectByExample(userExample);
        assert users.size()<=1;
        return users.size()==0?null:users.get(0);
    }

}
