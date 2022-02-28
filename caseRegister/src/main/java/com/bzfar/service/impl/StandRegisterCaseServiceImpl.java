package com.bzfar.service.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bzfar.dto.other.Parent;
import com.bzfar.dto.stand.CaseFileList;
import com.bzfar.dto.stand.CaseLitigantList;
import com.bzfar.dto.stand.RegisterCaseInfoDto;
import com.bzfar.enums.CaseRegisterEnum;
import com.bzfar.exception.DataException;
import com.bzfar.service.RegisterCaseService;
import com.bzfar.vo.RegisterCaseVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @author Fimipeler
 */
@Service
@Slf4j
public class StandRegisterCaseServiceImpl implements RegisterCaseService {

    @Autowired
    private ObjectMapper objectMapper;

    private String tokenUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/uaa/open/login";

    private String tokenClientId = "jyytj";

    private String tokenClientSecret = "jyfyytj923";

    private String ayUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/jyytj/lawsuit/api/api/v1/third/baseCode/getTsAyByLb";

    private String bzdmUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/jyytj/lawsuit/api/api/v1/third/baseCode/getTsBzdmByKind";

    private String laUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/jyytj/lawsuit/api/api/v1/third/lawCase/saveLawCase";

    private String imgUrl = "https://sfpt.cdfy12368.gov.cn:806/gateway/jyytj/lawsuit/api/api/v1/third/file/fileUpload";

    private String aesKey = "TDHLAWSUITCASE@!";

    private String fydm = "511427";

