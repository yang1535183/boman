package com.boman.upms.rpc.constant;



/**
 * Activiti工作流 转换工具
 */
public class ActivitiUtils {/*

    *//**
     * 系统User转换成Activiti工作流UserEntity
     * @param upmsUser
     *//*
    public static UserEntity toActivitiUser(UpmsUser upmsUser){
        if(upmsUser == null){
            return null;
        }
        UserEntity ue = new UserEntity();
        ue.setId(String.valueOf(upmsUser.getUserId()));
        ue.setFirstName(upmsUser.getUsername());
        ue.setLastName(upmsUser.getRealname());
        ue.setEmail(upmsUser.getEmail());
        ue.setPassword(upmsUser.getPassword());
        ue.setRevision(1);
        return ue;
    }

    *//**
     * 系统Role转换成Activiti工作流Group
     * @param upmsRole
     *//*
    public static GroupEntity toActivitiGroup(UpmsRole upmsRole){
        if(upmsRole == null){
            return null;
        }
        GroupEntity ge = new GroupEntity();
        ge.setRevision(1);
        ge.setType("assignment");
        ge.setId(String.valueOf(upmsRole.getRoleId()));
        ge.setName(upmsRole.getName());
        return ge;
    }

    *//**
     * 系统Role列表转换成Activiti工作流Group列表
     * @param roles
     * @return
     *//*
    public static List<Group> toActivitiGroupList(List<UpmsRole> roles){
        List<Group> groups = new ArrayList<Group>();

        for(UpmsRole role : roles){
            if(role != null)
                groups.add(toActivitiGroup(role));
        }
        return groups;
    }*/
}
