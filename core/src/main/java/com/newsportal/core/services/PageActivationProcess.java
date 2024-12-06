package com.newsportal.core.services;


import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.Objects;


@Component(service = WorkflowProcess.class, property = {"process.label = Page Content Activation."})
public class PageActivationProcess implements WorkflowProcess {


    private static final Logger LOG = LoggerFactory.getLogger(PageActivationProcess.class);

    @Reference
    Replicator replicator;


    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

        Session session = workflowSession.adaptTo(Session.class);
        String payloadPath = workItem.getWorkflowData().getPayload().toString();
        try {
            ResourceResolver resourceResolver = workflowSession.adaptTo(ResourceResolver.class);
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
            Resource resource = pageManager.getPage(payloadPath).getContentResource();

            if (Objects.nonNull(resource)) {
                ModifiableValueMap modifiableValueMap = resource.adaptTo(ModifiableValueMap.class);
                if (Objects.nonNull(modifiableValueMap)) {
                    if (!modifiableValueMap.containsKey("isWorkflowProcessed")) {
                        modifiableValueMap.put("isWorkflowProcessed", true);
                        resourceResolver.commit();
                        replicator.replicate(session, ReplicationActionType.ACTIVATE, payloadPath);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}