    @Override
    public <T extends Parent> RegisterCaseVo caseRegister(CaseRegisterEnum type, T t) {
        RegisterCaseInfoDto registerCaseInfoDto = new RegisterCaseInfoDto();
        try {
            String param = objectMapper.writeValueAsString(t);
            log.info("【前端传入信息】 = {}",param);
            registerCaseInfoDto = objectMapper.readValue(param, RegisterCaseInfoDto.class);
        } catch (JsonProcessingException e) {
            throw new DataException("参数信息JSON解析错误");
        }
        List<Object> litigantList = new ArrayList<Object>();
        // 案件状态
        registerCaseInfoDto.getLawCase().setCaseStatus(110);
        // 角色只有案件当事人AJDSR
        registerCaseInfoDto.getApplicant().setApplicantAuthCode("AJDSR");
        // 证件类型只有身份证
        registerCaseInfoDto.getApplicant().setIdentType("15_000008-1");
        // 来源渠道固定jyytj
        registerCaseInfoDto.getLawCase().setCaseSource("JYYTJ");
        CaseLitigantList lists = registerCaseInfoDto.getCaseLitigantLists();
        if (null != lists) {
            if (null != lists.getZrrInfoDtos() && lists.getZrrInfoDtos().size() > 0) {
                litigantList.addAll(lists.getZrrInfoDtos());
            }
            if (null != lists.getFrOrFfrzzInfoDtos() && lists.getFrOrFfrzzInfoDtos().size() > 0) {
                litigantList.addAll(lists.getFrOrFfrzzInfoDtos());
            }
            registerCaseInfoDto.setCaseLitigantList(litigantList);
        }
        // 上传材料
        sendFileToTdh(registerCaseInfoDto);
        // JSON处理
        JSONObject jsonObject = cancleJson(registerCaseInfoDto);
        String data = jsonObject.toJSONString();
        log.info("【立案原生JSON】 = {}",data);
        AES aes = SecureUtil.aes(aesKey.getBytes());
        String base64 = aes.encryptBase64(data);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getToken());
        headers.set("clientId",tokenClientId);
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("courtCode", fydm);
        map.put("data", base64);
        String params = "";
        try {
            params = objectMapper.writeValueAsString(map);
            log.info("【立案提交参数】 = {}",params);
        } catch (JsonProcessingException e) {
            log.error("【la change json has an error】 = {}", e.getMessage());
        }
        String body = tempJsonPost(laUrl, params, headers);
        log.info("【立案提交接口返回信息】 = {}", body);
        JSONObject res = JSON.parseObject(body);
        Integer code = res.getInteger("code");
        if (200 != code) {
            String message = res.getString("message");
            throw new DataException(message);
        }
        String caseId = (String) res.get("data");
        return RegisterCaseVo.builder().code(200).msg(caseId).build();
    }

    /**
     * 获取token信息
     *
     * @return token
     */
    public String getToken() {
        HttpHeaders headers = new HttpHeaders();
        Map<String, Object> map1 = new HashMap<String, Object>(2);
        map1.put("clientId", tokenClientId);
        map1.put("clientSecret", tokenClientSecret);
        String params = "";
        try {
            params = objectMapper.writeValueAsString(map1);
        } catch (JsonProcessingException e) {
            log.error("【get token change json has an error】 = {}", e.getMessage());
        }
        String body = tempJsonPost(tokenUrl, params, headers);
        JSONObject res = JSON.parseObject(body);
        String token = (String) res.get("data");
        log.info("【通达海获取token】 = {}", token);
        return token;
    }

    private JSONObject cancleJson(RegisterCaseInfoDto registerCaseInfoDto){
        String datas = JSONObject.toJSONString(registerCaseInfoDto);
        JSONObject jsonObject = JSONObject.parseObject(datas);
        jsonObject.remove("caseLitigantLists");
        // 处理当事人及代理人信息
        JSONArray caseLitigantLists = jsonObject.getJSONArray("caseLitigantList");
        Iterator<Object> iterator = caseLitigantLists.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            JSONObject jsonObject1 = JSONObject.parseObject(next.toString());
            // 自然人姓名
            String participantName = jsonObject1.getString("participantName");
            if (StringUtils.isBlank(participantName)) {
                // 组织名称
                String orgName = jsonObject1.getString("orgName");
                if (StringUtils.isBlank(orgName)) {
                    // 两个名称都没有直接移除当前数据
                    iterator.remove();
                    continue;
                }
            }
            // 代理人信息处理
            JSONArray agentList = jsonObject1.getJSONArray("agentList");
            Iterator<Object> iterator1 = agentList.iterator();
            while (iterator1.hasNext()){
                Object next1 = iterator1.next();
                JSONObject agentInfo = JSONObject.parseObject(next1.toString());
                // 代理人姓名
                String agentName = agentInfo.getString("participantName");
                if (StringUtils.isBlank(agentName)) {
                    iterator1.remove();
                }
            }
            if (agentList.size() == 0) {
                ((JSONObject)next).remove("agentList");
            }
        }
        return jsonObject;
    }

    /**
     * 材料上传至通达海
     */
    private String sendFileToTdh(RegisterCaseInfoDto registerCaseInfoDto) {
        List<CaseFileList> caseFileList = registerCaseInfoDto.getCaseFileList();
        if (null == caseFileList || caseFileList.size() <= 0) {
            return "";
        }
        caseFileList.stream().forEach(item -> {
            try {
                String o = sendFile(item.getFilePath(), item.getFileName());
                item.setFilePath(o);
            } catch (IOException e) {
                log.error("【材料上传通达海错误】 = {}", e.getMessage());
            }
        });

        return "";
    }

    /**
     * 模板post  json请求
     *
     * @param url   路径
     * @param param 参数
     * @return 返回结果
     */
    private String tempJsonPost(String url, String param, HttpHeaders headers) {
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        RestTemplate restTemplate = new RestTemplate();
        //构造实体对象
        HttpEntity<String> entity = new HttpEntity<String>(param, headers);
        //发起请求,服务地址，请求参数，返回消息体的数据类型
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        //body
        String body = response.getBody();
        return body;
    }

    /**
     * 上传文件
     *
     * @param paths
     * @return
     */
    private String sendFile(String paths, String name) throws IOException {

        URL url = new URL(paths);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(imgUrl);
        uploadFile.setHeader("Authorization", "Bearer " + getToken());

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();

        // 把文件加到HTTP的post请求中
        builder.addBinaryBody(
                "files",
                inputStream,
                ContentType.APPLICATION_OCTET_STREAM,
                name
        );

        org.apache.http.HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = httpClient.execute(uploadFile);
        org.apache.http.HttpEntity responseEntity = response.getEntity();
        String sResponse = EntityUtils.toString(responseEntity, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(sResponse);
        JSONArray data = jsonObject.getJSONArray("data");
        String string = data.getJSONObject(0).getString("filePath");
        return string;
    }
}
