package com.bzfar.service.impl;

import com.bzfar.config.MailConfiguration;
import com.bzfar.dto.AnnexDto;
import com.bzfar.dto.EmailDTO;
import com.bzfar.exception.DataException;
import com.bzfar.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Autowired
    private MailConfiguration mailConfiguration;

    private String PATH;

    @PostConstruct
    public void init(){
        PATH = System.getProperty("user.dir") + "\\";
    }

    @Override
    public void sendEmail(EmailDTO dto , List<AnnexDto> annexList) {
        List<File> list = new ArrayList<>();
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(mailConfiguration.getUserName());
            helper.setTo(dto.getTo());
            helper.setSubject(dto.getSubject());
            helper.setText(dto.getText());
            for (AnnexDto annex : annexList){
                String name = annex.getName();
                String type = name.substring(name.indexOf('.'));
                String newName =  UUID.randomUUID().toString().replaceAll("-", "") + type;
                /** 解决名称乱码问题 */
                String newFileName =  MimeUtility.encodeWord(name,"utf-8","B");
                log.info("【路径】NAME = {}" , newName);
                File attachment = new File(PATH + newName);
                list.add(attachment);
                try {
                    OutputStream outStream = new FileOutputStream(attachment);
                    byte[] buffer = new byte[annex.getIs().available()];
                    annex.getIs().read(buffer);
                    outStream.write(buffer);
                    helper.addAttachment(newFileName,attachment);
                } catch (Exception e) {
                    throw new DataException(e.getMessage());
                }
            }
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }finally {
            list.forEach(item -> {
                if(item.exists()){
                    item.delete();
                }
            });
        }
    }

    @Override
    public void semdEmail(EmailDTO dto) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(mailConfiguration.getUserName());
            helper.setTo(dto.getTo());
            helper.setSubject(dto.getSubject());
            helper.setText(dto.getText());
            javaMailSender.send(mimeMessage);
        }catch (Exception e){
            throw new DataException(e.getMessage());
        }
    }
}
