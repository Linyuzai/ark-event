package com.github.linyuzai.arkevent.support.condition.group;

import com.github.linyuzai.arkevent.ArkEvent;
import com.github.linyuzai.arkevent.ArkEventConditionFilter;
import com.github.linyuzai.arkevent.support.condition.group.exclude.ArkEventGroupExclude;
import com.github.linyuzai.arkevent.support.condition.group.exclude.OnArkEventGroupExclude;
import com.github.linyuzai.arkevent.support.condition.group.include.ArkEventGroupInclude;
import com.github.linyuzai.arkevent.support.condition.group.include.OnArkEventGroupInclude;

public class GroupArkEventConditionFilter implements ArkEventConditionFilter {

    private String[] groups;

    private boolean requireGroupCondition;

    public GroupArkEventConditionFilter(String[] groups, boolean requireGroupCondition) {
        this.groups = groups == null ? new String[]{} : groups;
        this.requireGroupCondition = requireGroupCondition;
    }

    @Override
    public boolean filter(ArkEvent event, Object... args) {
        boolean hasInclude = false;
        boolean hasExclude = false;
        String[] includes = null;
        String[] excludes = null;
        if (event instanceof ArkEventGroupInclude) {
            hasInclude = true;
            ArkEventGroupInclude arkEventGroupInclude = (ArkEventGroupInclude) event;
            includes = arkEventGroupInclude.arkEventIncludeGroups();
        } else {
            OnArkEventGroupInclude onArkEventGroupInclude = event.getClass().getAnnotation(OnArkEventGroupInclude.class);
            if (onArkEventGroupInclude != null) {
                hasInclude = true;
                includes = onArkEventGroupInclude.value();
            }
        }
        if (event instanceof ArkEventGroupExclude) {
            hasExclude = true;
            ArkEventGroupExclude arkEventGroupExclude = (ArkEventGroupExclude) event;
            excludes = arkEventGroupExclude.arkEventExcludeGroups();
        } else {
            OnArkEventGroupExclude onArkEventGroupExclude = event.getClass().getAnnotation(OnArkEventGroupExclude.class);
            if (onArkEventGroupExclude != null) {
                hasExclude = true;
                excludes = onArkEventGroupExclude.value();
            }
        }
        if (hasInclude && hasExclude) {
            return include(includes) && exclude(excludes);
        } else if (hasInclude) {
            return include(includes);
        } else if (hasExclude) {
            return exclude(excludes);
        } else {
            return !requireGroupCondition;
        }
    }

    private boolean include(String[] targets) {
        if (targets == null) {
            return false;
        }
        for (String target : targets) {
            for (String group : groups) {
                if (target.equals(group)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exclude(String[] targets) {
        if (targets == null) {
            return true;
        }
        for (String target : targets) {
            for (String group : groups) {
                if (target.equals(group)) {
                    return false;
                }
            }
        }
        return true;
    }
}
