package golem.mud.story.das.model;

import golem.mud.common.das.model.AbstractDO;

public abstract class AbstractComponentDO extends AbstractDO {

    protected Integer entityId;

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
}
