package com.github.linyuzai.arkevent.support.condition.group;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.support.condition.group.exclude.ArkEventGroupExclude;
import com.github.linyuzai.arkevent.support.condition.group.exclude.OnArkEventGroupExclude;
import com.github.linyuzai.arkevent.support.condition.group.include.ArkEventGroupInclude;
import com.github.linyuzai.arkevent.support.condition.group.include.OnArkEventGroupInclude;

public class GroupArkEventConditionFilter implements ArkEventConditionFilter {

    private String[] groups;

    private boolean forceGroupCondition;

    public GroupArkEventConditionFilter(String[] groups, boolean forceGroupCondition) {
        this.groups = groups;
        this.forceGroupCondition = forceGroupCondition;
    }

    @Override
    public boolean filter(ArkEvent event) {
        if (event instanceof ArkEventGroupInclude) {
            ArkEventGroupInclude arkEventGroupInclude = (ArkEventGroupInclude) event;
            String[] targets = arkEventGroupInclude.arkEventIncludeGroups();
            return filter0(targets);
        } else if (event instanceof ArkEventGroupExclude) {
            ArkEventGroupExclude arkEventGroupExclude = (ArkEventGroupExclude) event;
            String[] targets = arkEventGroupExclude.arkEventExcludeGroups();
            return filter0(targets);
        } else if (event.getClass().isAssignableFrom(OnArkEventGroupInclude.class)) {
            OnArkEventGroupInclude onArkEventGroupInclude = event.getClass().getAnnotation(OnArkEventGroupInclude.class);
            String[] targets = onArkEventGroupInclude.value();
            return filter0(targets);
        } else if (event.getClass().isAssignableFrom(OnArkEventGroupExclude.class)) {
            OnArkEventGroupExclude onArkEventGroupExclude = event.getClass().getAnnotation(OnArkEventGroupExclude.class);
            String[] targets = onArkEventGroupExclude.value();
            return filter0(targets);
        } else {
            return !forceGroupCondition;
        }
    }

    private boolean filter0(String[] targets) {
        for (String target : targets) {
            for (String group : groups) {
                if (target.equals(group)) {
                    return true;
                }
            }
        }
        return false;
    }
}
