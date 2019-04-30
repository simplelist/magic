package flowable;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import common.ValidateUtil;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.ParallelGateway;
import org.flowable.bpmn.model.UserTask;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static flowable.CommonConstants.*;


/**
 * @Author kun
 * @DATETIME 2019-03-02 16:37
 * 通用的工作流工具类
 */
@Service
public class ActService {
    public final static String PARALLELGATEWAY = "PARALLELGATEWAY";
    private final static String USERTASK = "USERTASK";
    private Log log = LogFactory.get();
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    @Lazy
    private ProcessEngine processEngine;

    public List<String> getAllTemplateDefId() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionTenantId(Convert.toStr(0)).list();
        return list.stream().map(ProcessDefinition::getId).collect(Collectors.toList());
    }

    public Deployment deployFromModel(int factoryId, BpmnModel bpmnModel, String oldProDefId) {
        String resourceName = repositoryService.createProcessDefinitionQuery().processDefinitionId(oldProDefId).singleResult().getResourceName();
        return repositoryService.createDeployment()
                .addBpmnModel(StrUtil.format(FACTORY_WORKFLOW_NAME, factoryId, resourceName), bpmnModel)
                .tenantId(Convert.toStr(factoryId))
                .category(FACTORY_WORKFLOW)
                .deploy();
    }

    public String findDefIdByDeployId(String deployId) {
        return repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployId)
                .singleResult().getId();
    }

    public ProcessDefinition findDefByKeyAndCategory(String key) {
        return repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).processDefinitionCategory(FACTORY_WORKFLOW_TEMPLATE).singleResult();
    }

    /**
     * 启动流程实例
     *
     * @param productId 用作查询某种产品的件数
     * @param projectId 用作查询当前项目是否还有任务在执行
     * @return 流程实例id
     */
    public String startFromModel(String newProDefId, int productId, int projectId) {
        String instanceId = runtimeService.startProcessInstanceById(newProDefId).getId();
        log.debug("流程实例已经启动【{}】", instanceId);
        runtimeService.setVariable(instanceId, PROCESS_VARIABLE_PRODUCT_ID, productId);
        runtimeService.setVariable(instanceId, PROCESS_VARIABLE_PROJECT_ID, projectId);
        log.debug("流程实例变量已经设置【{}】", productId);
        return instanceId;
    }

    public String getNewDefId(Integer factoryId, BpmnModel bpmnModel, String oldProDefId) {
        Deployment deployment = deployFromModel(factoryId, bpmnModel, oldProDefId);
        log.debug("流程定义(从 bpmnModel)已经部署,部署id为【{}】", deployment.getId());
        String newProDefId = findDefIdByDeployId(deployment.getId());
        repositoryService.setProcessDefinitionCategory(newProDefId, PRO_DEF_CATEGORY);
        log.debug("新的流程定义id为【{}】,设定当前流程定义为运行时配置", newProDefId);
        return newProDefId;
    }

    /**
     * 工厂模板设置
     *
     * @param xmlName
     * @return
     */
    public Deployment deployTemplate(String xmlName) {
        return repositoryService.createDeployment()
                .tenantId("0")
                .category(FACTORY_WORKFLOW_TEMPLATE)
                .addClasspathResource(xmlName)
                .deploy();
    }

    /**
     * @return java.lang.String
     * @Author cf
     * @Description 获取流程中的流程图片
     * @Date 18:09 2019/3/18
     * @Param [defId] 流程定义ID
     **/
    public String generateImageByDefId(String defId) {
        BpmnModel bpmnModel = getModelByDefId(defId);
        ProcessEngineConfigurationImpl engine = (ProcessEngineConfigurationImpl) processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engine.getProcessDiagramGenerator();
        //获取到图片
        InputStream in = diagramGenerator.generatePngDiagram(bpmnModel, false);
        return cn.hutool.core.codec.Base64.encode(in);
    }


    /**
     * 为工艺路线设置工序办理候选人
     *
     * @param assigneeList
     * @param proDefId
     * @param taskCategoryMap 任务分类,任务名字:分类
     * @return
     */
    public BpmnModel setCandidateUsersByTaskName(List<AssigneeResponseVO> assigneeList, String proDefId, Map<String, Integer> taskCategoryMap) {
        BpmnModel bpmnModel = getModelByDefId(proDefId);
        Collection<FlowElement> flowElement = bpmnModel.getMainProcess().getFlowElements();
        Map<String, List<String>> assigneeMap = new HashMap<>();
        for (AssigneeResponseVO assigneeResponseVO : assigneeList) {
            assigneeMap.put(assigneeResponseVO.getName(), assigneeResponseVO.getAssigneeList());
        }
        for (FlowElement element : flowElement) {
            if (element instanceof UserTask) {
                String name = element.getName();
                if (!assigneeMap.get(name).isEmpty()) {
                    ((UserTask) element).setCandidateUsers(assigneeMap.get(name));
                    ((UserTask) element).setCategory(Convert.toStr(taskCategoryMap.get(name)));
                    log.debug("为任务【{}】设置待办人【{}】,任务分类【{}】", name, assigneeMap.get(name), ((UserTask) element).getCategory());
                }

            }
        }
        return bpmnModel;
    }

    /**
     * @return java.util.Map<java.lang.String ,   java.util.List   <   java.lang.String>>
     * @Author cf
     * @Description 获取xml文件中的任务与任务代理人
     * @Date 15:07 2019/3/20
     * @Param [proDefId]
     **/
    public Map<String, List<String>> getCandidateUsersByTaskName(String proDefId) {
        Map<String, List<String>> candidateUserMap = new LinkedHashMap<>();
        BpmnModel bpmnModel = getModelByDefId(proDefId);
        Collection<FlowElement> flowElement = bpmnModel.getMainProcess().getFlowElements();
        for (FlowElement element : flowElement) {
            if (element instanceof UserTask) {
                String name = element.getName();
                List<String> candidateUsers = ((UserTask) element).getCandidateUsers();
                candidateUserMap.put(name, candidateUsers);
            }
        }
        return candidateUserMap;
    }

    public boolean existsParallelGateway(String proDefId) {
        boolean exists = false;
        BpmnModel bpmnModel = getModelByDefId(proDefId);
        Collection<FlowElement> flowElement = bpmnModel.getMainProcess().getFlowElements();
        for (FlowElement element : flowElement) {
            if (element instanceof ParallelGateway) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public List<HistoricActivityInstance> findHistoryActivityInstanceByEndTime(String processInstanceId) {
        return historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceEndTime()
                .asc()
                .list();
    }

    public List<HistoricActivityInstance> findHistoryActivityInstanceByStartTime(String processInstanceId) {
        return historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();
    }

    public List<HistoricActivityInstance> findFinishedHistoryActivityInstanceByStartTime(String processInstanceId) {
        return historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime()
                .finished()
                .asc()
                .list();
    }

    public List<String> tasksAndGatewayList(String processInstanceId, List<HistoricActivityInstance> historyActivityInstance) {
        List<String> list = new ArrayList<>();

        historyActivityInstance.stream().forEach(historicActivityInstance -> {
            String activityType = historicActivityInstance.getActivityType();
            if (PARALLELGATEWAY.equalsIgnoreCase(activityType)) {
                list.add(PARALLELGATEWAY);
            }
            if (USERTASK.equalsIgnoreCase(activityType)) {
                list.add(historicActivityInstance.getActivityName());
            }
        });
        return list;
    }


    public List<String> getGatewayBeforeTask(String processInstanceId) {
        List<HistoricActivityInstance> historyActivityInstance = findHistoryActivityInstanceByEndTime(processInstanceId);
        List<String> list = tasksAndGatewayList(processInstanceId, historyActivityInstance);
        List<String> nameList = new ArrayList<>();
        int index = list.indexOf(PARALLELGATEWAY);
        for (int i = index; i < list.size(); i++) {
            if (i != index) {
                if (PARALLELGATEWAY.equals(list.get(i))) {
                    nameList.add(list.get(i - 1));
                }
            }
        }
        return nameList;
    }

    public boolean existsGateway(String startPoint, String endPoint, String processInstanceId) {
        List<HistoricActivityInstance> historyActivityInstance = findHistoryActivityInstanceByStartTime(processInstanceId);
        List<String> gatewayList = tasksAndGatewayList(processInstanceId, historyActivityInstance);
        int startIndex = gatewayList.indexOf(startPoint);
        int endIndex = gatewayList.indexOf(endPoint);
        List<String> subList = gatewayList.subList(startIndex, endIndex);
        return subList.indexOf(PARALLELGATEWAY) != -1;
    }

    public BpmnModel getModelByDefId(String proDefId) {
        return repositoryService.getBpmnModel(proDefId);
    }

    /**
     * 找到当前用户要办理的二维码对应的任务
     *
     * @param employeeId
     * @param proInstanceId
     * @return
     */
    public List<Task> findTaskByEmployeeId(int employeeId, String proInstanceId) {
        return taskService.createTaskQuery()
                .processInstanceId(proInstanceId)
                .taskCandidateOrAssigned(Convert.toStr(employeeId))
                .orderByTaskCreateTime().desc()
                .list();
    }

    /**
     * 用在列表查询传递当前tab页的category
     *
     * @param employeeId
     * @param category
     * @return
     */
    public List<Task> findTaskByEmployeeIdAndCategory(int employeeId, int category) {
        return taskService.createTaskQuery()
                .taskCandidateOrAssigned(Convert.toStr(employeeId))
                .taskCategory(Convert.toStr(category))
                .list();
    }

    /**
     * 轮到我但是未开始的任务
     *
     * @param employeeId
     * @param category
     * @param productId  FlowProduct表的id,表示一类产品
     * @return
     */
    public List<Task> findUnStartTask(int employeeId, int category, int productId) {
        return unStartTask(employeeId, category, productId).list();
    }

    public List<Task> findUnStartAndProcessTask(int employeeId, int category, int productId) {
        return unStartAndProcessTask(employeeId, category, productId).list();
    }

    private TaskQuery unStartTask(int employeeId, int category, int productId) {
        return taskService.createTaskQuery()
                .taskCandidateUser(Convert.toStr(employeeId))
                .taskCategory(Convert.toStr(category))
                .processVariableValueEquals(PROCESS_VARIABLE_PRODUCT_ID, productId);
    }

    private TaskQuery unStartAndProcessTask(int employeeId, int category, int productId) {
        return taskService.createTaskQuery()
                .taskCandidateOrAssigned(Convert.toStr(employeeId))
                .taskCategory(Convert.toStr(category))
                .processVariableValueEquals(PROCESS_VARIABLE_PRODUCT_ID, productId);
    }


    public List<HistoricTaskInstance> findMyHisTask(int employeeId, Integer category) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .finished()
                .taskAssignee(Convert.toStr(employeeId))
                .taskCategory(Convert.toStr(category))
                .list();
    }

    public List<HistoricTaskInstance> findMyHisTaskCategory(int employeeId, int category) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .finished()
                .taskAssignee(Convert.toStr(employeeId))
                .taskCategory(Convert.toStr(category))
                .list();
    }

    public long selectCountUnStartTask(int employeeId, int category, int productId) {
        return unStartTask(employeeId, category, productId).count();
    }

    /**
     * 我正在做的任务
     *
     * @param employeeId
     * @param category
     * @return
     */
    public List<Task> findProcessingTask(int employeeId, int category, int productId) {
        return processingTask(employeeId, category, productId).list();
    }

    public long selectCountProcessingTask(int employeeId, int category, int productId) {
        return processingTask(employeeId, category, productId).count();
    }

    private TaskQuery processingTask(int employeeId, int category, int productId) {
        return taskService.createTaskQuery()
                .taskAssignee(Convert.toStr(employeeId))
                .taskCategory(Convert.toStr(category))
                .processVariableValueEquals(PROCESS_VARIABLE_PRODUCT_ID, productId);
    }

    /**
     * 我已经完成的任务
     *
     * @param employeeId
     * @param category
     * @return
     */
    public List<HistoricTaskInstance> findFinishTaskByCategory(int employeeId, int category, int productId) {
        return finishTaskByCategory(employeeId, category, productId).list();
    }

    private HistoricTaskInstanceQuery finishTaskByCategory(int employeeId, int category, int productId) {
        return finishTask(employeeId, productId)
                .taskCategory(Convert.toStr(category)).orderByHistoricTaskInstanceStartTime().asc();
    }

    public long selectCountFinishTaskByCategory(int employeeId, int category, int productId) {
        return finishTaskByCategory(employeeId, category, productId).count();
    }


    /**
     * 不包含分类,用在全局扫码
     *
     * @param employeeId
     * @param productId
     * @return
     */
    private HistoricTaskInstanceQuery finishTask(int employeeId, int productId) {
        return historyService.createHistoricTaskInstanceQuery().finished()
                .processVariableValueEquals(PROCESS_VARIABLE_PRODUCT_ID, productId)
                .taskAssignee(Convert.toStr(employeeId));
    }

    public long selectCountFinishTaskByEmployeeId(int employeeId, int productId) {
        return finishTask(employeeId, productId).count();
    }

    public List<HistoricTaskInstance> selectFinishTaskByEmployeeId(int employeeId, int productId) {
        return finishTask(employeeId, productId).orderByHistoricTaskInstanceEndTime().asc().list();
    }


    /**
     * 找出指定工厂拥有的流程图模板
     *
     * @param tenantId
     * @return
     */
    public List<ProcessDefinition> findDefByTenantId(int tenantId) {
        return repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionCategory(FACTORY_WORKFLOW_TEMPLATE)
                .processDefinitionTenantId(Convert.toStr(tenantId)).list();
    }

    public void claimTask(int userId, String taskId) {
        taskService.claim(taskId, Convert.toStr(userId));
    }


    public void completeTask(String taskId) {
        ValidateUtil.isFalse(existTask(taskId), "当前任务已完成");
        //最后完成用户
        taskService.complete(taskId);
    }

    /**
     * 是否存在指定任务
     *
     * @param taskId
     * @return
     */
    public boolean existTask(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult() != null;
    }

    /**
     * 获取历史任务实例
     *
     * @param processInstanceId 流程实例id
     * @return
     */
    public List<HistoricTaskInstance> findHisTaskInsByExecutionId(String processInstanceId) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .taskCategory(String.valueOf(2))
                .processInstanceId(processInstanceId)
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .list();
    }

    public List<HistoricTaskInstance> findHisTaskInsByExecutionIdList(String executionId) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .executionId(executionId)
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .list();
    }

    public List<HistoricTaskInstance> findHisTaskInsByExecutionIdListAsc(String executionId) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .executionId(executionId)
                .orderByHistoricTaskInstanceStartTime()
                .asc()
                .list();
    }

    public List<HistoricTaskInstance> findHisTaskInsByProInstanceAndCategory(String proInstanceId) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .taskCategory(String.valueOf(2))
                .processInstanceId(proInstanceId)
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .list();
    }

    public List<HistoricTaskInstance> findHisTaskInsByProInstance(String proInstanceId) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId(proInstanceId)
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .list();
    }

    /**
     * 获取历史任务实例
     *
     * @param executionId 运行实例id
     * @return
     */
    public List<HistoricTaskInstance> findHiTaskInsByExecutionId(String executionId) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .executionId(executionId)
                .orderByHistoricTaskInstanceStartTime()
                .asc().list();
    }

    /**
     * 获得task实例
     *
     * @param taskId 任务id
     * @return
     */
    public Task findTaskInstanceById(String taskId) {
        return taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
    }

    /**
     * 获取task实例
     *
     * @param executionId 运行id
     * @return
     */
    public Task findTaskInstanceByExecutionId(String executionId) {
        return taskService
                .createTaskQuery()
                .executionId(executionId)
                .singleResult();
    }

    /**
     * 节点跳转
     *
     * @param currentTaskId    当前运行的任务id
     * @param targetTaskDefKey 需要跳转的历史任务节点key （act_hi_taskinst.TASK_DEF_KEY_）
     * @return
     */
    public boolean jumpNode(String currentTaskId, String targetTaskDefKey) {
//        ManagementService managementService = processEngine.getManagementService();
//        managementService.executeCommand(new JumpTaskCommand(currentTaskId, targetTaskDefKey));
        return true;
    }

    /**
     * @param TaskDefKey
     * @return
     */
    public Task findTaskByTaskDefKey(String TaskDefKey, String proInstanceId) {
        return taskService
                .createTaskQuery()
                .taskDefinitionKey(TaskDefKey)
                .processInstanceId(proInstanceId)
                .singleResult();
    }

    public HistoricTaskInstance findHisTaskByTaskDefKey(String TaskDefKey, String proInstanceId) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .taskDefinitionKey(TaskDefKey)
                .processInstanceId(proInstanceId)
                .orderByHistoricTaskInstanceStartTime()
                .asc()
                .list()
                .get(0);
    }

    public String findDefIdByInstanceId(String InstanceId) {
        return runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(InstanceId)
                .singleResult()
                .getProcessDefinitionId();
    }

    /**
     * @Description 根据流程实例查询到流程定义实例
     * @Date 11:32 2019/3/20
     **/
    public ProcessDefinition findDefByInstanceId(String instanceId) {
        String processDefinitionId = findDefIdByInstanceId(instanceId);
        return repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId)
                .singleResult();
    }

    public Long countProInstanceByVar(Integer projectId) {
        return taskService
                .createTaskQuery()
                .processVariableValueEquals(PROCESS_VARIABLE_PROJECT_ID, projectId)
                .count();
    }

    public ProcessDefinition findDefById(String id) {
        return repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionId(id)
                .singleResult();
    }

    public List<ProcessInstance> findProInstanceByProcessVariable(String variableName, int value) {
        return runtimeService
                .createProcessInstanceQuery()
                .variableValueEquals(variableName, value)
                .list();
    }

    public ProcessDefinition selectTemplateByKey(String key) {
        return repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionKey(key)
                .processDefinitionCategory(FACTORY_WORKFLOW_TEMPLATE)
                .singleResult();
    }

    public boolean isFinished(String processInstanceId) {
        return historyService.createHistoricProcessInstanceQuery().finished().processInstanceId(processInstanceId).count() > 0;
    }

    /**
     * 获取高亮图
     *
     * @param processId
     * @return
     */
    public String getCurrentFlowItemPng(String processId) {
        /**
         * 获得当前活动的节点
         */
        String processDefinitionId = "";
        if (this.isFinished(processId)) {// 如果流程已经结束，则得到结束节点
            HistoricProcessInstance pi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();

            processDefinitionId = pi.getProcessDefinitionId();
        } else {// 如果流程没有结束，则取当前活动节点
            // 根据流程实例ID获得当前处于活动状态的ActivityId合集
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
            processDefinitionId = pi.getProcessDefinitionId();
        }
        List<String> highLightedActivitis = new ArrayList<String>();

        /**
         * 获得活动的节点
         */
        List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processId).orderByHistoricActivityInstanceStartTime().asc().list();

        for (HistoricActivityInstance tempActivity : highLightedActivitList) {
            String activityId = tempActivity.getActivityId();
            highLightedActivitis.add(activityId);
        }

        List<String> flows = new ArrayList<>();
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        ProcessDiagramGenerator pdg = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();
        //生成流图片
        InputStream inputStream = pdg.generateDiagram(bpmnModel, "PNG", highLightedActivitis, flows, 1.5, true);
        return cn.hutool.core.codec.Base64.encode(inputStream);
    }

    public List<Task> findTaskByProjectId(int projectId) {
        return taskService.createTaskQuery().includeIdentityLinks().processVariableValueEquals(PROCESS_VARIABLE_PROJECT_ID, projectId).list();
    }

    public List<HistoricTaskInstance> findHisTaskInsByExecutionIdAndName(String executionId, String name) {
        return historyService.createHistoricTaskInstanceQuery().processUnfinished().executionId(executionId).taskName(name).list();
    }

    public List<HistoricTaskInstance> findHisTaskInsByProjectIdAndName(Integer projectId, String name, String proInstanceId) {
        return historyService.createHistoricTaskInstanceQuery().processInstanceId(proInstanceId).processUnfinished().processVariableValueEquals(PROCESS_VARIABLE_PROJECT_ID, projectId).taskName(name).list();
    }

    public void turnDown(String proInstanceId, List<String> currTaskIds, String targetKey) {
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(proInstanceId)
                .moveActivityIdsToSingleActivityId(currTaskIds, targetKey)
                .changeState();
    }

    public void moveSingleActivityIdToActivityIds(String proInstanceId, String currTaskKey, List<String> targetKeys) {
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(proInstanceId)
                .moveSingleActivityIdToActivityIds(currTaskKey, targetKeys)
                .changeState();
    }

    public List<Task> findTaskByInstanceId(String proInstanceId) {
        return taskService.createTaskQuery().processInstanceId(proInstanceId).list();
    }

    public void setReworkNameSet(String proInstanceId, Collection<?> collect) {
        //设置流程变量返工集
        runtimeService.setVariable(proInstanceId, REWORK_NAME_SET, collect);
    }

    public Object getVariable(String proInstanceId, String variableName) {
        return runtimeService.getVariable(proInstanceId, variableName);
    }

    public Object getReworkNameSet(String variableName, String processInstanceId) {
        //根据变量名获取到全部的id
        Object variable = runtimeService.getVariable(processInstanceId, variableName);
        return variable;
    }

    public Object getHisReWorkNameSet(String processInstanceId) {
        Object variable = null;
        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
        for (int i = 0; i < list.size(); i++) {
            HistoricVariableInstance historicVariableInstance = list.get(i);
            if (historicVariableInstance.getVariableName().equals(REWORK_NAME_SET)) {
                variable = historicVariableInstance.getValue();
            }
        }

        return variable;
    }

    public void setProDefCategory(String newDefId, String factoryWorkflowTemplate) {
        repositoryService.setProcessDefinitionCategory(newDefId, factoryWorkflowTemplate);
    }

    public Task findTaskByName(String name, String processInstanceId) {
        return taskService
                .createTaskQuery()
                .taskName(name)
                .processInstanceId(processInstanceId).singleResult();
    }

    public HistoricTaskInstance findHisTaskByName(String name, String processInstanceId) {
        return historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .taskName(name)
                .orderByHistoricTaskInstanceStartTime()
                .asc()
                .list().get(0);
    }

    public List<Task> findTaskByProductId(Integer id) {
        return taskService.createTaskQuery().includeIdentityLinks().processVariableValueEquals(PROCESS_VARIABLE_PRODUCT_ID, id).list();
    }
}