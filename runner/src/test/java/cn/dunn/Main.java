package cn.dunn;

import cn.dunn.mode.ChatGroup;
import cn.dunn.mode.FriendNexus;
import cn.dunn.mode.GroupMember;
import cn.dunn.mode.User;
import cn.dunn.mongo.*;
import cn.dunn.service.FileService;
import cn.dunn.util.MD5Util;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/9/19.
 */
public class Main {
    ApplicationContext context;
    ChatGroupRepository chatGroupRepository;
    FriendNexusRepository friendNexusRepository;
    GroupMemberRepository groupMemberRepository;
    MessageRepository messageRepository;
    UserRepository userRepository;
    MongoTemplate mongoTemplate;
    FileService fileService;

    {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        chatGroupRepository = context.getBean(ChatGroupRepository.class);
        friendNexusRepository = context.getBean(FriendNexusRepository.class);
        groupMemberRepository = context.getBean(GroupMemberRepository.class);
        messageRepository = context.getBean(MessageRepository.class);
        userRepository = context.getBean(UserRepository.class);
        mongoTemplate = context.getBean(MongoTemplate.class);
        fileService = context.getBean(FileService.class);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("422450455@qq.com");
        user.setPassword(MD5Util.MD5("123456"));
        userRepository.save(user);

        user.setUsername("522168033@qq.com");
        user.setPassword(MD5Util.MD5("123456"));
        user.setId(null);
        userRepository.save(user);

        user.setUsername("865795615@qq.com");
        user.setPassword(MD5Util.MD5("123456"));
        user.setId(null);
        userRepository.save(user);
    }

    @Test
    public void testAddfriend() {
        FriendNexus friend = new FriendNexus();
        friend.setSelf(new User("57dfff487294a0af27bd7426"));
        friend.setFriend(new User("57dfff487294a0af27bd7427"));
        friend.setLastReadTime(System.currentTimeMillis());
        friendNexusRepository.save(friend);


        friend.setId(null);
        friend.setSelf(new User("57dfff487294a0af27bd7426"));
        friend.setFriend(new User("57dfff487294a0af27bd7428"));
        friend.setLastReadTime(System.currentTimeMillis());
        friendNexusRepository.save(friend);

        friend.setId(null);
        friend.setSelf(new User("57dfff487294a0af27bd7427"));
        friend.setFriend(new User("57dfff487294a0af27bd7426"));
        friend.setLastReadTime(System.currentTimeMillis());
        friendNexusRepository.save(friend);


        friend.setId(null);
        friend.setSelf(new User("57dfff487294a0af27bd7428"));
        friend.setFriend(new User("57dfff487294a0af27bd7426"));
        friend.setLastReadTime(System.currentTimeMillis());
        friendNexusRepository.save(friend);
    }

    @Test
    public void testgetFriends() {
        List<FriendNexus> friends = friendNexusRepository.findBySelf(new User("57dfff487294a0af27bd7426"));
        List<User> collect = friends.stream().map(FriendNexus::getFriend).collect(Collectors.toList());
        collect.stream().forEach(u -> {
            System.out.println(u.getUsername());
        });
    }


    @Test
    public void testAddChatGroup() {
        ChatGroup chatGroup = new ChatGroup();
        chatGroup.setAffiche("这是麻将群");
        chatGroup.setChatGroupName("麻将群");
        chatGroup = chatGroupRepository.save(chatGroup);

        GroupMember groupMember = new GroupMember();
        groupMember.setChatGroup(chatGroup);
        groupMember.setMember(new User("57dfff487294a0af27bd7426"));
        groupMemberRepository.save(groupMember);


        chatGroup.setId(null);
        chatGroup.setAffiche("这是游戏群");
        chatGroup.setChatGroupName("游戏群");
        chatGroupRepository.save(chatGroup);

        groupMember = new GroupMember();
        groupMember.setChatGroup(chatGroup);
        groupMember.setMember(new User("57dfff487294a0af27bd7426"));
        groupMemberRepository.save(groupMember);
    }


    @Test
    public void testGetChatGroups() {
        List<ChatGroup> chatGroups = groupMemberRepository.findByMember(new User("57dfff487294a0af27bd7426")).stream().map(GroupMember::getChatGroup).collect(Collectors.toList());
        chatGroups.stream().forEach(chatGroup -> {
            System.out.println(chatGroup.getChatGroupName());
        });
    }

    @Test
    public void testAddFile() throws FileNotFoundException {
        Map<String, Object> map = new HashMap<>();
        fileService.saveFile(new FileInputStream("C:\\Users\\Administrator\\Desktop\\绘图1.vsdx"), "绘图1.vsdx", "vsdx", map);
        fileService.saveFile("".getBytes(), "", "", null);
    }

    @Test
    public void testDeleteFile() {
        fileService.deleteFile(() -> "57e1247372942202601ba652");
    }

    @Test
    public void testGetOneFile() throws Exception {
        fileService.getOneFile("57e1247372942202601ba652");
    }
}

