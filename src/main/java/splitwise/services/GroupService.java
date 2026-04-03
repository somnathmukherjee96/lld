package splitwise.services;

import splitwise.models.Group;

import java.util.HashMap;
import java.util.Map;

public class GroupService {
    private final Map<String, Group> groupRegistry = new HashMap<>();

    public Group getGroupById(String groupId) {
        if (!groupRegistry.containsKey(groupId))
            throw new IllegalArgumentException("Group not found!");

        return groupRegistry.get(groupId);
    }

    public void addNewGroup(String groupId, Group group) {
        groupRegistry.put(groupId, group);
    }
}
