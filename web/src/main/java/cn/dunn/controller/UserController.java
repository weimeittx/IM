package cn.dunn.controller;

import cn.dunn.mode.ChatGroup;
import cn.dunn.mode.FriendNexus;
import cn.dunn.mode.GroupMember;
import cn.dunn.mode.User;
import cn.dunn.mongo.FriendNexusRepository;
import cn.dunn.mongo.GroupMemberRepository;
import cn.dunn.util.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/9/22.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private FriendNexusRepository friendNexusRepository;

    @Resource
    private GroupMemberRepository groupMemberRepository;

    @RequestMapping("/getFriends")
    @ResponseBody
    public List<User> getFriends(HttpServletRequest request) {
        return friendNexusRepository.findBySelf(WebUtil.LoginUser(request)).stream().map(FriendNexus::getFriend).collect(Collectors.toList());
    }

    @RequestMapping("/getChatGroups")
    @ResponseBody
    public List<ChatGroup> getChatGroups(HttpServletRequest request) {
        return groupMemberRepository.findByMember(WebUtil.LoginUser(request)).stream().map(GroupMember::getChatGroup).distinct().collect(Collectors.toList());
    }
}
