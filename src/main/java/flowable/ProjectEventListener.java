package flowable;

import org.flowable.common.engine.api.delegate.event.FlowableEngineEventType;
import org.flowable.common.engine.api.delegate.event.FlowableEvent;
import org.flowable.common.engine.api.delegate.event.FlowableEventListener;
import org.flowable.common.engine.impl.event.FlowableEngineEventImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author cf
 * @Date 2019/4/3 13:53
 **/
@Service
@SpringBootApplication
@ServletComponentScan
@Import(value = SpringUtil.class)
public class ProjectEventListener implements FlowableEventListener {
    /**
     * Called when an event has been fired
     *
     * @param event the event
     */
    @Override
    public void onEvent(FlowableEvent event) {
        FlowableEngineEventType type = (FlowableEngineEventType) event.getType();
        if (event instanceof FlowableEngineEventImpl) {
            FlowableEngineEventImpl entityEvent = (FlowableEngineEventImpl) event;
            switch (type) {
                case TASK_CREATED:
                    taskCreated(entityEvent);
                    break;
                case PROCESS_COMPLETED:
                    processCompleted(entityEvent);
                    break;
                default:
            }
        }
    }

    private void processCompleted(FlowableEngineEventImpl entityEvent) {

    }

    private void taskCreated(FlowableEngineEventImpl entityEvent) {
    }

    /**
     * @return whether or not the current operation should fail when this listeners execution throws an exception.
     */
    @Override
    public boolean isFailOnException() {
        return false;
    }

    /**
     * @return Returns whether this event listener fires immediately when the event occurs or
     * on a transaction lifecycle event (before/after commit or rollback).
     */
    @Override
    public boolean isFireOnTransactionLifecycleEvent() {
        return false;
    }

    /**
     * @return if non-null, indicates the point in the lifecycle of the current transaction when the event should be fired.
     */
    @Override
    public String getOnTransaction() {
        return null;
    }
}
