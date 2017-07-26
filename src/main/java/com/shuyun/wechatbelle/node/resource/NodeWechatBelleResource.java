package com.shuyun.wechatbelle.node.resource;

import com.shuyun.wechatbelle.node.vo.NodeWechatBelleOpen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by shuyun on 2017/7/26.
 */

@Component
@Path("/node/wechatbelle")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NodeWechatBelleResource {
    private static Logger logger = LoggerFactory.getLogger(NodeWechatBelleResource.class);
//    @Autowired
//    private MsWorkflowNodeSdkService workflowNodeSdkService;
//    @Autowired
//    private NodeCouponBelleSDKService nodeCouponBelleSDKService;

    @GET
    @Path("/{id}")
    public NodeWechatBelleOpen open(@PathParam("id") final long id) throws InvocationTargetException, IllegalAccessException{
        logger.info("Open Coupon Node id：{}", id);

        NodeWechatBelleOpen objRtn = new NodeWechatBelleOpen();
//        NodeCouponBelle nodeCoupon = nodeCouponBelleSDKService.findById(id);
//        if(null == nodeCoupon || null == nodeCoupon.getId()){
//            Node node = workflowNodeSdkService.findById(id);
//            if (null == node || null == node.getId()) {
//                logger.error("Workflow node is null!");
//                throw new IllegalArgumentException("节点信息为空，打开节点失败！");
//            }
//
//            objRtn.setId(node.getId());
//
//            objRtn.setName(node.getName());
//            objRtn.setUseable(true);
//            objRtn.setOutputType(OutputType.SEND_SUCCESS.getValue());
//            return objRtn;
//        }

        objRtn.setId(id);
//        objRtn.setName(nodeCoupon.getName());
//        objRtn.setOutputType(nodeCoupon.getOutputType());
//        objRtn.setRemark(nodeCoupon.getRemark());
//        objRtn.setBrandNames(nodeCoupon.getBrandNames());
//        objRtn.setRuleName(nodeCoupon.getRuleName());
//        objRtn.setCouponRuleCode(nodeCoupon.getCouponRuleCode());
//        objRtn.seteUsedDate(nodeCoupon.geteUsedDate());
//        objRtn.setsUsedDate(nodeCoupon.getsUsedDate());
//        objRtn.setPublishOrganName(nodeCoupon.getPublishOrganName());
//        objRtn.setReleaseNum(nodeCoupon.getReleaseNum());
//        objRtn.setDiscType(nodeCoupon.getDiscType());
//        objRtn.setFaceMoney(nodeCoupon.getFaceMoney());
//        objRtn.setSettlementPrice(nodeCoupon.getSettlementPrice());
//        objRtn.setInstructions(nodeCoupon.getInstructions());
//        objRtn.setNodeRemark(nodeCoupon.getNodeRemark());
//        objRtn.setBrandCode(nodeCoupon.getBrandCode());

        return objRtn;
    }

}
