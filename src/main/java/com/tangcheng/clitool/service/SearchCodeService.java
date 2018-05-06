package com.tangcheng.clitool.service;

import com.beust.jcommander.JCommander;
import com.tangcheng.clitool.command.Args;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

import static com.tangcheng.clitool.CliToolApplication.COMMAND_ARGS;

@Slf4j
@Component
public class SearchCodeService implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(String... args) {
        search();
    }

    private void search() {
        /**
         * str:mobile:{mobile_number}.code
         */
        final String MOBILE_VERIFY_CODE = "str:mobile:{0}.code";
        try {
            Args commandArgs = new Args();
            JCommander.newBuilder()
                    .addObject(commandArgs)
                    .build()
                    .parse(COMMAND_ARGS);
            List<String> mobiles = commandArgs.getMobiles();
            log.info("{}", mobiles);
            for (String mobile : mobiles) {
                String key = MessageFormat.format(MOBILE_VERIFY_CODE, mobile);
                BoundValueOperations<String, String> mobileKey = stringRedisTemplate.boundValueOps(key);
                String verifyCode = mobileKey.get();
                long expireMin = mobileKey.getExpire() / 1000 / 60;
                log.info("{}->{} ,{} min to expire", key, verifyCode, expireMin);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
