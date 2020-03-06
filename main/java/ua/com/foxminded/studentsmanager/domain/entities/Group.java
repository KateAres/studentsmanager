package ua.com.foxminded.studentsmanager.domain.entities;

public class Group {

    private String groupName;
    private int groupId;

    public Group(int groupId, String groupName) {
        this.groupName = groupName;
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }
}
