package com.bzfar.util;

import com.bzfar.dto.ComNodeSourceVo;
import com.bzfar.dto.ComNodeDto;
import com.bzfar.dto.ComPageDto;
import com.bzfar.dto.ComSourceVo;
import com.bzfar.vo.AllPageVo;
import com.bzfar.vo.SourceInfoVo;
import com.bzfar.vo.NodeInfoVo;
import com.bzfar.vo.PageInfoVo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 将资源处理成父子关系
 *
 * @author Fimipeler
 */
public class SourceUtils {

    /**
     * 处理成key-value
     */
    public static AllPageVo toKeyValue(ComPageDto pageDto){
        AssertUtil.assertNull(pageDto.getNodes(),"节点信息为空");
        AssertUtil.assertNull(pageDto.getNodeSource(),"节点资源关联为空");
        AssertUtil.assertNull(pageDto.getSource(),"资源信息为空");
        AssertUtil.assertNull(pageDto.getUrl(),"资源路径不存在");
        Map<String, PageInfoVo> pageInfo = new HashMap<>();
        List<ComNodeDto> parNode = pageDto.getNodes().stream().filter(item -> item.getParentId() == -1).collect(Collectors.toList());
        AssertUtil.assertNull(parNode,"页面数据为空");
        parNode.stream().forEach(node -> {
            List<ComNodeDto> chilNode = pageDto.getNodes().stream().filter(item -> item.getParentId().equals(node.getId())).collect(Collectors.toList());
            // 每个页面子节点按照sort排序
            Collections.sort(chilNode, new Comparator<ComNodeDto>() {
                @Override
                public int compare(ComNodeDto o1, ComNodeDto o2) {
                    //升序
                    return o2.getSort().compareTo(o1.getSort());
                }
            });
            boolean pageIsShow = node.getIsShow().equals("1");
            Map<String, NodeInfoVo> nodeInfo = getChildNodeId(pageIsShow,chilNode,pageDto);
            PageInfoVo pageInfoVo = PageInfoVo.builder()
                    .cnName(node.getCnName())
                    .isReturn(node.getIsReturn().equals("1"))
                    .isShow(pageIsShow)
                    .nodeInfo(nodeInfo).build();
            pageInfo.put(node.getEnName(),pageInfoVo);
        });
        return AllPageVo.builder().pageVos(pageInfo).build();
    }

    /**
     * 处理节点信息
     *
     * @param pageIsShow 页面节点是否展示
     * @param chilNode 页面子节点
     * @param pageDto 资源信息
     * @return 处理完成后的节点信息
     */
    private static Map<String, NodeInfoVo> getChildNodeId(Boolean pageIsShow, List<ComNodeDto> chilNode,ComPageDto pageDto){
        List<ComNodeSourceVo> nodeSource = pageDto.getNodeSource();
        List<ComSourceVo> sources = pageDto.getSource();
        String url = pageDto.getUrl();
        Map<String, NodeInfoVo> nodeInfo = new HashMap<>();
        chilNode.stream().forEach(childNode->{
            // 获取当前节点关联的资源ID
            List<String> sourceId = nodeSource.stream().
                    filter(item -> item.getNodeId().equals(childNode.getId())).
                    map(ComNodeSourceVo::getSourceId).collect(Collectors.toList());
            // 获取资源
            List<ComSourceVo> nodeSources = sources.stream().filter(item -> sourceId.contains(String.valueOf(item.getSourceId()))).collect(Collectors.toList());
            AssertUtil.assertNull(childNode.getIsShow() , "数据中是否展示不能为空");
            boolean nodeShows = childNode.getIsShow().equals("1");
            if (!pageIsShow) {
                nodeShows = false;
            }
            Map<String, SourceInfoVo> sourceInfoes = cancleSource(url, nodeSources, nodeShows);
            NodeInfoVo nodeInfoVo = NodeInfoVo.builder()
                    .cnName(childNode.getCnName())
                    .enName(childNode.getEnName())
                    .router(childNode.getRouter())
                    .isShow(nodeShows)
                    .sort(childNode.getSort())
                    .sourceInfo(sourceInfoes)
                    .build();
            nodeInfo.put(childNode.getEnName(),nodeInfoVo);
        });
        return nodeInfo;
    }

    /**
     * 处理节点对应的资源信息
     *
     * @param url 资源存储路径
     * @param nodeSources 节点资源
     * @param nodeShows 节点是否展示
     * @return 处理完成后的资源信息
     */
    private static Map<String, SourceInfoVo> cancleSource(String url, List<ComSourceVo> nodeSources, boolean nodeShows) {
        Map<String, SourceInfoVo> sourceInfoes = new HashMap<>();
        for (ComSourceVo sourceVo: nodeSources) {
            boolean sourceShow = sourceVo.getIsShow().equals("1");
            if (!nodeShows) {sourceShow = false;}
            String elType = sourceVo.getElType();
            SourceInfoVo sourceInfoVo = SourceInfoVo.builder()
                    .isShow(sourceShow)
                    .value(sourceVo.getValue())
                    .name(sourceVo.getValue())
                    .build();
            if (checkElType(elType)) {
                sourceInfoVo.setValue(url +"/"+sourceVo.getValue());
            }
            sourceInfoes.put(elType,sourceInfoVo);
        }
        return sourceInfoes;
    }

    /**
     * 过滤需要给全路径的资源
     *
     * @param elType 资源类型
     * @return 是否需要全路径
     */
    private static boolean checkElType(String elType){
        return  "back".equals(elType) ||
                "icon".equals(elType) ||
                "audio".equals(elType) ||
                "icon1".equals(elType) ||
                "video".equals(elType) ||
                "gif".equals(elType);
    }
}
